package org.pri.entities;

import java.util.ArrayList;
import java.util.Date;

public class Domain {
	
	private String 				url;
	
	private String 				vertexType;
	
	private Date 				createdOn;
	
	private ArrayList<Page> 	pages;
	
	private Integer				nbPages;				

	public Domain() {
		
		super();
		
		this.pages = new ArrayList<Page>();
		
		createdOn = new Date();
		
		setVertexType("Domain");
		
		nbPages=0;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Domain [url=");
		builder.append(url);
		builder.append(", vertexType=");
		builder.append(vertexType);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", pages=");
		builder.append(pages);
		builder.append(", nbPages=");
		builder.append(nbPages);
		builder.append("]");
		return builder.toString();
	}


	public Integer getNbPages() {
		return nbPages;
	}


	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVertexType() {
		return vertexType;
	}

	public void setVertexType(String vertexType) {
		this.vertexType = vertexType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
	
	public void addPage(Page page) {
		
		page.setDomainUrl(this.url);
		
		this.pages.add(page);
		
		nbPages++;
	}
	
	public void deletePage(Page page) {
		
		this.pages.remove(page);
		
	}
	
	
	
}
