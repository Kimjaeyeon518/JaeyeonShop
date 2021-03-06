package com.jaeyeon.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass    // JPA Entity 클래스들이 이 클래스(BaseTimeEntity)를 상속할 경우 필드(createdDate, modifiedDate)들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate       // Entity가 생성되어 저장될 때 시간이 자동으로 저장됨
    public LocalDateTime createdDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동으로 저장됨
    public LocalDateTime modifiedDate;

}