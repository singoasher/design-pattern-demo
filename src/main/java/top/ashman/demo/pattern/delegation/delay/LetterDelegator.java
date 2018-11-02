package top.ashman.demo.pattern.delegation.delay;

/**
 * @author sunzhaojie
 * @date 2018/11/1
 */
public class LetterDelegator extends Letter {
    private Letter delegate;
    private LetterBuilder delegateBuilder;
    private final Object delegateMonitor = new Object();

    public LetterDelegator(LetterBuilder delegateBuilder) {
        this.delegateBuilder = delegateBuilder;
    }

    @Override
    public String getFrom() {
        if (delegate != null) {
            return delegate.getFrom();
        }

        synchronized (delegateMonitor) {
            if (delegate == null) {
                delegate = this.delegateBuilder.getObject();
                this.delegateBuilder = null;
            }
        }

        return delegate.getFrom();
    }

    @Override
    public String getTo() {
        if (delegate != null) {
            return delegate.getTo();
        }

        synchronized (delegateMonitor) {
            if (delegate == null) {
                delegate = this.delegateBuilder.getObject();
                this.delegateBuilder = null;
            }
        }

        return delegate.getTo();
    }
}
