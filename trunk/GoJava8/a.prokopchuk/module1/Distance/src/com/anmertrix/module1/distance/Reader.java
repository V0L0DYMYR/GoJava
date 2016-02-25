package com.anmertrix.module1.distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Reader {
	
	protected String readUserNumbersLine() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String numbersLine = null;
		
		try {
			numbersLine = reader.readLine();
		} catch (IOException e) {
			numbersLine = "-1";
		} finally {
			reader.close();
		}

		return numbersLine;
	}
}
