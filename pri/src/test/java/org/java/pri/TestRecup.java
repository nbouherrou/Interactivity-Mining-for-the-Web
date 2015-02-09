package org.java.pri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;

public class TestRecup {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException {
		Neo4jConnection connect = new Driver().connect(
				"jdbc:neo4j://localhost:7474", new Properties());

		ResultSet resultSet = connect.createStatement().executeQuery(
				"MATCH (pNode) RETURN pNode");
		
		resultSet.toString();
		
		System.out.println(resultSet.toString());

		if (resultSet.next()) {
			Map<String, Object> e = (Map<String, Object>) resultSet
					.getObject("pNode");
			System.out.println(e.toString());
		}
	}

}
