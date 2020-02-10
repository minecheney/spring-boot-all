##过滤器和拦截器

* 相似之处:

Spring的Interceptor于Servlet的Filtery, 比如二者都是AOP编程思想的体现，都能实现权限检查、日志记录等
* 不同的是:
    * 使用范围不同：【Filter是Servlet规范固定的，只能用于Web程序中】，而【拦截器既可以用户Web程序，也可以用于Application、Swing程序中】。
    * 规范不同：Filter是在Servlet规范中定义的，是【Servlet容器支持】的，而拦截器是在Spring容器内的，是【Spring框架支持】的。
    * 使用的资源不同：同其他的代码块一样，拦截器也是一个Spring的组件，归Spring管理，配置在Spring文件中，因此能使用Spring里得任何资源、对象，例如Service对象、数据源、事务管理等，通过Ioc注入道拦截器即可。而Filter则不能。
    * 深度不同：【Filter在只在Servlet启动前后起作用。而拦截器能够深入到方法前后、异常抛出前后等】，因此拦截器的使用弹性更大一些，所以在使用Spring架构的程序中，要优先使用拦截器。
    
1. 过滤器和拦截器触发时机不一样，过滤器是在【请求进入容器后，但请求进入servlet之前】进行预处理的。请求结束返回也是，是【在servlet处理完后，返回给前端之前】。  
如图：  
![Stargazers over time](../assets\filter_interceptor.jpg)
2. 拦截器可以获取IOC容器中的各个bean，而过滤器就不行，因为拦截器是spring提供并管理的，spring的功能可以被拦截器使用，在拦截器里注入一个service，可以调用业务逻辑。而过滤器是JavaEE标准，只需依赖servlet api ，不需要依赖spring。  
过滤器拦截器运行先后步骤：
![Stargazers over time](../assets\aaa.jpg)  
其中第2步，SpringMVC的机制是由DispaterServlet来分发请求给不同的Controller，其实这一步是在Servlet的service()方法中执行的.
3. 过滤器的实现基于回调函数。而【拦截器（代理模式）的实现基于反射】，代理分静态代理和动态代理，动态代理是拦截器的简单实现。
* 何时使用拦截器？何时使用过滤器？
    * 如果是非spring项目，那么拦截器不能用，只能使用过滤器。
    * 如果是处理controller前后，既可以使用拦截器也可以使用过滤器。
    * 如果是处理dispaterServlet前后，只能使用过滤器