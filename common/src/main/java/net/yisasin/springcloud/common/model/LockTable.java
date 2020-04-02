package net.yisasin.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LockTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xid;
    private String rowKey;
    private Long transactionId;
    private Long branchId;
    private String resourceId;
    private String tableName;
    private String pk;
    private Date gmtCreate;
    private Date gmtModified;

}
