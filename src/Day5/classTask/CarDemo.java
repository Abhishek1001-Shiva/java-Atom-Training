package Day5.classTask;

public class CarDemo {
    public static void main(String[] args) {
        Tesla t1 = new Tesla("Model S", "Tesla");
        t1.start();
        t1.speed = 50;
        t1.brake();
        t1.handBrake();
    }
}

class Car {
    String brand;
    String model;
    int speed;
    int maxSpeed;

    // methods
    public void start() {
        System.out.println("Car Started");
    }

    public void brake() {
        speed = speed - 5;
        System.out.println("Brake applied, car speed is " + speed);
    }

    public void handBrake() {
        this.speed = 0;
        System.out.println("Hand brake applied, car stopped");
    }
}

class ElectricCar extends Car {
    ElectricCar(String model, String brand) {
        this.model = model;
        this.brand = brand;
        System.out.println("Electric car model: " + model + ", brand: " + brand);
    }

    @Override
    public void start() {
        System.out.println("Electric car is started");
        super.start();
    }
}

class Tesla extends ElectricCar {
    Tesla(String model, String brand) {
        super(model, brand);
    }
}
