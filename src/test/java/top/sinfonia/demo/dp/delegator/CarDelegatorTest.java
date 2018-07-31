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

        carBuilder.build();
        carDelegator.run();
        carDelegator.run();

        CarBuilder carBuilder1 = new CarBuilder();
        carBuilder1.name("Singo").price(1234567890).build();
        CarDelegator carDelegator1 = new CarDelegator(carBuilder1);

        carDelegator1.run();
        carDelegator1.run();
    }

}