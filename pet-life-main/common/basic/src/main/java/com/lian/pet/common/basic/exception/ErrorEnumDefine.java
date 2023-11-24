package com.lian.pet.common.basic.exception;

/**

 * @Date: 2021/11/1 19:39
 * @Description: 错误定义接口
 */
public interface ErrorEnumDefine {
    /**
     * 错误码
     * @return
     */
    long getCode();

    /**
     * 本地化错误码标识
     * @return
     */
    String getLocaleCode();

    /**
     * 默认的消息
     * @return
     */
    String getDefMsg();
}
