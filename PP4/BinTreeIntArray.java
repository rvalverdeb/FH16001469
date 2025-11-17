import java.util.Arrays;
import java.util.Stack;

public class BinTreeIntArray {

    private Integer[] _tree;

    public BinTreeIntArray(int size) {
        _tree = new Integer[size];
    }

    public Integer[] getTree() {
        return _tree;
    }

    public void insert(int value) {

       int index = 0;

    while (index < _tree.length) {
        if (_tree[index] == null) {
            _tree[index] = value;
            break;
        }
        if (value < _tree[index]) {
            index = 2 * index + 1; // left
        } else {
            index = 2 * index + 2; // right
        }
    }

        System.out.println(" ↳ insert: " + value + " → " + Arrays.toString(_tree));
    }

    public void inOrderTraversal() { // left -> root -> right
        System.out.print(" ↳ inOrderTraversal → ");
        var stack = new Stack<Integer>();
        var index = 0;
        while (!stack.isEmpty() || (index < _tree.length && _tree[index] != null)) {
            while (index < _tree.length && _tree[index] != null) {
                stack.push(index);
                index = 2 * index + 1;
            }
            index = stack.pop();
            System.out.print(_tree[index] + " ");
            index = 2 * index + 2;
        }
        System.out.println();
    }

    public void preOrderTraversal() { // root -> left -> right
        System.out.print(" ↳ preOrderTraversal → ");

       Stack<Integer> stack = new Stack<>();

    if (_tree[0] != null) {
        stack.push(0);
    }

    while (!stack.isEmpty()) {
        int index = stack.pop();
        System.out.print(_tree[index] + " ");

        int right = 2 * index + 2;
        int left = 2 * index + 1;

        if (right < _tree.length && _tree[right] != null) {
            stack.push(right);
        }
        if (left < _tree.length && _tree[left] != null) {
            stack.push(left);
        }
    }


        System.out.println();
    }

    public void postOrderTraversal() { // left -> right -> root
        System.out.print(" ↳ postOrderTraversal → ");

        Stack<Integer> stack = new Stack<>();
    int index = 0;
    int lastVisited = -1;

    while (!stack.isEmpty() || (index < _tree.length && _tree[index] != null)) {

        // Bajar por la izquierda
        while (index < _tree.length && _tree[index] != null) {
            stack.push(index);
            index = 2 * index + 1;
        }

        int peek = stack.peek();
        int right = 2 * peek + 2;

        // Revisar si el hijo derecho existe y no ha sido procesado
        if (right < _tree.length && _tree[right] != null && lastVisited != right) {
            index = right;
        } else {
            System.out.print(_tree[peek] + " ");
            lastVisited = stack.pop();
        }
    }


        System.out.println();
    }

    public static void main(String[] args) {
        var tree = new BinTreeIntArray(7);
        System.out.println("Empty array → " + Arrays.toString(tree.getTree()));
        tree.insert(4);
        tree.insert(6);
        tree.insert(5);
        tree.insert(2);
        tree.insert(7);
        tree.insert(1);
        tree.insert(3);
        System.out.println("\nFull array → " + Arrays.toString(tree.getTree()));
        tree.inOrderTraversal(); // 1 2 3 4 5 6 7
        tree.preOrderTraversal(); // 4 2 1 3 6 5 7
        tree.postOrderTraversal(); // 1 3 2 5 7 6 4
    }
}
