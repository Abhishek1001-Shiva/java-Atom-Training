package DAY7;
import java.util.Arrays;
import java.util.Scanner;
public class ArrayDemo {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String [] names =new String[3];
        for(int i=0;i<names.length;i++){
            System.out.println(Arrays.toString(names));
            for(int j=1;j<4;j++) {
                System.out.println("KINDLY ENTER THE NUMBER" +j);
            }
            names[i]=sc.nextLine();
        }
            System.out.println(Arrays.toString(names));



    }
}
