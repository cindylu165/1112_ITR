//todo: �i�f�� M114020017, �f�߷O M114020002
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
        int endIndex2 = startIndex; // �� copy startIndex
        while (true)
        {
            // �P�_ (1) �O�_�W�X�}�C (2) �P�_�e�� 2 �Ӫ��O�_�۵�, �Y�p��ά۵��|�^�� true, �j��^�� false
            if (endIndex2 < endIndex && (!(a[endIndex2] == a[endIndex2+1]) == (less(a[endIndex2], a[endIndex2+1])))){
                endIndex2++; // end ��m���U��
            }
            // ���� run �� end, break
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
        MergeNatural2023 _merge = new MergeNatural2023(); // �ŧi MergeNatural2023
        int end = -1; // ���F�q 0 �}�l��A��l�]�� -1
        if (isSorted(a)){
            end = _merge.findNextRun(a, end+1);
            return; // �ˬd�O�_�w�Ʀn
        }
        int[] end_list = new int[a.length]; // �x�s�C�� run �� end
        int end_index = 0; // ���� end_list �s���ĴX��
        // ���٨S��� a ���̫�@�Ӥ����]����̫�@�� run�^�N�~���
        while (end != a.length-1){
            end = _merge.findNextRun(a, end+1); // �� run, start ���e�@ run �� end �U�@�Ӧ�m
            end_list[end_index++] = end; // �[�J end_list
            // end_index++; // ���U���@��
        }
        // �C��� run �i�� merge
        for (int i=0; i<end_list.length; i=i+2){
            if (end_list[i] == 0) break; // �p�G�J�� 0, �N��w�g���� merge ����
            // �Ĥ@ run �P�ĤG run merge
            if (i == 0){
                merge(a, new Comparable[a.length], 0, end_list[i], end_list[i+1]);
            }
            // ��L run
            else{
                merge(a, new Comparable[a.length], end_list[i-1]+1, end_list[i], end_list[i+1]);
            }
        }
        // �̷ӥثe���A�A���U�i��Ƨ�
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
