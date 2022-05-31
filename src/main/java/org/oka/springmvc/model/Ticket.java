package org.oka.springmvc.model;

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

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Entity
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
