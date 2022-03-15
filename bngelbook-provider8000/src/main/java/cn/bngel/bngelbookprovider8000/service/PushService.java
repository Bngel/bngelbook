package cn.bngel.bngelbookprovider8000.service;

public interface PushService {

    void pushMsgToUserById(Long id, String msg);

    void pushMsgToAllUsers(String msg);
}
