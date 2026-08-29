package Day8.JavaDsa;

public class Swap {
    public static void main(String[] args) {
        int a =5;
        int b =10;
        int temp=0;
        System.out.println(" before swap = "  + a +" | " + b );
//        temp = a;
//        a=b;
//        b=temp;
        a=a+b;
        b=a-b;
        a=a-b;
        System.out.println(" after swap = " + a + " | " + b);
    }
}
