package Model;

import Connection.JdbcConnection;
import Security.Security;
import static java.lang.System.out;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class LoginDataAccess
{
    
    final private HttpServletRequest request;
     public LoginDataAccess(HttpServletRequest request)
    {
        this.request = request;
    }

    public String validateLogin(LoginUser loginUser) 
    {
        String username=loginUser.getUsername(); 
        String password=loginUser.getPassword(); 

        String dbusername; 
        String dbpassword;
        String dbroles;
        
        Connection con;
        try
        {
            con = JdbcConnection.getConnection();
            PreparedStatement pstmt; 
            
            pstmt=con.prepareStatement("select * from userDB where username=? and password=?"); 
            pstmt.setString(1,username);
            out.println(Security.encrypt(password));
            pstmt.setString(2,Security.encrypt(password));
            
            ResultSet rs=pstmt.executeQuery(); 
            while(rs.next())
            {    
                
                dbusername=rs.getString("username");   
                dbpassword=Security.decrypt(rs.getString("password"));
                out.println(Security.decrypt(dbpassword));
                dbroles = rs.getString("roles");
                
             HttpSession session = request.getSession();
             Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
             request.setCharacterEncoding("UTF-8"); 
             String answer = request.getParameter("LoginForm");
                
                if(username.equals(dbusername) && password.equals(dbpassword) && dbroles.equals("Admin") && captcha.isCorrect(answer))
                {
                    return "Admin"; 
                }
                if(username.equals(dbusername) && password.equals(dbpassword) && dbroles.equals("Guest") && captcha.isCorrect(answer))  
                {
                    return "Guest"; 
                }
                if(!captcha.isCorrect(answer))
                {
                    return "INCORRECT CAPTCHA";
                }
                else
                    return "FAILED LOGIN";
            }
           
            pstmt.close(); 
            
            con.close(); 
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return "WRONG USERNAME OR PASSWORD"; 
    }
    
}


