package view;

import presenter.GuestPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestView implements GuestUserInterface {

    private JFrame frame;
    private JLabel titleLabel;
    private JComboBox comboBox;
    private JButton filterButton;
    private JTextField filterField;
    private JButton loginButton;
    private JTable artWorksTable;
    String[] head = {"Id","Name", "Artist", "Year", "Type", "Art Gallery"};
    Object[][] data = new Object[100][6];


    public GuestView(){

        frame = new JFrame("Guest");
        frame.setSize(700, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 227, 255 ));
        GuestPresenter guestPresenter=new GuestPresenter(this);
        comboBox=new JComboBox();
        comboBox.setBounds(50, 400, 120, 30);
        comboBox.setFont(new Font("Verdana", Font.BOLD, 15));
        comboBox.setBackground(new Color(152, 178, 255));
        comboBox.addItem("Type");
        comboBox.addItem("Artist");

        filterField = new JTextField();
        filterField.setBounds(175, 400, 180, 30);
        filterField.setFont(new Font("Verdana", Font.BOLD, 15));
        filterField.setBackground(new Color(152, 178, 255));

        titleLabel = new JLabel("ART WORKS");
        titleLabel.setBounds(250, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(530, 400, 120, 30);
        loginButton.setFont(new Font("Verdana", Font.BOLD, 20));
        loginButton.setBackground(new Color(100, 135, 242  ));

        filterButton = new JButton("FILTER");
        filterButton.setBounds(360, 400, 120, 30);
        filterButton.setFont(new Font("Verdana", Font.BOLD, 20));
        filterButton.setBackground(new Color(100, 135, 242  ));

        guestPresenter.showData();


        frame.setVisible(true);
        frame.add(comboBox);
        frame.add(titleLabel);
        frame.add(filterButton);
        frame.add(loginButton);
        frame.add(filterField);
        addPlaceHolderToFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addPlaceHolderToFields() {

        GuestPresenter guestPresenter=new GuestPresenter(this);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               guestPresenter.changeToLogin();

            }
        });
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guestPresenter.filterData();

            }
        });

    }

    public void paintTable( Object[][] data){
        artWorksTable = new JTable(data, head);
        JScrollPane scrollPane = new JScrollPane(artWorksTable);
        scrollPane.setBounds(50, 70, 600, 300);
        frame.add(scrollPane);

    }
    @Override
    public void changeToLogin(){
        try {
            new LoginView();
        }catch (Exception e){
        }
        frame.dispose();
    }

    @Override
    public void writeMessageOptionPanel(String mesaj) {
        JOptionPane.showMessageDialog(this.frame, mesaj);
    }

    @Override
    public String accessComboBox() {
        return this.comboBox.getSelectedItem().toString();
    }

    @Override
    public String accessFilterField() {
        return this.filterField.getText();
    }
}
