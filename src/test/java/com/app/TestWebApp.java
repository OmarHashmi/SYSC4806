package com.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestWebApp {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DefaultController dc;

    @Test
    public void contextLoads() {
        assertThat(dc).isNotNull();
    }

    @Test
    public void getDefaultPage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/questions",
                String.class)).contains("Survey");
		System.out.println("\n\n\nhttp://localhost:" + port + "/questions\n\n\n");
    }
}
