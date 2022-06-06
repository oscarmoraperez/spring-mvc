package org.oka.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.oka.springmvc.loader.LocalDateAdapter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;
}
