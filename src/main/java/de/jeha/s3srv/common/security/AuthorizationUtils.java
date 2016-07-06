package de.jeha.s3srv.common.security;

import java.security.SignatureException;
import java.util.Locale;

/**
 * @author jenshadlich@googlemail.com
 */
public class AuthorizationUtils {

    public static boolean checkAuthorization(String givenAuthorization, Credentials credentials,
                                             String httpVerb, String contentMD5, String contentType,
                                             String date, String resource) {
        final String stringToSign =
                String.format(Locale.US,
                        "%s\n%s\n%s\n%s\n%s",
                        httpVerb,
                        contentMD5 == null ? "" : contentMD5,
                        contentType == null ? "" : contentType,
                        date,
                        resource);

        String hmac;
        try {
            hmac = SignatureUtils.calculateHmacSha1(stringToSign, credentials.getSecretKey());
        } catch (SignatureException e) {
            return false;
        }

        return givenAuthorization.equals("AWS " + credentials.getAccessKey() + ":" + hmac);
    }

    public static String extractAccessKey(String authorization) {
        return null; // TODO
    }

}