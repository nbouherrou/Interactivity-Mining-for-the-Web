package org.java.pri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFile {

	public static void main(String[] args) {
		String msg = "hello;hi \n nacer ";
		try {
			Files.write(Paths.get(System.getProperty("user.dir")+"/src/result/result.csv"), msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
