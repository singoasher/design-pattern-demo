package top.sinfonia.demo.dp.delegator;

import javafx.util.Builder;

/**
 * @author singoasher
 * @date 2018/7/31
 */
public class CarBuilder implements Builder<Car> {
    private String name;
    private Integer price;

    CarBuilder() {
    }

    CarBuilder name(String name) {
        this.name = name;
        return this;
    }

    CarBuilder price(Integer price) {
        this.price = price;
        return this;
    }

    @Override
    public Car build() {
        return new RealCar(name, price);
    }
}
