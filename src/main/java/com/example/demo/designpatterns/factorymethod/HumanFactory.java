package com.example.demo.designpatterns.factorymethod;


import com.fasterxml.jackson.databind.util.ClassUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @program: demo
 * @description: 八卦炉
 *
 * 今天讲女娲造人的故事，这个故事梗概是这样的：
 * * 很久很久以前，盘古开辟了天地，用身躯造出日月星辰、山川草木，天地一片繁华
 * * One day，女娲下界走了一遭，哎！太寂寞，太孤独了，没个会笑的、会哭的、会说话的东东
 * * 那怎么办呢？不用愁，女娲，神仙呀，造出来呀，然后捏泥巴，放八卦炉（后来这个成了太白金星的宝
 * 贝）中烤，于是就有了人：
 * * 我们把这个生产人的过程用Java程序表现出来：
 * @author: Michael
 * @create: 2019-04-22 21:53
 */
public class HumanFactory {

    //定义一个MAP,初始化过的Human对象都放在这里
    private static HashMap<String,Human> humans = new HashMap<String,Human>();


    //定一个烤箱，泥巴塞进去，人就出来，这个太先进了
    public static Human createHuman(Class c){
        Human human=null; //定义一个类型的人类

        try {
            //========以下延迟初始化======
            //如果MAP中有，则直接从取出，不用初始化了
            if(humans.containsKey(c.getSimpleName())){
                human = humans.get(c.getSimpleName());
            }else{
                human = (Human)Class.forName(c.getName()).newInstance();
                //放到MAP中
                humans.put(c.getSimpleName(), human);
            }
            //=========延迟初始化结束=========

//            human = (Human)Class.forName(c.getName()).newInstance(); //产生一个人类
        } catch (InstantiationException e) {//你要是不说个人类颜色的话，没法烤，要白的黑，你说话了才好烤
            System.out.println("必须指定人类的颜色");
        } catch (IllegalAccessException e) { //定义的人类有问题，那就烤不出来了，这是...您的设计模式
            System.out.println("人类定义错误！");
        } catch (ClassNotFoundException e) { //你随便说个人类，我到哪里给你制造去？！
            System.out.println("混蛋，你指定的人类找不到！");
        }
        return human;
    }

    //女娲生气了，把一团泥巴塞到八卦炉，哎产生啥人类就啥人类
    public static Human createHuman(){
        Human human=null; //定义一个类型的人类

        //首先是获得有多少个实现类，多少个人类
        List<Class<?>> concreteHumanList = ClassUtils.getAllClassByInterface(Human.class); //定义了多少人类
        //八卦炉自己开始想烧出什么人就什么人
        Random random = new Random();
        int rand = random.nextInt(concreteHumanList.size());

        human = createHuman(concreteHumanList.get(rand));

        return human;
    }

}
