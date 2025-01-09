package org.taskntech.tech_flow.contollers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.taskntech.tech_flow.config.SecurityConfig;
import org.taskntech.tech_flow.controllers.HomeController;

@Import(SecurityConfig.class)
@WebMvcTest(HomeController.class)

public class HomeLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAdminView() throws Exception {
        /*this.mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is(200));*/
    }
}
