package su.kilko.fakeclient.goodclient;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Kosilov Nikita on 05.10.2015.
 */
public class RequestGenerator {
    public Object convertRequest(Object message) throws IOException, XMLStreamException {
        Xml xml = new Xml(message, new Date());
        XMLOutputFactory f = XMLOutputFactory.newInstance();
        XMLStreamWriter w = f.createXMLStreamWriter(System.out);

        try {
            w.writeStartDocument();
            w.writeCharacters("\n");
            w.writeStartElement("Request");
            w.writeStartElement("UserMessage");
            w.writeCharacters(xml.getMessage().toString());
            w.writeEndElement();
            w.writeStartElement("TimeStamp");
            w.writeCharacters(xml.getDate().toString());
            w.writeEndElement();
            w.writeEndElement();
            w.writeEndDocument();
        }
        finally {
            w.close();
        }
        return xml;
    }

    static class Xml {
        private Object userMessage;
        private Date timeStamp;

        public Xml(Object message, Date date) {
            this.userMessage = message;
            this.timeStamp = date;
        }

        public Object getMessage() {
            return userMessage;
        }

        public Date getDate() {
            return timeStamp;
        }
    }
}


