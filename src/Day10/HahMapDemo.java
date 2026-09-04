package Day10;
import java.util.*;
public class HahMapDemo {
        public static void main(String[] args) {

            HashMap<Integer, String> hm = new HashMap<Integer, String>();

            hm.put(1,"RAYA");
            hm.put(2,"KAALA");
            hm.put(3, "DAWG");

            hm.put(3,"DEVA");
            for(String i : hm.values()) {
                System.out.println(i+" "+hm.get(i));
            }


            String name="ALLAH";
            printFreq(name);

            System.out.println(hm);

            System.out.println(hm.keySet());

            System.out.println(hm.values());
        }
        public static void printFreq(String name) {
            char [] chars;
            HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
            for (int i = 0; i < name.length(); i++) {
                if(!(freq.containsKey(name.charAt(i)))){
                    freq.put(name.charAt(i),1);
                }
                else {
                    freq.put(name.charAt(i), freq.get(name.charAt(i))+1);
                }
                System.out.println(freq);

            }

        }
    }