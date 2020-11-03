package com.s1mple.minischoolsys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @TableId
    private Long admin_id;

    private String username;

    private String password;

    private String avatar;

    private Boolean status;

    private Date create_time;

    private Date last_login_time;

}
