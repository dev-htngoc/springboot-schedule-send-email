package com.example.springbootscheduleemail.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMailDTO {
    private String userName;
    private String verificationCode;
}
