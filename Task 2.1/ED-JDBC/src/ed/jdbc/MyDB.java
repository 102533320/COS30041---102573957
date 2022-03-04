/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class MyDB {

    public static Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "org.apache.derby.jdbc.ClientDriver");
        String url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        String username = "APP";
        String password = "APP";
        return DriverManager.getConnection(url, username, password);
    }

    public void createMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("CREATE TABLE MYUSER ( "
                    + " UserId CHAR(6) CONSTRAINT PK_CUSTOMER PRIMARY KEY, "
                    + " Name CHAR(30), Password CHAR(6), Email CHAR(30), "
                    + " Phone CHAR(10), Address CHAR(60), "
                    + " SecQn CHAR(60), SecAns CHAR(60))");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void dropMyuserTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute("DROP TABLE MYUSER");
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public void addRecords(ArrayList<MyUser> myUsers) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            for (MyUser myuser : myUsers) {
                pStmnt.setString(1, myuser.getUserid());
                pStmnt.setString(2, myuser.getName());
                pStmnt.setString(3, myuser.getPassword());
                pStmnt.setString(4, myuser.getEmail());
                pStmnt.setString(5, myuser.getPhone());
                pStmnt.setString(6, myuser.getAddress());
                pStmnt.setString(7, myuser.getSecQn());
                pStmnt.setString(8, myuser.getSecAns());
                int rowCount = pStmnt.executeUpdate();
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
    }

    public MyUser getRecord(String userid){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        MyUser found = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM MYUSER WHERE USERID = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, userid);
            ResultSet rs = pStmnt.executeQuery();
            ArrayList<MyUser> mappedResultSet = MyUser.fromResultSetCollection(rs);
            if (mappedResultSet.size() > 0){
                found = mappedResultSet.get(0);
            }

        }catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }

        return found;
    }

    public boolean createRecord(MyUser myuser){
        // cannot create an existing record
        if (getRecord(myuser.getUserid()) == null) return false;

        // perform insertion of a single record
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO MYUSER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, myuser.getUserid());
            pStmnt.setString(2, myuser.getName());
            pStmnt.setString(3, myuser.getPassword());
            pStmnt.setString(4, myuser.getEmail());
            pStmnt.setString(5, myuser.getPhone());
            pStmnt.setString(6, myuser.getAddress());
            pStmnt.setString(7, myuser.getSecQn());
            pStmnt.setString(8, myuser.getSecAns());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                throw new SQLException("Cannot insert record!");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        
        return true;
    }

    boolean updateRecord(MyUser myuser){
        // cannot update an non-existent record
        if (getRecord(myuser.getUserid()) == null) return false;

        // perform updates of the record
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = getConnection();
            String preQueryStatement =    "UPDATE MYUSER SET"
                                        + "NAME = ?"
                                        + "PASSWORD = ?"
                                        + "EMAIL = ?"
                                        + "PHONE = ?"
                                        + "ADDRESS = ?"
                                        + "SECQN = ?"
                                        + "SECANS = ?"
                                        + "WHERE USERID = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, myuser.getName());
            pStmnt.setString(2, myuser.getPassword());
            pStmnt.setString(3, myuser.getEmail());
            pStmnt.setString(4, myuser.getPhone());
            pStmnt.setString(5, myuser.getAddress());
            pStmnt.setString(6, myuser.getSecQn());
            pStmnt.setString(7, myuser.getSecAns());
            pStmnt.setString(8, myuser.getUserid());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 0) {
                throw new SQLException("Cannot insert record!");
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }

        return true;
    }
}
