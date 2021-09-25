package com.boot.springcloud2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@AutoConfigureMockMvc
@SpringBootTest
class Springcloud2ApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	CatRepository catRepository;

	@BeforeEach
	public void before() {
		Stream.of("고양이1","cat2","고양이3")
		.forEach(n -> catRepository.save(new Cat(n)));
	}

	@Test
	@WithMockUser //스프링 시큐리티 URL 권한 무시
	public void catsList() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cats_list")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
	            .characterEncoding(StandardCharsets.UTF_8.displayName());
		
		this.mockMvc
			.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(
					mvcResult -> {
						String contentAsString =
								mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
						System.out.println("여기1: " + contentAsString);
					});
		/*
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse();
        System.out.println("여기2: " + response.getContentAsString(StandardCharsets.UTF_8));*/
	}

	@Test
	void contextLoads() {
	}

}
