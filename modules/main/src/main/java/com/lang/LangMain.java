package com.lang;

/**
 * 多态
 * 对于引用类型的变量,编译器编译时按照它申明的类型处理,运行时按照它实际引用的对象处理.
 * 在运行时,通过引用类型的变量访问方法和属性时,有以下规则:
 *     1,实例方法与引用变量实际引用的对象方法绑定
 *     2,静态方法和静态变量与引用变量所申明的类型的静态方法和静态变量绑定
 *     3,成员变量与引用变量所申明的类型的成员变量绑定(虽然规则是这样定的,但搞不懂为啥这样定,有违多态本身的定义)
 */
public class LangMain {

    public static void main(String args []) {
        Base b=new Sub();
        System.out.println(b.var); //为啥是返回父类的变量值,搞不懂,虽然规则是这样定的,但搞不懂为啥这样定,不科学
        b.md();
    }
}
