package net.yisasin.springcloud.dao;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import net.yisasin.springcloud.BaseJunit;
import net.yisasin.springcloud.entity.Payment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class PaymentDaoTest extends BaseJunit {

    @Autowired
    private PaymentDao paymentDao;

    @Test
    public void insert() {
        Date date = new Date();
        Payment payment = new Payment(null, "--", date);
        int insert = paymentDao.insert(payment);
        System.out.printf(" >>> date: %s >>> time: >>> insert: %d\n", DateUtil.formatDateTime(date), date.getTime(), insert);

//        System.out.printf(" >>> paymentDate: %s >>> time: >>> id: %d", DateUtil.formatDateTime(payM.getCtime()), payM.getCtime().getTime(), payM.getId());

        Date date1 = new Date(1585472702441L);

    }

    @Test
    public void a() {

        DateTime parse = DateUtil.parse("2020-03-29 17:05:02");
        Long insertDateStamp = parse.getTime() - 10;

        Date insertDate = new Date(insertDateStamp);
        System.out.println(" >>> waitInsertDate: " + DateUtil.formatDateTime(insertDate) + ", waitInsertDateStamp: " + insertDate.getTime());

        Payment payment = new Payment(null, "--", insertDate);
        int insert = paymentDao.insert(payment);


    }

    @Test
    public void b() {

        Long insertDateStamp = 1585444508518L;

        Date insertDate = new Date(insertDateStamp);
        System.out.println(" >>> waitInsertDate: " + DateUtil.formatDateTime(insertDate) + ", waitInsertDateStamp: " + insertDate.getTime());

        Payment payment = new Payment(null, "--", insertDate);
        int insert = paymentDao.insert(payment);


    }

    @Test
    public void c() {
        Payment payment = paymentDao.getById(7L);
        System.out.println(" >>> queryDate: " + DateUtil.formatDateTime(payment.getCtime()) + ", waitInsertDateStamp: " + payment.getCtime().getTime());
    }

}