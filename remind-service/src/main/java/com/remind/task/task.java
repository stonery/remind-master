package com.remind.task;

import com.remind.mail.Mail;
import com.remind.mail.MailUtil;
import com.remind.persistence.domain.CoinXpath;
import com.remind.persistence.domain.UserMind;
import com.remind.persistence.manager.CoinXpathManager;
import com.remind.persistence.manager.UserMindManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Component
public class task {
    private static final Logger LOGGER = LoggerFactory.getLogger(task.class);
    @Autowired
    UserMindManager userMindManager;

    @Autowired
    CoinXpathManager coinXpathManager;

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    @Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
    public void cronJob() {
        long l = System.currentTimeMillis();
        UserMind userMind = userMindManager.selectByPrimaryKey(1L);
        Integer percent = userMind.getPercent();

        System.out.println(new Date().toLocaleString());
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        ListOperations<String, String> opsForList = redisTemplate.opsForList();

        Example coinXpathExample = new Example(CoinXpath.class);
        coinXpathExample.createCriteria().andEqualTo("isDelete",false);
        List<CoinXpath> coinXpaths = coinXpathManager.selectCoinXpathByExample(coinXpathExample);
        for (CoinXpath coinXpath : coinXpaths) {
            String listDayKey = "id_"+coinXpath.getId()+"class_"+coinXpath.getCss()+"DAY";
            String listHOURKey = "id_"+coinXpath.getId()+"class_"+coinXpath.getCss()+"HOUR";
            compareValue(percent, opsForList, opsForValue,listDayKey,1);
            compareValue(percent, opsForList, opsForValue,listHOURKey,0);
        }
        LOGGER.info("耗时"+(System.currentTimeMillis()-l));
        System.out.println("耗时"+(System.currentTimeMillis()-l));
    }

    private void compareValue(Integer percent, ListOperations<String, String> opsForList,ValueOperations<String, String> opsForValue, String listDayKey,Integer cycle) {
        long l = System.currentTimeMillis();
        List<String> listDay = opsForList.range(listDayKey, 0, -1);
        String lastDayValue = listDay.get(listDay.size() - 1);
        Float falgMax = 0F;
        Float falgMin = 100000F;
        Float lastDayFloat = new Float(lastDayValue);
        for (String value : listDay) {
            Float valueFloat = new Float(value);
            if (valueFloat>falgMax){
                falgMax = valueFloat;
            }
            if (valueFloat<falgMin){
                falgMin = valueFloat;
            }

        }
        // 比最大值小多少
        Float subtractMax =  falgMax -lastDayFloat;
        // 比最小值大多少
        Float subtractMin = lastDayFloat - falgMin;
        // 低
        Float max = subtractMax * 100 / lastDayFloat;
        // 高
        Float min = subtractMin * 100 / lastDayFloat;
        Float absDown = Math.abs(max);
        Float absUp = Math.abs(min);
        Float x = absDown - percent;
        Float m = absUp - percent;
        String IS_SEND_UP = opsForValue.get(listDayKey + "IS_SEND_UP");
        String IS_SEND_DOWN = opsForValue.get(listDayKey + "IS_SEND_DOWN");
        System.out.println("IS_SEND_UP"+IS_SEND_UP+"====IS_SEND_DOWN"+IS_SEND_DOWN);
        LOGGER.info("缓存中是否发送过邮件IS_SEND_DOWN={},IS_SEND_UP={}",IS_SEND_DOWN,IS_SEND_UP);
        boolean isSend = false;
        if (StringUtils.isEmpty(IS_SEND_DOWN)&&StringUtils.isEmpty(IS_SEND_UP)){
            isSend = true;
        }else {
            Float cacheDown = new Float(IS_SEND_DOWN);
            Float cacheUp = new Float(IS_SEND_UP);
            if (cacheDown<absDown||cacheUp>absUp){
                isSend = true;
            }
        }
        if ((x>0||m>0)&& isSend){
            Mail mail = new Mail();
            mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
            mail.setSender("18566053720@163.com");
            mail.setReceiver("641673371@qq.com"); // 接收人
            //mail.setReceiver("641673371@qq.com"); // 接收人
            mail.setUsername("18566053720@163.com"); // 登录账号,一般都是和邮箱名一样吧
            mail.setPassword("stonery90"); // 发件人邮箱的登录密码
            mail.setSubject("测试邮件功能");
            if (cycle==0){
                if (x>0){
                    mail.setMessage("时跌幅超过百分之"+percent+";已经达到百分之"+absDown+"+;关注");
                    opsForValue.set(listDayKey+"IS_SEND_DOWN",String.valueOf(absDown),60);
                }
                if (m>0){
                    mail.setMessage("时漲幅超过百分之"+percent+";已经达到百分之"+absUp+"+;关注");
                    opsForValue.set(listDayKey+"IS_SEND_UP",String.valueOf(absUp),60);
                }
            }else if (cycle==1){
                if (x>0){
                    mail.setMessage("日跌幅超过百分之"+percent+";已经达到百分之"+absDown+"+;关注");
                    opsForValue.set(listDayKey+"IS_SEND_DOWN",String.valueOf(absDown),60);
                }else if (m>0) {
                    mail.setMessage("日涨幅超过百分之"+percent+";已经达到百分之"+absUp+"+;关注");
                    opsForValue.set(listDayKey+"IS_SEND_UP",String.valueOf(absUp),60);
                }
            }
            MailUtil.send(mail);
            System.out.println("发送邮件成功!absDown="+absDown+"--"+"absUp"+absUp);
            LOGGER.info("发送邮件成功!absDown={},absUp={}",absDown,absUp);
        }
//        for (String value : listDay) {
//            Float lastDayFloat = new Float(lastDayValue);
//            Float valueFloat = new Float(value);
//            Float subtractFloat = lastDayFloat - valueFloat;
//            Float v = subtractFloat * 100 / lastDayFloat;
//            Float abs = Math.abs(v);
//            Float i = abs - percent;
//            System.out.println("_"+lastDayFloat+"_"+valueFloat+"_"+subtractFloat+"_"+i );
//            String isSend = opsForValue.get(listDayKey + "IS_SEND");
//            if (i>0&&StringUtils.isEmpty(isSend)){
//                Mail mail = new Mail();
//                mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
//                mail.setSender("18566053720@163.com");
//                mail.setReceiver("641673371@qq.com"); // 接收人
//                //mail.setReceiver("641673371@qq.com"); // 接收人
//                mail.setUsername("18566053720@163.com"); // 登录账号,一般都是和邮箱名一样吧
//                mail.setPassword("stonery90"); // 发件人邮箱的登录密码
//                mail.setSubject("测试邮件功能");
//                if (cycle==0){
//                    mail.setMessage("时浮动超过百分之"+percent+"已经达到百分之"+v+"+,关注");
//                }else if (cycle==1){
//                    mail.setMessage("日浮动超过百分之"+percent+"已经达到百分之"+v+"+,关注");
//                }
//                MailUtil.send(mail);
//                opsForValue.set(listDayKey+"IS_SEND","true",1000*60);
//                System.out.println("发送邮件成功!");
//            }
//        }
        System.out.println("缓存长度="+listDay.size()+"用时="+(System.currentTimeMillis()-l));
        LOGGER.info("缓存长度={},用时={}",listDay.size(),(System.currentTimeMillis()-l));
//        for (String value : listDay) {
//            // 最新数据
//            BigDecimal lastDec = new BigDecimal(lastDayValue).setScale(5);
//            // 当前
//            BigDecimal valueDec = new BigDecimal(value).setScale(5);
//            // 差值
//
//            BigDecimal subtract = (lastDec.subtract(valueDec)).setScale(5);
//            // 差值*100 / 最新值
//            BigDecimal divide = ((subtract.multiply(new BigDecimal("100"))).divide(lastDec, 5, BigDecimal.ROUND_HALF_UP)).abs();
//
//            int i = divide.compareTo(new BigDecimal(percent));
//            System.out.println("_"+lastDec+"_"+valueDec+"_"+divide+"_"+lastDayValue);
//            String isSend = opsForValue.get(listDayKey + "IS_SEND");
//            if (i>0&&StringUtils.isEmpty(isSend)){
//                Mail mail = new Mail();
//                mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
//                mail.setSender("18566053720@163.com");
//                mail.setReceiver("641673371@qq.com"); // 接收人
//                //mail.setReceiver("641673371@qq.com"); // 接收人
//                mail.setUsername("18566053720@163.com"); // 登录账号,一般都是和邮箱名一样吧
//                mail.setPassword("stonery90"); // 发件人邮箱的登录密码
//                mail.setSubject("测试邮件功能");
//                if (cycle==0){
//                    mail.setMessage("时浮动超过百分之"+percent+"已经达到百分之"+divide+"+,关注");
//                }else if (cycle==1){
//                    mail.setMessage("日浮动超过百分之"+percent+"已经达到百分之"+divide+"+,关注");
//                }
//                MailUtil.send(mail);
//                opsForValue.set(listDayKey+"IS_SEND","true",1000*60);
//                System.out.println("发送邮件成功!");
//            }
//        }
    }
}
