package Day12;

public class StackDemo {
    public static void main(String[] args) {
        StackByArray s =new StackByArray();
        s.push(3);
        s.push(13);
        s.push(34);


        System.out.println(s.poll());
        System.out.println(s.poll());
        System.out.println(s.poll());
        System.out.println(s.poll());

    }
}
class StackByArray{
    int []arr;
    int size;
    int tos=-1;
    int capacity=5;

    StackByArray(){
        this.arr=new int[capacity];
        this.size=0;

    }


    public void push(int val) throws IllegalStateException{
        if(size==capacity){
            throw new IllegalStateException("stack is full");
        }

        {
            tos=tos+1;
            arr[tos] = val;
            size++;
        }


    }

    public void pop(){
        if(tos==-1){
            System.out.println("stack is empty");
            return;
        }

        tos--;
        size--;
    }
    public boolean isEmpty(){
        return tos == -1;
    }
    public int poll(){
        int data=arr[tos];
        if(!isEmpty()){
            tos--;
            size--;
        }else{
            throw new IllegalStateException("STACK IS EMPTY");
        }
        return data;

    }


public void display(){
        for(int i=0;i<size;i++){
            System.out.println(arr[i]);
        }
}


}