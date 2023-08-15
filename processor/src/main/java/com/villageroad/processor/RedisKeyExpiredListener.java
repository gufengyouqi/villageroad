package com.villageroad.processor;

import com.villageroad.utils.MonitorLog;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    @Resource
    private MonitorLog monitorLog;
//    @Resource
//    private TimerTaskService timerTaskService;

    private String expiredKey = "expiredKey";

    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 使用该方法监听，当Redis的key失效的时候执行该方法
     *
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 过期的Key
        String redisKey = message.toString();
        long start = System.currentTimeMillis();
        monitorLog.info("redis_expired", redisKey);
        if (redisKey.startsWith(expiredKey)) {
            monitorLog.info("redis_expired_monitor", redisKey);
            String timerTaskKey = redisKey.replace(expiredKey, "");
            log.info("consumer expired key:{}", redisKey);
            try {
                log.info("alertTimeout fail key:{}", redisKey);
            } catch (Exception e) {
                log.error("alertTimeout fail key:{}", redisKey, e);
            }
            monitorLog.info("redis_expired_monitor_cost",System.currentTimeMillis()-start, redisKey);
        } else {
            log.info("ignore key:{}", redisKey);
        }
        monitorLog.info("redis_expired_cost",System.currentTimeMillis()-start, redisKey);
    }

}
