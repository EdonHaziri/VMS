<%@ page import="java.sql.*"%>

<% 
String name = request.getParameter("rname");
String email = request.getParameter("remail");
String gender = request.getParameter("rgender");
String epass = request.getParameter("renterPass");
String cpass = request.getParameter("rconfirmPass");
if(epass.equals(cpass)){
try{
    Connection conn = DriverManger.getConnection("jdbc:oracle:thin:localhost:1521:orcl","c##edon","protect");
    PreparedStatement ps = conn.prepareStatement("insert into registration values(?, ?, ?, ?)");
    ps.setString(1, name);
    ps.setString(2, email);
    ps.setString(3, gender);
    ps.setString(4, epass);
    int x = ps.executeUpdate();
    if(x != 0){
        out.print("Signup done succesfully...");
    }
} catch (Exception e){
    out.println("Email id should be unique....");
}
}
else{
    out.println("Password not matching...");
}
%>