package com.webgis.security.jwt.verifier;

/**
 * Created by CCMEOW on 2017/5/9.
 */
public class BloomFilterTokenVerifier implements TokenVerifier {
    @Override
    public boolean verify(String jti) {
        return true;
    }
}
