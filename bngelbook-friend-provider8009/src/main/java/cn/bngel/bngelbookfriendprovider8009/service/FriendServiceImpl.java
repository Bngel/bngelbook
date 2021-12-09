package cn.bngel.bngelbookfriendprovider8009.service;

import cn.bngel.bngelbookcommonapi.bean.Friend;
import cn.bngel.bngelbookfriendprovider8009.dao.FriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendDao friendDao;

    @Override
    public Integer addFriend(Friend friend) {
        if (friend.getUser1Id().equals(friend.getUser2Id()))
            return -2;
        Integer friendCount = friendDao.judgeFriendExists(friend.getUser1Id(), friend.getUser2Id());
        if (friendCount != 0) {
            return -1;
        }
        else {
            return friendDao.addFriend(friend);
        }
    }

    @Override
    public Integer deleteFriendById(Long id) {
        return friendDao.deleteFriendById(id);
    }

    @Override
    public Integer deleteFriendByFriend(Friend friend) {
        return friendDao.deleteFriendByFriend(friend);
    }

    @Override
    public Integer updateFriendById(Friend friend) {
        return friendDao.updateFriendById(friend);
    }

    @Override
    public Friend getFriendById(Long id) {
        return friendDao.getFriendById(id);
    }

    @Override
    public List<Friend> getFriendsByUserId(Long userId) {
        return friendDao.getFriendsByUserId(userId);
    }

    @Override
    public Integer judgeFriendExists(Long user1Id, Long user2Id) {
        return friendDao.judgeFriendExists(user1Id, user2Id);
    }


}
