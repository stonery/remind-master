package com.remind.task;

import com.remind.domain.CoinXpath;
import com.remind.domain.CoinXpathExample;
import com.remind.domain.UserMind;
import com.remind.mail.Mail;
import com.remind.mail.MailUtil;
import com.remind.mapper.CoinXpathMapper;
import com.remind.mapper.UserMindMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public class task {
    @Autowired
    UserMindMapper userMindMapper;

    @Autowired
    CoinXpathMapper coinXpathMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    @Scheduled(cron = "0,10,20,30,40,50 * * * * ?")
    public void cronJob() {
        long l = System.currentTimeMillis();
        UserMind userMind = userMindMapper.selectByPrimaryKey(1);
        Integer percent = userMind.getPercent();

        System.out.println(new Date().toLocaleString());
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        ListOperations<String, String> opsForList = redisTemplate.opsForList();

        CoinXpathExample coinXpathExample = new CoinXpathExample();
        coinXpathExample.createCriteria().andCoinIdEqualTo(1);
        List<CoinXpath> coinXpaths = coinXpathMapper.selectByExample(coinXpathExample);
        for (CoinXpath coinXpath : coinXpaths) {
            String listDayKey = "id_"+coinXpath.getId()+"class_"+coinXpath.getCss()+"DAY";
            String listHOURKey = "id_"+coinXpath.getId()+"class_"+coinXpath.getCss()+"HOUR";
            compareValue(percent, opsForList, opsForValue,listDayKey,1);
            compareValue(percent, opsForList, opsForValue,listHOURKey,0);
        }
        System.out.println("耗时"+(System.currentTimeMillis()-l));
    }

    private void compareValue(Integer percent, ListOperations<String, String> opsForList,ValueOperations<String, String> opsForValue, String listDayKey,Integer cycle) {

        List<String> listDay = opsForList.range(listDayKey, 0, -1);
        String lastDayValue = listDay.get(listDay.size() - 1);
        boolean flag= false;
        for (String value : listDay) {
            // 最新数据
            BigDecimal lastDec = new BigDecimal(lastDayValue).setScale(10);
            // 当前
            BigDecimal valueDec = new BigDecimal(value).setScale(10);
            // 差值
            BigDecimal subtract = (lastDec.subtract(valueDec)).setScale(10);
            // 差值*100 / 最新值
            BigDecimal divide = ((subtract.multiply(new BigDecimal("100"))).divide(lastDec, 10, BigDecimal.ROUND_HALF_UP)).abs();
            int i = divide.compareTo(new BigDecimal(percent));
            System.out.println("_"+lastDec+"_"+valueDec+"_"+divide+"_"+lastDayValue);
            String isSend = opsForValue.get(listDayKey + "IS_SEND");
            if (i>0&&StringUtils.isEmpty(isSend)){
                Mail mail = new Mail();
                mail.setHost("smtp.163.com"); // 设置邮件服务器,如果不用163的,自己找找看相关的
                mail.setSender("18566053720@163.com");
                mail.setReceiver("641673371@qq.com"); // 接收人
                //mail.setReceiver("641673371@qq.com"); // 接收人
                mail.setUsername("18566053720@163.com"); // 登录账号,一般都是和邮箱名一样吧
                mail.setPassword("stonery90"); // 发件人邮箱的登录密码
                mail.setSubject("测试邮件功能");
                if (cycle==0){
                    mail.setMessage("时浮动超过百分之"+percent+"已结达到百分之"+divide+"+,关注");
                }else if (cycle==1){
                    mail.setMessage("日浮动超过百分之"+percent+"已结达到百分之"+divide+"+,关注");
                }
                MailUtil.send(mail);
                opsForValue.set(listDayKey+"IS_SEND","true",1000*60);
                System.out.println("发送邮件成功!");
            }
        }
    }
}
