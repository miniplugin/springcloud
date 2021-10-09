package com.boot.test2.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.security.Principal;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.RequestEntity.get;

@Service
public class UsersService {

    private UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public Users getUserByPrincipal(Principal principal) {
        // 인증된 사용자 이름을 세션에서 가져오는 메소드
        return Optional.ofNullable(principal)
                .map(p -> usersRepository.findUserByUsername(p.getName())).orElse(null);
    }

    private String serviceHost;
    private RestTemplate restTemplate;

    @Autowired
    public UsersService(RestTemplate restTemplate,
                        @Value("${user-service.host:user-service}") String sh) {
        this.serviceHost = sh;
        this.restTemplate = restTemplate;
    }

    public Users getAuthenticatedUser() {
        URI url = URI.create(String.format("http://%s/uaa/v1/me", serviceHost));
        RequestEntity<Void> request = get(url).header(HttpHeaders.CONTENT_TYPE,
                APPLICATION_JSON_VALUE).build();
        return restTemplate.exchange(request, Users.class).getBody();
    }
}