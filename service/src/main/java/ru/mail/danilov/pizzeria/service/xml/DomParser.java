package ru.mail.danilov.pizzeria.service.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class DomParser {

    private static final Logger logger = LogManager.getLogger(DomParser.class);

    public List<ItemXMLDto> parse(File file) throws IOException, SAXException {
        List<ItemXMLDto> items = new ArrayList<>();
        try {
            logger.info("Try to parse file " + file.getName());
            DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dBFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                ItemXMLDto item = new ItemXMLDto();
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    item.setId(element.getAttribute("id"));
                    item.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    item.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
                    item.setPrice(Float.valueOf(element.getElementsByTagName("price").item(0).getTextContent()));
                }
                items.add(item);
            }
        } catch (ParserConfigurationException e) {
            logger.info("parsing " + file.getName() + " failed");
            logger.error(e.getMessage(), e);

        }
        Collections.emptyList();
        new ArrayList<>();
        logger.info("File " + file.getName() + "parsed successful. " + items.size() + " items received");
        return items;
    }
}

