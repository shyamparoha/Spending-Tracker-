    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */package dbutil;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.swing.JOptionPane;


    public class DbConnection {

    //    connection is static because it has to be used inside the static block
        private static Connection conn = null;

    //    loading the driver and connecting to the DB has to be done only once that's why it is written inside static block
        static {

            try {

                //load and register the driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                //connect to the DB
    //            conn = DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-2U9U6NF8:1521/xe", "advjavabatch", "admin");
                  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spendingdb?useSSL=false", "root", "root");

            } catch (SQLException sq) {

                JOptionPane.showMessageDialog(null, "Error in DB connection!", "Error", JOptionPane.ERROR_MESSAGE);
                sq.printStackTrace();

            } catch (ClassNotFoundException cnf) {

                JOptionPane.showMessageDialog(null, "Error in Driver loading!", "Error", JOptionPane.ERROR_MESSAGE);
                cnf.printStackTrace();

            }
        }

    //    These two methods are static because no one is instance data member in this class and it is recommended that if in our class there is no instance data member then we should keep all methods as static 
    //    method for providing connection object
        public static Connection getConnection() {

            return conn;

        }

    //    for closing the connection 
        public static void closeConnection() {

            try {

                conn.close();

            } catch (SQLException sq) {

                JOptionPane.showMessageDialog(null, "Error in Closing the DB connection!", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }


