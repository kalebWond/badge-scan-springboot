package edu.miu.eaproject.exceptions;

public class NotFoundException extends BadgeSystemException {

    public NotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
