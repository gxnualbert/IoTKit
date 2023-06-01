package cc.iotkit.data.service;

import cc.iotkit.data.dao.IJPACommData;
import cc.iotkit.data.dao.SysRoleDeptRepository;
import cc.iotkit.data.model.TbSysDept;
import cc.iotkit.data.model.TbSysRoleDept;
import cc.iotkit.data.system.ISysRoleDeptData;
import cc.iotkit.data.util.PredicateBuilder;
import cc.iotkit.model.system.SysRoleDept;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static cc.iotkit.data.model.QTbSysRoleDept.tbSysRoleDept;

/**
 * author: 石恒
 * date: 2023-05-30 16:20
 * description:
 **/
@Primary
@Service
@RequiredArgsConstructor
public class SysRoleDeptDataImpl implements ISysRoleDeptData, IJPACommData<SysRoleDept, Long> {


    private final SysRoleDeptRepository sysRoleDeptRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void deleteByRoleId(List<Long> roleIds) {
        jpaQueryFactory.delete(tbSysRoleDept).where(PredicateBuilder.instance().and(tbSysRoleDept.roleId.in(roleIds)).build());
    }

    @Override
    public long insertBatch(List<SysRoleDept> list) {
        return 0;
    }

    @Override
    public JpaRepository getBaseRepository() {
        return sysRoleDeptRepository;
    }

    @Override
    public Class getJpaRepositoryClass() {
        return TbSysRoleDept.class;
    }
}
