package Day6.ClassTask;

public class EH {
     public static void main(String[] args) {
         System.out.println("program started.....");
         int a =5;
         try{
             System.out.println(a/0);

         }
         catch(Exception e){
             System.out.println(e);
             System.out.println("exception handled!!!");
         }
         System.out.println("program finished....");
    }
}
