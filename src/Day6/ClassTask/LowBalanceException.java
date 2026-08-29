package Day6.ClassTask;

public class LowBalanceException extends Exception{

    LowBalanceException(){
        System.out.println("CANNOT WITHDRAW WHILE BALANCE IS ZERO");
    }
}
