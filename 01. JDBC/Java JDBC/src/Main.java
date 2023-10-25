import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

class Main {

    public static void main(String args[]) {

            /*
            Driver: This is the interface that controls communication with the database server. It also withdraws information associated with driver objects.
            Driver Manager: It manages any required set of JDBC drivers
            */

        try {
            /*
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/jdbc", "root", "PASSWORD");

            if (con.isClosed()) {

                System.out.println("Connection Not Established");

            } else {
                System.out.println("Connection Established");

            }

            con.close();

            */

            // createTable(con);
            // insertData(con);
            // insertImage(con);
            // insertLargeImage();
            // updateData();
            // getData();
            SwingUtilities.invokeLater(Main::getImage);


        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    private static void getImage() {
        JFrame frame = new JFrame("Display Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel imageLabel = new JLabel();
        frame.add(imageLabel);

        try {
            Connection conn = ConnectionProvider.getConnection();
            String query = "SELECT pic FROM images WHERE id = ?";
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the image ID: ");
            int imageId = sc.nextInt();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, imageId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                byte[] imageData = resultSet.getBytes("pic");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(image);
                imageLabel.setIcon(scaledImageIcon);
            } else {
                imageLabel.setText("Image Not Found");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private static void getData() {
        try {
            Connection conn = ConnectionProvider.getConnection();
            String query = "select * from table1";
            Statement stmt = conn.createStatement();
            /*
            The executeQuery method is used for executing SQL queries that retrieve data from the database. This includes statements like SELECT.
            It returns a ResultSet object that contains the results of the query. You can iterate over the result set to access the retrieved data.
            It does not directly modify the database but is used to fetch data from the database based on the provided query.
            */
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                String city = set.getString(3);

                System.out.println("ID: " + id + " Name: " + name + " City: " + city);


            }
            conn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateData() {
        try {
            Connection conn = ConnectionProvider.getConnection();
            String query = "update table1 set tCity=? where tid =?";
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter new city name: ");
            String cityName = sc.nextLine();
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, cityName); // For 1st ?
            pstmt.setInt(2, id); // For 2nd ?

            pstmt.executeUpdate();
            System.out.println("Data Updated");
            conn.close();
        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    private static void insertLargeImage() {
        try {
            Connection conn = ConnectionProvider.getConnection();

            String query = "insert into images(pic) values(?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            JFileChooser jfc = new JFileChooser(); // Choose file manually
            /*
            Passing null as the parent component is common when you want to create a simple, standalone dialog box that is not associated with any particular UI element. This might be suitable when the dialog box doesn't require any specific relationship with other UI elements and just needs to be presented on its own.
            When you pass null, it indicates that the dialog is a standalone or top-level window, not attached to any specific parent component.
            */
            jfc.showOpenDialog(null); // Open the dialog box to choose the file
            File file = jfc.getSelectedFile(); // After the dialog is closed and the user has made a selection, you can retrieve the selected file using jfc.getSelectedFile(). This method returns a File object representing the selected file.
            FileInputStream fis = new FileInputStream(file); // The FileInputStream class is part of Java's Input/Output (I/O) classes and is used for reading raw bytes of data from a file. It's important to note that FileInputStream operates at a low level and reads data in the form of bytes.
            pstmt.setBinaryStream(1, fis, fis.available()); // available() method of FileInputStream class is used to return the estimated number of remaining bytes that can be read from the input stream without blocking.

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Image Inserted");


        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static void insertImage(Connection con) {
        try {
            String query = "insert into images(pic) values(?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            FileInputStream fis = new FileInputStream("C:\\Users\\wwwar\\Desktop\\Java JDBC\\pic-2.jpg"); // reading the file from the absolute path
            pstmt.setBinaryStream(1, fis, fis.available()); // available() method of FileInputStream class is used to return the estimated number of remaining bytes that can be read from the input stream without blocking.

            pstmt.executeUpdate();
            System.out.println("Image Inserted");


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static void insertData(Connection con) {

        // Insert data using PreparedStatement - Dynamic Query

        /*
        The PreparedStatement interface is a subinterface of Statement. It is used to execute parameterized query.
        We are passing parameter (?) for the values. Its value will be set by calling the setter methods of PreparedStatement.
        */
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter City: ");
            String city = sc.nextLine();
            String query = "insert into table1(tName,tCity) values(?,?)";

            // Create PreparedStatement Object

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, name); // For 1st ?
            pstmt.setString(2, city); // For 2nd ?
            pstmt.executeUpdate(); // we don't need to pass `query` again over here for PreparedStatement

            System.out.println("Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createTable(Connection con) {
        try {
            // Create a Table Query

            String query =
                    "create table table1(tId int(20) primary key auto_increment, tName varchar(200) not null, tCity varchar(400))";


            // Create a Statement

            Statement stmt = con.createStatement();

            /*
            Returns the number of rows affected by the execution of the SQL statement. Use this method to execute SQL statements, for which you expect to get a number of rows affected - for example, an INSERT, UPDATE, or DELETE statement.
            */
            stmt.executeUpdate(query);

            System.out.println("Table Created");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}