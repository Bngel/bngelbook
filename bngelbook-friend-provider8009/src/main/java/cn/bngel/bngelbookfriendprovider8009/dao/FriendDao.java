package cn.bngel.bngelbookfriendprovider8009.dao;

import cn.bngel.bngelbookcommonapi.bean.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendDao {

    Integer addFriend(@Param("friend") Friend friend);

    Integer deleteFriendById(@Param("id") Long id);

    Integer updateFriendById(@Param("friend") Friend friend);

    Friend getFriendById(@Param("id") Long id);

    List<Friend> getFriendsByUserId(@Param("userId") Long userId);

    Integer deleteFriendByUserId(@Param("friend") Friend friend);

    Integer judgeFriendExists(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
}
