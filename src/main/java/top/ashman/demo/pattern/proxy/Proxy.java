package top.ashman.demo.pattern.proxy;

/**
 * @author sunzhaojie
 * @date 2018/11/2
 */
public class Proxy implements Subject {
    private final RealSubject realSubject;

    public Proxy() {
        this.realSubject = new RealSubject();
    }


    @Override
    public void operation() {
        System.out.println("Do something before in proxy");
        this.realSubject.operation();
        System.out.println("Do something after in proxy");
    }
}
