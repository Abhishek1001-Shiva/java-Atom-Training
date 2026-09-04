package Day11.DSA;
import java.util.*;
public class NodeDemo {
    public static void main(String[] args) {
       Node n1= new Node(4);
       Node n2 =new Node(6);
       n1.next=new Node(10);

        n1.next.next=n2;
        n2.next=n1;
        System.out.println(n1.next.next.next.next.next.data);
    }
}
