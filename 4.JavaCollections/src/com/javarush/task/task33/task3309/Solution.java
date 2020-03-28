package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
  public static String toXmlWithComment(Object obj, String tagName, String comment) {
    StringWriter writer = new StringWriter();
    try {
      JAXBContext context = JAXBContext.newInstance(obj.getClass());
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      marshaller.marshal(obj, writer);

      DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
      domFactory.setCoalescing(true);
      domFactory.setIgnoringComments(true);
      DocumentBuilder builder = domFactory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(writer.toString())));

      NodeList nodes = doc.getElementsByTagName(tagName);
      for (int i = 0; i < nodes.getLength(); i++) {
        Comment c = doc.createComment(comment);
        nodes.item(i).getParentNode().insertBefore(c, nodes.item(i));
      }

      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer transformer = tf.newTransformer();
      transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
      transformer.setOutputProperty( OutputKeys.INDENT, "yes" );
      transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );

      writer = new StringWriter();
      DOMSource domSource = new DOMSource(doc);
      StreamResult result = new StreamResult(writer);
      transformer.transform(domSource, result);
    } catch (JAXBException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (TransformerException e) {
      e.printStackTrace();
    }
    String result = writer.toString();
    return result;
  }

  public static void main(String[] args) {
    First firstSecondObject = new First();
    System.out.println(toXmlWithComment(firstSecondObject, "second", "it's a comment"));
  }
}
