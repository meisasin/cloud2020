package net.yisasin.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long branchId;
    private String xid;
    private Long transaction_id;
    private String resourceGroupId;
    private String resourceId;
    private String branchType;
    private Integer status;
    private String clientId;
    private String applicationData;
    private Date gmtCreate;
    private Date gmtModified;

}
