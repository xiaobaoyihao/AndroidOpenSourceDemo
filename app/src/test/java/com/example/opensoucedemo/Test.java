package com.example.opensoucedemo;

/**
 * Created by dingbaosheng on 12/9/20 3:02 PM.
 */
public class Test {


    @org.junit.Test
    public void test() {

        Long a = Long.valueOf(123);
        Long b = Long.valueOf(123);


        assert a == b;
    }
}
