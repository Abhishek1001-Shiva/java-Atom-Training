package Day8.JavaDsa;
import java.util.Arrays;
public class NeedDsa {
    public static void main(String[] args) {
        int[]arr={1,2,3,4,5};
        NeedDsa n1 =new NeedDsa();
        n1.delElement(arr ,0);
        n1.delElement(arr ,0);
        n1.delElement(arr ,0);
        n1.delElement(arr ,0);

        n1.delElement(arr ,4);

        n1.delElement(arr ,0);
        n1.delElement(arr ,0);
        n1.delElement(arr ,0);
        n1.delElement(arr ,0);

    }
    public void delElement(int[] arr , int index){
        if(index==arr.length-1){
            arr[index]=0;
        }
        for(int i =index ; i<arr.length-1 ; i++){
            arr[i]=arr[i+1];
        }
        System.out.println(Arrays.toString(arr));
    }
}
