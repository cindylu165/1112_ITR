public class Insertion {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 1; i < N; i++)
        {
            for(int j = i; j > 0; j--){
                if (less(a[j], a[j-1])) exch(a, i, j-1);
                else break;
            }
        }
    }
    public static void sort_H(Comparable[] a, int h)
    {
        int N = a.length;
        for (int i = h; i < N; i++)
        {
            for(int j = i; j > 0; j-=h){
                if (less(a[j], a[j-h])) exch(a, i, j-h);
                else break; // 前面都有符合代表排完，直接結束
            }
        }
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
