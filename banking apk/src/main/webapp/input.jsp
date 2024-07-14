<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'> 
    <!--https://boxicons.com/--> 
    <link rel="stylesheet" href="style.css">
    <title>bankapk</title>
    

</head>
<body>
    <div class="container" id="container">
        <div class="form-container sign-up">
            <form action="LoginServlet" method="post">
                <h1 class="Eg">WELCOME TO PRIME BANK</h1>
                         
                         
                <input type="number"  name="accountnumber" placeholder="Enter Account Number ">
                <input type="password" name="password" placeholder="Enter Password">
                <button type="submit">Customer Login</button>
            </form>
     </div>
    <div class="form-container sign-in">	
    
        <form action= "AdminLoginServlet" method="post">
        <h1 class="WW">WELCOME TO PRIME BANK</h1>
        
        
        
        <input type="text" placeholder="Enter Username" id="username" name="username">
        <input type="password" placeholder="Enter Password" id="password" name="password">
        <button type="submit">Admin Login</button>

    </form>   
    
    </div>


    <div class="toggle-container">
        <div class="toggle">
               <div class="toggle-panel toggle-left">
               
                
                <button class="hidden" id="login">Admin Login</button>
               </div>
               <div class="toggle-panel toggle-right">
               
                <button class="hidden" id="register">Customer Login</button>

               </div>
        </div>
    </div>
</div>

    <script src="script.js"></script>
    <%
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red'>" + request.getParameter("error") + "</p>");
        }
    %>
    
       
  <div id="loaderPagina" class="section_loader">
    <div class="loader">
      <div class="loader_1"></div>
      <div class="loader_2"></div>
    </div>
  </div>
  <script src="loader.js"></script>
</body>
</html>