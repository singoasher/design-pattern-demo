package top.ashman.demo.pattern.delegation;

/**
 * Delegate
 *
 * @author singoasher
 * @date 2018/7/31
 */
public class RealCar implements Car {
    private String name;
    private Integer price;

    public RealCar() {
    }

    public RealCar(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public void run() {
        System.out.println("Car Name: " + name + ", Price: " + price);
    }
}
