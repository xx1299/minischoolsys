package com.s1mple.minischoolsys.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @TableId
    private Long authority_id;

    private String name;

    private String description;

    private Boolean status;
//    权限类型 1.1级菜单2.2级菜单3.功能
    private Integer type;
//    权限路径 对于菜单来说是path 对于功能来说则是该功能的接口地址
    private String path;

    private Long parent_id;

}
