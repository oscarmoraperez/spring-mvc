package org.oka.springmvc.db;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDB_initBean_Test {

    @Test
    void shouldPopulate() throws IOException {
        // Given
        UserDB userDB = new UserDB();

        // When
        userDB.initBean();

        // Then
        assertThat(userDB.getUsers()).isNotEmpty();
    }
}
