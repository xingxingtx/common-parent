###创建型设计模式
#####1.1简单工厂（Simple Factory）
要点：是指一个工厂对象决定创建出那一种产品类的实例，适用于工厂类负责创建的对象较少的场景。
    参与者：
    1.1、抽象接口
         定义顶层接口规范
    1.2、具体实例类实现抽象接口
    1.3、工厂类负责创建具体实现者
    1.4、源码中应用
       Calendar类
       public static Calendar getInstance(TimeZone zone,
                                              Locale aLocale)
           {
               return createCalendar(zone, aLocale);
           }
       
           private static Calendar createCalendar(TimeZone zone,
                                                  Locale aLocale)
           {
               CalendarProvider provider =
                   LocaleProviderAdapter.getAdapter(CalendarProvider.class, aLocale)
                                        .getCalendarProvider();
               if (provider != null) {
                   try {
                       return provider.getInstance(zone, aLocale);
                   } catch (IllegalArgumentException iae) {
                       // fall back to the default instantiation
                   }
               }
#####1.2工厂方法（Factory Method）
要点：定义一个创建对象的接口，让子类决定实例化那个类
        参与者：
        1.1、product 
            定义工厂方法
        1.2、concreteProduct 
            实现product接口
        1.3、creator
            声明工厂方法，返回一个product类型对象
        1.4、concreteCreator 
            重新定义工厂方法已返回一个concreteProduct实例
        1.5、源码中应用
             ILoggerFactory
#####2.抽象工厂（AbstractFactory）
要点：创建相关或依赖对象的家族，而无需明确指定具体类。
        2.1、AbstractFactory
            声明一个创建抽象产品对象的操作接口 
        2.2、ConcreteFactory
            实现创建具体产品对象的操作
        2.3、AbstractProduct 
            为一类产品对象声明一个接口
        2.4、ConcreteFactory
            定义一个将被相应的具体工厂创建的产品对象
            实现AbstractProduct接口
        2.5、 Client
           仅仅使用由AbstractFactory和AbstractProduct类声明的接口
#####3.建造者模式（Builder）
要点：封装一个复杂对象的构建过程，并可以按步骤构造。
        3.1、builder
            为创建一个product对象的各个部件指定抽象接口
        3.2、ConcreteBuilder 
            实现builder的接口以构造和和装配该产品的各个部件
        3.3、Director
            构造一个使用builder接口的对象
        3.4、product
            表示被构造的复杂对象
#####4.单例模式（Singleton）
        要点：某个类只能有一个实例，提供一个全局的访问点
        懒汉和饿汉模式，枚举实例
#####5.原型模式（Prototype）
要点：通过复制现有的实例来创建新的实例,
深克隆：不仅克隆了当前对象，还把对象的应用对象都克隆了。
浅克隆：仅仅克隆当前对象。
    