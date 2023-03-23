package model.persistence;

import model.User;

import java.util.ArrayList;

public class UserPersistence extends AbstractPersistence<User>{
    public User findById(int id){
        ArrayList<User> users= (ArrayList<User>) readAll();
        for(User user: users){
            if(user.getIdUser()==id)
                return user;
        }
        return null;
    }
    public User findByUsername(String username){
        ArrayList<User> users= (ArrayList<User>) readAll();
        for(User user: users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }
}
