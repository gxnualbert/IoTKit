/*
 * +----------------------------------------------------------------------
 * | Copyright (c) 奇特物联 2021-2022 All rights reserved.
 * +----------------------------------------------------------------------
 * | Licensed 未经许可不能去掉「奇特物联」相关版权
 * +----------------------------------------------------------------------
 * | Author: xw2sy@163.com
 * +----------------------------------------------------------------------
 */
package cc.iotkit.data.service;

import cc.iotkit.data.IOauthClientData;
import cc.iotkit.data.dao.OauthClientRepository;
import cc.iotkit.data.model.OauthClientMapper;
import cc.iotkit.model.OauthClient;
import cc.iotkit.model.Paging;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service
public class OauthClientDataImpl implements IOauthClientData {

    @Autowired
    private OauthClientRepository oauthClientRepository;

    @Override
    public OauthClient findByClientId(String clientId) {
        return OauthClientMapper.M.toDto(oauthClientRepository.findByClientId(clientId));
    }

    @Override
    public OauthClient findById(String s) {
        return OauthClientMapper.M.toDto(oauthClientRepository.findById(s).orElse(null));
    }

    @Override
    public OauthClient save(OauthClient data) {
        if (StringUtils.isBlank(data.getId())) {
            data.setId(UUID.randomUUID().toString());
            data.setCreateAt(System.currentTimeMillis());
        }
        oauthClientRepository.save(OauthClientMapper.M.toVo(data));
        return data;
    }

    @Override
    public OauthClient add(OauthClient data) {
        return save(data);
    }

    @Override
    public void deleteById(String s) {
        oauthClientRepository.deleteById(s);
    }

    @Override
    public long count() {
        return oauthClientRepository.count();
    }

    @Override
    public List<OauthClient> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Paging<OauthClient> findAll(int page, int size) {
        return new Paging<>();
    }
}
