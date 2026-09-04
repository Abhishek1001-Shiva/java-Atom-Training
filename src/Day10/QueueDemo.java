package Day10;
import java.util.*;
public class QueueDemo {
    public static void main(String[] args) {
        ArrayDeque <Integer> a1 =new ArrayDeque <Integer>();
        a1.add(2);
        a1.add(1);
        a1.add(3);
//        System.out.println(a1);
        System.out.println(a1.poll());
        System.out.println(a1);
        System.out.println(a1.poll());
        System.out.println(a1);
        System.out.println(a1.poll());
        System.out.println(a1);
        System.out.println(a1.remove());

    }
}
