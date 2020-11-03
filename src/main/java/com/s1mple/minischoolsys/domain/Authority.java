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

    private Integer type;

}
