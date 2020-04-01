package net.yisasin.springcloud.dao;

import net.yisasin.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int insert(Payment payment);

    Payment getById(@Param("id") Long id);

}
