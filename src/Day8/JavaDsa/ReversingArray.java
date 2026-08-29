package Day8.JavaDsa;
import java.util.Arrays;

public class ReversingArray {
    public static void main(String[] args) {
        int arr [] ={1,2,4,5,6};
        reverse(arr);
        reverseArr(arr);
    }

    public static void reverse(int arr[]) {
        int n = arr.length;
        int temp[]=new int[n];

        for(int i=arr.length-1;i>=0;i--){
            temp[n-i-1]=arr[i];
        }
        System.out.println("for " + Arrays.toString(temp));
    }

    public static void reverseArr(int arr[]){
        int left = 0;
        int right = arr.length -1;

        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
        System.out.println(" while  " + Arrays.toString(arr));
    }
}
