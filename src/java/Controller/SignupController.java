package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.SignupUser;
import Model.SignupDataAccess;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

public class SignupController extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(request.getParameter("btn_signup")!=null)
        {
            String username=request.getParameter("txt_username"); 
            String password=request.getParameter("txt_password");
            String roles = request.getParameter("select_roles");
            SignupUser signupUser=new SignupUser(); 
            
            signupUser.setUsername(username);  
            signupUser.setPassword(password);
            signupUser.setRoles(roles);
            
            SignupDataAccess signupData=new SignupDataAccess(request); 
            
            String signupValidate=signupData.validateSignup(signupUser);
            
            HttpSession session = request.getSession();
            
             Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
             request.setCharacterEncoding("UTF-8"); 
             String answer = request.getParameter("RegisterForm");
            
            if(signupValidate.equals("SUCCESSFULLY SIGNEDUP") && captcha.isCorrect(answer))
            {
                request.setAttribute("RegiseterSuccessMsg",signupValidate); 
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp"); 
                rd.forward(request, response);
            }
            else
            {
                request.setAttribute("RegisterErrorMsg",signupValidate);
                RequestDispatcher rd=request.getRequestDispatcher("signup.jsp");
                rd.include(request, response);
            }
        }
    }

}
