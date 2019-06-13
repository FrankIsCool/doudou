package com.service.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@NodeEntity(label = "User")
public class UserNode implements Serializable {
    //用户状态 --正常
    public static final String USER_STATE_NORMAL = "1";
    //用户状态 --冻结
    public static final String USER_STATE_FREEZE = "2";
    //用户状态 --异常
    public static final String USER_STATE_ABNORMAL = "3";
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "userCode")
    private String userCode;
    @Property(name = "userName")
    private String userName;
    @Property(name = "password")
    private String password;
    @Property(name = "state")
    private String state;
    public UserNode() {}
    public UserNode(Long id, String userCode, String userName, String password, String state) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
