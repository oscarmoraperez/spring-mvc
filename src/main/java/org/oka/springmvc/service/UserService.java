package org.oka.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oka.springmvc.model.User;
import org.oka.springmvc.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(final long userId) {
        log.info("Retrieving tickets by userId: " + userId);

        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found (byId: " + userId + ")"));
    }

    public User getUserByEmail(final String email) {
        log.info("Retrieving users by email: " + email);

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found (byEmail: " + email + ")"));
    }

    public List<User> getUsersByName(final String name, final int pageSize, final int pageNum) {
        log.info("Retrieving users by name: " + name);

        return userRepository.findByName(name, PageRequest.of(pageNum, pageSize));
    }

    public User createUser(final User user) {
        log.info("Creating user: " + user);

        return userRepository.save(user);
    }

    public User updateUser(final User user) {
        log.info("Updating user: " + user);

        return userRepository.save(user);
    }

    public boolean deleteUser(final long userId) {
        log.info("Deleting user: " + userId);

        boolean exists = userRepository.existsById(userId);
        if (exists) {
            userRepository.deleteById(userId);
        }

        return exists;
    }
}
