package top.ashman.demo.pattern.delegator;

/**
 * Delegator
 *
 * @author singoasher
 * @date 2018/7/31
 */
@Slf4j
public class CarDelegator extends RealCar {
    private CarBuilder delegateBuilder;
    private Car delegate;
    private final Object delegateMonitor = new Object();

    CarDelegator(CarBuilder delegateBuilder) {
        super();
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public void run() {
        if (delegate != null) {
            log.info("Delegate is not null");
            delegate.run();
            return;
        }

        log.info("Delegate is null");
        synchronized (delegateMonitor) {
            if (delegate == null) {
                this.delegate = delegateBuilder.getObject();
                if (this.delegate == null) {
                    log.error("Has not been built");
                    return;
                }

                // DelegateBuilder under CarDelegator will never be used
                this.delegateBuilder = null;
            }
        }

        delegate.run();
    }
}
