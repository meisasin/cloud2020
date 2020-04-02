# cloud2020
Spring cloud 2020 版本学习

- 版本选择
    - Spring cloud	        Hoxton.SR1
    - Spring boot		    2.2.2.RELEASE
    - Srping cloud alibaba 	2.1.0.RELEASE
    - seata                 1.1.0
    - Java 	                Java8
    - Maven	                3.5及以上
    - Mysql	                5.7及以上 

- 基础配置
    - 服务名: port
    
        ### Spring cloud
        - cloud-payment:        8001
        - cloud-payment-bak:    8002
        - cloud-eureka:         10001
        - cloud-eureka:         10002
        - cloud-consumer:       9001
        - cloud-feign-consumer: 9010
        
        ### Consul 实现
        - consul-payment:      7001
        - consul-consumer:     6001
        
        ### Spring cloud alibaba -> nacos + sentinel
        - alibaba-payment:      3001
        - alibaba-payment-bak:  3002
        - alibaba-consumer:     5001
        - alibaba-config:       8868
        - alibaba-sentinel:     8810
        
        ### Spring cloud stream 实现
        - alibaba-cloud-stream-provider:    10001
        - alibaba-cloud-stream-consumer:    10002
        
        ### Spring cloud alibaba ->  nacos + seata
        - alibaba-seata-order:      20001
        - alibaba-seata-storage:    20002
        - alibaba-seata-account:    20002
        - alibaba-seata-business:   20004

