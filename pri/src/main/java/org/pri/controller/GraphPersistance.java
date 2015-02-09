package org.pri.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.parboiled.common.StringUtils;
import org.pri.constants.Constants;
import org.pri.entities.Domain;
import org.pri.entities.Event;
import org.pri.entities.Link;
import org.pri.entities.Page;

public class GraphPersistance {
	
	private RestAPI restGraphDbLink;

	private Neo4jConnection connection;
	
	private static Logger logger = LogManager.getLogger(GraphPersistance.class);


	public static void main(final String[] args) throws ClassNotFoundException {
		
		
		Domain dom = new Domain();
		
		dom.setUrl("http://machin.fr");
		
		Page p = new Page();
		
		p.setDomainUrl(dom.getUrl());
		
		p.setImportsNb(20);
		
		p.setLinksNb(5);
		
		dom.addPage(p);
		
		dom.addPage(p);
		
		dom.addPage(p);
		
		dom.addPage(p);
		
		GraphPersistance hello = new GraphPersistance();
		
		hello.persist(dom);
		
		//hello.createDb(dom);
		
		
		// hello.removeData();
		// hello.shutDown();
		
		
	}
	

	public GraphPersistance() {
		
		super();
		
		// declarer le driver 
		try {
			
			Class.forName("org.neo4j.jdbc.Driver");
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		// on insert avec un graph en JAVA
		this.restGraphDbLink = new RestAPIFacade("http://localhost:7474/db/data");
		
		
	}
	
	
	public void persist(Domain domain){
		
		
		Transaction tx = this.restGraphDbLink.beginTx();  
		
		// on cree un noeud domain
		 Node nodeDomain = this.restGraphDbLink.createNode(new HashMap<String, Object>()); 
		 
		 nodeDomain.addLabel(new Label() {
				
				@Override
				public String name() {
					
					return "Domain";
				}
		 });

		 nodeDomain.setProperty("vertexType", domain.getVertexType());  
		 
		 nodeDomain.setProperty("domainUrl", domain.getUrl());  
		 
		 nodeDomain.setProperty("createdOn", domain.getCreatedOn().toString());  
		 
		 for( Page p : domain.getPages()){
			 
			Node nodePage = this.persistElement(p);
				
			nodeDomain.createRelationshipTo(nodePage, DynamicRelationshipType.withName(Constants.A_POUR_PAGE));
			
			 for( Link l : p.getLinks()){
				 
					Node nodeLink = this.persistElement(l);
						
					nodePage.createRelationshipTo(nodeLink, DynamicRelationshipType.withName(Constants.A_POUR_LIEN));
					
					 for( Event e : l.getEvents()){
						 
							Node nodeEvent = this.persistElement(e);
								
							nodeLink.createRelationshipTo(nodeEvent, DynamicRelationshipType.withName(Constants.A_POUR_EVENT));
							 
					 }
					 
			 }
			 
		 }
		 
		 tx.success();   
		
	}
	
	
	public Node persistElement(Object object){
		
		logger.info("BEGIN persistElement");
		
		Node node = this.restGraphDbLink.createNode(new HashMap<String, Object>()); 
		
		try (Transaction tx = this.restGraphDbLink.beginTx()) {
		
			/**
			 * Cas ou object est un element PAGE
			 */
			if(object instanceof Page){
				
				Page page = (Page) object;
				
				node.addLabel(new Label() {
					
					@Override
					public String name() {
						
						return "Page";
					}
				});
				
				node.setProperty("vertexType", page.getVertexType());

				node.setProperty("pageUrl", page.getUrl());

				node.setProperty("createdOn", page.getCreatedOn().toString());

				node.setProperty("nbImports", page.getImportsNb());

				node.setProperty("nbLinks", page.getLinksNb());

				node.setProperty("nbMedias", page.getMediaNb());
				
				logger.info("Page persisted");
				
			}
			
			/**
			 * Cas ou object est un element LINK
			 */
			if(object instanceof Link){
				
				Link link = (Link) object;
				
				node.addLabel(new Label() {
					
					@Override
					public String name() {
						
						return "Link";
					}
				});
				
				node.setProperty("vertexType", link.getVertexType());

				node.setProperty("pageUrl", link.getPageUrl());

				node.setProperty("node", link.getNode());

				node.setProperty("eventNb", link.getEventNb());

				node.setProperty("createdOn", link.getCreatedOn().toString());
				
				logger.info("Link persisted");
				
				
			}
			
			/**
			 * Cas ou object est un element EVENT
			 */
			if(object instanceof Event){
				
				Event event = (Event) object;
				
				node.addLabel(new Label() {
					
					@Override
					public String name() {
						
						return "Event";
					}
				});
				
				node.setProperty("vertexType", event.getVertexType());

				node.setProperty("linkNode", event.getLinkNode());

				node.setProperty("code", event.getCode());

				node.setProperty("source", event.getSource());

				node.setProperty("type", event.getType());
				
				logger.info("Event persisted");
			
			}
		
		
			tx.success();
		}
		
		logger.info("END persistElement");
		
		return node;
	}
	



	public Node persistePage(Page page, GraphDatabaseService graphDb) {
		Node pNode = null;

		try (Transaction tx = graphDb.beginTx()) {

			pNode = graphDb.createNode();

			pNode.setProperty("vertexType", page.getVertexType());

			pNode.setProperty("pageUrl", page.getUrl());

			pNode.setProperty("createdOn", page.getCreatedOn().toString());

			pNode.setProperty("nbImports", page.getImportsNb());

			pNode.setProperty("nbLinks", page.getLinksNb());

			pNode.setProperty("nbMedias", page.getMediaNb());

			tx.success();

			return pNode;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return pNode;

	}

	public Node persisteLink(Link link, GraphDatabaseService graphDb) {

		Node lnkNode = null;

		try (Transaction tx = graphDb.beginTx()) {

			lnkNode = graphDb.createNode();

			lnkNode.setProperty("vertexType", link.getVertexType());

			lnkNode.setProperty("pageUrl", link.getPageUrl());

			lnkNode.setProperty("node", link.getNode());

			lnkNode.setProperty("eventNb", link.getEventNb());

			lnkNode.setProperty("createdOn", link.getCreatedOn().toString());

			tx.success();

			return lnkNode;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return lnkNode;

	}

	public Node persisteEvent(Event event, GraphDatabaseService graphDb) {

		Node evNode = null;

		try (Transaction tx = graphDb.beginTx()) {

			evNode = graphDb.createNode();

			evNode.setProperty("vertexType", event.getVertexType());

			evNode.setProperty("linkNode", event.getLinkNode());

			evNode.setProperty("code", event.getCode());

			evNode.setProperty("source", event.getSource());

			evNode.setProperty("type", event.getType());

			tx.success();

			return evNode;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return evNode;

	}

	public void removeData() {
		
		try (Transaction tx = this.restGraphDbLink.beginTx()) {

			/**
			 * driver connection NEO4J
			 */
			Neo4jConnection connect = new Driver().connect("jdbc:neo4j://localhost:7474", new Properties());
			
			/**
			 * requete Cypher
			 */
			List<String> dataLoad = new ArrayList<String>();
			
			dataLoad.add("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r");

			/**
			 * on commit la requete
			 */
			connect.createStatement().executeQuery(StringUtils.join(dataLoad, "\n"));

			tx.success();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	void shutDown() {
		
		System.out.println();
		
		System.out.println("Shutting down database ...");
		
		this.restGraphDbLink.close();

	}

	
	public RestAPI getRestGraphDbLink() {
		return restGraphDbLink;
	}

	public void setRestGraphDbLink(RestAPI restGraphDbLink) {
		this.restGraphDbLink = restGraphDbLink;
	}
	
	
	public Neo4jConnection getConnection() {
		return connection;
	}


	public void setConnection(Neo4jConnection connection) {
		this.connection = connection;
	}

}
