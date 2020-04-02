package net.yisasin.alibaba.seata.dao;

import net.yisasin.alibaba.seata.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {

    int insert(Order order);

}
