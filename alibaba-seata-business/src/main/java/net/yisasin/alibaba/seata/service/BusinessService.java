package net.yisasin.alibaba.seata.service;

public interface BusinessService {

    void purchase(String userId, String commodityCode, int orderCount);
}
