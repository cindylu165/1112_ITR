// class 0327
// string 本身就有Comparable物件
public class MergeBU {
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {

    }
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) // 決定size，0,2,4,6,8,10,
        {
            for (int lo = 0; lo < N-sz; lo += sz+sz)
            {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));

            }
        }
    }
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while(less(a[i],a[lo]))
            {
                i++;
                if (i == hi) break;
            }
            while(less(a[hi],a[j]))
            {
                j--;
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
