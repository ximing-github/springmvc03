mvc知识点测试
    1.数据库表的准备
    2.创建实体类
    3.加入依赖
    4.创建servic和dao
        dao层中的namespace 路径要写到具体的接口（不是包名）
    5.创建controller控制器类
    6.创建配置文件
        mybatis
            关联dao包，实体类重命名（简写）
        properties
            设置jdbc连接数据库需要的url 用户名 密码
        spring
            目的是管理service和dao对象

        springmvc
            声明控制器controller实体类所在的包
            声明视图解析器
            声明注解驱动（选择mvc结尾）
        web.xml
            配置中央调度器（servlet）
            设置字符集过滤器
            注册监听器 在启动服务器时创建spring容器

    7.编写前端页面jsp
    8.  控制器方法的四种返回值类型
       第一种：ModelAndView     传递数据并跳转页面
       第二种：String           以字符串的方式返回视图名称 不处理数据只跳转视图  请求数据仍然以请求参数的方式传递
       第三种：无返回值void       用于响应ajax
       第四种：返回Object  用于响应ajax（常用）  不跳转视图 响应数据为json格式 需要jackson依赖两个 需要引入jquery
                Object 可以是任意类型 自定义的 集合 String（用于响应ajax时有注解，区别于响应视图字符串）
                返回值为string时
                        （不以json格式返回）需要在注解 @RequestMapping 中设置属性值（produces）设置字符编码方式解决中文乱码
                                ajax请求中的datatype要注释掉
            加入json
            加入依赖
            mvc配置文件中声明注解驱动（用于创建消息转换器对象） 以mvc结尾的约束文件

    10.使用mvc控制所有资源（静态资源与动态资源）
        第一种方式
            依赖于tomcat服务器 将静态资源转发给tomcat服务器的default servlet
            需要在mvc配置文件中加入
                注解驱动  <mvc:annotation-driven/> 和声明静态资源转发<mvc:default-servlet-handler />
        第二种方式
            使用mvc自身的静态资源处理servlet
                在mvc配置文件中声明静态资源处理器和注解驱动
                    <mvc:annotation-driven/>
                    <mvc:resources mapping="请求路径通配符" location="资源路径通配符"/>
    注意事项
        当请求参数名与方法中的形参名不一致时，可以使用注解校正

        当传递的参数有多个且没有合适的javabean时 如何接收参数
            1.以request接收 存在list或者map集合中

        maven工程中新增依赖要刷新maven

        当请求使用相对路径 且文件不再根目录下时 需要使用base标签

        关闭项目--->unload

        mvc中处理器可以直接调用service 不需要先拿到spring容器（spring容器的创建配置在监听器中）

        SpringMVC容器是Spring容器的子容器 Controller可以调用service对象

        shift+字母可以直接输入大写字母，不需要切换

       在springmvc中 @Resource注解的使用具有局限，优先使用 @Autowired





    bug及解决
        ajax请求无响应
            实际原因        路径错误
            错误产生原因     jsp文件不在根目录下 请求路径为相对路径没有以 "/" 开头
            解决方式        加入base标签  导致js文件的引入路径冲突
                            解决 在引入路径前以el表达式加上项目名
        消息转换器找不到类
            mvc注解驱动文件有多个 本项目中应使用以mvc结尾

        返回值为String（数据）时无法显示
            错误原因   字符编码格式转换冲突
            解决       将ajax请求中的dataType属性注释掉


================================================================================================
    springmvc中处理器方法返回视图默认是转发
        可以使用forward与redirect关键字实现转发和重定向
        setViewName("forward:资源路径") 不匹配视图解析器 即需要完整的资源路径
        setViewName("redirect:资源路径") 不匹配视图解析器  不能访问web-inf文件下的资源

================================================================================================
    springmvc异常统一处理
        创建异常处理类 普通类（作为异常处理器）
          类名需要 @ControllerAdvice
          方法需要 @ExceptionHandler(value = Exception.class)
        mvc配置文件中创建扫描器 扫描异常处理类所在的包

================================================================================================
    springmvc拦截器的使用（与过滤器功能相似） 做登录验证（拦截非法访问资源的请求）
        创建自定义拦截器类实现拦截器接口handlerInterceptor
            default 修饰接口方法  则实现类可以不用实现接口中的每个方法  需要用到哪个就实现哪个
        重写其中的三个方法
            在处理器之前执行
            在处理器之后执行
            最终执行（类似于finally，一般用于释放资源）
        在mvc配置文件中注册拦截器 设置拦截器的作用路径和拦截器类

        拦截器执行链 执行顺序 在配置文件中先声明的拦截器先执行，方法是否执行

        拦截器与过滤器


