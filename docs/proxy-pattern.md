# 代理模式（Proxy Pattern）

> A proxy, in its most general form, is a class functioning as an interface to something else. 

## 概述

```
client -> proxy -> real subject
```

代理模式，大概就是上面这样的场景，客户端通过代理，间接访问真正提供服务的对象。

有了代理，当客户端访问时，代理的功能可以仅仅是重定向之，也可以是附加了额外的处理逻辑。

此时，真正提供服务的对象不需要被改变，遵循开放封闭原则。

---

在代理模式中，有四种典型的抽象：Client, Subject、RealSubject、Proxy。 —— [示例代码传送门](../src/main/java/top/ashman/demo/pattern/proxy)

- **Client**: 客户端，访问 Subject 的某一实现
- **Subject**: 公共接口
- **RealSubject**: 实际对象，幕后提供服务的对象
- **Proxy**: 代理，也会实现 Subject 接口，对外提供一致的方法调用

由于都实现了 Subject 接口，对于 Client 来讲，使用 Proxy 和 RealSubject 是没有太大区别的。
