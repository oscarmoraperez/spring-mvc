package org.oka.springmvc.dao;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Pageable_paginate_Test {
    Pageable<Element> pageable = new Pageable<>();

    private final List<Element> ELEMENTS = List.of(
            new Element("value1"),
            new Element("value2"),
            new Element("value3"),
            new Element("value4"),
            new Element("value5"),
            new Element("value6"));

    @Test
    void shouldReturnPage1With2Elements() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new Element("value1"),
                new Element("value2"));
    }

    @Test
    void shouldReturnPage6With1Elements() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 1, 5);

        // Then
        assertThat(actual).containsExactly(
                new Element("value6"));
    }

    @Test
    void shouldReturnEmptyWhenOutOfRange() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 1, 99);

        // Then
        assertThat(actual).isEmpty();
    }

    @Test
    void shouldReturnPageOneWithTwoElements() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 2, 0);

        // Then
        assertThat(actual).containsExactly(
                new Element("value1"),
                new Element("value2"));
    }

    @Test
    void shouldReturnPageTwoWithTwoElements() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 2, 1);

        // Then
        assertThat(actual).containsExactly(
                new Element("value3"),
                new Element("value4"));
    }

    @Test
    void shouldReturnPageOneWithOneTicket() {
        // Given

        // When
        List<Element> actual = pageable.paginate(ELEMENTS, 1, 5);

        // Then
        assertThat(actual).containsExactly(new Element("value6"));
    }

    @Data
    static class Element {
        private final String value;
    }
}
