package top.sinfonia.demo.dp.delegator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Delegate
 *
 * @author singoasher
 * @date 2018/7/31
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealCar implements Car {
    private String name;
    private Integer price;

    @Override
    public void run() {
        log.info("Car Name: {}, Price: {}", name, price);
    }
}
