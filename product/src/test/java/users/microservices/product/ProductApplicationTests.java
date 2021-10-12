package users.microservices.product;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import users.microservices.product.users.*;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@WebMvcTest(UsersController.class)
class ProductApplicationTests {

	@MockBean
	private UsersService usersService;

	@MockBean
	private AuthService authService;

	@BeforeEach
	public void setup() {

		Users actual = new Users("kimilguk", "김", "일국", "kimilguk@test.com");
		actual.setLastModified(12345L);
		actual.setCreatedAt(12345L);
		actual.setId(0L);
		given(this.usersService.getUserByPrincipal(new PrincipalImpl("kimilguk")))
				.willReturn(actual);

		given(this.authService.getAuthenticatedUser(null)).willReturn(
				new PrincipalImpl("kimilguk"));

		RestAssuredMockMvc.standaloneSetup(new UsersController(usersService,
				authService));
	}

	public void assertThatRejectionReasonIsNull(Object rejectionReason) {
		assert rejectionReason == null;
	}

}
