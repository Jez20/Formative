package Controller;

import Model.LoginUser;
import Model.LoginDataAccess;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class LoginController extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(request.getParameter("btn_login")!=null) 
        {
            String username=request.getParameter("txt_username"); 
            String password=request.getParameter("txt_password"); 
             
            LoginUser loginUser=new LoginUser(); 
            
            loginUser.setUsername(username); 
            loginUser.setPassword(password); 
            
            LoginDataAccess loginData=new LoginDataAccess(request); 
            
            String loginValidate=loginData.validateLogin(loginUser); 
            
            HttpSession session = request.getSession();
            
             Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
             request.setCharacterEncoding("UTF-8"); 
             String answer = request.getParameter("LoginForm");
            
            if(loginValidate.equals("Admin") && captcha.isCorrect(answer)) 
            {
                session=request.getSession(); 
                session.setAttribute("login",loginUser.getUsername()); 
                
                RequestDispatcher rd=request.getRequestDispatcher("admin.jsp"); 
                rd.forward(request, response);

            }
            else if(loginValidate.equals("Guest") && captcha.isCorrect(answer)) 
            {
                 session=request.getSession();
                        session.setAttribute("login",loginUser.getUsername());
                        
                    RequestDispatcher rd=request.getRequestDispatcher("guest.jsp");
                    rd.forward(request, response);
            }
            else
            {
                request.setAttribute("WrongLoginMsg",loginValidate); 
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp"); 
                rd.include(request, response);
            }
        }
    }

}
