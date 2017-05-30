package com.webgis.security.jwt.verifier;

/**
 * Created by CCMEOW on 2017/5/9.
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
