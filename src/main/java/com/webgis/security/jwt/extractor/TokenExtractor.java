package com.webgis.security.jwt.extractor;

/**
 * Created by CCMEOW on 2017/5/7.
 */
public interface TokenExtractor {
    public String extract(String payload);
}
