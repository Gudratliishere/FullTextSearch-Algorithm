package com.gudratli.fts.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class XMLReader {
    public static <T> List<T> readDocuments(String filePath, Class<T> clazz) {
        System.out.println("XML is reading..");

        List<T> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            NodeList nodeList = getNodeList(clazz, file);

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                System.out.printf("%s. element\r", itr);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    list.add(collectElements(clazz, eElement));
                }
            }
            System.out.println();
        } catch (ParserConfigurationException | SAXException | IOException | NoSuchMethodException |
                 InvocationTargetException | InstantiationException | IllegalAccessException e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }

        return list;
    }

    private static <T> T collectElements(Class<T> clazz, Element eElement)
            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        T obj = clazz.getDeclaredConstructor().newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String fieldName = method.getName().substring(3).toLowerCase();
                method.invoke(obj, eElement
                        .getElementsByTagName(fieldName)
                        .item(0)
                        .getTextContent());
            }
        }
        return obj;
    }

    private static <T> NodeList getNodeList(Class<T> clazz, File file)
            throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        System.out.printf("Root element: %s\n", doc.getDocumentElement().getNodeName());

        return doc.getElementsByTagName(getRootTag(clazz));
    }

    private static <T> String getRootTag(Class<T> clazz) {
        try {
            Field f = clazz.getField("DOC_NAME");
            return String.valueOf(f.get(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }

        return "";
    }
}
