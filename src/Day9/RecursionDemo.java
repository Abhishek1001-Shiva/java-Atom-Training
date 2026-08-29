package Day9;

public class RecursionDemo {
    public static void main(String[] args) {
        int n = 6;
        System.out.println("Fibonacci at position " + n + " = " + fibo(n));
    }

    public static int fibo(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibo(n - 1) + fibo(n - 2);
    }
}
