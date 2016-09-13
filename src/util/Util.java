package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
    
    public static List<String> getNextNodeStrings(String str) {
    	
    	List<String> list = new ArrayList<String>();
    	int strLen = str.length();

    	for (int i = 0; i < strLen; i++) {
    	    char c = str.charAt(i);

    	    if (c == '(') {
    	        int b = 1;
    	        StringBuilder sb = new StringBuilder("(");

    	        while (b > 0 && i < strLen - 1) {
    	            sb.append( c = str.charAt(++i) );

    	            if (c == ')') b--;
    	            else if (c == '(') b++;
    	        }
    	        list.add(sb.toString());
    	    }
    	}
    	
    	return list;
    }
    
    public static String getTabsForIdentationAmmount(int identationAmmount) {
    
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < identationAmmount; i++) {
    		sb.append("\t");
    	}
    	
    	return sb.toString();
    }
    
}
