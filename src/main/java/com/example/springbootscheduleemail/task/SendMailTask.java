package com.example.springbootscheduleemail.task;

import com.example.springbootscheduleemail.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendMailTask {
    @Autowired
    private SendMailService sendMailService;

    private Thread emailThread;

    @Scheduled(fixedRate = 60000) // Gửi email mỗi 60 giây
    public void scheduleTaskWithFixedRate() {
        if (emailThread == null || !emailThread.isAlive()) {
            emailThread = new Thread(() -> {
                sendMailService.sendMail();
            });
            emailThread.start();
        } else {
            System.out.println("Previous email task is still running, skipping this schedule.");
        }
    }
}
