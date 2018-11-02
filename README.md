# Design Pattern Demo

> 设计模式（Design Pattern）是一套被反复使用、多数人知晓的、经过分类的、代码设计经验的总结。


## GoF 设计模式

### 创建型（Creational）模式

- Abstract factory pattern groups object factories that have a common theme.
- Builder pattern constructs complex objects by separating construction and representation.
- Factory method pattern creates objects without specifying the exact class to create.
- Prototype pattern creates objects by cloning an existing object.
- Singleton pattern restricts object creation for a class to only one instance.

### 结构型（Structural）模式

- Adapter allows classes with incompatible interfaces to work together by wrapping its own interface around that of an already existing class.
- Bridge decouples an abstraction from its implementation so that the two can vary independently.
- Composite composes zero-or-more similar objects so that they can be manipulated as one object.
- Decorator dynamically adds/overrides behaviour in an existing method of an object.
- Facade provides a simplified interface to a large body of code.
- Flyweight reduces the cost of creating and manipulating a large number of similar objects.

- [代理模式（Proxy Pattern）](./docs/proxy-pattern.md)

> Proxy provides a placeholder for another object to control access, reduce cost, and reduce complexity.

### 行为型（Behavioral）模式

- Chain of responsibility delegates commands to a chain of processing objects.
- Command creates objects which encapsulate actions and parameters.
- Interpreter implements a specialized language.
- Iterator accesses the elements of an object sequentially without exposing its underlying representation.
- Mediator allows loose coupling between classes by being the only class that has detailed knowledge of their methods.

- [备忘录模式（Memento Pattern）](./docs/memento-pattern.md)

> Memento provides the ability to restore an object to its previous state (undo).

- Observer is a publish/subscribe pattern which allows a number of observer objects to see an event.
- State allows an object to alter its behavior when its internal state changes.
- Strategy allows one of a family of algorithms to be selected on-the-fly at runtime.
- Template method defines the skeleton of an algorithm as an abstract class, allowing its subclasses to provide concrete behavior.
- Visitor separates an algorithm from an object structure by moving the hierarchy of methods into one object.

## Spring 中的设计模式

### 委托模式

- [Delegation Pattern in Spring](./docs/delegation-patttern-in-spring.md)
- [委托模式（Delegation Pattern）](./docs/delegation-pattern.md)（暂未完工）
