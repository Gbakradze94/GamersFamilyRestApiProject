package com.gamersfamily.gamersfamily.service.serviceimpl;

import com.gamersfamily.gamersfamily.model.setting.Setting;
import com.gamersfamily.gamersfamily.model.setting.SettingCategory;
import com.gamersfamily.gamersfamily.repository.SettingRepository;
import com.gamersfamily.gamersfamily.service.SettingService;
import com.gamersfamily.gamersfamily.utils.mail.EmailSettingBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    private SettingRepository settingRepository;

    public EmailSettingBag getEmailSettings() {
        List<Setting> settings = settingRepository.findByCategory(SettingCategory.MAIL_SERVER);
        settings.addAll(settingRepository.findByCategory(SettingCategory.MAIL_TEMPLATES));

        return new EmailSettingBag(settings);
    }

}
