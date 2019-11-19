package com.dongtian.shopmember.dao;

import com.dongtian.shopcommon.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserDao {
    String TABLE_NAME = "user";
    String INSET_FIELDS = " username, password, salt, phone, email, create_time, update_time  ";
    String SELECT_FIELDS = " id, username, password, salt, phone, email, create_time, update_time ";

    //增加
    @Insert({"insert into ", TABLE_NAME, "(", INSET_FIELDS,
            ") values (#{username},#{password},#{salt},#{phone},#{email},#{create_time},#{update_time})"})
    int addUser(User user);

    //通过id查
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(Long id);

    //通过username查
    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username=#{username}"})
    User selectByName(String username);

    //更新密码
    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    //通过id删除
    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
