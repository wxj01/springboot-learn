package com.wxj.springboot.mybatisplus;

import java.util.concurrent.atomic.AtomicReference;

public class Test002 {

    public static void main(String[] args) {


        AtomicReference<Long> atomicReference = new AtomicReference<>();

        while (atomicReference.compareAndSet(null,1L)){

            System.out.println(atomicReference.get());

            int a = 1/0;

            atomicReference.compareAndSet(1L,null);

            System.out.println(atomicReference.get());
        }
    }
}
