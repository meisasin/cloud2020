package net.yisasin.springcloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success = false;          // 返回是否成功
    private String msg = "";                  // 返回信息
    private T obj = null;                // 返回其他对象信息


    // ==========  static methods

    public static <T> CommonResult<T> error() {
        return error("操作失败");
    }

    public static <T> CommonResult<T> error(String message) {
        return new CommonResult<T>(false, message, null);
    }


    public static <T> CommonResult<T> success() {
        return success("操作成功", null);
    }

    public static <T> CommonResult<T> success(T obj) {
        return success("操作成功", obj);
    }

    public static <T> CommonResult<T> success(String message, T obj) {
        return new CommonResult<T>(true, message, obj);
    }

}
