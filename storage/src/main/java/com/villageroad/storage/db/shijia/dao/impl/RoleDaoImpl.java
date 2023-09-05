package com.villageroad.storage.db.shijia.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.villageroad.storage.db.shijia.dao.RoleDao;
import com.villageroad.storage.db.shijia.entity.Role;
import com.villageroad.storage.db.shijia.mapper.AccountMapper;
import com.villageroad.storage.db.shijia.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houshengbin
 */
@Service
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements RoleDao {


}
