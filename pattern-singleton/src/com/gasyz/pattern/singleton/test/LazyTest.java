package com.gasyz.pattern.singleton.test;

import com.gasyz.pattern.singleton.lazy.LazyFour;
import com.gasyz.pattern.singleton.lazy.LazyOne;
import com.gasyz.pattern.singleton.lazy.LazyThree;
import com.gasyz.pattern.singleton.lazy.LazyTwo;

/**
 * Created by gaoang on 2018/3/16.
 */
public class LazyTest {

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        for (int i=0;i<200000000;i++) {
            LazyOne lazyOne = LazyOne.getInstance();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("时间1："+(end1-start1));

        long start2 = System.currentTimeMillis();
        for (int i=0;i<200000000;i++) {
            LazyTwo lazyTwo = LazyTwo.getInstance();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("时间2："+(end2-start2));

        long start3 = System.currentTimeMillis();
        for (int i=0;i<200000000;i++) {
            LazyThree lazyThree = LazyThree.getInstance();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("时间3："+(end3-start3));

        long start4 = System.currentTimeMillis();
        for (int i=0;i<200000000;i++) {
            LazyFour lazyFour = LazyFour.getInstance();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("时间4："+(end4-start4));

    }
}
