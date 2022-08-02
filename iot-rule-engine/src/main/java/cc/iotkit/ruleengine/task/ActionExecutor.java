/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.ruleengine.task;

/**
 * 动作执行器接口
 */
public interface ActionExecutor<T> {

    /**
     * 动作名
     */
    String getName();

    /**
     * 执行动作
     */
    void execute(String data);

}
