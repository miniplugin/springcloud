package com.boot.test2.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest //MVC 컨트롤러 테스트 전용 인터페이스
public class AccountControllerTest {
    @Autowired//스프링빈 주입
    private MockMvc mockMvc;//컨트롤러 @Get,Post 처럼 모의테스트 메서드로 요청을 처리 한다.
    @MockBean//모의테스트용 스프링빈을 주입받을때 사용,실제 운영에 영향을 주지 않음
    private AccountService accountService;
    @Test
    public void getUserAccountTest() throws Exception {
        String content = "[{\"username\":\"kimilguk\",\"accountNumber\":\"123456789\"}]";
        given(this.accountService.getUserAccounts()).willReturn(
                Collections.singletonList(new Account("kimilguk","123456789"))
        );
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/v1/account")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(content));
    }
}
