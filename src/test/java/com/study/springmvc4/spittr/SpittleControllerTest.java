package com.study.springmvc4.spittr;

import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.data.SpittleRepository;
import com.study.springmvc4.spittr.web.SpittleController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpittleControllerTest {

    @Test
    public void shouldShowRecentSpittles()throws Exception{
      /*  List<Spittle> exceptSpittles = createSpittleList(20);
        SpittleRepository mockRepository = mock(SpittleRepository.class);

        when(mockRepository.findSpittles(Long.MAX_VALUE,20)).thenRutern();
        SpittleController controller = new SpittleController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).setSingleView(
                new InternalResourceView("/WEB-INF/views/spittles.jsp")
        ).build();
        mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList",hasItems(exceptSpittles.toArray())));*/
    }

    public List<Spittle> createSpittleList(int count){
        List<Spittle> spittles = new ArrayList<>();
        for (int i=0;i<count;i++){
            spittles.add(new Spittle("Spittle-"+i,new Date()));
        }
        return spittles;
    }
}
