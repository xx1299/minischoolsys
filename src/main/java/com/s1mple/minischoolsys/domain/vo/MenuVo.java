package com.s1mple.minischoolsys.domain.vo;

import com.s1mple.minischoolsys.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo {

    private Long menu_id;

    private String title;

    private String path;

    private List<Menu> children;

}
