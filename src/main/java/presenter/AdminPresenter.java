package presenter;

import model.User;
import model.persistence.UserPersistence;
import view.UserInterface;

import java.util.ArrayList;

public class AdminPresenter {
    UserPersistence userPersistence;
    UserInterface userInterface;
    public AdminPresenter(UserInterface userInterface){
        this.userInterface = userInterface;
        userPersistence=new UserPersistence();
    }

    public void showData(){
        Object[][] data=new Object[100][6];
        ArrayList<User> users = (ArrayList<User>) userPersistence.readAll();
        for (int i = 0; i < 100; i++) {
            data[i][0] = "";
            data[i][1] = "";
            data[i][2] = "";
            data[i][3] = "";
            data[i][4] = "";
            data[i][5] = "";
        }
        int index=0;
        for(User user: users){
            data[index][0]= user.getIdUser();
            data[index][1] = user.getName();
            data[index][2] = user.getUsername();
            data[index][3] = user.getPassword();
            data[index][4] = user.getUserType();
            data[index][5] = user.getEmail();
            index++;
        }
        this.userInterface.paintTable(data);
    }

    public void insertUserData(){
        String[] col=this.userInterface.getSelectedInfo();
        ArrayList<User> users = (ArrayList<User>) userPersistence.readAll();
        User user = new User((users.size() + 1), col[1], col[2], col[3], col[4], col[5]);
        if(col[4].equals("Employee")||col[4].equals("Admin")) {
            this.userPersistence.insert(user);
            this.userInterface.writeMessageOptionPanel("Insert done!!");
        }
        else this.userInterface.writeMessageOptionPanel("User type should be Employee or Admin!");
        refresh();
    }

    public void updateUserData(){
        String[] col=this.userInterface.getSelectedInfo();
        User user=userPersistence.findById(Integer.parseInt(col[0]));
        user.setName(col[1]);
        user.setUsername(col[2]);
        user.setPassword(col[3]);
        user.setUserType(col[4]);
        user.setEmail(col[5]);
        if(col[4].equals("Employee")||col[4].equals("Admin")) {
            this.userPersistence.update(user);
            this.userInterface.writeMessageOptionPanel("Update done!!");
        }
        else this.userInterface.writeMessageOptionPanel("User type should be Employee or Admin!");
        refresh();

    }
    public void deleteUserData(){
        String[] col=this.userInterface.getSelectedInfo();
        User user=userPersistence.findById(Integer.parseInt(col[0]));
        this.userPersistence.delete(user);
        this.userInterface.writeMessageOptionPanel("Delete done!!");
        refresh();

    }

    public void changeToGuestView() {
        this.userInterface.changeViewtoGuest();
    }
    public void refresh() {
        this.userInterface.refresh();
    }

}
