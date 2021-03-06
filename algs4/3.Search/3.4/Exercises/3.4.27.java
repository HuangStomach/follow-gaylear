import edu.princeton.cs.algs4.*;

class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hashA(Key key) {
        int index = key.toString().toUpperCase().charAt(0);
        return (11 * index) % M;
    }

    private int hashB(Key key) {
        int index = key.toString().toUpperCase().charAt(0);
        return (17 * index) % M;
    }

    public void put(Key key, Value val) {
        // if (N * 2 >= M) resize(2 * M);
        var stA = st[hashA(key)];
        var stB = st[hashB(key)];
        
        if (stA.size() >= stB.size()) stB.put(key, val);
        else stA.put(key, val);
        
        N = 0;
        for (int i = 0; i < M; i++) {
            if (st[i] != null) N += st[i].size();
        }
    }

    public void print() {
        for (int i = 0; i < M; i++) {
            if (st[i] == null || st[i].size() == 0) continue;
            for (Key key: st[i].keys()) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String test = "E A S Y Q U T I O N";
        String[] keys = test.split("\\s+");
        int n = keys.length;
        
        SeparateChainingHashST<String, Boolean> st = new SeparateChainingHashST(3);
        for (int i = 0; i < n; i++) {
            st.put(keys[i], true);
            st.print();
            System.out.println("----");
        }
    }
}