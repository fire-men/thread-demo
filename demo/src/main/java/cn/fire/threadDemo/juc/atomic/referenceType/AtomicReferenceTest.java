package cn.fire.threadDemo.juc.atomic.referenceType;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 15:31:10
 * @Description AtomicReference类
 */
public class AtomicReferenceTest {
    private static AtomicReference<User> ar = new AtomicReference();

    @SneakyThrows
    public static void main(String[] args) {
        store();
        System.out.println("after store value ："+ar.get());
        User newUser = new User("韩信", 1);
        User oldValue = ar.getAndSet(newUser);
        System.out.println("oldValue = "+oldValue);
        System.out.println("newValue = "+ar.get());

        //cas 当前内存中的值与预期的值比较，相等，则更新，否则，不更新
        User expectUser = new User("韩信",1);
        User expectUser2 = newUser;
        User updateUser = new User("关羽",2);
        boolean flag = ar.compareAndSet(expectUser2, updateUser);
        System.out.println(flag == true?"可以进行更新":"不可以进行更新");


    }

    private static void store() {
        ar.set(new User("马超",1));
    }
}
