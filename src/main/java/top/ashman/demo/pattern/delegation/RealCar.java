package top.ashman.demo.pattern.delegator;

/**
 * Delegate
 *
 * @author singoasher
 * @date 2018/7/31
 */
public class RealCar implements Car {
    private String name;
    private Integer price;

    @Override
    public void run() {
        System.out.println("Car Name: " + name + ", Price: " + price);
    }
}
