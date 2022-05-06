package com.pichincha.cell.template.domain.base;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public class Audit {
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "CREATED_BY", nullable = false, length = 25)
    private String createdBy;

    @Column(name = "MODIFIED_BY", length = 25)
    private String modifiedBy;

    @Column(name = "CREATED_IP", nullable = false, length = 15)
    private String createdIP;

    @Column(name = "MODIFIED_IP", length = 15)
    private String modifiedIP;

    @Column(name = "STATUS", nullable = false, columnDefinition = "boolean default true")
    private Boolean status;
}