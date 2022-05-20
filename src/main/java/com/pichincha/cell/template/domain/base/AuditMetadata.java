package com.pichincha.cell.template.domain.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AuditMetadata {
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate = new Date();

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    @Column(name = "CREATED_BY", nullable = false, length = 25)
    private String createdBy = "System";

    @Column(name = "MODIFIED_BY", length = 25)
    private String modifiedBy;

    @Column(name = "CREATED_IP", nullable = false, length = 15)
    private String createdIP = "1.1.1.1";

    @Column(name = "MODIFIED_IP", length = 15)
    private String modifiedIP;

    @Column(name = "STATUS", nullable = false, columnDefinition = "boolean default true")
    private Boolean status = Boolean.TRUE;
}