package de.jeha.s3srv.errors;

/**
 * @author jenshadlich@googlemail.com
 */
public class BadDigestException extends Exception {

    public BadDigestException(String message) {
        super(message);
    }

}
