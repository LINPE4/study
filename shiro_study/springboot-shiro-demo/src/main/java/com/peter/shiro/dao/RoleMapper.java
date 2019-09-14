package com.peter.shiro.dao;

import com.peter.shiro.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {



    @Select("")
    List<Role> findRoleListByUserId(@Param("userId") int userId);


}
