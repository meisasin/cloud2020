package net.yisasin.alibaba.seata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String commodityCode;
    private Integer count;

}
