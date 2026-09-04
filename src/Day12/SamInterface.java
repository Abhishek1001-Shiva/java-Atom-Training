package Day12;

public class SamInterface {
    public static void main(String[] args) {
        flyable f=()->{
            System.out.println("flyable");
        };
        runnable r=()->{};
        f.fly();
        r.run();
    }
}
interface flyable{
    public void fly();
}
interface runnable{
    public void run();
}
