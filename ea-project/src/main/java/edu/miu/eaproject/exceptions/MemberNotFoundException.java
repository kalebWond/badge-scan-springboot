package edu.miu.eaproject.exceptions;

public class MemberNotFoundException extends BadgeSystemException {

    public MemberNotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
