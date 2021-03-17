package com.hotstrip.linux.monitor.admin.pojo.query;

import com.hotstrip.linux.monitor.admin.page.PageParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserQuery implements Serializable {
    private PageParams pageParams;
    private String user;
}
