package com.hao.laker.study.spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 这个类和java疯狂讲义里的反射那章的objectFactory是类似的，这里是使用dom4j操作的是xml文件。
 * Created by haojiahong on 17/3/3.
 */
@Slf4j
public class BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    public void init(String xml) {

        try {
            SAXReader reader = new SAXReader();
            File file = new File(xml);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream(xml);
//            Document document = reader.read(stream);
            Document document = reader.read(file);
            Element root = document.getRootElement();

            Iterator iteBean = root.elementIterator("bean");
            while (iteBean.hasNext()) {
                Element foo = (Element) iteBean.next();
                Attribute id = foo.attribute("id");
                Attribute cls = foo.attribute("class");

                Class bean = Class.forName(cls.getText());
                BeanInfo beanInfo = Introspector.getBeanInfo(bean);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

                //生成实例对象
                Object obj = bean.newInstance();

                Iterator iteProperty = foo.elementIterator("property");
                while (iteProperty.hasNext()) {
                    Element elementProperty = (Element) iteProperty.next();
                    Attribute name = elementProperty.attribute("name");
                    //属性值有可能是一个集合,这里先简单定义成一个简单的对象，在spring源码的时候看是怎么实现的。
                    Object value = null;
                    Iterator iteValue = elementProperty.elementIterator("value");
                    while (iteValue.hasNext()) {
                        Element elementValue = (Element) iteValue.next();
                        //这个方法返回的也是字符串，所以age也set不进去
                        value = elementValue.getData();
                        break;
                    }
                    for (int i = 0; i < propertyDescriptors.length; i++) {
                        if (propertyDescriptors[i].getName().equalsIgnoreCase(name.getText())) {
                            log.info("开始set属性为{}的值。", propertyDescriptors[i].getName());
                            Method mSet = propertyDescriptors[i].getWriteMethod();

                            mSet.invoke(obj, value);
                        }
                    }
                }
                beanMap.put(id.getText(), obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) {
        return beanMap.get(name);
    }

    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.init("/Users/haojiahong/work/mytest/laker/laker.manager/src/main/java/com/hao/laker/study/spring/ioc/config.xml");
        UserIocBean userIocBean = (UserIocBean) beanFactory.getBean("userIocBean");
        System.out.println(userIocBean.getUserName());
        System.out.println(userIocBean.getPassword());
        System.out.println(userIocBean.getAge());

    }

}
