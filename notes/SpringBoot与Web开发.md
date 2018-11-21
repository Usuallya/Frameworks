## Spring Boot对静态资源的映射规则

映射规则写在WebMVCAutoConfiguration类中

``` java
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
           if (!this.resourceProperties.isAddMappings()) {
               logger.debug("Default resource handling disabled");
          } else {
            Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
               CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();

               if (!registry.hasMappingForPattern("/webjars/**")) {                  this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
               }
              String staticPathPattern = this.mvcProperties.getStaticPathPattern();
               if (!registry.hasMappingForPattern(staticPathPattern)) {
                  this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
              }
          }
       }
```

- 规则1：所有/webjars/**，都去classpath:/META-INF/resources/webjars/下找资源

webjars是指以jar包方式引入的静态资源。例如通过webjars导入jquery，前端将会访问

Localhost:8080/webjars/jquery/3.3.1/jquery.js，那么它将会被映射到classpath下的路径。

- 规则2：访问当前项目的任何资源，都会从这几个路径去找内容：

   Class:/META-INF/resources

  Classpath:/resources/（也就是编译器的resource下再新建一个resources）

  Classpath:/static/

  Classpath:/public/

  /（当前项目根目录）

![计算机生成了可选文字: this.staticPathPattern](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png)



 

- 规则3：欢迎页的映射，是静态资源文件夹下的所有index.html页面，被"/**"映射。

例如localhost:8080 就会找index.html页面

 

- 规则4：要指定页面的图标，静态文件夹下放一个**/favicon.ico 

 

 

## 模板引擎Thymeleaf

类似于JSP，Thymeleaf也是一种模板引擎

![计算机生成了可选文字: 10n0a《e HelloS(user) </htm卜 Data model.addAttribute( TemplateEngine <html> №《zhangsan](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image002.png)

 

SpringBoot推荐的Thymleaf：

语法更简单，功能更强大。

### 引入Thymleaf

```
<dependency>
<groupId>org.thymeleaf</groupId>
<artifactId>thymeleaf-spring5</artifactId>
</dependency>
<dependency>
<groupId>org.thymeleaf.extras</groupId>
<artifactId>thymeleaf-extras-java8time</artifactId>
</dependency>
```

 

### Thymeleaf语法、使用Thymeleaf

 

![计算机生成了可选文字: @configuratåonproperties(prefix -spring.thymeleaf-） publlc（1a5，ThyeIeafProperties{ private‰tfinal（har畎DEFAULT_ENCOOING-（№et，for•Nane(．8．); privatestaticfinalminerypeDEFAULT_C(NTENT_TYPE·NineType.valueof(-text/html-); publlcstaticStringDEFAULT_PREFIX--classpath：iteepiates/- pubi跹tt《（f《鬩1StringDEFAULT_SUFFIX /／只0拢们吧HT臌页面放在cla”path：/te•plates/。t leafhr zi'donl l.自 2．东 3．字都能4．自动能 5．](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image003.png)

通过查看它的属性类，就能知道，只要把html页面放在classpath:/templates/，thymeleaf就能帮我们自动渲染页面。

 

#### Thymeleaf语法

1. th标签

可以通过导入xmlns:th来导入语法提示

给标签加入 th:text=${hello},就能取出域对象中的hello键对应的值。

```
<!DOCTYPEhtml>
<htmllang="en"xmlns:th="http://www.thymeleaf.org">
<head>
<metacharset="UTF-8">
<title>Thymeleaf渲染页面</title>
</head>
<body>
```



``` html
<p>Thymeleaf渲染页面</p>
`<div th:id="div1"th:class="111"th:text="${hello}">显示欢迎信息</div>`
`</body>`
`</html>`
```

th命名空间中的标签实际上是具有动态替换功能，它能将原来标签中的静态属性值替换为指定值。

在官网文档中，说明了th标签以及他们的优先级。

 

![计算机生成了可选文字: Feature Fragmentinclusion Fragmentiteration 片段包含，jsp:include 谝历《c:forEach 2 3 4 5 6 7 8 Conditionalevaluation 条判断：c:if Localvariabledefinition声明变量：C还忒 Generalattributemodification任意属性修改 ·append Specificattributemodification Text(tagbodymodification) Fragmentspecification 修改指定属忖默认 改标签体内容 声明片段 Attributes th：insert th：replace th：Suitch th：℃a、e th:object th:with th：attr th：attrprepend th：attrappend th：value th：href th：SrC 转义特殊字符 th：text th：utext 不转义特殊字符 th：fragænt](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)

 

1. 表达式语法

 

![计算机生成了可选文字: Si唧expressions：（达式还） VariableExpressions：．．．}：量喳 1）取对象的属性“用方 2）．用内置的基苓对。 （t泯：theContextObject、 。OGNL： •vars：thecontextV孬ri以bl酽、． #10（以le：the（00t起，t10（ale· (onlyinWebContexts)theHttpServletRequestObject， 'request *response：(onlyHebContexts，theHttøSem'1etResponseobject． 5e551！〈onlyHebContexts）the卜《ttpSessionObject． •servletContext：(onlyinKeb〔ontet$）theServletContextObject． S{session.f00} I SelectionVariable£e5500鱿 MessageEprs飞on鱿叭． LinkURLExpressions：@{． 19 2B FragmentEp$$§00，： Literals text1飞tralS：。0text。 •Anotherone，“](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image005.png)

 

![计算机生成了可选文字: 3）、丙置的一些工鼻对象“ •execlnfo：Infor•ationaboutthetenplatebeingprocessed. 0，、“8《：th叼、forobt《n§external§：对d、，age、！n、1d0variablese跹p$on 、a艹way‰theyI wouldbeobtainedusing叭一}syntax. *uris；methodsfo「escaping00rt0OfURLs/URIs 《on艹r510n：methodsforexecutingthe〔on钅igur（0“e0510nservice（any)． •dates：methodsforjava-util.OateObjects:fornattång,CO—ponentextraction,etc 冒（aldr$：an01080u、t00d0t0、·butforjava.utii.Calendar0b〕（t、． 'numbers：nethodsf00for—attingnumericobj以：$． *strings：nethodsforStringObjects：contains.startsWith,prepending/appending. #0“ts：到thodsf00objectsingeneral. b001s：methodsorbooleanevaluation· •arrays：nett•odsf00arrays. ！st：艹th，钅or11，t •sets：method、for、et、· #0孬p：methods€0「naps· *aggregates；methodsforcreatingaggregatesonarraysor《011e《tion，， *ids：methodsfordealingwithattributesthatmight№repeated〈fore0p10 resultOfaniterat《on苏 1以t§00VariableEx丆@各各§00$：·{． №以geE又preion‰：叭， the](file:///C:/Users/王海奇/AppData/Local/Temp/msohtmlclip1/01/clip_image006.png)

 

## SpringBoot对SpringMVC所做的自动配置

 28.1.1 Spring MVC Auto-configuration

Spring Boot provides auto-configuration for Spring MVC that works well with most applications.

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  ==自动配置了viewResolver，根据方法的返回值得到视图对象，视图对象决定如何渲染==

  ==ContentNegotiatingViewResolver:组合所有的视图解析器==

- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-static-content))). ==静态资源==

- Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans. ==自动注册了转换器，格式化器==

- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-message-converters)). ==消息转换器，默认加了json,xml这些的，是从容器中确定的，从容器中获取所有的HttpMessageConverter，如果自己要给容器中添加，只需要写好组件放到容器中就行了。==

- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-spring-message-codes)).

- Static `index.html` support. ==静态首页访问==

- Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-favicon)).

- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-web-binding-initializer)). ==

If you want to keep Spring Boot MVC features and you want to add additional [MVC configuration](https://docs.spring.io/spring/docs/5.1.2.RELEASE/spring-framework-reference/web.html#mvc)(interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`. If you wish to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or`ExceptionHandlerExceptionResolver`, you can declare a `WebMvcRegistrationsAdapter` instance to provide such components.

If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.

**所有的自动配置，在springboot-framework-boot-web-autoconfig中**



### 扩展自动配置

就像文档中说的，要添加一些扩展功能，比如拦截器。我们只需要写一个配置类(@Configuration)，实现WebMvcConfigurer接口，但是不能有EnableWebMVC注解。这个接口中有很多之前springmvc中可以配置的功能写成了空方法，我们要扩展哪个，就去覆盖哪个方法就可以了。例如，下面就扩展了一个视图映射

``` java
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
 public void addViewControllers(ViewControllerRegistry registry) {    registry.addViewController("/nothing").setViewName("ok");
    }
}
```

原理：

1. WebMvcAutoConfiguration是Spring MVC的自动配置类

2. 在做其他配置时会自动导入：@Import(EnableWebMvcConfiguration.class)

3. 容器中所有的WebMvcConfigurer都会起作用，我们的扩展配置也会被调用。

   ``` java
   @Configuration
   public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
       
   }
   
   
   //父类中有这么一段：
       @Autowired(
           required = false
       )
   //从容器中获取所有的webmvcConfiger，这就包含Springboot的和我们自己写的。
       public void setConfigurers(List<WebMvcConfigurer> configurers) {
           if (!CollectionUtils.isEmpty(configurers)) {
               this.configurers.addWebMvcConfigurers(configurers);
           }
       }
   
   //同样在该类中,有多个之前adapter中对应的功能模块方法，例如：
   protected void addViewControllers(ViewControllerRegistry registry) {
       //这里调用的都是configurers的addViewControllers方法，注意复数。。
           this.configurers.addViewControllers(registry);
   }
   //它内部实际上是把容器中所有的webmvcConfiger都拿过来，把他们的addview都执行一遍。
   public void addViewControllers(ViewControllerRegistry registry) {
       Iterator var2 = this.delegates.iterator();
   
       while(var2.hasNext()) {
           WebMvcConfigurer delegate = (WebMvcConfigurer)var2.next();
           delegate.addViewControllers(registry);
       }
   
   }
   ```

### 例子：定制我们的视图解析器

我们只需要自己给容器中加一个视图解析器，ContentNegotiatingViewResolver就会自动将它组合进来。

```java
@Bean
public ViewResolvere myViewResolver(){
    return new MybiewResolver();
}
private static class MyViewResolver implements ViewResolver{
    //实现视图解析器方法
}
```

这里自己实现的解析器也将直接被组合到SpringMVC中去。

![1542802999816](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542802999816.png)



### 全面接管Spring MVC

也就是完全摒弃Spring Boot对SpinrgMVC的自动配置，所有的都是我们自己配。 只需要在我们自己的配置类上加上EnableWebMvc就可以了。（也就是上面扩展的时候不能加的那个）

原理 ：为什么加这么个注解，自动配置就失效了：

```java
@Import({DelegatingWebMvcConfiguration.class})
public @interface EnableWebMvc {
```

EnableWebMvc的核心是上述代码，引入的这个类就是上面扩展时那个父类

``` java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
```

再回头看WebMvcAutoConfiguration类的签名

```java
@Configuration
@ConditionalOnWebApplication(
    type = Type.SERVLET
)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})

/*注意这句，条件配置，要求容器中没有webMvcConfigurationSupport时，autoConfiguration才起效。而上面已经有它的子类了。所以判断当前类失效。而这个support只是SpringMVC最基本的功能。*/
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@AutoConfigureAfter({DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
public class WebMvcAutoConfiguration {
```

## 总结：如何修改SpringBoot的默认配置

1. SpringBoot 自动配置很多组建的时候，都是先看容器中有没有用户自己配置的，如果没有才自动配置，有的话就用用户的。有些组件可以有多个配置，能够将自动配置和用户的结合起来。
2. 在SpringBoot中，会有非常多的XXXConfigurer帮助我们进行扩展配置

