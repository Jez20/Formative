package Model;

import Connection.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Security.Security;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class SignupDataAccess 
{
    
    final private HttpServletRequest request;
     public SignupDataAccess(HttpServletRequest request)
    {
        this.request = request;
    }
    public String validateSignup(SignupUser signupUser) throws UnsupportedEncodingException 
    {

        String username=signupUser.getUsername();  
        String password=signupUser.getPassword();
        String roles=signupUser.getRoles();
        
         Connection con;
         
            HttpSession session = request.getSession();
            
             Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
             request.setCharacterEncoding("UTF-8");
             String answer = request.getParameter("RegisterForm");
             
        try
        {
            if(captcha.isCorrect(answer))
            {
            con = JdbcConnection.getConnection();
            PreparedStatement pstmt; 
            
            pstmt=con.prepareStatement("insert into userDB(username,password,roles) values(?,?,?)"); 
            pstmt.setString(1,username);
            pstmt.setString(2,Security.encrypt(password)); 
            pstmt.setString(3,roles);
            pstmt.executeUpdate(); 
             
            pstmt.close(); 
            
            con.close(); 
            
            return "SUCCESSFULLY SIGNEDUP"; 
            }
            else
            {
                return "INCORRECT CAPTCHA";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return "FAILED SIGNUP"; 
    }
}
