package net.yisasin.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xid;
    private Long transactionId;
    private Integer status;
    private String applicationId;
    private String transactionServiceGroup;
    private String transactionName;
    private Integer timeout;
    private Long beginTime;
    private String applicationData;
    private Date gmtCreate;
    private Date gmtModified;
}
