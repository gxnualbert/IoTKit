/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.data.model;

import cc.iotkit.model.OauthClient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OauthClientMapper {

    OauthClientMapper M = Mappers.getMapper(OauthClientMapper.class);

    OauthClient toDto(TbOauthClient vo);

    TbOauthClient toVo(OauthClient dto);
}
