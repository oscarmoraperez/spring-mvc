package org.oka.springmvc.model;

import com.sun.xml.bind.AnyTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
@Getter
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
@XmlSeeAlso({TicketsContainer.class})
public class TicketsContainer {
    @XmlElementWrapper(name = "tickets")
    @XmlAnyElement(lax = true)
    private List<Ticket> tickets;
}
