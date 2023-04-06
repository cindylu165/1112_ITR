// package edu.princeton.cs.algs4;

// import java.util.ArrayList;
// import java.util.InputMismatchException;
// import java.util.Locale;
// import java.util.NoSuchElementException;
// import java.util.Scanner;
// import java.util.regex.Pattern;
public class QuickFindUF {
    private int[] id;
    private int[] sz;
    public QuickFindUF(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    public int find(int p)
    { 
        return id[p]; 
    }
    public int find_root(int p)
    { 
        while(p != id[p]){
            // find(id[p]);
            p = id[p];
        }
        return p; 
    }
    public void union_old(int p, int q) 
    {
        if(find(p) != find(q))
        {
            int pid = id[p];
            int qid = id[q];
            for(var i = 0; i < id.length; i++)
            {
                if(id[i] == pid) id[i] = qid;
            }
        }
    }
    public void union(int p, int q) 
    {
        if(find_root(p) != find_root(q))
        {
            int p_root = find_root(p);
            int q_root = find_root(q);
            id[p_root] = q_root;
        }
    }
    public void weight_union(int p, int q) 
    {
        sz = new int[10];
        int i = find_root(p);
        int j = find_root(q);
        if(i == j) return;
        if(sz[i] < sz[j])
        { 
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    public boolean connected(int p, int q)
    {
        return find_root(p) == find_root(q);
    }
    public static int binarySearch(int[] a, int key)
    {
        int lo = 0, hi = a.length-1;
        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;
            if(key < a[mid])
            { 
                hi = mid - 1;
            }else if(key > a[mid]){
                lo = mid +1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    // public static void main(String[] args)
    // {
    //     int N = StdIn.readInt();
    //     QuickFindUF uf = new QuickFindUF(N);
    //     while (!StdIn.isEmpty())
    //     {
    //         int p = StdIn.readInt();
    //         int q = StdIn.readInt();
    //         if (!uf.connected(p, q))
    //         {
    //             uf.union(p, q);
    //             StdOut.println(p + " " + q);
    //         }
    //     }
    // }    
}