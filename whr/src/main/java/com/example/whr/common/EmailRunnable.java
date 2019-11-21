package com.example.whr.common;

import com.example.whr.bean.Employee;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;

/**
 * 使用线程来发送邮件
 */
public class EmailRunnable implements Runnable {
    private Employee employee;
    private JavaMailSender javaMailSender;
    private String emailAddress;

    public EmailRunnable(Employee employee,
                         JavaMailSender javaMailSender,
                         String emailAddress) {
        this.employee = employee;
        this.javaMailSender = javaMailSender;
        this.emailAddress = emailAddress;
    }

    //读取freemarker模板文件（解析freemarker模板文件）
    private String getEmailText(){
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassLoaderForTemplateLoading(ClassLoader.getSystemClassLoader(),"ftl");
        Template emailTemplate = null;
        String txt = "";
        try {
            emailTemplate = cfg.getTemplate("email.ftl");

            txt = FreeMarkerTemplateUtils.processTemplateIntoString(emailTemplate,emailTemplate);
            //另一种写法
//            StringWriter out = new StringWriter();
//            emailTemplate.process(employee,out);
//            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        return txt;
    }

    @Override
    public void run() {

        try {
            MimeMessage messge = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(messge, true);
            helper.setTo(employee.getEmail());
            helper.setFrom(emailAddress);
            helper.setSubject("Xxx集团：通知");
            helper.setText(getEmailText(),true);
            /**
             * 在邮件中添加附件的操作
             */
            // 通过文件路径获取文件名字
            //String filename = StringUtils.getFileName(location);
            // 定义要发送的资源位置
            //File file = new File(location);
            //FileSystemResource resource = new FileSystemResource(file);
            //mMessageHelper.addAttachment(filename, resource);// 在邮件中添加一个附件
            javaMailSender.send(messge);//发送邮件
        } catch (MessagingException e) {
            System.out.println("发送失败");
            e.printStackTrace();
        }
    }
}
