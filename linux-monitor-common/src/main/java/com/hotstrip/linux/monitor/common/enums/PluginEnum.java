package com.hotstrip.linux.monitor.common.enums;

import java.util.Arrays;

public enum PluginEnum {
    SSH("ssh"),
    WEBSOCKET("websocket"),
    ;

    private String name;

    PluginEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * get plugin enum by name
     * @param name name
     * @return {@linkplain PluginEnum}
     */
    public static PluginEnum getPluginEnumByName(final String name) {
        return Arrays.stream(PluginEnum.values())
                .filter(pluginEnum -> pluginEnum.getName().equals(name))
                .findFirst().orElse(null);
    }
}
