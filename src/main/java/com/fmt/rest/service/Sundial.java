package com.fmt.rest.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.fmt.FileUtilities;

@Path("/sundial")
public class Sundial {
	  
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> dbQuery(@Context HttpServletRequest servletRequest) {
		List<String> lines= new ArrayList();
		
		try {
			String path = servletRequest.getServletContext().getRealPath("/");
			final File data= new File(path, "GET.txt");
			System.out.println("local file: "+ data.getAbsolutePath());
			lines= FileUtilities.getFileLines(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}
}
