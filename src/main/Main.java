package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

import ast.AST;
import ast.ASTNode;
import ast.ASTNodeType;
import modifiednodes.ClassDeclNode;
import modifiednodes.ExtensionDeclNode;
import modifiednodes.FuncDeclNode;
import modifiednodes.ProtocolNode;
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


				printTest(dumperAST);


				/* analyzing what nodes aren't implemented yet 
                List<ASTNode> nodes = (List<ASTNode>) dumperAST.getSubnodesOfType(UnknownNode.class);

                for (ASTNode node : nodes){
                	System.out.println(node.getNodeInfo());
                }
				 */

			} catch (IOException e) {
				System.err.println("Could not read " + file.getAbsolutePath());
			}

		}
	}

	public static void printTest(ASTNode node){

		System.out.println("Total: " + node.metricContainer.toString());

		int ident = 0;

		// for each class declaration:
		for (ASTNode child : node.containsChildrenOfTypeRecursive(ASTNodeType.ClassDecl)) {
			String identation = Util.getTabsForIdentationAmmount(ident);
			System.out.println(identation + "Class: "+ ((ClassDeclNode) child).name + " " + child.metricContainer );

			ident += 1;
			identation = Util.getTabsForIdentationAmmount(ident);
			for (ASTNode childFunc : child.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
					filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList())) {
				identation = Util.getTabsForIdentationAmmount(ident);
				System.out.println(identation + "Func: "+ ((FuncDeclNode) childFunc).name + " " + childFunc.metricContainer );

			}
			ident -= 1;
		}

		// for each extension declaration:
		for (ASTNode child : node.containsChildrenOfTypeRecursive(ASTNodeType.PxtensionDecl)) {
			String identation = Util.getTabsForIdentationAmmount(ident);
			System.out.println(identation + "Extension: "+ ((ExtensionDeclNode) child).className + " " + child.metricContainer );

			ident += 1;
			identation = Util.getTabsForIdentationAmmount(ident);
			for (ASTNode childFunc : child.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
					filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList())) {
				identation = Util.getTabsForIdentationAmmount(ident);
				System.out.println(identation + "Func: "+ ((FuncDeclNode) childFunc).name + " " + childFunc.metricContainer );

			}
			ident -= 1;
		}
		
		// for each protocol declaration:
				for (ASTNode child : node.containsChildrenOfTypeRecursive(ASTNodeType.Protocol)) {
					String identation = Util.getTabsForIdentationAmmount(ident);
					System.out.println(identation + "Protocol: "+ ((ProtocolNode) child).name + " " + child.metricContainer );

					ident += 1;
					identation = Util.getTabsForIdentationAmmount(ident);
					for (ASTNode childFunc : child.containsChildrenOfType(ASTNodeType.FuncDecl, false).stream().
							filter(u -> !((FuncDeclNode)u).anonymous).collect(Collectors.toList())) {
						identation = Util.getTabsForIdentationAmmount(ident);
						System.out.println(identation + "Func: "+ ((FuncDeclNode) childFunc).name + " " + childFunc.metricContainer );

					}
					ident -= 1;
				}
	}
}