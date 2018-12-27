package com.study.springmvc4.spittr.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {

    @Test
    public void testHomePage() throws Exception{
        HomeController home = new HomeController();
//        Assert.assertEquals("home",home.home());
        //MockMvC针对控制器执行HTTP请求的机制
        MockMvc mock = standaloneSetup(home).build();
        //发起了get请求，并断言结果视图的名称为home
        mock.perform(get("/")).andExpect(view().name("home"));
    }
}
