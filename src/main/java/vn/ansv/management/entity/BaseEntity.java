package vn.ansv.management.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Phục vụ JPA Auditing
public abstract class BaseEntity {
    @Id // Primary key + not null
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    @Column(name = "uid", unique = true, nullable = false)
    private String uid;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column(name = "moddified_by", nullable = false)
    @LastModifiedBy
    private String moddifiedBy;

    @Column(name = "moddified_date", nullable = false)
    @LastModifiedDate
    private Date moddifiedDate;

    public Long getId() {
        return this.id;
    }

    // Id được set tự tăng => Không có setter

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModdifiedBy() {
        return this.moddifiedBy;
    }

    public void setModdifiedBy(String moddifiedBy) {
        this.moddifiedBy = moddifiedBy;
    }

    public Date getModdifiedDate() {
        return this.moddifiedDate;
    }

    public void setModdifiedDate(Date moddifiedDate) {
        this.moddifiedDate = moddifiedDate;
    }

}
