

Spring Boot能够独立于tomcat实现部署，直接打包成jar包即可发布（内嵌tomcat）

要启动Spring Boot应用，只需要首先导入maven依赖（各种启动器），以SpringBootApplication类作为主类运行一个main方法，然后以普通jar的形式运行或者发布即可。

 

## Hello word探究（POM文件）

1、父项目做了版本仲裁，这样我们之后引入的依赖不需要写明版本

2、导入的依赖，如spring-boot-starter-web是spring-boot场景启动器，帮我们导入了web模块正常运行所依赖的组件。Spring boot相当于把所有功能场景都抽取出来做成了starter（启动器），我们只需要在项目中引入它们，那么相关场景所需的依赖都会导入进来。

 

SpringBootApplication注解：说明一个类是SpringBoot的主配置类，SpringBoot应该运行这个类的main方法。来启动应用。

这个注解实际上是一个组合注解：

 

![计算机生成了可选文字: 还 1e @Target(ElenentType.TYPE) @Retention(RetentionPollcy、RUNTIME) @！n№t @SpringBootConfiguration @EnableAutoConfiguration @Co•ponentScan(excludeFilters“{ 飞Iter(type-FilterType.（巧T， @:FIIter(type“FilterType.（T publ跹'interfaceSpr攵900tpli（ion -TypeExcludeFllter.（1a55), “to（onflgutionE〈ludeF！！t@的（！孬《各）}）](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png)

 

包含了Springbootconfiguration：是springboot的配置类注解。

注意：配置类本身也是容器中的一个component

 

@enableAutoConfiguration：开启自动配置功能。它也是个组合注解，相当于autoconfigurationpackage和import的组合。它们都给容器中导入了组件。它的内部实现相当于将主配置类（springbootapplication）所在的包下所有子包的组件都扫描到容器中。此外，还会给容器中导入非常多的自动配置类比如springapplicationAutoConfiguration这种。有了自动配置类，免去了手动编写配置和注入功能组件的工作。

Spring Boot启动时，从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作。

 

webmvc的自动配置类：

![计算机生成了可选文字: 》0}痣0（。n艹》“b》@W，6M“№to（。，不9“0'》·《OWdM№p00，0 卸四'i“吆印'峋．№叶呷。“'每“·巧．鱈段糨讎和叭．№@噼f粼概以×《W'0M“t。（。補9“， econditionalOn•Missing8ean(We%cConfigurationSupport。'1000 @AutOConfigureOr•der•(Ordered.HINEST_PRECEDENCE·10） @AutoConfigureAfter、（{DispatcherServletAutoConfiguration.《10， ValidationAutoConfiguration．《10苤．}） public100pebmvcAutoConfiguration{ publ《staticn01String0下F乙P下F了X pbi飞statn01StringDEFAULT_SVFFIX。 OndtionalOnMissiIt·10） publicOrderedHiddenHttpNethodFilterhiddenHttpMethodFilter(）{returnnewOrderedHid @ConditOnal） @ConditionalOnProperty(prefix “0n0b1“ publicOrderedHttpPutFornContentFi1terhttpPutFormContentFilter(){returnnewOrde //Deft”ed0过0”《ted'0”figto”思u'0紂'bv'Co”f讠gu'cr00Pt0'讠孬”o*'《ad“伽0””of ／／00亡he苤ath econfiguration @lmport(Enable*ebMvcConfiguration.100 @Enab1eConfigurationProperte、（{WebMvcProperties·100》ResourceProperties．10爹}） public亡亡10能WebmvcAutoConfigurationAdapter0end能WebMvcConfigurerAdapter{ 玳at《f攴n01Log'=LogFactory •getLog(WebMvcConfigurerAdapter.1）； 0英」 、地by“如戊0（0。匆““》 0](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

 

Spring Boot帮我们实现了java ee的整体解决方案和自动配置，都在springboot-autoconfigure里。



 

 

 

 