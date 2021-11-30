package cn.fire.threadDemo.juc.atomic.referenceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author zhangle
 * @CreateTime 2021-11-30 15:34:30
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private String userName;
    private Integer id;
}
