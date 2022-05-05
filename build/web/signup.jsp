<%
    if(session.getAttribute("login")!=null)
    {
        response.sendRedirect("login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/captcha.css" rel="stylesheet" type="text/css"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page import="nl.captcha.Captcha" %>
        <title>Sign Up</title>
        <script language="javascript">	
	function validate()
	{
            var password = document.getElementById("password"); 
            
            var confirmpassword = document.getElementById("conpassword"); 
            
            var roles = document.getElementById("roles");
            
            if (confirmpassword.value!==password.value)
            {
		alert("Password not match "); 
		confirmpassword.style.background = '#f08080';
		confirmpassword.focus();
		return false;
            }
            if (roles.value === "Select Roles") 
            {
		alert("Please Select a Role"); 
		roles.style.background = '#f08080'; 
		roles.focus();
		return false;
            }
	}		
	</script>
    </head>
    <body>
   
    <header>
        <%@ include file="header.jsp"%>
    </header>
    <br><br>
    <form method="post" action="SignupController" name="RegisterForm" onsubmit="return validate();">
      <div class="flex-1 h-full max-w-4xl mx-auto bg-white rounded-lg shadow-xl">
       <div class="flex flex-col md:flex-row">
          <div class="flex items-center justify-center p-6 sm:p-12 md:w-1/2">
            <div class="w-full">
              <div class="flex justify-center">
                  <img src="data:image/png;base64,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" alt="alt"/>
              </div>
              <h1 class="mb-4 text-2xl font-bold text-center text-gray-700">
                Sign Up
              </h1>
              <div>
                <label class="block text-sm">
                  Name
                </label>
                <input type="text"
                  class="w-full px-4 py-2 text-sm border rounded-md focus:border-blue-400 focus:outline-none focus:ring-1 focus:ring-blue-600"
                  placeholder="Name" name="txt_username" id="uname" required/>
              </div>
              <div>
                <label class="block mt-4 text-sm">
                  Password
                </label>
                <input
                  class="w-full px-4 py-2 text-sm border rounded-md focus:border-blue-400 focus:outline-none focus:ring-1 focus:ring-blue-600"
                  placeholder="Password" type="password" name="txt_password" id="password" required/>
              </div>
              <div>
                <label class="block mt-4 text-sm">
                  Confirm Password
                </label>
                <input
                  class="w-full px-4 py-2 text-sm border rounded-md focus:border-blue-400 focus:outline-none focus:ring-1 focus:ring-blue-600"
                  placeholder="Confirm Password" type="password" name="txt_confirmpassword" id="conpassword" required/>
              </div>
              <br>
            <select class="form-select" aria-label="Default select example" name="select_roles" id="roles">
              <option selected>Select Roles</option>
              <option value="Admin">Admin</option>
              <option value="Guest">Guest</option>
            </select>
            <div class="wrapper">
            <div class="captcha-area">
              <div class="captcha-img">
                <img src="<c:url value="stickyCaptcha.png" />" alt="Captch Background">
                <span class="captcha"></span>
              </div>
            </div>
            <div class="status-text"></div>
            </div>
            <div>
                <label class="block mt-4 text-sm">
                  Enter Captcha
                </label>
                <input
                  class="w-full px-4 py-2 text-sm border rounded-md focus:border-blue-400 focus:outline-none focus:ring-1 focus:ring-blue-600"
                  placeholder="Enter Captcha" name="RegisterForm" required/>
            </div>
              <button
                class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-blue-600 border border-transparent rounded-lg active:bg-blue-600 hover:bg-blue-700 focus:outline-none focus:shadow-outline-blue"
                href="#" style="background-color:#FF4B2B" type="submit" name="btn_signup" value="SignUp">
                Sign Up
              </button>
              <div class="mt-4 text-center">
                <p class="text-sm">Already have an account? <a href="login.jsp"
                    class="text-blue-600 hover:underline" style="color: #FF4B2B">Log In.</a></p>
                    </p>
                    <p style="color:red">
                    <%
                    if(request.getAttribute("RegisterErrorMsg")!=null)
                    {
                        out.println(request.getAttribute("RegisterErrorMsg")); 
                    }
                    %>
                    </p>
              </div>
            </div>
          </div>
          <div class="h-32 md:h-auto md:w-1/2">
            <img class="object-cover w-full h-full" src="https://pbs.twimg.com/media/D0ef8qrUcAAhUB5?format=jpg&name=medium"
              alt="img" />
          </div>
        </div>
      </div>
    <br><br>
</form>
</body>
    
</html>
