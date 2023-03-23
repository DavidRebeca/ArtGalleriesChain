package view;

public interface LoginInterfaceView {

    void changeViewtoGuest();
    void changeViewtoAdmin();
    void changeViewtoEmployee();
    String accessUsernameField();
    String accessPasswordField();
    void writeMessageOptionPanel(String mesaj);

}
