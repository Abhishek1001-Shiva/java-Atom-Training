package Day11.DSA;
import java.util.*;
public class DynamicArraydemo  {

    public static void main(String[] args) {
        DynamicArray d1 = new DynamicArray();

        for(int i=0;i<10000;i++){
            d1.add(i);
        }
        System.out.println("added");
        System.out.println(d1);
        d1.remove(4);
    //       d1.add(1);
//       d1.add(5);
//       d1.add(6);
//       d1.add(9);
//       d1.remove(3);
//        System.out.println(d1.get(-3));
//        System.out.println(d1.size());
//        System.out.println(Arrays.toString(d1.arr));
//        System.out.println(d1.capacity);
//        System.out.println(d1);


    }
}
class DynamicArray{
    int init_capacity;
    int capacity;
    int arr[];
    int size;
    int pos;

    DynamicArray(){
        init_capacity=5;
        capacity=init_capacity;
        arr=new int[capacity];
        size=0;
        pos=0;

    }
    public void resize(){
            capacity=2*capacity;
            int[] newarr = new int[capacity];
            for(int i = 0;i<size;i++){
                newarr[i]=arr[i];

            }
            arr=newarr;
    }
    public void add(int val){

        if(size>=capacity/2){resize();
            capacity=2*capacity;
            int[] newarr = new int[capacity];
            for(int i = 0;i<size;i++){
                newarr[i]=arr[i];

            }
            arr=newarr;

        }
        arr[pos]=val;
        pos=pos+1;
        size++;
    }

    @Override
    public String toString(){
        int []temp = new int[size];
        for(int i=0;i<size;i++){
            temp[i]=arr[i];
        }
        return Arrays.toString(temp);

    }
    public int size(){
        return size;
    }

public void remove(int index){
        int shifts=0;
        for(int i=index;i<size;i++){
            arr[i]=arr[i+1];
            shifts++;
        }
        pos--;
        size--;
    System.out.println("shifted" + shifts + "times");
        if(size<capacity/2) {shrink();}

}
public int get(int index){
        if (index>size-1 || index <0){
            System.out.println("INVALID INDEX");
            return -1;
        }
        return arr[index];
}
public void shrink(){

}
}
