/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.model.rule;

import lombok.Data;

@Data
public class RuleAction {
    /**
     * 动作类型
     */
    protected String type;

    /**
     * 动作配置
     */
    protected String config;

}
