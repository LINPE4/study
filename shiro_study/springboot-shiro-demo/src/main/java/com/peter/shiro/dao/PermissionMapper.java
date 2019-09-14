package com.peter.shiro.dao;

import com.peter.shiro.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {


    @Select("")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);

}
