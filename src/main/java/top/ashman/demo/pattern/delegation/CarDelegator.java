package top.ashman.demo.pattern.delegation;

/**
 * Delegator
 *
 * @author singoasher
 * @date 2018/7/31
 */
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
            System.out.println("Delegate is not null and going to run");
            delegate.run();
            return;
        }

        System.out.println("Delegate is null");
        synchronized (delegateMonitor) {
            if (delegate == null) {
                this.delegate = delegateBuilder.getObject();
                if (this.delegate == null) {
                    System.out.println("Not built");
                    return;
                }

                // DelegateBuilder under CarDelegator will never be used
                this.delegateBuilder = null;
            }
        }

        delegate.run();
    }
}
