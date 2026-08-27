package com.teammesh.TeamMesh.common.entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.EntityListeners;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    
    @CreatedDate
    @Column(name = "created_at", nullable=false, updatable=false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable=false)
    private Instant updatedAt;


    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdateAt(){
        return updatedAt;
    }
}
