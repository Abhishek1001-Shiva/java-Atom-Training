package Day8.JavaDsa;
public class DuplicateOP {
    public static void main(String[] args) {

        int arr[] = {1, 2, 3, 4, 4};

        dupFind(arr);
    }

    public static void dupFind(int[] arr) {

        int left = 0;

        int right = 1;

        while (left < arr.length-1)
        {

            if ((arr[left] == arr[right]) ) {
                System.out.println(arr[left]);
                left += 1;
                right = left + 1;

            } else if(right>=arr.length-1){
                left+=1;
                right = left + 1;
            }
            else {
                right++;
            }


        }
    }
}