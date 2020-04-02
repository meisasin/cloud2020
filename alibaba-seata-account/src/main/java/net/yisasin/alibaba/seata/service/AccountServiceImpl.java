package net.yisasin.alibaba.seata.service;

import net.yisasin.alibaba.seata.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void debit(String userId, int money) {
        accountDao.debit(userId, money);
    }
}
