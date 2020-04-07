package net.yisasin.springboot;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "spring.redis.redisson", name = "active", havingValue = "true")
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    private static final String EMPTY = "";

    /** 单节点配置文件路径 */
    private static final String singleClientConfigFilePath = "redisson.yml";
    /** 集群配置文件路径 */
    private static final String clusterClientConfigFilePath = "redisson-cluster.yml";

    @Bean
    @ConditionalOnProperty(prefix = "spring.redis.redisson", name = "model", havingValue = "single")
    public RedissonClient singleRedissonClient() throws IOException {

        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource(singleClientConfigFilePath));
        final SingleServerConfig singleServerConfig = config.useSingleServer();

        final String redissonAddress = wrapRedissonAddress(redisProperties.getHost(), redisProperties.getPort());
        singleServerConfig.setAddress(redissonAddress);
        singleServerConfig.setPassword(redisProperties.getPassword());

        RedissonClient redissonClient = Redisson.create(config);
        log.info("Redisson single config initialized for {}", redissonAddress);
        return redissonClient;
    }

    /**
     * 仅供参考
      * @return
     * @throws IOException
     */
    @Bean
    @ConditionalOnProperty(prefix = "spring.redis.redisson", name = "model", havingValue = "cluster")
    public RedissonClient clusterRedissonClient() throws IOException {
        List<String> redissonAddress = new ArrayList<>();
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource(clusterClientConfigFilePath));
        ClusterServersConfig clusterServersConfig = config.useClusterServers();

        // 设置 spring redis 配置
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        final String master = sentinel.getMaster();
        final List<String> nodes = sentinel.getNodes();

        final String masterAddress = wrapRedissonAddress(master);
        clusterServersConfig.addNodeAddress(masterAddress);
        redissonAddress.add(masterAddress);

        if (nodes != null && nodes.size() > 0) {
            for (String node : nodes) {
                final String nodeAddress = wrapRedissonAddress(node);
                clusterServersConfig.addNodeAddress(nodeAddress);
                redissonAddress.add(nodeAddress);
            }
        }
        clusterServersConfig.setPassword(redisProperties.getPassword());

        RedissonClient redissonClient = Redisson.create(config);
        log.info("Redisson cluster config initialized for {}", join(redissonAddress, ","));
        return redissonClient;
    }

    private String wrapRedissonAddress(String host, int port) {
        return wrapRedissonAddress(host + ":" + port);
    }

    private String wrapRedissonAddress(String redisAddress) {
        return "redis://" + redisAddress;
    }


    /**
     * Joins an collection of objects into one string with separator.
     */
    private static String join(final Collection collection, final String separator) {
        if (collection == null) {
            return null;
        }

        if (collection.size() == 0) {
            return EMPTY;
        }

        final StringBuilder sb = new StringBuilder(collection.size() * 16);
        final Iterator it = collection.iterator();

        for (int i = 0; i < collection.size(); i++) {

            if (i > 0) {
                sb.append(separator);
            }

            sb.append(it.next());
        }

        return sb.toString();
    }

}
