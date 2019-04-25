package com.service.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@NodeEntity(label = "User")
public class UserNode implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "userCode")
    private String userCode;
    @Property(name = "userName")
    private String userName;
//    @Property(name = "password")
    private String password;
    public UserNode() {}
    public UserNode(Long id, String userCode, String userName, String password) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
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
