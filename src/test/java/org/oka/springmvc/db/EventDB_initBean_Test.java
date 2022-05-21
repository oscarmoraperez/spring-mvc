package org.oka.springmvc.db;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class EventDB_initBean_Test {

    @Test
    void shouldPopulate() throws IOException {
        // Given
        EventDB eventDB = new EventDB();

        // When
        eventDB.initBean();

        // Then
        assertThat(eventDB.getEvents()).isNotEmpty();
    }
}
