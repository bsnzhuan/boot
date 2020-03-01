package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@PropertySource("classpath:test.properties")
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    private Environment environment;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxTotal")));
        config.setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxIdle")));
        //config.setMinIdle(Integer.parseInt(environment.getProperty("redis.minIdle")));
        //config.setMaxWaitMillis(Integer.parseInt(environment.getProperty("redis.maxWaitMills")));
        config.setMaxWaitMillis(1000);
        config.setMinEvictableIdleTimeMillis(Long.parseLong(
                environment.getProperty("redis.minEvictableIdleTimeMillis")));
        config.setNumTestsPerEvictionRun(Integer.parseInt(
                environment.getProperty("redis.numTestsPerEvictionRun")));
        config.setTimeBetweenEvictionRunsMillis(Long.parseLong(
                environment.getProperty("redis.timeBetweenEvictionRunsMillis")));
        config.setTestOnBorrow(Boolean.valueOf(environment.getProperty("redis.testOnBorrow")));
        config.setTestWhileIdle(Boolean.valueOf(environment.getProperty("redis.testWhileIdle")));
        return config;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(environment.getProperty("redis.hostname"));
        redisStandaloneConfiguration.setPassword(environment.getProperty("redis.password"));
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(Integer.parseInt(environment.getProperty("redis.port")));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        jpcb.poolConfig(jedisPoolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        return new JedisConnectionFactory(redisStandaloneConfiguration,jedisClientConfiguration);
    }

    /**
     * RedisTemplate 是一个泛型类，而 StringRedisTemplate 不是，
     * 后者只能对键和值都为 String 类型的数据进行操作，而前者则可以操作任何类型。
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     *
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return stringRedisTemplate;
    }

}
