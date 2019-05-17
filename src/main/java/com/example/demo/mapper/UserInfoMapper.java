package com.example.demo.mapper;

import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);
    int insertSelective(UserInfo record);
    UserInfo selectByPrimaryKey(UserInfo userId);

    List<UserInfo> findList(UserInfo userInfo);

}
