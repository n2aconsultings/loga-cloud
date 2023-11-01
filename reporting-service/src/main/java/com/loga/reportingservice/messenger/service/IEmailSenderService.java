package com.loga.reportingservice.messenger.service;

import com.loga.reportingservice.messenger.factory.EmailMessage;
import org.springframework.web.multipart.MultipartFile;

public interface IEmailSenderService {
    void sendMessage(EmailMessage emailMessage);
    void sendMessage(EmailMessage emailMessage, MultipartFile[] files);
}
