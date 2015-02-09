package org.pri.controller;

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;

public class HelloRest {

	public static String SERVER_ROOT_URI = "http://localhost:7474/db/data/";

	public static void main(String[] args) {
		Client client = Client.create();
		client.addFilter(new LoggingFilter(System.out));
		WebResource cypher = client.resource(SERVER_ROOT_URI + "cypher");
		String request = "{\"query\":\"MATCH (domaine) RETURN domaine\"}";
		ClientResponse cypherResponse = cypher.accept(
				MediaType.APPLICATION_JSON).post(ClientResponse.class, request);
		cypherResponse.close();
		System.out.println(cypherResponse);
	}
}