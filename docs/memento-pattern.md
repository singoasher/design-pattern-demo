# 备忘录模式（Memento Pattern）

> 备忘录模式是一种软件设计模式，在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样以后就可将该对象恢复到原先保存的状态。

## 概述

备忘录模式是 23 种 GoF 设计模式之一，属于行为模式。

在备忘录模式中，有三种典型的抽象：Originator、Caretaker、Memento。 —— [示例代码传送门](../src/main/java/top/ashman/demo/pattern/memento)

- **Originator**: 需要进行备忘的对象，具有若干内部状态。

Originator 是一个设计完善的对象，具有封装性，对外部来讲，其属性和实现细节是隐藏的，仅提供了公共访问方式。

- **Caretaker**: 执行 Originator 状态修改、保存、恢复操作的客户端。

由于 Originator 的实现细节对外部是隐藏的，Caretaker 只是通过 Originator 提供的公开方法，进行的状态修改、保存、恢复操作而已。

- **Memento**: 保存 Originator 在某个瞬间的内部状态，Memento 对象创建后，这些状态便不可再改变。

Caretaker 通过保存 Memento 对象，间接的保存 Originator 的内部状态。并在需要恢复操作时，回传 Memento 对象给 Originator 使用。

此外，Memento 应当是一个不透明的对象（opaque data type），即一个不能、也不应该被客户端 Caretaker 所改变的对象。

**执行一次备忘及恢复操作的流程大致如下：**

1. Caretaker 请求 Originator 获取一个 Memento 对象。
2. Caretaker 继续执行对 Originator 的其他操作。
3. Caretaker 回传之前的 Memento 对象给 Originator 执行状态恢复的操作。

在备忘录模式中，Caretaker 可以通过 Memento 保存若干次 Originator 的内部状态，并在需要时，以 **回滚** 的形式，进行撤销操作，完成状态恢复。

在使用备忘录模式时，如果 Originator 会改变其他对象或者资源，则需要额外注意 -- 备忘录模式应该仅作用于单一的对象。

## 备忘录模式所解决的问题

一个设计完善的对象应该是具有封装性的，其实现细节（数据结构）隐藏在对象的内部，无法在外部直接访问。

- 需要将一个对象的内部状态在 **外部保存**，以便在有需要时，将该对象 **恢复至** 之前的状态。
- 对象的封装 **不可以** 被破坏。

在面临以上需求时，便可以使用备忘录模式。由 Originator 来执行如下两项操作：

1. 保存自己当前的内部状态到一个 Memento 对象。
2. 通过一个 Memento 对象恢复到之前的内部状态。

一般来讲，应当只有 Originator 能够有权限访问 Memento 所保存的状态。

客户端（Caretaker）可以通过 Originator 获取一个 Memento 对象实例（保存一次 Originator 的内部状态），也可以将一个 Memento 实例作为参数回传给 Originator（恢复到之前的状态）。

## 参考链接

- [Memento pattern](https://en.wikipedia.org/wiki/Memento_pattern)