package com.study.springmvc4.spittr;

import com.study.springmvc4.spittr.web.SpitterController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {
    @Test
    public void shouldShowRecentSpittles()throws Exception{
        SpitterController controller = new SpitterController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        //断言registerForm视图
        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    /**
     *请求处理后重定向
     * **/
    @Test
    public void shouldProcessRegistration()throws Exception {
        SpitterController controller = new SpitterController();
        MockMvc  mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spitter/register"))
                .andExpect(redirectedUrl("/spitter/jbauer"));
    }
}
