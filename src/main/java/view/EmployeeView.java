package view;

import presenter.EmployeePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame implements UserInterface {
    private JFrame frame;
    private JLabel titleLabel;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton guestButton;
    private JTable artWorksTable;

    String[] head = {"Id","Name", "Artist", "Year", "Type", "Art Gallery"};
    Object[][] data = new Object[100][6];

    public EmployeeView(){
        frame = new JFrame("Employee");
        frame.setSize(700, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(212, 227, 255 ));

        EmployeePresenter employeePresenter=new EmployeePresenter(this);

        titleLabel = new JLabel("ART WORKS");
        titleLabel.setBounds(250, 10, 200, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));

        createButton = new JButton("Create");
        createButton.setBounds(50, 400, 120, 30);
        createButton.setFont(new Font("Verdana", Font.BOLD, 20));
        createButton.setBackground(new Color(100, 135, 242  ));

        updateButton = new JButton("Update");
        updateButton.setBounds(175, 400, 120, 30);
        updateButton.setFont(new Font("Verdana", Font.BOLD, 20));
        updateButton.setBackground(new Color(100, 135, 242  ));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(300, 400, 120, 30);
        deleteButton.setFont(new Font("Verdana", Font.BOLD, 20));
        deleteButton.setBackground(new Color(100, 135, 242  ));


        guestButton = new JButton("Guest");
        guestButton.setBounds(530, 400, 120, 30);
        guestButton.setFont(new Font("Verdana", Font.BOLD, 20));
        guestButton.setBackground(new Color(100, 135, 242  ));


        employeePresenter.showData();

        frame.setVisible(true);
        frame.add(updateButton);
        frame.add(titleLabel);
        frame.add(createButton);
        frame.add(deleteButton);
        frame.add(guestButton);
        addPlaceHolderToFields();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void addPlaceHolderToFields() {

        EmployeePresenter employeePresenter=new EmployeePresenter(this);

        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.changeToGuestView();

            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.insertArtData();

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.updateArtData();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employeePresenter.deleteArtData();

            }
        });

    }

    @Override
    public String[] getSelectedInfo() {
        int row = this.artWorksTable.getSelectedRow();
        String[] col= new String[6];
        for(int i=0; i<6; i++)
            col[i]=this.artWorksTable.getValueAt(row, i).toString();
        return col;
    }

    @Override
    public void paintTable( Object[][] data) {
        artWorksTable = new JTable(data, head);
        JScrollPane scrollPane = new JScrollPane(artWorksTable);
        scrollPane.setBounds(50, 70, 600, 300);
        frame.add(scrollPane);
    }

    @Override
    public void changeViewtoGuest() {
        new GuestView();
        frame.dispose();
    }

    @Override
    public void refresh() {
        new EmployeeView();
        frame.dispose();
    }

    @Override
    public void writeMessageOptionPanel(String mesaj) {
        JOptionPane.showMessageDialog(this.frame, mesaj);
    }
}
