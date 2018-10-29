package com.justl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author buhuaqi
 * @date 2018-10-29 11:16
 */
public class ConfigUtils {

    private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    public static Properties getProps(String confFileName) {
        Properties result = new Properties();
        logger.info("Load resource: " + confFileName);
        try (InputStream in = ConfigUtils.class.getClassLoader().getResourceAsStream(confFileName);) {
            result.load(in);
            return result;
        } catch (final Exception e) {
            logger.error("Config file '{}' does not exist!", confFileName);
            throw new RuntimeException();
        }
    }

}
