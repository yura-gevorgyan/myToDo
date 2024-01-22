<%--
  Created by IntelliJ IDEA.
  User: Yura
  Date: 1/21/2024
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/loginStyle.css"/>
</head>
<body>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form method="post" action="/register">
            <% if (session.getAttribute("registerMsg") != null) {%>
            <span style="color: red"><%=session.getAttribute("registerMsg")%></span>
            <%}%>
            <h1>Create Account</h1>
            <input type="text" placeholder="Name" name="name"/>
            <input type="text" placeholder="Surname" name="surname"/>
            <input type="email" placeholder="Email" name="email"/>
            <input type="password" placeholder="Password" name="password"/>
            <button type="submit">Sign Up</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form method="post" action="/login">
            <% if (session.getAttribute("loginMsg") != null) {%>
            <span style="color: red"><%=session.getAttribute("loginMsg")%></span>
            <%}%>
            <h1>Sign in</h1>
            <input type="email" placeholder="Email" name="email"/>
            <input type="password" placeholder="Password" name="password"/>
            <button type="submit">Sign In</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Welcome Back!</h1>
                <p>To keep connected with us please login with your personal info</p>
                <button class="ghost" id="signIn">Sign In</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and start journey with us</p>
                <button class="ghost" id="signUp">Sign Up</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="/js/login.js"></script>
</html>
