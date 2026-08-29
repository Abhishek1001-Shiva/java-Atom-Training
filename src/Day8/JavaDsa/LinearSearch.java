package Day8.JavaDsa;
import java.util.Arrays;
public class LinearSearch {
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6,7};
        System.out.println(linearSearch(arr,4));


    }
    public static int linearSearch(int []arr, int num){
        int n = arr.length;
        for(int i=0;i<n;i++){
            if(arr[i]==num){return i;}



        }
    return-1;
    }
}
