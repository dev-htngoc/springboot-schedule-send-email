package com.example.springbootscheduleemail.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME",nullable = false)
    private String userName;
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;
    @Column(name = "AVATAR")
    private String avatar;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "LAST_LOGIN_DT")
    private Date lastLoginDt;

    @Column(name = "USED_YN", length = 1)
    private String usedYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "REG_DT")
    private Date regDt;

    @Column(name = "REG_ID")
    @CreatedBy
    private Long regId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "MOD_DT")
    private Date modDt;

    @LastModifiedBy
    @Column(name = "MOD_ID")
    private Long modId;

    @Column(name = "SSO_ID")
    private String ssoId;

    @Column(name = "LOGIN_TYPE")
    private String loginType;

    @Column(name = "VERIFY_YN")
    private String verifyYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "VERIFY_DT")
    private Date verifyDt;

    @Column(name = "VERIFY_CODE")
    private String verifyCode;

    @Column(name = "EMAIL_SEND_YN")
    private String emailSendYn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name = "EMAIL_SEND_DT")
    private Date emailSendDt;
}
