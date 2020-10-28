package com.s1mple.minischoolsys.domain.vo;

import com.s1mple.minischoolsys.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {

    private Long role_id;

    private String name;

    private String description;

    private List<Authority> authorities;

}
