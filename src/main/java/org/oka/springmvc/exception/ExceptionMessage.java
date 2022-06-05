package org.oka.springmvc.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ExceptionMessage {
    private final String message;
}
