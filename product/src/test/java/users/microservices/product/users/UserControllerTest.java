package users.microservices.product.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UsersController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AuthService authService;

    @Test
    public void getUsersTest() throws Exception {
        String content = "{\"username\": \"kimilguk\", \"firstName\": \"김\", \"lastName\": \"일국\", \"email\": \"kimilguk@test.com\"}";

        given(this.usersService.getUserByPrincipal(new PrincipalImpl("kimilguk")))
                .willReturn(new Users("kimilguk", "김", "일국", "kimilguk@test.com"));

        given(this.authService.getAuthenticatedUser(null)).willReturn(
                new PrincipalImpl("kimilguk"));

        this.mvc.perform(get("/uaa/v1/me").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(content));
    }
}
