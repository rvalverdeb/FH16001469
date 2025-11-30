public class CustomStack {
// Fuente: "ChatGPT conversation with the student" (no se modificaron Nodes ni Main; solo lógica interna)

    private StackNode _head;

    public CustomStack() {
        _head = null;
    }

    public void push(String word) {
        StackNode node = new StackNode(word);
        node.setNext(_head);
        _head = node;
    }

    public String pop() {
        String word = null;

        if (_head != null) {
            word = _head.getWord();
            _head = _head.getNext();
        }

        return word;
    }

    public int size() {
        int length = 0;
        StackNode temp = _head;
        while (temp != null) {
            length++;
            temp = temp.getNext();
        }
        return length;
    }

    public String getWords() {
        var builder = new StringBuilder("(" + size() + ") [");
        for (var word = pop(); word != null; word = pop()) {
            builder.append(" " + word);
        }
        builder.append(" ]");
        return builder.toString();
    }
}