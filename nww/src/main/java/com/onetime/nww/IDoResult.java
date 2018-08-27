package com.onetime.nww;

/**
 * 做事结果
 *
 * Created by Jin Liang on 2018/8/22.
 */
public interface IDoResult<T> {
    void onSuccess(T t);

    void onFail(String msg);
}
