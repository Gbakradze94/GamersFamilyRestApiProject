package com.gamersfamily.gamersfamily.utils.mail;

import com.gamersfamily.gamersfamily.model.setting.Setting;
import com.gamersfamily.gamersfamily.model.setting.SettingBag;

import java.util.List;

public class EmailSettingBag extends SettingBag {

    public EmailSettingBag(List<Setting> settings) {
        super(settings);
    }

    public String getHost() {
        return super.getValue("MAIL_HOST");
    }

    public int getPort() {
        return Integer.parseInt(super.getValue("MAIL_PORT"));
    }

    public String getUsername() {
        return super.getValue("MAIL_USERNAME");
    }

    public String getPassword() {
        return super.getValue("MAIL_PASSWORD");
    }

    public String getSmtpAuth() {
        return super.getValue("SMTP_AUTH");
    }

    public String getSmtpSecured() {
        return super.getValue("SMTP_SECURED");
    }

    public String getFromAddress() {
        return super.getValue("MAIL_FROM");
    }

    public String getSenderName() {
        return super.getValue("MAIL_SENDER_NAME");
    }

    public String getUserVerifySubject() {
        return super.getValue("CUSTOMER_VERIFY_SUBJECT");
    }

    public String getCustomerVerifyContent() {
        return super.getValue("CUSTOMER_VERIFY_CONTENT");
    }

}
