package cn.ds.double_live_demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String code;
    private String msg;
    private T result;

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.result = null;
    }
}
