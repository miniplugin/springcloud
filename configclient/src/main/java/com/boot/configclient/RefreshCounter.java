package com.boot.configclient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 이 클래스는 RefreshScopeRefreshedEvent 로 ProjectNameRestController 스프링 빈의 리프레시를 확인
 */
@Component
public class RefreshCounter {

    private final Log log = LogFactory.getLog(getClass());
    private final AtomicLong counter = new AtomicLong(0); // 리프레시 이벤트 발생 카운트 변수

    @EventListener
    public void refresh(RefreshScopeRefreshedEvent e) {
        this.log.info("The refresh count is now at: "
                + this.counter.incrementAndGet());
    }
}