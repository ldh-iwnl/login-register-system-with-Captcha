package hk.hku.entity;

public class HkuUserEntity {
    private Integer id;
    private String userName;
    private String userPwd;

    public HkuUserEntity(Integer id, String userName, String userPwd) {
        this.id = id;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
