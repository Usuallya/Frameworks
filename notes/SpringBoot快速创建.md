- 默认生成的Spring  Boot：

- resource文件夹目录结构：

- 1、static:保存所有的静态资源，例如js css image

- 2、templates：保存所有的末班页面(freemarker，thymeleaf等）

- 3、application.properties 全局配置文件

- 例如：改变服务器端口号：

- Server.port=8081

-  

- ## Spring Boot的配置

- ### 配置文件

- Application.properties和application.yml两种格式的文件。配置文件名称是固定的。Spring Boot在底层为我们把组件都自动配置好了，如果我们需要修改什么配置，就写在这里。

-  

- YAML Ain't Markup Language 是/不是一个标记语言，比json和xml更适合作为配置文件。配置实例：

- ``` yml
  Server:
  	Post:8081
  ```

- 

- 

- 如果是同样的配置在XML中，需要：

- ``` xml
  <server>
  <port>8081</port>
  </server>
  ```

- #### YAML的语法，properties的语法略

- 利用 [k:空格v:空格值] 来表示一对键值对。以空格的缩进来控制层级关系。只要是左对齐的一列数据都是同一层级的。其中的属性和值都是大小写敏感的。

-  

- 值的写法：普通的值（字面量，其中字符串默认不用加引号，如果加了双引号，那么表示不会转义字符串中的特殊字符，比如\n这种，就会直接给你换个行。单引号则会转义特殊字符，特殊字符将作为一个普通字符输出出来）、对象和map（写出key之后，直接按照对象中的属性对应关系来配置。

- 例如 friends: 

- lastName:zhangsan

- Age:20

- 或者行内写法：friends:  {lastName: zhangsan,age: 10}），数组（List,set）（注意，需要使用-来表示数组中的一个元素。比如：

- Pets:

- -cat

- -dog

- -pig

- 行内写法是用[]）。

-  

- ![问题](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png) 如何将配置文件中的引入到我们的domain中呢（YML和Properties都可以）？

- 只需要在类上方使用ConfigurationProperties注解，指明prefix，就能将配置文件中的属性和本类中的属性进行绑定。（注意，必须是容器管理的组件，才可以这样用，所以还需要加一个Component注解。）在这样使用的时候，还需要导入一个springboot的配置文件处理器依赖。配置完成后，可以使用springboot单元测试来进行绑定的测试。

-  

- 除了使用@ConfigurationProperties注解，还可以使用Spring提供的@Value注解。它支持字面量、或者以${key}从环境变量中获取值，以及#{spEL}是spring表达式。但是它不支持复杂类型封装（比如某个变量是一个对象，就不能获取这个对象的属性值）

-  

- 可以使用@Validate注解，来说明对当前类的属性需要校验，例如给某个属性加上@Email，就会按照Emal的要求来校验。但这个注解只支持ConfigurationProperties。

-  

- 如果只是在某个业务逻辑中需要获取一下某项值，使用@Value

- 如果专门写了个JavaBean来和配置文件映射，那么使用ConfigurationProperties

-  

- @PropertySource和@ImportResource：@PropertySource的作用是绑定指定的配置文件，上面的两种配置都是默认从全局配置文件中获取值的，而这俩可以从其他文件中获取。

- @ImportResource的作用是导入额外的Spring的配置文件，让配置文件生效。

-  

- ### SprintBoot推荐的给容器中加组件的方式

- 不编写spring配置文件，而是采用全注解的方式。可以专门写一个配置类（加configuration注解），通过@Bean注解来添加组件。（但是这种方式和直接注Component有啥区别？？？？）

-  

- ### 配置文件占位符

- 例子：

- ``` properties
  //产生随机数
  Person.age  = ${random.int}
  App.name=Myapp
  App.description  = ${app.name} is a spring boot application
  ```

- ### 配置文件多环境支持（profile）

- 多profile文件形式：

- 配置文件可以加上环境标识：

- Application-{profile}.properties/yml

- 然后在主配置文件中通过spring.acitve来制定激活的文件

-  

- 或者使用yml文件中的文档块模式（在chapter4文件中有）

- 或者可以在idea的配置中，相当于以命令行的方式，来指定激活的配置文件:

- --spring-profiles.active=

-  

- 还有一种虚拟机参数的办法，也就是VM options，可以再idea中指定：

- -D  Spring-profiles-active=

-  

- ### 配置文件的加载位置

- 优先级由高到低，高优先级中的配置将会生效。

- [file:/config/](file://config/)

- [file:./](file://./)

- Classpath:/config

- Classpath:/

- 低优先级文件依然会被加载，会形成互补配置。特别的，还可以通过命令行参数命令spring.config.localtion来改变配置文件位置。此外，Spring Boot还可以从以下位置加载配置：

-  

- ![计算机生成了可选文字: 七、外部配置加载川页序 SpringBoot支持多种外部配置方式 这些方式优先级如下 h NAPH reerence/tmSinge/#00eatures-external-confid p/env的JNDI属性 1， 2、 0 3． 4． 5. 6． 7、 9． 10． 11. 丿ava系统属性（System.getproperties()） 作系统环境变量 RandomValuePropertySourceUB9random.*ß性值 jaréJ9tBffiapplication-{profile).propert1esüapplication.yml(äspring.profile)&,m•件 -{profile).propertiesüapplication.yml(äspring.profilefr,m•件 jar包appICa《 jar包3ffiapplicati.propertiesüapplication.yml(不%spring.profile)&,m•件 .propertiesö%application.yrnl(不Gspring.profile)NÄ-•件 j訕包 @Configurationä%*上的@PropertySource MSpringApplicationsetDefaultProperties指定圈默认属性 0](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

-  

- 可见，运行时可以通过命令行参数来覆盖原配置。

-  

- ## Spring Boot的自动配置原理

- Spring Boot官方文档最后一章中介绍了可以application中配置的属性

- <https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#common-application-properties>

-  

- 1. 从SpringBoot启动时，加载启动类，其中注解SpringBootApplication开启了EnableAutoConfiguration自动配置功能
  2. 它的作用是利用EnableAutoConfigurationImportSelector给容器中导入一些组件（在selectImportors中）

- List<String> configurations =  getCandidateConfigurations(annotationMetadata,attributes);获取候选配置

- springFactoriesLoader.loadFactoryNames()是扫描所有jar包类路径下，META-INF/spring.factories文件

- 把扫描到的这些文件内容包装成properties对象，然后从properties中获取到EnableAutoConfiguration.class对应的值，将他们添加到容器中。 

-  

- ![计算机生成了可选文字: Maven：。四亠'“。““-2舂0 0Maven;Org-hibern•te:hib•rn•te•v•Sd•tor: M0丐i“9少g扣0“№93盞1 Maven：0r9“0Ck0喋0r0：L10．19 Maven;。．“：1．7 0Maven:0蜀刘．“ ，年Maven：。r909导。“《1.7莹5 Maven：0四嗎渊罐．。：1.7之5 0M以“：。四．即击“艹艹，k．。以p，汾 地Maven：“鲈p衔'“-'k．№。以p'汾 v，伊；四．boot．“0“·1，，，1 META•INF MANIFEST.MF >0org-springfrarne•aod-boot-•utoconfigu Maven：“9”毒'“艹，№。以p Maven：。四”击，艹艹。憔伊汾 地Maven：。四．”击'艹津艹'k．。憔p'汾 0能《501000 23 org.springfr»•work.boot。autoconfigure .condition·0nc10苤Ond飞t飞on #AutoCO”fgu～ 0·sprn'000'Ork.boot·autocre·Enable禹f氩=、 org．苤p'ng''000“'k·boot。·0n·SpringAppiieationAdmin'mxAu±oConftguration,\ org，springfr"•uork，boot，autoconfigure，00P“op（on，\ 0g．苤pngf00“·boot．auto《00f飞gu0．Eqp·，\ org.springfranework.bOOt．autoconfigure．batch·BatchAutOConfiguration，\ Org．springfranework.bOotauto《00fgure．loud·CloudAutoCOnfiguratiOn，\ or晷．springfram•work·boot．autoconfigure．context．ConfigurationProp•rti•sAutOConfiguration，\ org．spring'rameuork·boot00on'igure·ontet．0ageSou0（guaton，\ 0；·n'000'k.bOOt·autoconfigure·context·'0h01d0'（on，\ org·springfr"ework·boot·autoconfigure。《ouchbase·CouchbaseAu±oConfigura±ion，\ org.苤p'ngf'000“0'k.boot。autoconfigure。d00。PersistenceExceptionTransiationAutoConfiguration，\ 0．gfra0“rk.bOot，autoeonfigure“data“《0艹d0“（andraDa±u*onfguraton，\ org.springframework.bOOt· ·《0苤苤andra·（0000ndr0R0p0torAutoConf羡gu飞on，\ .data 0'·，p'ngf'000“'k·boot“autoconfigure。data“ouchba，0·CouchbaseOataAutoConfiguration，\ org.p''000“'k。autoconftgure ．ouhb000．（ouhba0R0p00to''Au0（on'u'on，\ or，springfrancuork.boo±，auto仁onfure -data“01000“h。E10c00rhufo（onfgura±on，\ 0g．p“.bOOt．autoeonfigure．data ·010t00h．E10．00hDataAutOCOnf，\ Or．springfram•work.b00tauto《onf飞卜ure．data·010ti《00h．E10生ti《000r《hRepos飞tor攵esAutO（onf攵下urat飞on 。p'ng''000“'k·boot00《on'gu'0。data“Spa。〕p•Repo．'0苤Auto（guon，\ org·p'nfr000“'k.boot·autoconfigure ·springfranework.boot。autoconfigure 。data·Idap.LdapRepoor0ufo（on'gur•ton，\ or卜．springfram•work·boot．autoconfigur•．data00n0．on000t0uto（onfuraton，\ 0．0rk··auto00f．data·00ng0·凹以0R0p00on org.p'nf'000“'k.bOOt，autou'0.data ，n0040，（onu'aton，\ org.springfranework。boot。autoconfigure 。data。n伫04j。N004jR0p0to'0ufo（on'gura±on，\ 30唱 GBK:](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image003.png)

- 也就是将类路径下META-INF/spring.factories里面配置的所有EnableAutoConfiguration的值加入到了容器中。

- 每一个这样的autoConfiguration类都是一个组件，他们用来做自动配置。

- 1. 每一个自动配置类进行自动配置功能，以HttpEncodingAutoConfiguration为例：

-  

- ![计算机生成了可选文字: -@Configuration @EnableConfigurationProperties（{HttpProperties.class}） @Conditiona10nWebApplication（ type=Type.SERVLET @Conditiona10nClass（{CharacterEncodingFiclass}） @ConditionalOnProperty（ spring.http.encoding prefix {"enabled"}, value matchlfMissing true pubIicc1assHttpEncodingAutoConfiguration{ privatefinalEncodingproperties pubIicHttpEncodingAutoConfiguration(HttpPropertiesproperties) @Bean @Conditiona10nMissingBean pubIicCharacterEncodingFiltercharacterEncodingFilter {this.properties properties.getEncoding()。](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

-  

-  

- 1. 所有在配置文件中能配置的属性都是在***Properties类中封装的。这个类通过ConfigurationProperties来读取Properties属性。

- Condition是Spring底层注解，根据不同的条件来生效配置。一旦这个配置类生效，就会给容器中添加各种组件，这些组件的属性是从对应的properties类中获取的。

- 1. 我们能配置的属性都是来源于这个功能的Properties类。

-  

- ### 配置类中的条件判断

- ConditionalOnMissingBean：容器中没有这个组件时配一个

- ConditionalOnWebApplication：当是webapp时生效

-  

- ![计算机生成了可选文字: @ConditionalT @ConditionalOnJava @ConditionalOnBean @ConditionalOnMissingBean @ConditionalOnExpression @ConditionalOnClass @ConditionalOnMissingClass @ConditionalOnSingleCandidate @ConditionalOnProperty @conditionalOnResource @ConditionalOnWebApplication @ConditionalOnNotWebApplication @ConditionalOnJndi 系2〗ava版不是百符台要求 宕器中存在指定Bean： 吝器甲不存在指定Bean： 屑足SPEL表达式指定 系甲有指定的类 系娩中没有定的类 容器中只与一个湛定的Bean，或者这个Bean是首Bean 甲指定的属性是雪有指定的值 类路径下生存在指定资源文件 当前是web环境 当前不是web环境 」ND|存在指定浈](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image005.png)

-  

- 他们实际都是由Spring底层的Conditional注解派生的。

- 既然自动配置类必须在一定条件下才能生效，我们怎么知道当前生效了哪些自动配置类呢？只需要在配置文件中加入debug = true 运行时控制台就会告诉我们哪些自动配置类生效了。