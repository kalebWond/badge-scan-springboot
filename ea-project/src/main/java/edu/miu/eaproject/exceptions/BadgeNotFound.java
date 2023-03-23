package edu.miu.eaproject.exceptions;

public class BadgeNotFound extends BadgeSystemException {

    public BadgeNotFound(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
