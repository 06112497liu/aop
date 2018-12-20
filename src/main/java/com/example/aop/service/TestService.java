package com.example.aop.service;

import com.example.aop.annotation.TimeUsed;
import org.springframework.stereotype.Service;

/**
 * @author liuweibo
 * @date 2018/12/20
 */
@Service
public class TestService {

    @TimeUsed(5L)
    public Integer method01(Integer integer) {
        System.out.println("我是切入点方法！！！");
        return 5;
    }
    @TimeUsed
    public Integer method02(Integer integer) {
        System.out.println("我是切入点方法！！！");
        return 5;
    }

}
