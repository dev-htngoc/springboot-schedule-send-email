package com.example.springbootscheduleemail.service;

import com.example.springbootscheduleemail.Repository.UserRepository;
import com.example.springbootscheduleemail.dto.SendMailDTO;
import com.example.springbootscheduleemail.entity.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendMail(){
        try {
            log.info("send mail schedule start!");
            List<User> userList = userRepository.findAllByVerifyYnAndEmailSendYn("N", "N");
            if(!userList.isEmpty()){
                for (User user : userList) {
                    //tao mot dto de gui mail
                    String verifyCode = generateVerificationCode();
                    SendMailDTO sendMailDTO = SendMailDTO.builder()
                            .userName(user.getUserName())
                            .verificationCode(verifyCode)
                            .build();

                    MimeMessage message = mailSender.createMimeMessage();
                    Context context = new Context();
                    context.setVariable("user", sendMailDTO);
                    String html = templateEngine.process("email-template", context);

                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                    helper.setTo(user.getEmail());
                    helper.setText(html, true);
                    helper.setSubject("Xác thực tài khoàn email của bạn");
                    helper.setFrom(fromEmail);

                    mailSender.send(message);

                    //cap nhat lai thong tin user sau khi da gui mail
                    updateSendMailToDB(user, verifyCode);
                }
            }
            log.info("send mail schedule end!");
        }catch (Exception ex){
            log.error("send email error. " +  ex.getMessage());
        }
    }

    private void updateSendMailToDB(User user, String verifyCode){
        user.setEmailSendYn("Y");
        user.setEmailSendDt(new Date());
        user.setVerifyCode(verifyCode);
        userRepository.save(user);
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
