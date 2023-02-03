package com.pertree.designmodel.di;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王跃斌
 * @date 2023/1/26
 */
public class XmlBeanConfigParser implements BeanConfigParser {
    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputStream);

            // TODO: read it later, 关于 xml 为什么需要 normalize 一下
            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList beanList = doc.getElementsByTagName("bean");

            for (int i = 0; i < beanList.getLength(); i++) {
                Node node = beanList.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");
                BeanDefinition beanDefinition = new BeanDefinition(id, className);

                if ("singleton".equals(element.getAttribute("scope"))) {
                    beanDefinition.setScope(BeanDefinition.Scope.SINGLETON);
                }
                if ("true".equals(element.getAttribute("lazy-init"))) {
                    beanDefinition.setLazyInit(true);
                }
                NodeList constructorArgList = element.getElementsByTagName("constructor-arg");
                loadConstructorArgs(constructorArgList, beanDefinition);

                beanDefinitions.add(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return beanDefinitions;
    }

    public void loadConstructorArgs(NodeList nodes, BeanDefinition beanDefinition) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element element = (Element) node;

            BeanDefinition.ConstructorArg constructorArg = null;
            if (!element.getAttribute("type").isEmpty()) {
                String value = element.getAttribute("value");
                constructorArg = new BeanDefinition.ConstructorArg(String.class, value);
            }

            if (!element.getAttribute("ref").isEmpty()) {
                constructorArg = new BeanDefinition.ConstructorArg();
                constructorArg.setRef(true);
                constructorArg.setArg(element.getAttribute("ref"));
            }

            beanDefinition.addConstructorArg(constructorArg);
        }
    }

    @Override
    public List<BeanDefinition> parse(String configContent) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // TODO:...
        return beanDefinitions;
    }
}
