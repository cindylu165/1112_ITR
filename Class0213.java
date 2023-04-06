// package inclass;
public class Class0213{
    // sum of two number
    public static int sum(int a, int b) {
        return a + b;
    }
    public static int Multi3Sum(int[] inputArray) {
        int sum = 0;
        for(var i = 0; i < inputArray.length ; i++) {
            if(inputArray[i] % 3 == 0) {
                sum += inputArray[i];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        // System.out.println("Sum = "+sum(1,2));
        int[] inputArray = {1, 7, 1, 3, 10, 100, 99, 33, 15, 71, 10};
        System.out.println(Multi3Sum(inputArray));
    }
}