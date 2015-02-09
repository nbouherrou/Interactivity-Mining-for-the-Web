package org.pri.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pri.constants.Constants;
import org.pri.entities.Domain;
import org.pri.entities.Event;
import org.pri.entities.Link;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {

	private static Logger logger = LogManager.getLogger(MyCrawler.class);

	private Domain domain;

	private StringBuilder result, result2;
	
	private TestEngine engine;

	public MyCrawler() {

		super();

		domain 			= new Domain();
		
		this.domain.setUrl(Constants.URL_TO_VISIT);
		
		this.engine 	= new TestEngine();

		result = new StringBuilder();

		result2 = new StringBuilder();

		result.append("pageUrl ; nbTotal_pages ; nbTotal_nbMedias ; nbTotal_nbImports ; nbTotal_nbLinks ; nbTotal_linksEvents ;nbTotal_events \n");
		try {
			Files.write(Paths.get(System.getProperty("user.dir")
					+ "/src/result/profil1.csv"), result.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		result2.append("pageURL ; page_nbLinks ; nbTotal_linksEvent ; nb_EventLibrary[jQuery] ; nb_EventLibrary[Autres]; nb_EventType[Click];nb_EventType[Keydown] \n");
		try {
			Files.write(Paths.get(System.getProperty("user.dir")
					+ "/src/result/profil2.csv"), result2.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		if (href.contains(url.toString()) && !href.contains(".css")
				&& !href.contains(".js"))
			return true;

		return false;

	}

	@Override
	public void onBeforeExit() {
		
		super.onBeforeExit();
		
		this.engine.close();
		
		GraphPersistance gp = new GraphPersistance();
		
		gp.removeData();
		
		gp.persist(this.domain);
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();

		logger.info("\n\n\n");
		logger.info("URL: " + url);

		Integer nb_EventLibrary_JQuery = 0;
		Integer nb_EventLibrary_Autres = 0;
		Integer nb_EventType_Click = 0;
		Integer nb_EventType_keydown = 0;

		ListLinks list = new ListLinks(url);

		org.pri.entities.Page nodePage = new org.pri.entities.Page();

		nodePage.setUrl(url);
		nodePage.setImportsNb(list.getNbImports());
		nodePage.setLinksNb(list.getNbLinks());
		nodePage.setMediaNb(list.getNbMedia());
		
		this.engine.resetLinks();
		
		this.engine.setPageUrl(url);

		try {

			this.engine.launch();

			for (Link link : this.engine.getLinks()) {
				for (Event event : link.getEvents()) {
					if (event.getSource().contains("jQuery")) {
						nb_EventLibrary_JQuery++;
					} else {
						nb_EventLibrary_Autres++;
					}
					if (event.getType().equals("click")) {
						nb_EventType_Click++;
					}
					if (event.getType().equals("keydown")) {
						nb_EventType_keydown++;
					}
				}

				nodePage.addLink(link);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		this.domain.addPage(nodePage);

		logger.info(this.domain);
		logger.info(nodePage);
		logger.info(nodePage.getLinks());

		result.append(nodePage.getUrl() + ";" + this.domain.getNbPages() + ";"
				+ nodePage.getMediaNb() + ";" + nodePage.getImportsNb() + ";"
				+ nodePage.getLinksNb() + ";" + nodePage.getNbLinksEvents()
				+ ";" + nodePage.getNbTotalEvents() + "\n");
		try {
			Files.write(Paths.get(System.getProperty("user.dir")
					+ "/src/result/profil1.csv"), result.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		result2.append(nodePage.getUrl() + ";" + nodePage.getLinksNb() + ";"
				+ nodePage.getNbLinksEvents() + ";" + nb_EventLibrary_JQuery
				+ ";" + nb_EventLibrary_Autres + ";" + nb_EventType_Click + ";"
				+ nb_EventType_keydown + "\n");
		try {
			Files.write(Paths.get(System.getProperty("user.dir")
					+ "/src/result/profil2.csv"), result2.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onStart() {
		
		super.onStart();
		
		logger.info("onStart Crawler");
		logger.info("\n\n");
	}
}