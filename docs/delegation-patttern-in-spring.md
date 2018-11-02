# Delegation Pattern in Spring

## AuthenticationManagerDelegator

下面是 `WebSecurityConfigurerAdapter` 中，获取 AuthenticationManager Bean 方法的代码，实际返回了 `AuthenticationManagerDelegator` 的实例。

```
public AuthenticationManager authenticationManagerBean() throws Exception {
    return new AuthenticationManagerDelegator(authenticationBuilder, context);
}
```

代码注释中，对 `AuthenticationManagerDelegator` 作用的描述如下，

```
Delays the use of the AuthenticationManager build from the AuthenticationManagerBuilder to ensure that it has been fully configured.
```

推迟执行 `AuthenticationManagerBuilder` 的 `build()` 方法，保证 `AuthenticationManager` 被充分的配置。

一般来讲，会通过重载 `WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)` 对 `AuthenticationManager` 进行自定义配置。

举个栗子，在自定义配置类 `WebSecurityConfiguration extends WebSecurityConfigurerAdapter` 中，

```
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("admin").password("{noop}123456").authorities("ADMIN").roles("ADMIN")
            .and()
            .withUser("master").password("{noop}123456").authorities("MASTER").roles("MASTER")
            .and()
            .withUser("test").password("{noop}123456").authorities("TEST").roles("TEST");
}
```

而在需要 AuthenticationManager Bean 时，则可以在 `WebSecurityConfiguration` 配置类中，

```
@Bean
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
}
```

这也是 `WebSecurityConfigurerAdapter#authenticationManagerBean()` 官方注释所描述的，暴露 AuthenticationManager Bean 的实现方式。

```
Override this method to expose the AuthenticationManager from configure(AuthenticationManagerBuilder) to be exposed as a Bean. 
```

如果这个方法直接返回 `AuthenticationManager` 实际对象的实例，那么上面 `configure` 方法中的配置则无法生效。

那么 `AuthenticationManagerDelegator` 是如何进行 **推迟** 操作的呢？

`AuthenticationManagerDelegator` 本身也实现了 `AuthenticationManager` 接口，核心方法 `authenticate()` 的实现代码如下，

```
public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
    if (delegate != null) {
        return delegate.authenticate(authentication);
    }

    synchronized (delegateMonitor) {
        if (delegate == null) {
            delegate = this.delegateBuilder.getObject();
            this.delegateBuilder = null;
        }
    }

    return delegate.authenticate(authentication);
}
```

方法中 `delegate` 便是需要实际执行 `authenticate()` 认证方法的，"真正"的 `AuthenticationManager` 实例。

当一个 Spring 应用启动完毕，并调用 AuthenticationManager Bean 的认证方法时，实际是调用 `AuthenticationManagerDelegator#authenticate`。

根据代码逻辑，不难发现，如果 `delegate` 尚未赋值，便会调用 delegateBuilder (`AuthenticationManagerBuilder`) 的 `getObject()` 方法。

```
public final O getObject() {
    if (!this.building.get()) {
        throw new IllegalStateException("This object has not been built");
    }
    return this.object;
}
```

通过这样的逻辑，只要在第一次使用 AuthenticationManager Bean 之前，执行 `build()` 动作即可，保证充分配置。

没有这个 Delegator 时，那个 `@Bean` 方法返回前，肯定就要执行 `build()` 方法了。

---

接下来仿照 `AuthenticationManagerDelegator` 写一个样例，可参考包 [代码传送门](../src/main/java/top/ashman/demo/pattern/delegation/delay) 中的代码。

`Letter` 相当于 `AuthenticationManager` 的一个实际实现，有 `from` 和 `to` 两个属性，方便查看使用 Delegator 前后的区别。

```
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
```

`LetterBuilder` 相当于 `AuthenticationManagerBuilder`，参考实现了 `getObject()` 与 `build()` 方法。

```
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
```

`LetterDelegator` 自然是相当于 `AuthenticationManagerDelegator`，起到延迟配置的效果，且在 `build()` 方法执行前调用会抛出异常。

```
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
```

`Client` 则是相当于 Spring 的整个启动加载流程。

```
public class Client {
    public static void main(String[] args) {
        System.out.println("*** Get letter directly ***");
        LetterBuilder letterBuilder1 = new LetterBuilder();
        letterBuilder1.from("Atlantis");
        Letter letter = getLetter(letterBuilder1);
        letterBuilder1.to("Babylon");
        System.out.println(letter.getFrom());
        System.out.println(letter.getTo());

        System.out.println();
        System.out.println("*** Get letter via delegator ***");
        LetterBuilder letterBuilder2 = new LetterBuilder();
        letterBuilder2.from("New Atlantis");
        Letter letterDelegator = getLetterDelegator(letterBuilder2);
        letterBuilder2.to("New Babylon");

        System.out.println("--- Without building ---");
        try {
            System.out.println(letterDelegator.getFrom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(letterDelegator.getTo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--- Built ---");
        letterBuilder2.build();
        System.out.println(letterDelegator.getFrom());
        System.out.println(letterDelegator.getTo());
    }

    private static Letter getLetter(LetterBuilder letterBuilder) {
        return letterBuilder.build();
    }

    private static Letter getLetterDelegator(LetterBuilder letterBuilder) {
        return new LetterDelegator(letterBuilder);
    }
}
```

执行输出如下，效果还是比较直观的，就不展开描述了。

```
*** Get letter directly ***
Atlantis
null

*** Get letter via delegator ***
--- Without building ---
This object has not been built
This object has not been built
--- Built ---
New Atlantis
New Babylon
```
