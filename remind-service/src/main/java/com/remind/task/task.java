package com.remind.task;

import com.remind.mail.Mail;
import com.remind.mail.MailUtil;
import com.remind.redis.redis.persistence.manager.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class task {
    @Autowired
    private RedisManager redisManager;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
    public void cronJob() {
        System.out.println(new Date().toLocaleString());
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        ListOperations<String, String> opsForList = redisTemplate.opsForList();

        List<String> rediscunt1061477 = opsForList.range("id_1class_pid-1061477-lastDAY", 0, -1);

        Long size = opsForList.size("id_1class_pid-1061477-lastDAY");
        int intValue = size.intValue();
        String lastValue = rediscunt1061477.get(intValue - 1);
        boolean flag= false;
        for (String value : rediscunt1061477) {
            BigDecimal lastDec = new BigDecimal(lastValue).setScale(10);
            BigDecimal valueDec = new BigDecimal(value).setScale(10);
            BigDecimal subtract = lastDec.subtract(valueDec).setScale(10);
            BigDecimal divide5 = lastDec.divide(new BigDecimal("200").setScale(10));
            int i =  subtract.abs().compareTo(divide5);
            System.out.println("_"+lastDec+"_"+valueDec+"_"+divide5+"_"+intValue);
            if (i>0&&!flag){
                Mail mail = new Mail();
                mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
                mail.setSender("18566053720@163.com");
                mail.setReceiver("641673371@qq.com"); // 接收人
                //mail.setReceiver("641673371@qq.com"); // 接收人
                mail.setUsername("18566053720@163.com"); // 登录账号,一般都是和邮箱名一样吧
                mail.setPassword("stonery90"); // 发件人邮箱的登录密码
                mail.setSubject("测试邮件功能");
                mail.setMessage("浮动超过百分之5,关注");
                MailUtil.send(mail);
                flag = true;
                System.out.println("发送邮件成功!");
            }
        }

    }
}
