package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
     @Id
     @Column(unique = true, nullable = false)
     private int idUser;
     private String name;
     private String username;
     private String password;
     private String userType;
     private String email;

     public User(){}

     public User(int idUser, String name, String username, String password, String userType, String email) {
          this.idUser = idUser;
          this.name = name;
          this.username = username;
          this.password = password;
          this.userType = userType;
          this.email = email;
     }

     public String getUserType() {
          return userType;
     }

     public void setUserType(String userType) {
          this.userType = userType;
     }

     public int getIdUser() {
          return idUser;
     }

     public void setIdUser(int idUser) {
          this.idUser = idUser;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }
}
