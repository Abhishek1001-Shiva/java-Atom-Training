package DAY7;

public class ArrayOps {
    public static void main(String[] args) {
        int[] arr ={2,3,4,5,6,7};

        int min=arr[0];

        for(int i=0;i<arr.length;i++){
           if(arr[i]<min){
               min=arr[i];
           }
        }
        System.out.println(min);

    }
}
