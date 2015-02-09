package org.pri.mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.parboiled.common.StringUtils;

public class DataBase {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		// declarer le driver 
		Class.forName("org.neo4j.jdbc.Driver");
		
		// os name for fun
		System.out.println(System.getProperty("os.name"));
		
		
		System.out.println(System.getProperty("user.name"));
		
		
		// url 
		String url ="jdbc:neo4j://localhost:7474";
		

		
		// driver connection NEO4J
		Neo4jConnection connect = new Driver().connect(url, new Properties());
		
		// requete Cypher
		List<String> dataLoad = new ArrayList<String>();
		dataLoad.add("CREATE (person1 { personId: 1, started: 1361708546 })");
		dataLoad.add("CREATE (person2 { personId: 2, started: 1361708546, left: 1371708646 })");
		dataLoad.add("CREATE (company { companyId: 1 })");
		 
		// on commit la requete
		connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));
		
		
		// ------------------------------------ //
		
		// on insert avec un graph en JAVA
		RestAPI graphDb = new RestAPIFacade("http://localhost:7474/db/data");
		
		Transaction tx = graphDb.beginTx();  
		
		 Node node = graphDb.createNode(new HashMap<String, Object>()); 

		 node.setProperty("nom", "OL Lyon");  
		 node.setProperty("couleur", "Bleu");  
		 node.setProperty("points", 34);  
		 
		 
		 Node node2 = graphDb.createNode(new HashMap<String, Object>()); 
		 
		 node2.setProperty("nom", "PSG ");  
		 node2.setProperty("couleur", "Rouge");  
		 node2.setProperty("points", 32);  
		 
		 node.createRelationshipTo(node2, DynamicRelationshipType.withName("KNOWS"));
		 

		 tx.success();   
			
		 // ------------------------------------ //
		
		// on recupere le resultat d'une requete
		ResultSet resultSet = connect.createStatement().
		  executeQuery("MATCH (person1) RETURN person1");
		 
		if(resultSet.next()) {
		    @SuppressWarnings("unchecked")
			Map<String, Object> e = (Map<String, Object>) resultSet.getObject("person1");
		    System.out.println("personId: " + e.get("personId") + ", started: " + e.get("started"));

		}

	}
}
