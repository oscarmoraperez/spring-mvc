package org.oka.springmvc.dao;

import lombok.Setter;
import org.oka.springmvc.db.UserDB;
import org.oka.springmvc.model.User;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Setter
public class UserDAO {

    private UserDB userDB;
    private Pageable<User> pageable;

    public Optional<User> getById(final long id) {
        return userDB.getUsers().stream().filter(u -> u.getId() == id).findFirst();
    }

    public Optional<User> getByEmail(final String email) {
        return userDB.getUsers().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public List<User> getByName(final String name, final int pageSize, final int pageNum) {
        List<User> users = userDB.getUsers().stream()
                .filter(u -> u.getName().equals(name))
                .collect(toList());

        return pageable.paginate(users, pageSize, pageNum);
    }

    public User addUser(final User user) {
        return this.userDB.addUser(user);
    }

    public User update(final User user) {
        User userToUpdate = userDB.getUsers().stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst()
                .orElseThrow();
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());

        return userToUpdate;
    }

    public boolean delete(final long userId) {
        Optional<User> userToDelete = userDB.getUsers().stream().filter(u -> u.getId() == userId).findFirst();
        if (userToDelete.isEmpty()) {
            return false;
        }
        return userDB.getUsers().remove(userToDelete.get());
    }
}
