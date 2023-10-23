public class TreeHeapNode {

    public Integer subTreeSize;
    public Path path;

    TreeHeapNode left, right;

    public TreeHeapNode(Integer subTreeSize, Path path) {
        this.subTreeSize = subTreeSize;
        this.path = path;
        this.subTreeSize = 1;
    }

    public void add(Path value) {
        TreeHeapNode currentNode = this;
        subTreeSize += 1;
        if (currentNode.getValue().getDist() > value.getDist()) {
            Path temp = value;
            value = currentNode.getValue();
            currentNode.setValue(temp);
        }
        if (currentNode.right == null) {
            currentNode.right = new TreeHeapNode(subTreeSize, value);
        } else {
            if (currentNode.left == null) {
                currentNode.left = new TreeHeapNode(subTreeSize, value);
            } else {
                if (currentNode.right.subTreeSize < currentNode.left.subTreeSize) {
                    TreeHeapNode tmp = left;
                    right.add(value);
                    left = right;
                    right = tmp;
                } else {
                    TreeHeapNode tmp = right;
                    left.add(value);
                    right = left;
                    left = tmp;
                }
            }
        }
    }

    public TreeHeapNode remove() {
        subTreeSize -= 1;
        if (this.right == null) {
            return left;
        } else {
            if (left == null) {
                return right;
            } else {
                if (left.getValue().getDist() < right.getValue().getDist()) {
                    path = left.path;
                    left = left.remove();

                } else {
                    path = right.path;
                    right = right.remove();
                }
                return this;
            }
        }
    }

    
    public Integer getSubTreeSize() {
        return this.subTreeSize;
    }

    public Path getValue() {
        return this.path;
    }

    public void setValue(Path path) {
        this.path = path;
    }

    public void setSubTreeSize(Integer subTreeSize) {
        this.subTreeSize = subTreeSize;
    }

}
