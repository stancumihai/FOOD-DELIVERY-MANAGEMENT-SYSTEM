package org.stancu.model.userModel;

import java.io.Serializable;

public class Client extends User implements Serializable {

    private Integer id;
    private Integer userId;

    public Client() {

    }

    public Client(Integer userId) {
        this.userId = userId;
    }

    public Client(Integer id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public Client(Integer id, String userName, String password, String email) {
        super(id, userName, password, email);
    }

    public Client(Integer id, String userName, String password, String email, Integer id1, Integer userId) {
        super(id, userName, password, email);
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
        return "Client " +
                "id :" + id +
                ", userId " + userId + "\n";
    }
}
