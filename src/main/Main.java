package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import ast.AST;
import ast.ASTNode;
import util.Util;

/**
 * Analyzes given Swift projects with the analyzer of choice.
 */
public class Main {

	 //private static Formatter formatter = new JSONFormatter();
	 //private static Printer printer = new Printer(formatter);
	    
    /**
     * Analyzer of choice to analyze the project(s)
     */
	 
    //private static Analyzer analyzer = new MscrMetricsProjectAnalyzer(printer);

    /**
     * Main method accepts one or more project paths to analyze.
     *
     * @param args array of project paths
     */
    public static void main(String[] args) {
        
    	if (args.length < 1) {
            System.err.println("Specify one or more project paths");
            return;
        }
        
        for (String projectPath : args) {
        	
            File file = new File(projectPath);
            
            try {
            	
            	FileInputStream input = new FileInputStream(file);
                String inputString = Util.getStringFromInputStream(input);
                
                DumperParser parser = new DumperParser(inputString);
                AST dumperAST = parser.generateAST();
                //dumperAST.prettyPrint(0);
                
                dumperAST.fillMetricContainer();
                System.out.println(dumperAST.metricContainer.toString());
                
                for(ASTNode child : dumperAST.getChildren()){
                	System.out.println(child.getChildren());
                }
                
            } catch (IOException e) {
                System.err.println("Could not read " + file.getAbsolutePath());
            }
                   
        }
    }

}