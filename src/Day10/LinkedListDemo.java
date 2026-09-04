package Day10;
import java.util.LinkedList;
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> li =new LinkedList<String>();
        li.addFirst("Engine");
        li.addLast("coach1");
        li.addLast("coach2");
        System.out.println(li);
    }
}
