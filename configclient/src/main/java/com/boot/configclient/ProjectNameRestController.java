package com.boot.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RefreshScope 붙은 클래스 객체는 실행시 설정서버 정보를 항상 새로 불러 들인다.
 */
@RestController
@RefreshScope
class ProjectNameRestController {

    private final String projectName;

    @Autowired
    public ProjectNameRestController(
            @Value("${configuration.projectName}") String pn) { // <2>
        this.projectName = pn;
    }

    @RequestMapping("/project-name")
    String projectName() {
        return this.projectName;
    }
}
