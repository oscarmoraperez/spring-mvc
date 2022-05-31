package org.oka.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.*;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;
    private LocalDate date;
}
