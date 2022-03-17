package com.gamersfamily.gamersfamily.service;

import com.gamersfamily.gamersfamily.utils.mail.EmailSettingBag;
import org.springframework.stereotype.Service;


public interface SettingService {
    EmailSettingBag getEmailSettings();
}
