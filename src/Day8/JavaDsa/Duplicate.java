package Day8.JavaDsa;
import java.util.Arrays;
public class Duplicate {
    public static void main(String[] args){
        int[] nums =new int[10000];
        for(int i=0;i<nums.length;i++){
            nums[i]=i+1;
        }
        System.out.println(Arrays.toString(nums));
//        int []arr ={1,2,2,3,4};
//        findDup(arr);
    }
    public static void findDup(int []arr){
        int check=0;
        int found=0;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                System.out.println(" CHECKING  " +  ++check + " TIMES ");
                if(arr[i]==arr[j]){
                    System.out.println(" |_DUPLICATE FOUND " +  ++found + " TIMES_| ");
                    System.out.println( " __________ " + arr[i] + " __________" );
                }
            }
        }

    }

}
