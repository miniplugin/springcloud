package com.boot.test2.users;

import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    public Principal getAuthenticatedUser(Principal principal) {
        return principal == null ? new PrincipalImpl("user") : principal;
    }
}