package cn.bngel.bngelbookuserprovider8001.service;

public interface PushService {

    void pushMsgToUserById(Long id, String msg);

    void pushMsgToAllUsers(String msg);
}
