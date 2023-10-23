public class TreeHeap {

    TreeHeapNode root;

    TreeHeap() {
        this.root = null;
    }

    TreeHeap(TreeHeapNode node) {
        this.root = node;
    }

    public void enqueue(Path value) {
        if (root == null) {
            root = new TreeHeapNode(0, value);
        } else {
            root.add(value);
        }
    }

    public Path dequeue() {
        if (root == null) {
            return null;
        }
        if (root.left == null || root.right == null) {
            if (root.left == null) {
                TreeHeapNode temp = root;
                root = root.right;
                return temp.getValue();
            } else {
                TreeHeapNode temp = root;
                root = root.left;
                return temp.getValue();
            }
        } else {
            return root.remove().path;
        }
    }

}