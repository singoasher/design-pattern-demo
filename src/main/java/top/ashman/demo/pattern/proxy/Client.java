package top.ashman.demo.pattern.proxy;

/**
 * @author sunzhaojie
 * @date 2018/11/2
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("*** Without Proxy ***");
        Subject subject = new RealSubject();
        System.out.println("Do something before in client");
        subject.operation();
        System.out.println("Do something after in client");

        System.out.println("*** With Proxy ***");
        Subject proxy = new Proxy();
        proxy.operation();
    }
}
