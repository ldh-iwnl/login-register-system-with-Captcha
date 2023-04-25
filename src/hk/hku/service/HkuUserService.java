package hk.hku.service;

import hk.hku.dao.HkuUserDao;
import hk.hku.entity.HkuUserEntity;

public class HkuUserService {
    private HkuUserDao hkuUserDao = new HkuUserDao();

    public HkuUserEntity login(String userName, String userPwd){
        return hkuUserDao.login(userName,userPwd);
    }

    public int register(String userName, String userPwd){
        return hkuUserDao.register(userName,userPwd);
    }

    public HkuUserEntity findByUsername(String userName){
        return hkuUserDao.findByUsername(userName);
    }
}
