package top.ashman.demo.pattern.proxy;

/**
 * @author sunzhaojie
 * @date 2018/11/2
 */
public class RealSubject implements Subject {
    @Override
    public void operation() {
        System.out.println("Real Subject");
    }
}
