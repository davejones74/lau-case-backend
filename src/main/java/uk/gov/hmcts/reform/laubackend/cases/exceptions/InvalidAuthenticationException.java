package uk.gov.hmcts.reform.laubackend.cases.exceptions;

public class InvalidAuthenticationException extends RuntimeException {
    private static final long serialVersionUID = -4L;

    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
