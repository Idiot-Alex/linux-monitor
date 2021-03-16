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
public class AdminUserDTO implements Serializable {
    private Integer id;
    private String userName;
    private String password;
}
