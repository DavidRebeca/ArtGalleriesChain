package presenter;

import model.ArtWork;
import model.User;
import model.persistence.ArtWorkPersistence;
import model.persistence.UserPersistence;
import org.hibernate.event.service.internal.EventListenerServiceInitiator;
import view.LoginInterfaceView;

import java.util.ArrayList;

public class LoginPresenter {
       UserPersistence userPersistence;
       LoginInterfaceView loginInterfaceView;

       public  LoginPresenter(){}

       public  LoginPresenter(LoginInterfaceView loginInterfaceView){
           userPersistence=new UserPersistence();
             this.loginInterfaceView=loginInterfaceView;
       }

    public void changeToGuestView(){
            this.loginInterfaceView.changeViewtoGuest();
       }
    public void logIn(){
        User user=userPersistence.findByUsername(loginInterfaceView.accessUsernameField());
        if(user!=null){
           if(checkLogIn(user)){
               if(user.getUserType().equals("Admin"))
                   this.loginInterfaceView.changeViewtoAdmin();
               else
                   this.loginInterfaceView.changeViewtoGuest();
           }
           else this.loginInterfaceView.writeMessageOptionPanel("Wrong password or username! Try again!");
        }
          else   this.loginInterfaceView.writeMessageOptionPanel("Wrong password or username! Try again!");

    }

     public boolean checkLogIn(User user){
           if(this.loginInterfaceView.accessPasswordField().equals(user.getPassword()))
               return true;
           return false;
       }
}
