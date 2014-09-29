<%@ page language="java" import="java.sql.*"%>
<html>
<body>
<%
Class.forName("com.mysql.jdbc.Driver"); //.newInstance();


Connection connect=null;
ResultSet resultSet=null;
Statement statement=null;

try{
String url="jdbc:mysql://ec2-50-19-213-178.compute-1.amazonaws.com/fmt-test?"
		+ "user=ftaylor92&password=ftaylor92";

int i=1;
connect=DriverManager.getConnection(url);
statement=connect.createStatement();
resultSet=statement.executeQuery("select * from keyvalue");
//writeResultSet(resultSet);
while (resultSet.next()) {
%>
<%=resultSet.getString("name")%>
<%
}
resultSet.close();
statement.close();
connect.close();
}catch(Exception e){
	System.out.println(e.getMessage());
}
%>
</body>
</html>
