package Day9;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        int arr[]={2,3,4,5,1};
        selectionSort(arr);

    }

    public static void selectionSort(int arr[]){
        int min;
        int temp;
        int n =arr.length;

        for(int i=0;i<n-1;i++){

            min=i;

            for(int j=i+1;j<n;j++){

                if(arr[j]<arr[min]){
                    min=j;


                }


            }
            temp=arr[i];
            arr[i]=arr[min];
            arr[min]=temp;


        }
        System.out.println(Arrays.toString(arr));


    }


}
