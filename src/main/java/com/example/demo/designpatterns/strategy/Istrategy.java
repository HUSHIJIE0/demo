package com.example.demo.designpatterns.strategy;

/**
*@Author: Michael
*@date: 21:34 2019/4/21
*@Description: 首先定一个策略接口，这是诸葛亮老人家给赵云的三个锦囊妙计的接口
 * *
*/
public interface Istrategy {

    //每个锦囊妙计都是一个可执行的算法
    void operate();
}
