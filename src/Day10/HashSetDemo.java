package Day10;
import java.util.*;
public class HashSetDemo {
    public static void main(String[] args) {
        HashSet h1 =new HashSet<Integer>();
        h1.add(2);
        h1.add(3);
        h1.add(2);
        int arr []={1,2,2,3,4};
        System.out.println(h1.add(3));
        LinkedList a;

    }
    public static int [] removeDup(int arr[]){
        HashSet<Integer> hs= new HashSet<Integer>();

        for(int i=0;i<arr.length;i++){
            hs.add(arr[i]);

        }
        int [] temp=new int [hs.size()];
        int index=0;
        for (Integer i:hs){
            temp[index]=i;
            index++;

        }
        return temp;
    }
}
