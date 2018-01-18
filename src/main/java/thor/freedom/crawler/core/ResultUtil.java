package thor.freedom.crawler.core;

import thor.freedom.crawler.bean.common.Result;
import thor.freedom.crawler.bean.common.ResultCode;

/**
 * Created by Thor on 2018/1/18.
 */
public class ResultUtil {
    //**************************************** success ****************************************//
    public static <D> Result<D> success() {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_SUCCESS.getCode());
        result.setData(null);
        result.setMsg(ResultCode.DEFAULT_SUCCESS.getMsg());

        return result;
    }

    public static <D> Result<D> success(D data) {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_SUCCESS.getCode());
        result.setData(data);
        result.setMsg(ResultCode.DEFAULT_SUCCESS.getMsg());

        return result;
    }

    public static <D> Result<D> success(D data, String message) {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_SUCCESS.getCode());
        result.setData(data);
        result.setMsg(message);

        return result;
    }

    //**************************************** error ****************************************//
    public static <D> Result<D> error() {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_ERROR.getCode());
        result.setData(null);
        result.setMsg(ResultCode.DEFAULT_ERROR.getMsg());

        return result;
    }

    public static <D> Result<D> error(ResultCode resultCodeMsg) {
        Result<D> result = new Result<>();

        result.setCode(resultCodeMsg.getCode());
        result.setData(null);
        result.setMsg(resultCodeMsg.getMsg());

        return result;
    }

    public static <D> Result<D> error(D data) {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_ERROR.getCode());
        result.setData(data);
        result.setMsg(ResultCode.DEFAULT_ERROR.getMsg());

        return result;
    }

    public static <D> Result<D> error(ResultCode resultCodeMsg, D data) {
        Result<D> result = new Result<>();

        result.setCode(resultCodeMsg.getCode());
        result.setData(data);
        result.setMsg(resultCodeMsg.getMsg());

        return result;
    }

    //**************************************** default ****************************************//
    public static <D> Result<D> defaultError(String msg) {
        Result<D> result = new Result<>();

        result.setCode(ResultCode.DEFAULT_ERROR.getCode());
        result.setData(null);
        result.setMsg(msg);

        return result;
    }
}
