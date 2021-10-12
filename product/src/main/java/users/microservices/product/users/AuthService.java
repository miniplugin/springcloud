package users.microservices.product.users;

import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    public Principal getAuthenticatedUser(Principal principal) {
        return principal == null ? new PrincipalImpl("kimilguk") : principal;
    }
}