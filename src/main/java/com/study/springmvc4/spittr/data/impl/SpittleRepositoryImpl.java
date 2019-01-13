package com.study.springmvc4.spittr.data.impl;

import com.study.springmvc4.spittr.dao.Spittle;
import com.study.springmvc4.spittr.data.SpittleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, long count) {
        return null;
    }
    /**
     * unless属性只阻止将对象放入缓存，但方法调用时，依然会在缓存中去寻找，
     * 如果找到匹配的值，就会返回找到的值，如果没找到匹配值，返回结果会进缓存[result方法返回结果后做判断]
     * condition 表达式的结果为false的话，在方法调用时，缓存是禁用的。
     * 不会去缓存中查找，同时返回值也不会放入缓存中。[#id调用方法前做表达式判断]
     * **/
    @Override
    @Cacheable(value = "spittleCache",unless = "#result.message.contains('NoCache')",condition = "#id>=10")
    public Spittle findOne(long id) {
        return null;
    }

    @Override
    public void save(Spittle spittle) {

    }
    /**
     * @CacheEvict 能够应用在返回值为void的方法上，而@Cacheable和@CahcePut需要返回非void的值
     * 这个注解将条目从缓存中删除
     * **/
    @Override
    @CacheEvict("spittleCache")
    public void remove(Long spittleId) {

    }
}
