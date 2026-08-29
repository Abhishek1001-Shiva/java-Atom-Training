package Day7;

public class SecondMax {
    public static void main(String [] args){
        int [] arr = {2,3,4,5,6,7};
        int max = arr[0];
        int sMax = arr[0];
        int i = 0;
        for (i = 0; i < arr.length; i++) {
          if(i%2!=0){
              System.out.println(arr[i]);

          }
        }
    }
}