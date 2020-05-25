package com.skpl.mailapp;

import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.service.DiaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertEquals;

class MyThread extends Thread{  // 继承Thread类，作为线程的实现类
    private String name ;       // 表示线程的名称
    public MyThread(String name){
        this.name = name ;      // 通过构造方法配置name属性
    }
    public void run(){  // 覆写run()方法，作为线程 的操作主体
        for(int i=0;;i++){
        }
    }
};
public class ThreadTest{
    static int  go() {
        MyThread mt1 = new MyThread("线程A ") ;    // 实例化对象
        MyThread mt2 = new MyThread("线程B ") ;    // 实例化对象
        mt1.start() ;   // 调用线程主体
        mt2.start() ;   // 调用线程主体
        return 1;
    }
    public static void main(String args[]){
        System.out.println(go());
    }
};
