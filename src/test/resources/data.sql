<<<<<<< HEAD:src/test/resources/data.sql
INSERT INTO Users VALUES(100,1, 'email@gmail.ru','pass', 'anna');
INSERT INTO Users VALUES(101,1, 'mishamisha@gmail.ru','passppp', 'mishiko');
INSERT INTO News VALUES(200, 1,'this is news','news');
INSERT INTO Comment VALUES(300,1,'first comment to this news','2022-02-06 20:00:59.032804',null,100,200);
INSERT INTO Sub_Comment VALUES(400,1,'my subcomment to this comment','2022-03-06 19:00:22.032804',null,100,300);
INSERT INTO Rating VALUES(500,1,4,100,200);
=======
INSERT INTO Users VALUES(100,1,'hello@gmail.com',false,'password','hello12','verification_code');
INSERT INTO Roles VALUES(100,1,'ROLE_USER');
--INSERT INTO Users VALUES(101,2,'hell@gmail.com',false,'password','hello12',);
INSERT INTO Settings VALUES('MAIL_FROM','MAIL_SERVER','spacestore@gmail.com');
INSERT INTO Settings VALUES('MAIL_HOST','MAIL_SERVER','smtp.gmail.com');
INSERT INTO Settings VALUES('MAIL_PASSWORD','MAIL_SERVER','crjyasxnbecjcola');
INSERT INTO Settings VALUES('MAIL_PORT','MAIL_SERVER','587');
INSERT INTO Settings VALUES('MAIL_SENDER_NAME','MAIL_SERVER','Gamersfamily Company');
INSERT INTO Settings VALUES('MAIL_USERNAME','MAIL_SERVER','spacestore.georgia@gmail.com');
INSERT INTO Settings VALUES('CUSTOMER_VERIFY_SUBJECT','MAIL_TEMPLATES','Please, Verify Your Registration to enjoy Spacestore');
INSERT INTO Settings VALUES('CUSTOMER_VERIFY_CONTENT','MAIL_TEMPLATES','''<span style="font-size: 18px;">Dear [[name]],<br><br>Click the link below to verify your registration:</span><h2><br></h2><h2> <a href="[[URL]]" target="_self">VERIFY</a></h2><div><span style="font-size: 18px;"><br>Thanks,<br>The Spacestore Team.</span></div>');
INSERT INTO Settings VALUES('SMTP_AUTH','MAIL_SERVER',true);
INSERT INTO Settings VALUES('SMTP_SECURED','MAIL_SERVER',true);




>>>>>>> confirm_registration:src/main/resources/data.sql
