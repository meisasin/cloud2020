package net.yisasin.alibaba.seata.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.yisasin.alibaba.seata.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        storageDao.deduct(commodityCode, count);

        printAllTx();

    }

    private void printAllTx() {
        log.info("Global info -> {}", JSON.toJSONString(storageDao.listGlobal()));
        log.info("Branch info -> {}", JSON.toJSONString(storageDao.listBranch()));
        log.info("Lock info -> {}", JSON.toJSONString(storageDao.listLock()));
        log.info("Undolog info -> {}", JSON.toJSONString(storageDao.listUndolog()));
    }
}
