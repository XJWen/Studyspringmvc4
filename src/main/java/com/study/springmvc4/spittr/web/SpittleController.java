package com.study.springmvc4.spittr.web;

import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.data.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository repository;
    private static final String MAX_LONG = "0x7fffffffffffffffL";

    /**
     * 将SpittleRepository注入控制器中
     * **/
    @Autowired
    public SpittleController(SpittleRepository repository){
        this.repository = repository;
    }

    /**
     * 将spittle添加到model中
     * model为Map类型，可显式设置key值
     * **/
    @RequestMapping(method = RequestMethod.GET)
    public String spittles(Model model){
        model.addAttribute("spittleList",
                repository.findSpittles(Long.MAX_VALUE,20));
        //返回视图名
        return "spittles";
    }

    /**
     * <h2>查询参数</h2>
     * 含参请求处理，设置默认值
     * **/
    @RequestMapping(value = "/spittleList",method = RequestMethod.GET)
    public List<Spittle> spittles(
            @RequestParam(value = "max",defaultValue = MAX_LONG) long max,
            @RequestParam(value = "count",defaultValue = "20") int count){

        return repository.findSpittles(max,count);
    }

    /**
     * <h2>路径查询</h2>
     * 将参数变成请求路径 @PathVariable解析路径转变为参数
     * 如果@PathVariable的value值和参数名相同，可以省略value
     * 默认model的key值是从value的类型推断的小写形式
     * **/
    @RequestMapping(value = "/{spittleId}",method = RequestMethod.GET)
    public String spittles(
            @PathVariable long spittleId,
            Model model){

        model.addAttribute(repository.findOne(spittleId));
        return "spittle";
    }
}
