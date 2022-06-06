package org.oka.springmvc.model;

import com.sun.xml.bind.AnyTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Entity
@XmlRootElement
@XmlSeeAlso({Ticket.class})
@XmlJavaTypeAdapter(AnyTypeAdapter.class)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(targetEntity = Event.class)
    private Event event;
    @OneToOne(targetEntity = User.class)
    private User user;
    @Enumerated(EnumType.STRING)
    private Category category;
    private int place;

    public enum Category {STANDARD, PREMIUM, BAR}
}
