package cn.fire.threadDemo.juc.atomic.fieldUpdaterType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 15:54:59
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    //private Long id;
    volatile long id;
    private String goodsName;
}
