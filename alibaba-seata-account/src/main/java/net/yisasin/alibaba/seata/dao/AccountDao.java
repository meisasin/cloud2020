package net.yisasin.alibaba.seata.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {

    void debit(@Param("userId") String userId,
               @Param("money") int money);
}
