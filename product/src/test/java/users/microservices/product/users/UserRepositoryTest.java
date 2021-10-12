package users.microservices.product.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        this.entityManager.persist(new Users("kimilguk","김","일국","kimilguk@test.com"));
    }

    @Test
    public void findUsersTest() throws Exception {
        Users actual = this.usersRepository.findUserByUsername("kimilguk");
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo("kimilguk");
        assertThat(actual.getFirstName()).isEqualTo("김");
        assertThat(actual.getLastName()).isEqualTo("일국");
        assertThat(actual.getEmail()).isEqualTo("kimilguk@test.com");
    }

    @Test
    public void findUserShouldReturnNull() throws Exception {
        Users user = this.usersRepository.findUserByUsername("noMache");
        assertThat(user).isNull();
    }
}
