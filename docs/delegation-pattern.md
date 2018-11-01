# 委托模式（Delegation Pattern）

## Start

下面是 `WebSecurityConfigurerAdapter` 中，获取 AuthenticationManager Bean 的代码片段。

> Override this method to expose the {@link AuthenticationManager} from {@link #configure(AuthenticationManagerBuilder)} to be exposed as a Bean. 

那么，`AuthenticationManagerDelegator` 是个什么东西？启到了什么作用呢？
 
```
public AuthenticationManager authenticationManagerBean() throws Exception {
    return new AuthenticationManagerDelegator(authenticationBuilder, context);
}
```

首先，看下 `AuthenticationManagerDelegator` 的作用。

> Delays the use of the {@link AuthenticationManager} build from the {@link AuthenticationManagerBuilder} to ensure that it has been fully configured.

样例可参考包 `top.sinfonia.demo.dp.delegator` 中的代码，`Delegator` 将 `build()` 方法的调用推迟到了 `Delegator` 中业务方法的调用。

- 注意：~~样例代码在 `Delegator` 中调用了 `build()` 方法~~，但是 `AuthenticationManagerDelegator` 则是调用 `getObject()` 方法。
    - 修改了样例，添加了 `getObject()` 方法，不过为了 Test 类书写的方便，未抛出异常，手动判断了 null
    - 此外，`getObject()` 方法的存在还是很有必要的，在 `Delegator` 中调用 `build()` 方法是很奇怪的。

关于 `getObject()` 方法描述如下，

> Gets the object that was built. If it has not been built yet an Exception is thrown.

也就是，并没有将 `build()` 过程限定在 `Delegator` 中，而是保证在 `build()` 之后才能顺利的调用成功。

