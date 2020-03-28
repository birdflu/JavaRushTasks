package com.javarush.task.task33.task3309;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "first")
@XmlRootElement
public class First {
  @XmlElement
  String[] second = {"some string","some string","![CDATA[need CDATA because of < and >]]"};
}
