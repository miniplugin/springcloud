package com.boot.test2.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest({ UsersService.class })
@AutoConfigureWebClient(registerRestTemplate = true)
public class UsersServiceTests {
    @Value("${user-service.host:user-service}") //application.properties 파일의 전역변수 값
    private String serviceHost;

    @Autowired
    private UsersService usersService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    public void getAuthenticatedUserTest() {
        this.mockRestServiceServer.expect(
                requestTo(String.format("http://%s/uaa/v1/me", serviceHost))).andRespond(
                withSuccess(new ClassPathResource("users.json", getClass()),
                        MediaType.APPLICATION_JSON)); // <2>

        Users users = usersService.getAuthenticatedUser();

        assertThat(users.getUsername()).isEqualTo("kimilguk");
        assertThat(users.getFirstName()).isEqualTo("김");
        assertThat(users.getLastName()).isEqualTo("일국");
        assertThat(users.getCreatedAt()).isEqualTo(12345);
        assertThat(users.getLastModified()).isEqualTo(12346);
        assertThat(users.getId()).isEqualTo(1L);
    }

}
