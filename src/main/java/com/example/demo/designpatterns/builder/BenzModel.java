package com.example.demo.designpatterns.builder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Michael
 * @Date: 2019/12/05/19:32
 * @Description: 奔驰车模型
 */
public class BenzModel extends CarModel {

    @Override
    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这个样子的...");
    }
    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎是这个声音的...");
    }

    @Override
    protected void start() {
        System.out.println("奔驰车跑起来是这个样子的...");
    }
    @Override
    protected void stop() {
        System.out.println("奔驰车应该这样停车...");
    }
}
