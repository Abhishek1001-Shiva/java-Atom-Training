package Day11.DSA;
import java.util.*;
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList1 li = new LinkedList1();

        li.addAtBeginning(4);
        li.addAtBeginning(5);
        li.addAtBeginning(10);


        li.display();
       System.out.println();
        System.out.println("Head data: " + li.head.data);
        System.out.println(li.get(2));
    }
}

class LinkedList1 {
    Node head;
    int size = 0;

    public void addAtBeginning(int val) {
        Node newnode = new Node(val);
        newnode.next = head;  // works even if head is null
        head = newnode;
        size++;

    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print(head.data);
    }


        public int get(int index){
            Node temp=head;

                for (int i=1;i<=index;i++){
                    temp=temp.next;

                }

            return temp.data;
        }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            sb.append(temp.data).append(" -> ");
            temp = temp.next;
        }
        sb.append("null");
        return sb.toString();
    }
}

class Node {
    int data;
    Node next;

    Node(int val) {
        this.data = val;
        next = null;
    }
}
