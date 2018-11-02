package top.ashman.demo.pattern.delegation.delay;

import javafx.util.Builder;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sunzhaojie
 * @date 2018/11/1
 */
public class LetterBuilder implements Builder<Letter> {
    private AtomicBoolean building = new AtomicBoolean();
    private String from;
    private String to;
    private Letter letter;

    public LetterBuilder from(String from) {
        this.from = from;
        return this;
    }

    public LetterBuilder to(String to) {
        this.to = to;
        return this;
    }

    public Letter getObject() {
        if (!this.building.get()) {
            throw new IllegalStateException("This object has not been built");
        }
        return this.letter;
    }

    @Override
    public Letter build() {
        if (this.building.compareAndSet(false, true)) {
            this.letter = new Letter(from, to);
            return this.letter;
        }
        throw new IllegalStateException("This object has already been built");
    }
}
