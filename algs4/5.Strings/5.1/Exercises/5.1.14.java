import edu.princeton.cs.algs4.*;

public class Quick3string {
    private static int charAt(int s, int d) {
        char[] chars = Integer.toString(s).toCharArray();
        return d < chars.length ? (int)Character.toString(chars[d]) : -1;
    }

    public static void sort(int[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(int[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}