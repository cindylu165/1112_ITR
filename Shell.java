public class Shell {
    public static void sort(Comparable[], int h)
    {
        int N = a.length; // 假設句子長15
        int h = 1;
        while (h < N/3) h = 3*h + 1; // 取到最接近句子長度的值，1, 4, 13, 40, 121, 364..，以15來說會取13
        while (h >= 1)
        {
            for (int i = h; i < N; i++)
            {
                for(int j = i; j > 0; j-=h){
                    if (less(a[j], a[j-h])) exch(a, i, j-h);
                    else break; // 前面都有符合代表排完，直接結束
                }
            }
            h = h/3; // 13-> 4 -> 1去跑
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
