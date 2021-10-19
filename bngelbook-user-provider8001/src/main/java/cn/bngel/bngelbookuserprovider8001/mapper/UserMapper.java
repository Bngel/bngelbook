package cn.bngel.bngelbookuserprovider8001.mapper;

import cn.bngel.bngelbookuserprovider8001.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from bngel_user where (" +
            "phone = #{account} or " +
            "email = #{account}) and " +
            "password = #{password}")
    User login(@Param("account") String account, @Param("password") String password);
}
