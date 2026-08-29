package Day8.JavaDsa;

public class BinarySearch {
    public static void main(String[] args) {
        int[]arr={1,2,3,4,5,6,7,};
        System.out.println(binarySearch(arr,4));


    }
    public static int binarySearch(int[]arr, int num){
        int n=arr.length;
        int mid;
        int left=0;
        int right = n-1;
        while(left!=right) {
            mid = (left + right) / 2;
            if (arr[mid] == num) {
                return mid;
            } else if (num > mid) {
                left = mid + 1;
            } else if (num < mid) {
                right = mid - 1;
            }
        }
        return n;
    }
}
