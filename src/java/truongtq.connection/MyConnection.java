/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author harry
 */
public class MyConnection implements Serializable {
    public static Connection getMyConnection() throws NamingException, SQLException {
            Context context = new InitialContext();
            Context tomcatCtx = (Context)context.lookup("java:comp/env");
            DataSource ds = (DataSource)tomcatCtx.lookup("YellowMoonDS");
            Connection con = ds.getConnection();
            return con;
    }
}