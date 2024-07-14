<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="dd.css" />
    <title>Navbar menus</title>
</head>
<body>
 <div class="banner">
    <video autoplay loop muted plays-inline>
        <source src="videos/video3.mp4" type="video/mp4">
    </video>
</div>

    <ul>
        <li style="--i:6;"><a href="success.jsp">ADD CUSTOMER</a></li>
        <li style="--i:5;"><a href="deleteCustomer.jsp">DELETE CUSTOMER ACCOUNT</a></li>
        <li style="--i:3;"><a href="modifyCustomer.jsp">MODIFY CUSTOMER CREDENTIALS</a></li>
        <li style="--i:2;"><a href="viewCustomer.jsp">CUSTOMERS DETAILS</a></li>
        <li style="--i:1;"><a href="input.jsp">LOGOUT</a></li>
    </ul>
    <div id="loaderPagina" class="section_loader">
    <div class="loader">
      <div class="loader_1"></div>
      <div class="loader_2"></div>
    </div>
  </div>
  <script src="loader.js"></script>
</body>
</html>