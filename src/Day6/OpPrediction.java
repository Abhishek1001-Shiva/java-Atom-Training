package Day6;

public class OpPrediction {
    public static void main(String[] args){

    int result = 0;
    for(int i = 1; i <= 6; i++) {
        if (i % 2 == 0 && i > 2)
            result += i * 2;
        else if (i % 2 == 1)
            result += i;
        else
            result -= i;
        System.out.print(result + " ");
    }
    }

}
