package com.boot.test2.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JsonTest
public class UsersTests {
    private Users users;
    @Autowired
    private JacksonTester<Users> usersJacksonTester;

    @BeforeEach
    public void setUp() throws Exception {
        Users users = new Users(1L,"kimilguk","김","일국");
        users.setCreatedAt(12345L);
        users.setLastModified(12346L);
        this.users = users;
    }

    @Test
    public void deserializejson() throws Exception {
        String content = "{\"id\":1,\"username\":\"kimilguk\",\"firstName\":\"김\",\"lastName\":\"일국\"}";
        assertAll(
                () ->  assertThat(this.usersJacksonTester.parse(content))
                        .isNotEqualTo(new Users(1L,"kimilguk","김","일국")),
                () -> assertThat(this.usersJacksonTester.parseObject(content).getUsername())
                        .isEqualTo("kimilguk")
        );
    }

    @Test
    public void serializeJson() throws Exception {
        assertThat(this.usersJacksonTester.write(users)).isEqualTo("users.json");//resource 폴더에 users.json
        assertThat(this.usersJacksonTester.write(users)).isEqualToJson("users.json");
        assertThat(this.usersJacksonTester.write(users)).hasJsonPathStringValue("@.username");

        assertJsonPropertyEquals("@.username", "kimilguk");
        assertJsonPropertyEquals("@.firstName", "김");
        assertJsonPropertyEquals("@.lastName", "일국");
    }

    private void assertJsonPropertyEquals(String key, String value) throws Exception{
        assertThat(this.usersJacksonTester.write(users)).extractingJsonPathStringValue(key)
                .isEqualTo(value);
    }
}
