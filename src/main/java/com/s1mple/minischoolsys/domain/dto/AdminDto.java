package com.s1mple.minischoolsys.domain.dto;

import com.s1mple.minischoolsys.domain.Authority;
import com.s1mple.minischoolsys.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {

    private Long admin_id;

    private String username;

    private String password;

    private String avatar;

    private List<Role> roles;

    private List<Authority> Authoritys;

}
