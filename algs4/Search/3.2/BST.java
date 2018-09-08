public class BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;
        public Node (Key key, Value val, int N) {
            this.key = key;
            this.value = val;
            this.N = N;
        }

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) return 0;
            else return node.N;
        }

        public Value get(Key key) {
            return get(root, key);
        }

        private Value get(Node node, Key key) {
            if (node == null) return null;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) return get(node.left, key);
            else if (cmp > 0) return get(node.right, key);
            else return node.val;
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
        }

        private Node put(Node node, Key key, Value val) {
            if (node == null) return new Node(key, val, 1);
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node.left = put(node.left, key, val);
            else if (cmp > 0) node.right = put(node.right, key, val);
            else node.val = val;

            node.N = size(node.left) + size(node.right) + 1;
            return node;
        }

        public Key min() {
            return min(root).key;
        }

        private Key min(Node node) {
            if (node.left == null) return node.key;
            return min(node.left);
        }

        public Key floor(Key key) {
            Node node = floor(root, key);
            if (node == null) return null;
            return node.key;
        }

        public Node floor(Node node, Key key) {
            if (node == null) return null;
            int cmp = key.compareTo(node.key);
            if (cmp == 0) return node;
            else if (cmp < 0) return floor(node.left, key);
            Node t = floor(node.right, key);
            if (t != null) return t;
            else return node;
        }

        public Key select(int k) {
            return select(root, k).key;
        }

        private Node select(Node node, int k) {
            if (node == null) return null;
            int t = size(x.left);
            if (t > k) return select(node.left, k);
            else if (t < k) return select(node.right, k - t - 1);
            else return node;
        }

        public int rank(Key key) {
            return rank(key, root);
        }

        private int rank(Key key, Node node) {
            if (node == null) return 0;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) return rank(key, node.left);
            else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
            else return size(node.left);
        }
    }
}