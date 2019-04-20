package com.jba.opencms.security.encoder;

import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class MessageDigestWithSaltPasswordEncoder implements PasswordEncoder {

    private MessageDigest md;
    private final static String DEFAULT = "SHA-512";

    private final static Pattern PATTERN = Pattern.compile("([a-z]|[0-9])*");
    private final Log logger = LogFactory.getLog(getClass());

    public MessageDigestWithSaltPasswordEncoder() throws NoSuchAlgorithmException{
        this(DEFAULT);
    }

    public MessageDigestWithSaltPasswordEncoder(String algorithm) throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance(algorithm);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] digest = md.digest(rawPassword.toString().getBytes());
        BigInteger signum = new BigInteger(1,digest);
        String hash = signum.toString();
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            logger.warn("Empty encoded password");
            return false;
        }

        if (!PATTERN.matcher(encodedPassword).matches()) {
            logger.warn("Encoded password does not look like BCrypt");
            return false;
        }

        return rawPassword.toString().equals(encodedPassword);
    }
}
