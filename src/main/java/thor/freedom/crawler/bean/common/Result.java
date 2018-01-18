package thor.freedom.crawler.bean.common;

import lombok.Data;

/**
 * Created by Thor on 2018/1/18.
 */
@Data
public class Result<T> {
    //错误码
    private String code;

    //错误描述
    private String msg;

    //数据
    private T data;
}