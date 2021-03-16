package com.hotstrip.linux.monitor.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.CodeSource;

/**
 * VersionUtils.
 * Just read MANIFEST.MF file and find Implementation-Version property
 * @author Hotstrip
 */
@Slf4j
public class VersionUtil {

    private static final String VERSION = getVersion(com.hotstrip.linux.monitor.common.utils.VersionUtil.class, "1.0.0");

    private static final String JAR = ".jar";

    private VersionUtil() {
    }

    /**
     * Gets version.
     *
     * @return the version
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * Gets version.
     *
     * @param cls            the cls
     * @param defaultVersion the default version
     * @return the version
     */
    public static String getVersion(final Class<?> cls, final String defaultVersion) {
        // find version info from MANIFEST.MF first
        String version = cls.getPackage().getImplementationVersion();
        if (StringUtils.isEmpty(version)) {
            version = cls.getPackage().getSpecificationVersion();
        }
        if (!StringUtils.isEmpty(version)) {
            return version;
        }
        // guess version fro jar file name if nothing's found from MANIFEST.MF
        CodeSource codeSource = cls.getProtectionDomain().getCodeSource();

        if (codeSource == null) {
            log.warn("No codeSource for class {} when getVersion, use default version {}", cls.getName(), defaultVersion);
            return defaultVersion;
        }
        String file = codeSource.getLocation().getFile();
        if (file != null && file.length() > 0 && file.endsWith(JAR)) {
            file = file.substring(0, file.length() - 4);
            int i = file.lastIndexOf('/');
            if (i >= 0) {
                file = file.substring(i + 1);
            }
            i = file.indexOf("-");
            if (i >= 0) {
                file = file.substring(i + 1);
            }
            while (file.length() > 0 && !Character.isDigit(file.charAt(0))) {
                i = file.indexOf("-");
                if (i < 0) {
                    break;
                }
                file = file.substring(i + 1);
            }
            version = file;
        }
        // return default version if no version info is found
        return StringUtils.isEmpty(version) ? defaultVersion : version;
    }
}
