package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * Controlador del estado del juego con:
 * - persistencia por id de Carta (save/load XML)
 * - validación de mano que permite robar si no hay sándwiches y hay mazo
 * - lógica de sándwiches / envío de tripletas
 */
public class Juego {

    private Caja caja;
    private Mazo mazo;
    private Jugador jugador;
    private Pozo pozo;

    // estado simple
    private boolean terminado = false;
    private boolean ganado = false;

    public Juego() {
        reiniciar();
    }

    /** Reinicia los componentes del juego */
    public void reiniciar() {
        caja = new Caja();
        caja.inicializar(); // crea 52 cartas maestras
        mazo = new Mazo();
        jugador = new Jugador();
        pozo = new Pozo();
        terminado = false;
        ganado = false;
    }

    /** Pasa las cartas de la caja al mazo y las mezcla */
    public void barajar() {
        caja.barajar(mazo);
    }

    /**
     * Reparte 'cantidad' cartas al jugador.
     * Retorna la cantidad realmente repartida.
     */
    public int repartirInicial(int cantidad) {
        if (cantidad <= 0)
            return 0;
        List<Carta> sacadas = mazo.draw(cantidad);
        if (sacadas == null || sacadas.isEmpty())
            return 0;
        jugador.agregarCartas(sacadas);
        checkWinCondition();
        return sacadas.size();
    }

    /**
     * Hace que el jugador juegue (remover actual) y la pone en el pozo.
     */
    public boolean jugadorJuega() {
        Carta c = jugador.jugarCarta();
        if (c != null) {
            pozo.colocar(c);
            checkWinCondition();
            return true;
        }
        return false;
    }

    /**
     * Extrae hasta 'n' cartas del mazo y las agrega a la mano del jugador.
     * Devuelve la cantidad realmente tomada.
     */
    public int tomarCartasAlJugador(int n) {
        if (n <= 0)
            return 0;
        int espacio = Math.max(0, 8 - jugador.manoSize());
        int toTake = Math.min(n, espacio);
        List<Carta> tomadas = mazo.draw(toTake);
        if (tomadas == null || tomadas.isEmpty())
            return 0;
        jugador.agregarCartas(tomadas);
        checkWinCondition();
        return tomadas.size();
    }

    // ================= PERSISTENCIA POR ID (save / load) =================

    /**
     * Guarda el estado completo usando la baraja maestra y referencias por id.
     */
    public void save(File file) throws Exception {
        Objects.requireNonNull(file, "file no puede ser null");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        Element root = doc.createElement("Juego");
        root.setAttribute("terminado", String.valueOf(terminado));
        root.setAttribute("ganado", String.valueOf(ganado));
        doc.appendChild(root);

        Element jugadorEl = doc.createElement("Jugador");
        jugadorEl.setAttribute("nombre", jugador.getNombre());
        root.appendChild(jugadorEl);

        // Cartas maestras (todas las 52 instancias con id)
        Element maestrasEl = doc.createElement("CartasMaestras");
        List<Carta> maestras = caja.getMaestra();
        for (Carta c : maestras) {
            Element cel = doc.createElement("Carta");
            cel.setAttribute("id", String.valueOf(c.getId()));
            cel.setAttribute("nombre", c.getNombre());
            cel.setAttribute("palo", c.getPalo());
            cel.setAttribute("valor", String.valueOf(c.getValor()));
            maestrasEl.appendChild(cel);
        }
        root.appendChild(maestrasEl);

        // Secciones: Caja, Mazo, Mano, Pozo -> escribir referencias por id
        root.appendChild(refSection(doc, "Caja", caja.getCartas()));
        root.appendChild(refSection(doc, "Mazo", mazo.toList())); // toList: top -> bottom
        root.appendChild(refSection(doc, "Mano", jugador.manoToList())); // actual -> ...
        root.appendChild(refSection(doc, "Pozo", pozo.toList())); // frente -> ...

        // volcar a archivo
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource src = new DOMSource(doc);
        StreamResult res = new StreamResult(file);
        transformer.transform(src, res);
    }

    private Element refSection(Document doc, String name, List<Carta> listado) {
        Element sec = doc.createElement(name);
        if (listado != null) {
            for (Carta c : listado) {
                Element ref = doc.createElement("Ref");
                ref.setAttribute("id", String.valueOf(c.getId()));
                sec.appendChild(ref);
            }
        }
        return sec;
    }

    /**
     * Carga el estado del juego desde un XML guardado por save(...).
     * Restaura la baraja maestra y distribuye las mismas instancias por referencia.
     */
    public void load(File file) throws Exception {
        Objects.requireNonNull(file, "file no puede ser null");
        if (!file.exists())
            throw new IllegalArgumentException("Archivo no encontrado: " + file.getAbsolutePath());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        Element root = doc.getDocumentElement();
        if (!"Juego".equals(root.getNodeName())) {
            throw new IllegalArgumentException("Archivo XML no parece ser un juego válido");
        }

        // atributos de estado
        this.terminado = Boolean.parseBoolean(root.getAttribute("terminado"));
        this.ganado = Boolean.parseBoolean(root.getAttribute("ganado"));

        // jugador nombre (si existe)
        String jugadorNombre = "Jugador 1";
        NodeList jnodes = root.getElementsByTagName("Jugador");
        if (jnodes.getLength() > 0) {
            Element jel = (Element) jnodes.item(0);
            String n = jel.getAttribute("nombre");
            if (n != null && !n.isBlank())
                jugadorNombre = n;
        }

        // leer cartas maestras y crear mapa id->Carta
        Map<Integer, Carta> idMap = new HashMap<>();
        NodeList maestrasNodes = root.getElementsByTagName("CartasMaestras");
        if (maestrasNodes.getLength() > 0) {
            Element maesEl = (Element) maestrasNodes.item(0);
            NodeList cartaNodes = maesEl.getElementsByTagName("Carta");
            for (int i = 0; i < cartaNodes.getLength(); i++) {
                Element cel = (Element) cartaNodes.item(i);
                int id = Integer.parseInt(cel.getAttribute("id"));
                String nombre = cel.getAttribute("nombre");
                String palo = cel.getAttribute("palo");
                int valor = Integer.parseInt(cel.getAttribute("valor"));
                Carta c = new Carta(id, nombre, palo, valor);
                idMap.put(id, c);
            }
        } else {
            throw new IllegalArgumentException("XML no contiene CartasMaestras");
        }

        // helper para leer seciones de refs
        List<Integer> idsCaja = readRefIds(root, "Caja");
        List<Integer> idsMazo = readRefIds(root, "Mazo");
        List<Integer> idsMano = readRefIds(root, "Mano");
        List<Integer> idsPozo = readRefIds(root, "Pozo");

        // reconstruir estructuras usando las mismas instancias de idMap
        List<Carta> maestrosOrdered = new ArrayList<>();
        // ordenar por id para reconstruir la maestra en orden consistente (0..51)
        for (int i = 0; i < idMap.size(); i++) {
            if (idMap.containsKey(i))
                maestrosOrdered.add(idMap.get(i));
        }

        // reconstruir Caja (maestra + contenido actual)
        List<Carta> contenidoCaja = new ArrayList<>();
        for (Integer id : idsCaja) {
            Carta c = idMap.get(id);
            if (c != null)
                contenidoCaja.add(c);
        }
        Caja nuevaCaja = new Caja();
        nuevaCaja.setMaestra(maestrosOrdered, contenidoCaja);

        // reconstruir Mazo (lista de cartas en orden top->bottom)
        Mazo nuevoMazo = new Mazo();
        List<Carta> mazoList = new ArrayList<>();
        for (Integer id : idsMazo) {
            Carta c = idMap.get(id);
            if (c != null)
                mazoList.add(c);
        }
        nuevoMazo.cargar(mazoList); // cargar mantiene el orden

        // reconstruir Mano
        Jugador nuevoJugador = new Jugador(jugadorNombre);
        List<Carta> manoList = new ArrayList<>();
        for (Integer id : idsMano) {
            Carta c = idMap.get(id);
            if (c != null)
                manoList.add(c);
        }
        if (!manoList.isEmpty())
            nuevoJugador.agregarCartas(manoList);

        // reconstruir Pozo
        Pozo nuevoPozo = new Pozo();
        List<Carta> pozoList = new ArrayList<>();
        for (Integer id : idsPozo) {
            Carta c = idMap.get(id);
            if (c != null)
                pozoList.add(c);
        }
        if (!pozoList.isEmpty())
            nuevoPozo.colocarTodas(pozoList);

        // asignar al juego
        this.caja = nuevaCaja;
        this.mazo = nuevoMazo;
        this.jugador = nuevoJugador;
        this.pozo = nuevoPozo;
    }

    private List<Integer> readRefIds(Element root, String sectionName) {
        List<Integer> out = new ArrayList<>();
        NodeList nodes = root.getElementsByTagName(sectionName);
        if (nodes.getLength() == 0)
            return out;
        Element section = (Element) nodes.item(0);
        NodeList refs = section.getElementsByTagName("Ref");
        for (int i = 0; i < refs.getLength(); i++) {
            Element rel = (Element) refs.item(i);
            try {
                out.add(Integer.parseInt(rel.getAttribute("id")));
            } catch (NumberFormatException ex) {
                // ignorar
            }
        }
        return out;
    }

    // ================= VALIDACIÓN DE MANO =================

    /**
     * Resultado de validar la mano.
     * - HAS_SANDWICH: la mano tiene al menos un sándwich válido.
     * - NO_SANDWICH_MAZO_NOT_EMPTY: no hay sándwich, pero hay cartas en el mazo (se
     * puede intentar robar).
     * - NO_SANDWICH_MAZO_EMPTY: no hay sándwich y el mazo está vacío (pérdida).
     */
    public enum ValidationResult {
        HAS_SANDWICH,
        NO_SANDWICH_MAZO_NOT_EMPTY,
        NO_SANDWICH_MAZO_EMPTY
    }

    /**
     * Valida la mano y retorna el estado según si hay sándwiches o no y si el mazo
     * tiene cartas.
     * NO marca pérdida en el caso mazo != vacío (deja que la GUI decida si robar).
     */
    public ValidationResult validarMano() {
        List<TripletResult> validas = encontrarTripletasValidas();
        if (!validas.isEmpty()) {
            return ValidationResult.HAS_SANDWICH;
        }
        // no hay tripletas
        if (mazo != null && !mazo.isEmpty()) {
            return ValidationResult.NO_SANDWICH_MAZO_NOT_EMPTY;
        }
        // no hay tripletas y mazo vacío -> pérdida
        terminado = true;
        ganado = false;
        return ValidationResult.NO_SANDWICH_MAZO_EMPTY;
    }

    // ======== LÓGICA DEL JUEGO (resumen / compatibles) ========

    /**
     * Busca en la mano todas las tripletas (combinaciones i<j<k) que resulten en
     * al menos una permutación válida. Retorna una lista de TripletResult.
     */
    public List<TripletResult> encontrarTripletasValidas() {
        List<TripletResult> res = new ArrayList<>();
        List<Carta> manoList = jugador.manoToList();
        int n = manoList.size();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    List<Carta> trip = List.of(manoList.get(i), manoList.get(j), manoList.get(k));
                    List<PermutationResult> perms = SandwichEvaluator.evaluateAllPermutations(trip);
                    int best = 0;
                    List<Carta> bestPerm = null;
                    for (PermutationResult pr : perms) {
                        if (pr.getTakeCount() > best) {
                            best = pr.getTakeCount();
                            bestPerm = pr.getPermutation();
                        }
                    }
                    if (best > 0) {
                        res.add(new TripletResult(i, j, k, trip, bestPerm, best));
                    }
                }
            }
        }
        return res;
    }

    /**
     * Envía la tripleta indicada por índices (i<j<k) al pozo, y toma la cantidad
     * máxima de cartas determinada por la mejor permutación (respetando máximo 8
     * cartas en mano).
     * Retorna true si la tripleta era válida y fue enviada; false si no era válida.
     */
    public boolean enviarTripletaAlPozo(int i, int j, int k) {
        List<Carta> manoList = jugador.manoToList();
        int n = manoList.size();
        if (!(0 <= i && i < n && 0 <= j && j < n && 0 <= k && k < n))
            return false;
        if (!(i < j && j < k))
            return false;

        List<Carta> trip = List.of(manoList.get(i), manoList.get(j), manoList.get(k));
        List<PermutationResult> perms = SandwichEvaluator.evaluateAllPermutations(trip);
        int best = 0;
        for (PermutationResult pr : perms) {
            if (pr.getTakeCount() > best)
                best = pr.getTakeCount();
        }
        if (best == 0)
            return false;

        // remover por índices (mayor -> menor)
        jugador.jugarCartaEn(k);
        jugador.jugarCartaEn(j);
        jugador.jugarCartaEn(i);

        // agregar al pozo las cartas descartadas (usamos las instancias originales de
        // 'trip')
        pozo.colocar(trip.get(0));
        pozo.colocar(trip.get(1));
        pozo.colocar(trip.get(2));

        int espacio = Math.max(0, 8 - jugador.manoSize());
        int toTake = Math.min(best, espacio);
        if (toTake > 0) {
            List<Carta> tomadas = mazo.draw(toTake);
            if (tomadas != null && !tomadas.isEmpty()) {
                jugador.agregarCartas(tomadas);
            }
        }

        checkWinCondition();
        return true;
    }

    /**
     * Comprueba condiciones de victoria (mazo vacío).
     */
    private void checkWinCondition() {
        if (mazo == null || mazo.isEmpty()) {
            terminado = true;
            ganado = true;
        }
    }

    // ======== ESTADO DEL JUEGO ========

    public boolean isTerminado() {
        return terminado;
    }

    public boolean isGanado() {
        return ganado;
    }

    // ======== GETTERS ========

    public Caja getCaja() {
        return caja;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Pozo getPozo() {
        return pozo;
    }
}