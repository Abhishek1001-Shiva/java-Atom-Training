package Day6.ClassTask;

public class InvalidAmountException extends Exception{
    InvalidAmountException(){
        System.out.println("KINDLY ENTER POSITIVE DIGIT");
    }
}
