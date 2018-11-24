![计算机生成了可选文字: 小张；开发一个大型系统， 、System.out.println("")，将矢漣数打在控制台；去埠？写旺一个文件？ 2、框架来记录系统的一些运行时亻g息；日志框架；zhanglogging.jar， 3、高大上的几个功能？异步模式？自动0栏？××“？zhanglogging-good.jar？ 4、将以前框架卸下耒？换上新的框架，0新修改之前相关的AP《；zhanglogging-prefect.jar， 5、」DBC一数库驱动； 写了一个一的接口层；日志冂面（日志的一个油象层）；logging-abstract-jar； 给项目中导入興体的日志实现就行了：我们之前的日志框架都是实现的抽象层．](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png)

 

市面上的日志框架：

Jul,jcl,log4j,log4j2，slf4j等等 

日志门面：JCL,SLF4J,jboss-logging等。

日志实现：log4j，JUL,Log4j2等等。

Spring boot底层是Spring，Spring默认用JCL，而SpringBoot默认用SLF4J和LogBack

 

## SLF4j的使用

给系统中导入SLF4j和Logback的jar包，然后：

Logger logger = loggerFactory.getLogger(this.class);

Logger.info("Hello world");

 

 

![计算机生成了可选文字: unbound SLF4J /&v/null A SLF4Jboundto logback•classi SLF4J． bgback-classrcpr b“伽e AIn， software B a妇e -SLF4Jbrxlingdacta№clas»at' abstract 100冫ngapt We0nent》 Of引垧．a](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

 

每一个日志的实现框架都有自己的配置文件，使用slf4j之后，配置文件还是要使用实现框架的配置文件。

 

## 遗留问题

如果我们现在使用了多种框架，每个框架内部都有默认使用的日志框架，例如Spring默认使用Commons-logging,Hibernate默认使用jboss-logging，那么如何统一日志记录呢？即便是别的框架，一起统一使用SLF4J

 

![计算机生成了可选文字: SLF4Jboundtologback-classicwith redirectionOfcommons-logging，丨og andjava.util.loggingtoSLF4J Canmms loggingAR ]凶41A util《凶g 灿40．匆 SLF4JAR franewort ·丿．““．5-replacesgj ·SLF4JBnndgeHanderisinstalled在“e4万ar) SLF4Jboundtojava.util.loggtngwith](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image003.png)

可以利用图示的几个转换包来替换掉原来的包，引入同名类，但是实现的是基于SLF4J的功能。

 

## SprintBoot的日志关系

SpringBoot中的日志依赖使用Spring-starter-logging（是借助于boot-starter的来导入的），它又导入了这几个包：

 

 

![计算机生成了可选文字: 吧苤飽日志抟为诅t4.i 导入了日患捲层诅以](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

 

 

可见SpringBoot底层使用slf4j+logback方式实现的日志。同时也把其他的日志都替换成了SLF4J（通过导入很多替换包）

 

如果我们在SpringBoot中要引入其他框架，那么一定要移除掉这个框架的默认日志依赖。包括SpringBoot对Spring的默认依赖，都是采取了移除的策略：

 

![计算机生成了可选文字: (dependency) ingf00e“〈/groupld> <artifactld>spring•core</artifactld> 〈0On生> 孓0《1u0飞on> <groupld>commons-logging</groupld> (artifactld>co—ons•logging</artifactld> </excluon> 〈/0涎《1u0On苤> </dependency>](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image005.png)

 

 

SpringBoot默认已经帮我们配置好了日志，使用时只需要

Logger logger = LoggerFactory.getLogger(getClass());

Logger.trace("trace级别");

Logger.debug();

Logger.info();

warn和error

SpringBoot默认的日志级别是info级别的。

可用的log配置：

Logging.level:为某个包指定日志级别

Logging.path：指定日志输出路径

Logging.file：指定日志输出文件名

Logging.pattern：指定输出格式

 

![计算机生成了可选文字: 《egg讠”g．尹0亡h： #不指定路径在当前項目下生成”以四。．四日志 #可以指定完整的径《 #0”0．f讠'e=0：pr讠0b00t．t00 #在当前磁盘的根路径下创建”四文件夹和里面的《四文钭夹；使用”四。7作为默认文件 logging•path=/spr•ing/iog #在控台输出的日志的格式 10gnpattern．consol••X•d{yyyy-mm-dd}〔玳h“〕s10v01、10艹{SO}-。 。指宁文钭中日志辑出的格式 logging•pattern，file•Sd(yyyy•M•dd}==〔玳hr•ad〕==、-s10v01==logger{5ø}](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image006.png)

 

在springboot的jar包下logging中，为不同的日志实现已经配置好了默认的各项配置，保存在base.xml中，而如果我们想自己写一个日志配置文件，可以在类路径中放入各个实现框架的配置文件，比如logback就是logback-spring.xml或者logback.xml,这样SpringBoot就不会使用它默认配置的了。

Logback-spring.xml为名字的配置文件由SpringBoot加载，可以使用SpringProfile功能，既某段配置在某环境下生效。

如果使用logback.xml的名字，这个文件将会直接被日志框架识别，就不能使用profile了。

 

![ss](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image007.png)

 

 

### 如何切换日志框架

需要在依赖关系中，加入slf4j-log4j的依赖，然后去除掉log4j转slf4j的依赖

之后只需要把log4j的配置文件放到类目录下，就能自动以log4j输出了。

 