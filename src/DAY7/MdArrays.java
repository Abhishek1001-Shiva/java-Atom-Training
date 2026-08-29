package DAY7;
import java.util.Arrays;

public class MdArrays {
    public static void main(String[] args) {
        String [][]names = {
                {"a","b","c","d"},
                {"e","f","g","h"}
        };

        // Looping backwards through rows and columns
        for (int i = names.length - 1; i >= 0; i--) {
            for (int j = names[i].length - 1; j >= 0; j--) {
                System.out.print(names[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
