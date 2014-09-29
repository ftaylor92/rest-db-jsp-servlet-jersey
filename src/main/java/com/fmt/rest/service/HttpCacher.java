package com.fmt.rest.service;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.fmt.FileUtilities;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Path("/httpcache")
public class HttpCacher {
	public final static int FILEPATHMAX= 255;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response httpResponse(
			@QueryParam("url") String urlD,
			@Context HttpServletRequest servletRequest) {
		
		final String url= urlD.replaceAll("ANDK", "&");
		List<String> lines= new ArrayList<String>();
		Status stat= Response.Status.NO_CONTENT;
		
		CacheControl cc = new CacheControl();
		cc.setMaxAge(60);
		cc.setNoCache(true);
		
		try {
			String serverCacheFolder = servletRequest.getServletContext().getRealPath("/");
			final String fileUrl= URLEncoder.encode(urlD, "UTF-8").substring(0, Math.min(FILEPATHMAX, urlD.length())); //- serverCacheFolder.length());

			final File cachedResponseFile= new File(serverCacheFolder, fileUrl);
			final boolean isCached= cachedResponseFile.exists();
			System.out.println("local file exists="+isCached+": "+ cachedResponseFile.getAbsolutePath());
			
			if(!isCached) {
				final String realHttpResponse= GETUrl(URLDecoder.decode(url, "UTF-8"));
				FileUtilities.writeFileLines(cachedResponseFile, realHttpResponse);
			}
			
			lines= FileUtilities.getFileLines(cachedResponseFile);
			
			stat= Response.Status.OK;
		} catch (IOException e) {
			lines.add(e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			lines.add(e.getMessage());
			e.printStackTrace();
		}

		//return lines;
		ResponseBuilder rb = Response.status(stat).entity(lines);
		System.out.println("Response: "+ lines);
		return rb.cacheControl(cc).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods","GET").build();

	}
	
	private static String GETUrl(String url) throws RuntimeException {
		String GETResponse= "no response";
		//final String url= REST_URL+ "/password"; //?username=ftaylor92&password=ftaylor92&site=bookmarks";
		System.out.println("URL(GETUrl): "+ url);
		System.out.println("linux command: curl "+url);
		//try {

			Client client = Client.create();

			WebResource webResource = client
					.resource(url);

			ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).
					get(ClientResponse.class);

			if (!(response.getStatus() == Response.Status.OK.getStatusCode() || response.getStatus() == Response.Status.ACCEPTED.getStatusCode())) {
				throw new RuntimeException("Failed(GETUrl): HTTP error code : "
						+ response.getStatus());
			}

			GETResponse = response.getEntity(String.class);

			System.out.printf("Output from Server(GETUrl):\n%s\n", GETResponse);

//		} catch (Exception e) {
//			GETResponse= e.getMessage();
//			e.printStackTrace();
//		}
		
		return GETResponse;
	}
}
