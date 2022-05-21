package org.oka.springmvc.service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.dao.UserDAO;
import org.oka.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Setter
@Slf4j
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserById(final long userId) {
        log.info("Retrieving tickets by userId: " + userId);
        return userDAO.getById(userId).orElseThrow(() -> new RuntimeException("User not found (byId: " + userId + ")"));
    }

    public User getUserByEmail(final String email) {
        log.info("Retrieving users by email: " + email);
        return userDAO.getByEmail(email).orElseThrow(() -> new RuntimeException("User not found (byEmail: " + email + ")"));
    }

    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        log.info("Retrieving users by name: " + name);
        return userDAO.getByName(name, pageSize, pageNum);
    }

    public User createUser(final User user) {
        log.info("Creating user: " + user);
        return userDAO.addUser(user);
    }

    public User updateUser(final User user) {
        log.info("Updating user: " + user);
        return userDAO.update(user);
    }

    public boolean deleteUser(final long userId) {
        log.info("Deleting user: " + userId);
        return userDAO.delete(userId);
    }
}
