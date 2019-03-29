package com.study.springmvc4.spittr.api;

import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 新REST控制器
 * **/
@Controller
@RequestMapping("/spittles")
public class SpittleRESTController {
    private static final String MAX_LONG_AS_ASTRING="9223372036854775807";

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleRESTController(SpittleRepository spittleRepository){
        this.spittleRepository = spittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max",defaultValue = MAX_LONG_AS_ASTRING)long max,
            @RequestParam(value = "count",defaultValue = "20")int count
    )
    {
        return spittleRepository.findSpittles(max,count);
    }
}
