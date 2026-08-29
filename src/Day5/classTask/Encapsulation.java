package Day5.classTask;

public class Encapsulation {
    public static void main (String[] args) {
        BankAccount b1 = new BankAccount() ;
        System.out.println(b1.balance) ;
        b1.accName = "anu" ;
        b1.balance = 3000000 ;
        System.out.println(b1.accName) ;
        b1.setBal(5000);
        b1.deposit(2000) ;
        b1.withdraw(500);

        System.out.println(b1.balance);

    }

}
class BankAccount {
    String accName;
    int accId;
    public int balance;

    public int getBal() {
        return this.balance;
    }
    public void setBal(int amt){
        this.balance =amt;
    }

    public void deposit(int amt) {
        if(amt<=0){
            System.out.println("invalid anount");
            return;

        }
        balance = balance + amt;
        System.out.println("Amount deposited : " + amt );
    }

    public void withdraw(int amt) {
        balance = balance - amt;
        System.out.println("amount withdrawn : " + amt );
    }


}