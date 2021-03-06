package cn.bngel.bngelbookfriendprovider8009.service;

import cn.bngel.bngelbookcommonapi.bean.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendService {

    Integer addFriend(Friend friend);

    Integer deleteFriendById(Long id);

    Integer deleteFriendByFriend(Friend friend);

    Integer updateFriendById(Friend friend);

    Friend getFriendById(Long id);

    List<Friend> getFriendsByUserId(Long userId);

    Integer judgeFriendExists(Long user1Id, Long user2Id);
}
