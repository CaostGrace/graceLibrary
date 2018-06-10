# graceLibrary
自己搭建的一个便捷的软件架构

#### 使用依赖
>  在根项目build.gradle的allprojects -->  repositories 中添加： maven { url "https://jitpack.io" }

>  在项目的build.gradle的dependencies中添加：implementation 'com.github.CaostGrace:graceLibrary:1.1.1'

#### 使用注意
>  项目需要支持lambda表达式，也就是说jdk版本需要为1.8

>  如果要使用自带的mvp模式，项目的Application需要继承依赖的ApplicationLibrary重写getBaseUrl方法或者重写BaseModel的getHttpBaseUrl方法
