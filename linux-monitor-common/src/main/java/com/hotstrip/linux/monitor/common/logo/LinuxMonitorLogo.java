package com.hotstrip.linux.monitor.common.logo;

import com.hotstrip.linux.monitor.common.Consts;
import com.hotstrip.linux.monitor.common.utils.VersionUtil;

public class LinuxMonitorLogo {

    private static final String LOGO = "\n" +
            "  _        _                                   __  __                   _   _                  \n" +
            " | |      (_)                                 |  \\/  |                 (_) | |                 \n" +
            " | |       _   _ __    _   _  __  __  ______  | \\  / |   ___    _ __    _  | |_    ___    _ __ \n" +
            " | |      | | | '_ \\  | | | | \\ \\/ / |______| | |\\/| |  / _ \\  | '_ \\  | | | __|  / _ \\  | '__|\n" +
            " | |____  | | | | | | | |_| |  >  <           | |  | | | (_) | | | | | | | | |_  | (_) | | |   \n" +
            " |______| |_| |_| |_|  \\__,_| /_/\\_\\          |_|  |_|  \\___/  |_| |_| |_|  \\__|  \\___/  |_|   \n";

    /**
     * print linux monitor logo
     * @return
     */
    public static String buildBannerText() {
        return Consts.LINE_SEPARATOR
                + Consts.LINE_SEPARATOR
                + LOGO
                + " :: Linux-Monitor :: (v" + VersionUtil.getVersion(LinuxMonitorLogo.class, "1.0.0") + ")"
                + Consts.LINE_SEPARATOR;
    }
}
