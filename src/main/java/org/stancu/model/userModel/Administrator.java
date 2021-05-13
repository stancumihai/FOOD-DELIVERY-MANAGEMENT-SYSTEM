package org.stancu.model.userModel;

import java.io.Serializable;

public class Administrator extends User implements Serializable {

    private Integer id;
    private Integer userId;


    public Administrator(){

    }

    public Administrator(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public Administrator(Integer userId) {
        this.userId = userId;
    }

    public Administrator(Integer id, String userName, String password,String email) {
        super(id, userName, password,email);
    }

    public Administrator(Integer id, String userName, String password,String email, Integer id1, Integer userId) {
        super(id, userName, password,email);
        this.id = id1;
        this.userId = userId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
