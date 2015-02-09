package org.pri.entities;

import java.util.Date;
import java.util.ArrayList;

public class Link {
	
	private String 				pageUrl;
	
	private String 				vertexType;
	
	private String 				node;
	
	private Integer 			eventNb;

	private Date 				createdOn;
	
	private ArrayList<Event> 	events;
	
	private Integer				nbEvents;

	public Link() {
		
		super();
		
		this.events = new ArrayList<Event>();
		
		this.createdOn = new Date();
		
		setVertexType("Link");
		
		nbEvents =0;
	}

	public Integer getNbEvents() {
		return nbEvents;
	}

	public void setNbEvents(Integer nbEvents) {
		this.nbEvents = nbEvents;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getVertexType() {
		return vertexType;
	}

	public void setVertexType(String vertexType) {
		this.vertexType = vertexType;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	public Integer getEventNb() {
		return eventNb;
	}

	public void setEventNb(Integer eventNb) {
		this.eventNb = eventNb;
	}


	public void addEvent(Event event) {
		
		event.setLinkNode(this.node);
		
		this.events.add(event);
		
		nbEvents++;
	}
	
	public void deleteEvent(Event event) {
		
		this.events.remove(event);
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Link [pageUrl=");
		builder.append(pageUrl);
		builder.append(", vertexType=");
		builder.append(vertexType);
		builder.append(", node=");
		builder.append(node);
		builder.append(", eventNb=");
		builder.append(eventNb);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", events=");
		builder.append(events);
		builder.append(", nbEvents=");
		builder.append(nbEvents);
		builder.append("]");
		return builder.toString();
	}
	
	
}
