package cn.fire.threadDemo.create;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangle
 * @CreateTime 2021-11-29 15:37:59
 * @Description 实现Callable接口
 */
public class Test3 {

    static class ImplementCallable implements Callable<Integer> {
        Integer sum = 0;

        public Integer call() throws Exception {
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<Integer>(new ImplementCallable());
        task.run();

        Integer result = task.get();
        System.out.println(result);

    }
}
