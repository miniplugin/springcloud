package users.microservices.product.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path = "/uaa/v1")
public class UsersController {

    private UsersService usersService;

    private AuthService authService;

    @Autowired
    public UsersController(UsersService userService, AuthService authService) {
        this.usersService = usersService;
        this.authService = authService;
    }

    @RequestMapping(path = "/me")
    public ResponseEntity<Users> me(Principal principal) throws Exception {
        return Optional.ofNullable(authService.getAuthenticatedUser(principal))
                .map(p -> ResponseEntity.ok().body(usersService.getUserByPrincipal(p)))
                .orElse(new ResponseEntity<Users>(HttpStatus.UNAUTHORIZED));
    }
}
