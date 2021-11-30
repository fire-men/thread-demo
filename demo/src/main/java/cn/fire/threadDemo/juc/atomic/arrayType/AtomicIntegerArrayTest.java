package cn.fire.threadDemo.juc.atomic.arrayType;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 15:04:45
 * @Description AtomicIntegerArray类
 */
public class AtomicIntegerArrayTest {
    private static AtomicIntegerArray aia = new AtomicIntegerArray(10);

    public static void main(String[] args) {
        store();

        int currentValueByIndex = aia.get(1);
        System.out.println("currentValueByIndex = "+currentValueByIndex);

        //i++
        int oldValue = aia.getAndIncrement(1);
        System.out.println("oldValue = "+oldValue);
        System.out.println("newValue = "+aia.get(1));

        //i=i+3
        System.out.println(aia);
        int oldValue2 = aia.getAndAdd(1, 3);
        System.out.println("oldValue2 = "+oldValue2);
        System.out.println("newValue2 = "+aia.get(1));

        int oldValue3 = aia.getAndSet(1, 2);
        System.out.println("oldValue3 = "+oldValue3);
        System.out.println("newValue3 = "+aia.get(1));

        //i--
        int oldValue4 = aia.getAndDecrement(1);
        System.out.println("oldValue4 = "+oldValue4);
        System.out.println("newValue4 = "+aia.get(1));


    }

    private static void store() {
        aia.set(0,1);
        aia.set(1, 2);
        aia.set(2,3);
        aia.set(3,4);
        aia.set(4, 5);
    }
}
