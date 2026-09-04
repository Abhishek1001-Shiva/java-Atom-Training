package Day10;

import java.util.ArrayList;

public class ArrayListDemo1 {
    public static void main(String[] args) {
        ArrayList<Integer> li = new ArrayList<Integer>();
        li.add(4);
        li.add(45);
        System.out.println(li);

        ArrayList<Car> cars = new ArrayList<Car>();

        Car c1 = new Car();
        c1.brand = "Tesla";
        c1.model = "S1";

        Car c2 = new Car();
        c2.brand = "Tesla";
        c2.model = "S2";

        Car c3 = new Car();
        c3.brand = "Tesla";
        c3.model = "S3";

        cars.add(c1);
        cars.add(c2);
        cars.add(c3);


        for (int i = 0; i < cars.size(); i++) {
            System.out.println("Car " + (i + 1) + ": " + cars.get(i));
        }
    }
}

class Car {
    String brand;
    String model;

    @Override
    public String toString() {
        return this.brand + " " + this.model;
    }
}
