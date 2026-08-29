package DAY7;

public class SeatAlreadyBookedException extends Exception{
    SeatAlreadyBookedException(){
        System.out.println("seat already booked");
    }
}
