package edu.miu.eaproject.exceptions;

public class BadgeNotAcceptedException extends BadgeSystemException {

    public BadgeNotAcceptedException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
