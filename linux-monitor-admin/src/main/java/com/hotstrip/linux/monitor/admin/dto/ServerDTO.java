package com.hotstrip.linux.monitor.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerDTO implements Serializable {
    private Integer id;
    private String host;
    private String user;
    private String password;
    private Integer port;
}
