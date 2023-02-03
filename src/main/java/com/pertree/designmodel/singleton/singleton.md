## 为什么要使用单例设计模式
### 处理资源访问冲突
情景描述：  
比如我们自定义了一个往文件中打印日志的 Logger 类，Service 服务在使用 Logger 类时都会 new 一个 Logger 类，
不同 Logger 类访问文件写入日志时，因为文件对象是共享资源，对它进行修改的时候，会有并发问题存在，比如日志信息互
相覆盖的情况。  
解决：  
- 使用 Synchronize 为 log（写日志方法）加锁，因为加锁后制作用于当前对象，new 多个 Logger 对象时不起作用
- 使用分布式锁，安全可靠、无 bug、高性能的分布式锁，并不是件容易的事情
- 使用 Java 中的并发队列（BlockingQueue），多个线程同时往并发队列里写日志，一个单独的线程负责将并发队列中的数据，
  写入到日志文件，这种方式实现起来也稍微有点复杂。
- 使用单例模式，我们将 Logger 设计成一个单例类，程序中只允许创建一个 Logger 对象，所有的线程共享使用的这一个 Logger 对象，
  共享一个 FileWriter 对象，而 FileWriter 本身是对象级别线程安全的，也就避免了多线程情况下写日志会互相覆盖的问题。
### 表示全局唯一类
如配置信息类，ID 号码生成器
### 单例模式实现的几种方式
- 饿汉式  
饿汉式的实现方式，在类加载的期间，就已经将 instance 静态实例初始化好了，所以，
instance 实例的创建是线程安全的。不过，这样的实现方式不支持延迟加载实例。
- 懒汉式  
懒汉式相对于饿汉式的优势是支持延迟加载。这种实现方式会导致频繁加锁、释放锁，以及
并发度低等问题，频繁的调用会产生性能瓶颈。
- 双重检测  
双重检测实现方式既支持延迟加载、又支持高并发的单例实现方式。只要 instance 被创建之后，再调用 getInstance() 函数都不会进入到加锁逻辑中。所以，这种实现方式解决了懒汉式并发度低的问题。
- 静态内部类  
利用 Java 的静态内部类来实现单例。这种实现方式，既支持延迟加载，也支持高并发，实现起来也比双重检测简单。
- 枚举  
最简单的实现方式，基于枚举类型的单例实现。这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
## 单例模式的定义
我们重新看一下单例的定义：“一个类只允许创建唯一一个对象（或者实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式，简称单例模式。”
### 如何理解单例模式的唯一性
单例类中对象的唯一性的作用范围是进程内的，在进程间是不唯一的。  
实际上，对于 Java 语言来说，单例类对象的唯一性的作用范围并非进程，而是类加载器（Class Loader）。
### 如何实现一个多例设计模式
“单例”指的是，一个类只能创建一个对象。对应地，“多例”指的就是，一个类可以创建多个对象，但是个数是有限制的，比如只能创建 3 个对象。