package top.sinfonia.demo.dp.delegator;

import javafx.util.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author singoasher
 * @date 2018/7/31
 */
public class CarBuilder implements Builder<Car> {
    private String name;
    private Integer price;
    private Car car;

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

    Car getObject() {
        if (this.car == null) {
            return null;
        }
        return this.car;
    }

    @Override
    public Car build() {
        this.car = new RealCar(this.name, this.price);
        return this.car;
    }
}
