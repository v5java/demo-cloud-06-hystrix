package com.jadyer.demo.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
class CalculatorService {
    @Resource
    private RestTemplate restTemplate;

    //指定断路后的回调方法（回调方法必须与原方法参数类型相同、返回值类型相同、方法名可以不同）
    @HystrixCommand(fallbackMethod="addServiceToFallback")
    int addService(int a, int b){
        String reqURL = "http://CalculatorServer/add?a=" + a + "&b=" + b;
        return restTemplate.getForEntity(reqURL, Integer.class).getBody();
    }

    public int addServiceToFallback(int aa, int bb){
        return -999;
    }
}