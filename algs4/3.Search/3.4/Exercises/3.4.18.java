import edu.princeton.cs.algs4.*;

public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private int O;
    private double lgM;
    private int[] primes; // 先不往里面写了……
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(997, Integer.MAX_VALUE);
    }

    public SeparateChainingHashST(int M, int O) {
        this.M = M;
        this.lgM = Math.log(M) / Math.log(2);
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        int t = key.hashCode() & 0x7fffffff;
        if (lgM < 26) t = t % primes[lgM + 5];
        return t % M;
    }

    private void resize(int cap) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST(cap, this.O);
        for (int i = 0; i < M; i++) {
            if (st[i] == null || st[i].size() == 0) continue;
            for (Key key : st[i].keys()) {
                t.put(key, st[i].get(key));
            }
        }
        this.st = t.st;
        this.M = t.M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);

        N = 0;
        count = 0;
        for (int i = 0; i < M; i++) {
            if (st[i] == null) continue;

            N += st[i].size();
            count++;
        }

        if (N / count >= O) resize(2 * M);
    }

    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);
        
        if (N > 0 && N <= 2 * M) resize(M / 2);
    }
    
    public boolean contains(Key key) {
        if (key == null) return false;
        return get(key) != null;
    }
}