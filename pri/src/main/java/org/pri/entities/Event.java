package org.pri.entities;

import java.util.Date;

public class Event {
	
	private String 		linkNode;
	
	private String 		vertexType;

	private String 		code;
	
	private String 		source;
	
	private String 		type;
	
	private Date 		createdOn;

	public Event() {
		
		super();
		
		this.createdOn = new Date();
		
		setVertexType("Event");
	}


	public String getLinkNode() {
		return linkNode;
	}


	public void setLinkNode(String linkNode) {
		this.linkNode = linkNode;
	}


	public String getVertexType() {
		return vertexType;
	}

	public void setVertexType(String vertexType) {
		this.vertexType = vertexType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [linkNode=");
		builder.append(linkNode);
		builder.append(", vertexType=");
		builder.append(vertexType);
		builder.append(", code=");
		builder.append(code);
		builder.append(", source=");
		builder.append(source);
		builder.append(", type=");
		builder.append(type);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append("]");
		return builder.toString();
	}
	
	

}
