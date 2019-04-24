package com.fzk.ioc.beans.def.reader;

import com.fzk.ioc.beans.def.BeanDefinition;
import com.fzk.ioc.beans.def.BeanReference;
import com.fzk.ioc.beans.io.ResourceLoader;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从xml配置文件中读取bean定义
 * <p>
 * author:fanzhoukai
 * 2019/2/17 9:26
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader() {
    }

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 解析xml配置文件路径，解析xml为BeanDefinition对象，存入抽象类的map中
     *
     * @param location xml配置文件路径
     */
    public void loadBeanDefinitions(String location) throws Exception {
        // 获取配置文件输入流
        InputStream inputStream = super.getResourceLoader().getResource(location).getInputStream();
        // 解析为内存对象
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        // 注册bean定义
        registerBeanDefinitions(document);
        // 关闭输入流
        inputStream.close();
    }

    // xml内存对象解析为BeanDefinition内存对象，并存入抽象类的map中
    private void registerBeanDefinitions(Document document) throws Exception {
        NodeList childNodes = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                registerBeanDefinition((Element) node);
            }
        }
    }

    // 将一个xml节点转为bean定义对象，存入抽象类map中
    private void registerBeanDefinition(Element element) throws Exception {
        String beanName = element.getAttribute("name");
        String beanClass = element.getAttribute("class");

        // 构造bean定义对象
        BeanDefinition beanDefinition = new BeanDefinition();
        // 基本属性
        beanDefinition.setBeanName(beanName);
        beanDefinition.setClassName(beanClass);

        // 其他非必填属性
        if(element.hasAttribute("singleton")) {
        	beanDefinition.setSingleton("true".equals(element.getAttribute("singleton")));
        }
        // 属性赋值
        processPropertyValues(beanDefinition, element);

        super.getBeanDefinitionMap().put(beanName, beanDefinition);
    }

    // 将xml元素中的属性值，赋值给bean定义对象
    private void processPropertyValues(BeanDefinition beanDefinition, Element element) {
        NodeList propertyNodes = element.getChildNodes();
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node propertyItem = propertyNodes.item(i);
            if (propertyItem instanceof Element) {
                Element propertyElement = (Element) propertyItem;
                String name = propertyElement.getAttribute("name");
                String value = propertyElement.getAttribute("value");
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(name, value);
                } else {
                    String ref = propertyElement.getAttribute("ref");
                    Assert.assertTrue("属性" + name + "的value和ref值不能均为空", ref != null && ref.length() > 0);
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(name, beanReference);
                }
            }
        }
    }
}
