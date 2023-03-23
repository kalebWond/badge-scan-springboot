package edu.miu.eaproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BadgeSystemException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
}
