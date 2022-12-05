import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Employee {
    private JPanel main;
    private JTextField txtStudent_Name;
    private JTextField txtDepartment;
    private JTextField txtClass;
    private JTextField txtPRN_No;
    private JTextField txtEmail;
    private JTextField txtMobile_No;
    private JButton saveButton;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtid;
    private JScrollPane table_1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student");
        frame.setContentPane(new Employee().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement pst;
    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/rbcompany", "root","Password@123");
            System.out.println("Successs");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    void tabel_load()
    {
        try
        {
            pst = con.prepareStatement("select * from Student");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public Employee() {
        connect();
        tabel_load();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String student_id,Department = null,Student_Name = null,Class = null,PRN_No = null,Email = null,Mobile_NO = null;
                txtStudent_Name.setText("");
                txtDepartment.setText("");
                txtClass.setText("");
                txtPRN_No.setText("");
                txtEmail.setText("");
                txtMobile_No.setText("");


                try {
                    pst = con.prepareStatement("insert into Student(Student_Name,Department,Class,PRN_No,Email,Mobile_No)values(?,?,?,?,?,?)");
                    pst.setString(1, Student_Name);
                    pst.setString(2, Department);
                    pst.setString(3, Class);
                    pst.setString(4, PRN_No);
                    pst.setString(5, Email);
                    pst.setString(6, Mobile_NO);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added Successfully!!!!!");
                    tabel_load();
                    txtStudent_Name.setText("");
                    txtDepartment.setText("");
                    txtClass.setText("");
                    txtPRN_No.setText("");
                    txtEmail.setText("");
                    txtMobile_No.setText("");
                    txtStudent_Name.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String studentid = txtid.getText();

                    pst = con.prepareStatement("select Student_Name,Department,Class,PRN_No,Email,Mobile_No from Student where id = ?");
                    pst.setString(1, studentid);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String Student_Name = rs.getString(1);
                        String emsalary = rs.getString(2);
                        String emmobile = rs.getString(3);

                        txtStudent_Name.setText("");
                        txtDepartment.setText("");
                        txtClass.setText("");
                        txtPRN_No.setText("");
                        txtEmail.setText("");
                        txtMobile_No.setText("");

                    }
                    else
                    {
                        txtStudent_Name.setText("");
                        txtDepartment.setText("");
                        txtClass.setText("");
                        txtPRN_No.setText("");
                        txtEmail.setText("");
                        txtMobile_No.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Employee No");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String student_id,Department = null,Student_Name = null,Class = null,PRN_No = null,Email = null,Mobile_NO = null;
                txtStudent_Name.setText("");
                txtDepartment.setText("");
                txtClass.setText("");
                txtPRN_No.setText("");
                txtEmail.setText("");
                txtMobile_No.setText("");
                txtStudent_Name.requestFocus();
                student_id= txtid.getText();

                try {

                    pst = con.prepareStatement("update employee set Student_Name = ?,Department = ?,Class = ?,PRN_No = ?,Email = ?,Mobile_NO = ? where id = ?");
                    pst.setString(1, Student_Name);
                    pst.setString(2, Department);
                    pst.setString(2, Class);
                    pst.setString(2, PRN_No);
                    pst.setString(2, Email);
                    pst.setString(3, Mobile_NO);
                    pst.setString(4, student_id);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Update Successfully!!!!!");
                    tabel_load();
                    txtStudent_Name.setText("");
                    txtDepartment.setText("");
                    txtClass.setText("");
                    txtPRN_No.setText("");
                    txtEmail.setText("");
                    txtMobile_No.setText("");
                    txtStudent_Name.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentid;
                studentid = txtid.getText();

                try {
                    pst = con.prepareStatement("delete from employee  where id = ?");

                    pst.setString(1, studentid);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
                    tabel_load();
                    txtStudent_Name.setText("");
                    txtDepartment.setText("");
                    txtClass.setText("");
                    txtPRN_No.setText("");
                    txtEmail.setText("");
                    txtMobile_No.setText("");
                    txtStudent_Name.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        }
        );
    }
}
