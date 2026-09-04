package Day11.DSA;

public class DoublyLinkedList {
    public void main(String[] args) {
        Dll li =new Dll();
        li.addAtBeginning(5);
        li.addAtBeginning(4);
        li.addAtBeginning(5);
        li.addAtBeginning(10);
    }


class Dll{
     Node head;
     int size;
     Dll(){
         this.head=null;
         size=0;
     }
        public void addAtBeginning(int val){
         Node newnode=new Node(val);
         if(isEmpty()){
                head=newnode;

         }

         newnode.next=head;
         head.prev=newnode;
         head=newnode;
         size++;
        }

        public boolean isEmpty(){
         if(head==null){return true;}
            return false;

        }

     }
    class Node{
        int data;
        Node prev;
        Node next;

        Node(int val){
            this.data=val;
        }

    }

}
