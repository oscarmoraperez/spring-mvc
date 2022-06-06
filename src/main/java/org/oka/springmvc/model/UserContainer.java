package org.oka.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class UserContainer {
    @XmlElementWrapper(name = "users")
    @XmlAnyElement
    private List<User> users;
}
