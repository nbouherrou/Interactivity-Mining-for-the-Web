package org.java.pri;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pri.controller.ListLinks;

public class ListLinksTest {
	
	private ListLinks list;
	
	@Before
	public void init(){
		
		list = new ListLinks("http://ajaxmock-ahmedbacha.rhcloud.com/mock-3.php");
	}
	
	@Test
	public void testGetNbLinks(){

		Assert.assertEquals((Integer)5, list.getNbLinks());
	}
	
	@Test
	public void testGetNbImports(){

		Assert.assertEquals((Integer)1, list.getNbImports());
	}
	
	@Test
	public void testGetNbMedia(){

		Assert.assertEquals((Integer)2, list.getNbMedia());
	}

}
