package com.villageroad.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 监控日志
 */
@Component
@Slf4j(topic = "monitor")
public class MonitorLog {
    public void info(String key, Object... infos) {
        StringBuilder formatStr = new StringBuilder(key);
        for (Object ignored : infos) {
            formatStr.append("|{}");
        }
        log.info(formatStr.toString(), infos);
    }

    public void warn(String key, Object... infos) {
        StringBuilder formatStr = new StringBuilder(key);
        for (Object ignored : infos) {
            formatStr.append("|{}");
        }
        log.warn(formatStr.toString(), infos);
    }

    public void error(String key, Object... infos) {
        StringBuilder formatStr = new StringBuilder(key);
        for (Object ignored : infos) {
            formatStr.append("|{}");
        }
        log.error(formatStr.toString(), infos);
    }

}
