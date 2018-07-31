package top.sinfonia.demo.dp.delegator;

import org.junit.Test;

/**
 * @author singoasher
 * @date 2018/7/31
 */
public class CarDelegatorTest {
    @Test
    public void run() throws Exception {
        CarBuilder carBuilder = new CarBuilder();
        carBuilder.name("Asher");
        CarDelegator carDelegator = new CarDelegator(carBuilder);
        carBuilder.price(123456);

        carDelegator.run();
        carDelegator.run();
        carDelegator.run();
        carDelegator.run();
    }

}