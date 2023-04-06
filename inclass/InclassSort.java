//todo: student 1 M114020017 & ±i®f·ì, student 2 M114020002 & §f¤ß·O
//todo: write code in the sort(int[] a) function in InclassSort.java to sort the input array in the way shown in the expected output. 
//DO NOT EDIT other functions NOR add global variables. 

public class InclassSort {
	// todo: write code in this function
	public int[] sort(int[] a) {
		int[] result = new int[a.length];
		int indexi = 0;
		int indexj = a.length-1;
		for (int i = 0; i < a.length; i++)
            for (int j = i; j > 0; j--)
                if (a[j] < a[j-1])
				{
					int tmp = a[j];
					a[j] = a[j-1];
					a[j-1] = tmp;
				}
                else break;
		for (int i=0; i<a.length; i++){
			if (i % 2 == 0){
				result[i] = a[indexj];
				indexj = indexj - 1;
			}
			else{
				result[i] = a[indexi];
				indexi = indexi + 1;
			}
		}
		return result;
	}
	
	public static void printSorted(int[] sorted) {
		int i;
    	String output = "";
    	for (i=0; i<sorted.length; i++) {
    		output += sorted[i] + ",";
    	}
    	if (output != ""){
    		output = output.substring(0, output.length() - 1);
    	}
    	System.out.println(output);
	}

	public static void main(String[] args) {
		InclassSort ics = new InclassSort();
		printSorted(ics.sort(new int[]{1,3,6})); // expected output: 6,1,3 
		printSorted(ics.sort(new int[]{1,2,3,4,5,6})); // expected output: 6,1,5,2,4,3
		printSorted(ics.sort(new int[]{7,6,3,5,1,2,9})); // expected output: 9,1,7,2,6,3,5
		printSorted(ics.sort(new int[]{100,1,10,8,6,2,5,10,1})); // expected output: 100,1,10,1,10,2,8,5,6
	}
}
