package DAY7;

public class StringDemo {
    public static void main(String[] args) {
        String name = new String("ram");
        name="devaraya";
        int count=0;
        for(int i=name.length()-1;i>=0;i--){
         if(name.charAt(i)=='a'){
             count++;
            }

        }
        System.out.println(" number of a's " +count);
    }
}
