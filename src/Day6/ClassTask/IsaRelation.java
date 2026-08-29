package Day6.ClassTask;

public class IsaRelation {
    public static void main(String[] args){
//        Phone p1 = new Phone();
//        Sim s1 = new Sim();
//        JioSim j1 = new JioSim();
//        p1.videoCall(j1);
//        p1.call(j1);
        Sim s1 = new JioSim();
    }





}
class Phone{
    public void call(Sim s){
        s.connect();
        System.out.println("calling....");



    }
    public void videoCall(JioSim s){
        s.dataConnect();
        System.out.println("video call started....");


    }
}
class Sim{
    public void connect(){
        System.out.println("connecting....");
    }
}
class JioSim extends Sim{
    public void dataConnect(){
        System.out.println("Internet connected....");
    }
}