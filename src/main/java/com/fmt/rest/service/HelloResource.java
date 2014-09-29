package com.fmt.rest.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

 //see: http://www.ibm.com/developerworks/web/library/wa-aj-tomcat/index.html
@Path("/hello")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Massachusetts\n";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String dbQuery() {
		String foo= "unfound";
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/testCBDB");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rst = stmt.executeQuery("select name, value from keyvalue");
			while(rst.next()) {
			 String key= rst.getString(1);
			 foo= rst.getString(2);
			}
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foo;
	}
}   