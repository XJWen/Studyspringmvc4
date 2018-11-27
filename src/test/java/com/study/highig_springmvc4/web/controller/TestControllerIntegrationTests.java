package com.study.highig_springmvc4.web.controller;

import com.study.highig_springmvc4.MySpringMVCConfig;
import com.study.highig_springmvc4.service.DemoTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MySpringMVCConfig.class})
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {
    public MockMvc mockMvc;

    @Autowired
    private DemoTestService service;

    @Autowired
    WebApplicationContext context;

    @Autowired
    MockHttpSession session;

    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setup(){
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(this.context).build();//初始化
    }

    @Test
    public void testNormalController()throws Exception{
        mockMvc.perform(get("/normal"))
                .andExpect(status().isOk())//预期返回状态为200
                .andExpect(view().name("page"))//预期view名称为"page"
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))
                .andExpect(model().attribute("msg", service.saySomething()));
    }

    @Test
    public void testRestController() throws Exception{
        mockMvc.perform(get("/testRest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(service.saySomething()));
    }
}
