package org.pri.entities;


import java.util.ArrayList;
import java.util.Date;

public class Page {
	
	private String 		domainUrl;

	private String 		url;
	
	private String 		vertexType;
	
	private Integer 	mediaNb;
	
	private Integer 	linksNb;
	
	private Integer 	importsNb;
	
	private Date 		createdOn;
	
	private ArrayList<Link> links;
	
	private Integer nbLinksEvents;
	
	private Integer nbTotalEvents;
	
	public Page() {
		
		super();
		
		this.links = new ArrayList<Link>();
		
		createdOn = new Date();
		
		setVertexType("Page");
		
		nbLinksEvents=0;
		
		nbTotalEvents=0;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [domainUrl=");
		builder.append(domainUrl);
		builder.append(", url=");
		builder.append(url);
		builder.append(", vertexType=");
		builder.append(vertexType);
		builder.append(", mediaNb=");
		builder.append(mediaNb);
		builder.append(", linksNb=");
		builder.append(linksNb);
		builder.append(", importsNb=");
		builder.append(importsNb);
		builder.append(", createdOn=");
		builder.append(createdOn);
		builder.append(", links=");
		builder.append(links);
		builder.append(", nbLinksEvents=");
		builder.append(nbLinksEvents);
		builder.append(", nbTotalEvents=");
		builder.append(nbTotalEvents);
		builder.append("]");
		return builder.toString();
	}


	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public String getUrl() {
		return url;
	}

	public Integer getNbLinksEvents() {
		return nbLinksEvents;
	}

	public void setNbLinksEvents(Integer nbLinksEvents) {
		this.nbLinksEvents = nbLinksEvents;
	}

	public Integer getNbTotalEvents() {
		return nbTotalEvents;
	}

	public void setNbTotalEvents(Integer nbTotalEvents) {
		this.nbTotalEvents = nbTotalEvents;
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
	

	public Integer getLinksNb() {
		return linksNb;
	}

	public void setLinksNb(Integer linksNb) {
		this.linksNb = linksNb;
	}

	public Integer getImportsNb() {
		return importsNb;
	}

	public void setImportsNb(Integer importsNb) {
		this.importsNb = importsNb;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	
	public void addLink(Link link) {
		
		System.out.println("in add link");
		
		link.setPageUrl(this.url);
		
		this.nbLinksEvents=this.nbLinksEvents+1;
		
		this.nbTotalEvents=this.nbTotalEvents+link.getEventNb();

		this.links.add(link);
		
	}
	
	public void deleteLink(Link link) {
		
		this.links.remove(link);
		
	}

	public Integer getMediaNb() {
		return mediaNb;
	}

	public void setMediaNb(Integer mediaNb) {
		this.mediaNb = mediaNb;
	}
	


}
