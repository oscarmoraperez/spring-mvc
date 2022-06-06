package org.oka.springmvc.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class XMLManager {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public Object convertFromXMLToObject(String xmlfile) throws IOException {

        try (FileInputStream is = new FileInputStream(xmlfile)) {
            return unmarshaller.unmarshal(new StreamSource(is));
        }
    }
}
