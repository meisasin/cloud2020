package net.yisasin.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UndoLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long branchId;
    private String xid;
    private String context;
    private String rollbackInfo;
    private Integer logStatus;
    private Date logCreated;
    private Date logModified;
    private String ext;

}
