package com.hao.laker.study.spring.ioc;

/**
 * Created by haojiahong on 17/3/3.
 */
public class ResourceTest {
    public static void main(String[] args) {
        BeanFactory t = new BeanFactory();
        //文件名前不加“/”，则表示从当前类所在的包下查找该资源。如下则表示的是从包myspider下查找22.properties文件资源。
        System.out.println("1：" + t.getClass().getResourceAsStream("com/hao/laker/study/spring/ioc/config.xml"));//输出java.io.BufferedInputStream@61de33
        System.out.println("1：" + t.getClass().getResourceAsStream("22.properties"));//输出java.io.BufferedInputStream@61de33

        //文件名前加了“/”，则表示从类路径下也就是从classes文件夹下查找资源，如下表示从classes文件夹下查找22.properties文件资源。
        System.out.println("2：" + t.getClass().getResourceAsStream("/com/hao/laker/study/spring/ioc/config.xml"));//输出null

        //文件名前加了“/”，则表示从类路径下也就是从classes文件夹下查找资源，如下表示从classes文件夹下查找11.properties文件资源。
        System.out.println("3：" + t.getClass().getResourceAsStream("/com/hao/laker/study/spring/ioc/config.xml"));//输出java.io.BufferedInputStream@14318bb
        System.out.println();

        //当前包路径4：file:/E:/myobject/myspider/build/classes/myspider/
        System.out.println("4：" + t.getClass().getResource(""));

        //输出当前类路径5：file:/E:/myobject/myspider/build/classes/
        System.out.println("5：" + t.getClass().getResource("/"));

        /*
         * 如果类路径下的当前包有22.properties文件，则输出6：file:/E:/myobject/myspider/build/classes/myspider/22.properties
         * 否者输出源文件下的22.properties文件的路径，则输出：6：file:/E:/myobject/myspider/src/myspider/22.properties
         */
        System.out.println("6：" + t.getClass().getResource("com/hao/laker/study/spring/ioc/config.xml"));
        /*
         * 如果类路径下有11.properties文件，则输出7：file:/E:/myobject/myspider/build/classes/11.properties
         * 否者输出源文件下的11.properties文件的路径，则输出：6：7：file:/E:/myobject/myspider/src/11.properties
         */
        System.out.println("7：" + t.getClass().getResource("/com/hao/laker/study/spring/ioc/config.xml"));

    }
}
