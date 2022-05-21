package org.oka.springmvc.db;

import org.oka.springmvc.model.User;
import org.oka.springmvc.model.UserImpl;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDB {
    /**
     * Simple structure to hold the users of the system (simulates a proper DB)
     **/
    private final List<User> usersDb = new ArrayList<>();

    /**
     * Holds the incremental id (PK) of the User object
     **/
    private final AtomicInteger index = new AtomicInteger(1);

    /**
     * Inits/populate the storage of Users.
     *
     * @throws IOException in case there are errors with location of the file.
     */
    public void initBean() throws IOException {
        List<String> users = Files.readAllLines(ResourceUtils.getFile("classpath:users.txt").toPath());
        for (String user : users) {
            String[] split = user.split(";");
            this.addUser(UserImpl.builder().name(split[0]).email(split[1]).build());
        }
    }

    /**
     * Returns the complete list of User.
     *
     * @return list of User
     */
    public List<User> getUsers() {
        return this.usersDb;
    }

    /**
     * Adds a user to the repository
     *
     * @param user to add
     * @return user added (includes PK)
     */
    public User addUser(final User user) {
        user.setId(this.index.addAndGet(1));
        usersDb.add(user);

        return user;
    }
}
