/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.model.product;

import cc.iotkit.model.Owned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Owned<String> {

    private String id;

    private String name;

    private String category;

    private Integer nodeType;

    /**
     * 所属平台用户ID
     */
    private String uid;

    private String img;

    /**
     * 是否透传
     */
    private Boolean transparent;

    private Long createAt;

    public boolean isTransparent() {
        return transparent != null && transparent;
    }
}