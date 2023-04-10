//todo: 張宸瑜 M114020017, 呂心慈 M114020002
//todo: modify sort and findNextRun functions to implement a mergesort that finds natural ordered items 
//with "runs" and merges them using a bottom up approach.   
//DO NOT EDIT other functions NOR add global variables.

//MergeNatural2023 is modified from MergeBU from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MergeBU.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MergeBU.html
public class MergeNatural2023 {

    // This class should not be instantiated.
    private MergeNatural2023() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }
    
    // todo: write code in this function
    // finds next run in the input array with the given starting index
    // returns end index of the run
    public int findNextRun(Comparable[]a, int startIndex) {
    	int endIndex = a.length -1;
    	// add your code here
        int endIndex2 = startIndex; // 先 copy startIndex
        while (true)
        {
            // 判斷 (1) 是否超出陣列 (2) 判斷前後 2 個直是否相等, 若小於或相等會回傳 true, 大於回傳 false
            if (endIndex2 < endIndex && (!(a[endIndex2] == a[endIndex2+1]) == (less(a[endIndex2], a[endIndex2+1])))){
                endIndex2++; // end 位置往下找
            }
            // 找到該 run 的 end, break
            else {
                endIndex = endIndex2;
                break;
            }
        }
    	// do not modify the following lines
    	System.out.println("Run start: " + startIndex + ", end: " + endIndex);
    	show(a,startIndex,endIndex);
    	return endIndex;
    }
    
    // todo: write code in this function to sort the array with mergesort using natural runs
    // use findNextRun function in this function to identify runs
    public static void sort(Comparable[] a) {
        // add your code here
        MergeNatural2023 _merge = new MergeNatural2023(); // 宣告 MergeNatural2023
        int end = -1; // 為了從 0 開始找，初始設為 -1
        if (isSorted(a)){
            end = _merge.findNextRun(a, end+1);
            return; // 檢查是否已排好
        }
        int[] end_list = new int[a.length]; // 儲存每個 run 的 end
        int end_index = 0; // 紀錄 end_list 存放到第幾格
        // 當還沒找到 a 的最後一個元素（等於最後一個 run）就繼續找
        while (end != a.length-1){
            end = _merge.findNextRun(a, end+1); // 找 run, start 為前一 run 的 end 下一個位置
            end_list[end_index++] = end; // 加入 end_list
            // end_index++; // 往下移一格
        }
        // 每兩個 run 進行 merge
        for (int i=0; i<end_list.length; i=i+2){
            if (end_list[i] == 0) break; // 如果遇到 0, 代表已經全部 merge 完成
            // 第一 run 與第二 run merge
            if (i == 0){
                merge(a, new Comparable[a.length], 0, end_list[i], end_list[i+1]);
            }
            // 其他 run
            else{
                merge(a, new Comparable[a.length], end_list[i-1]+1, end_list[i], end_list[i+1]);
            }
        }
        // 依照目前狀態再往下進行排序
        sort(a);
    }

  /***********************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    // print array between indices to standard output
    private static void show(Comparable[] a, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] a = {1,2,3,9,12,20,7,15,11,6,20,300,0};
        MergeNatural2023.sort(a);
        
        System.out.println();
        System.out.print("Sorted: ");
        show(a); 
    }
}
