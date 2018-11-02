package top.ashman.demo.pattern.delegation.delay;

/**
 * @author sunzhaojie
 * @date 2018/11/1
 */
public class Letter {
    private String from;
    private String to;

    public Letter() {
    }

    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
