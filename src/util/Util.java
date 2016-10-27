package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
				sb.append('\n');
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

    	// `afterNewLine` serves to avoid creating new nodes when parentheses appear
    	// on the same line, e.g. in types.
    	//boolean afterNewLine = false;
    	
    	for (int i = 0; i < strLen; i++) {
    	    char c = str.charAt(i);

//    	    if (c == '\n'){
//    	    	afterNewLine = true;
//    	    }
    	    if (c == '(') {
    	        int b = 1;
    	        StringBuilder sb = new StringBuilder("(");

    	        while (b > 0 && i < strLen - 1) {
    	            sb.append( c = str.charAt(++i) );

    	            if (c == ')') b--;
    	            else if (c == '(') b++;
    	        }
    	        
    	        if (!sb.toString().equals("()")) {
    	        	list.add(sb.toString());
    	        	//System.out.println(sb.toString());
    	        }
    	        
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
    
    public static Pattern getRegexPatternBetweenQuotationMarks() {
    	return Pattern.compile("(?<=\")(.*?)(?=\")");
    }
    
    public static Pattern getRegexPatternBetweenSingleQuotationMarks() {
    	return Pattern.compile("(?<=\')(.*?)(?=\')");
    }
    
    public static Pattern getRegexPatternForType() {
    	return Pattern.compile("(?<=type=['’])(.*?)(?=['’])");
    }
    
    public static Pattern getRegexPatternForRange() {
    	return Pattern.compile("(?<=range=\\[)(.*?)(?=[\\]])");
    }
    
    public static Pattern getRegexPatternForRangeLine() {
    	return Pattern.compile("(?<=[:])(\\d*)(?=[:])");
    }
}
