package Assignment2;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/* Advanced Programming Assignment 1
 * Rink Stiekema & Ben den Drijver
 */

public class Main {

	PrintStream out;
	static final int MAX_NUMBER_OF_IDENTIFIERS = 10;

	public Set set1, set2;

	Main(){
		out = new PrintStream(System.out);
		set1 = new SetImplementation();
		set2 = new SetImplementation();
	}

	private void processInput(Scanner in){
		boolean isValidInput;
		String firstOrSecond = "first";
		boolean isFirstSet = true;

		do{
			out.printf("Give the %s set : ", firstOrSecond);
			isValidInput = checkInput(in, isFirstSet);

			if(isValidInput && isFirstSet){
				firstOrSecond = "second";
				isFirstSet = false;
				isValidInput = false;
			}
			in.reset();
		} while(!isValidInput);        
	}

	private boolean checkInput(Scanner in, boolean isFirstSet){
		if(in.hasNext()){
			String input = in.nextLine();

			if(isEmptySet(input)){
				return true;
			}

			if(!hasValidBrackets(input)){
				return false;
			} else {
				input = removeBrackets(input);
			}

			Scanner inputScanner = new Scanner(input);
			return validIdentifier(inputScanner, isFirstSet);
		} else {
			System.exit(0);
			return false;	
		}
	}

	private boolean validIdentifier(Scanner inputScanner, boolean isFirstSet){
		while(inputScanner.hasNext()){
			String newIdentifier = inputScanner.next();                    
			if(!isValidIdentifier(newIdentifier, isFirstSet)){
				return false;
			}
			Identifier toAdd = new IdentifierImplementation(newIdentifier);
			addIdentifierToSet(toAdd, isFirstSet);
			
			if(set1.size() > MAX_NUMBER_OF_IDENTIFIERS || set2.size() > MAX_NUMBER_OF_IDENTIFIERS){
                out.printf("Invalid input provided. The input may not contain more than 10 Identifiers.\n");
                return false;
			}
		}
		return true;
	}

	private boolean isEmptySet(String s){
		if(s.equals("{}")){
			return true;
		} else {
			return false;
		}
	}

	private boolean hasValidBrackets(String s){
		if(s.charAt(0) != '{'){
			out.printf("Missing '{'\n");
			return false;
		}
		if(s.charAt(s.length()-1) != '}'){
			out.printf("Missing '}'\n");
			return false;
		}
		return true;
	}

	private String removeBrackets(String s){
		String result = s.substring(1);
		result =  result.substring(0, result.length()-1);
		return result;
	}

	private boolean isValidIdentifier(String newIdentifier, boolean isFirstSet){
		if(!hasValidFirstChar(newIdentifier, isFirstSet) || !isAlphanumeric(newIdentifier, isFirstSet)){
			out.printf("Invalid input provided. Identifiers must be alphanumeric and start with a letter.\n");
			return false;
		}
		return true;
	}

	private boolean hasValidFirstChar(String s, boolean isFirstSet){
		if(!Character.isLetter(s.charAt(0))){
			initSet(isFirstSet);
			return false;
		}
		return true;
	}

	private boolean isAlphanumeric(String s, boolean isFirstSet){
		for(int i = 0; i<s.length(); i++){
			char c = s.charAt(i);
			if(!Character.isLetter(c) && !Character.isDigit(c)){
				initSet(isFirstSet);
				return false;
			}
		}
		return true;
	}

	private void addIdentifierToSet(Identifier newIdentifier, boolean isFirstSet){
		if(isFirstSet){
			try{
				set1.add(newIdentifier);
			} catch(Exception e){
				//Won't ever happen since we'll never accept more than 10 input elements
			}
		} else {
			try{
				set2.add(newIdentifier);
			} catch(Exception e){
				//Won't ever happen since we'll never accept more than 10 input elements
			}
		}
	}

	private void initSet(boolean isFirstSet){
		if(isFirstSet){
			set1.init();
		} else {
			set2.init();
		}
	}

	private void processSets(){
		Set union = new SetImplementation();
		Set intersection = new SetImplementation();
		Set difference = new SetImplementation();
		Set symDifference = new SetImplementation();
		try {
			union = set1.union(set2);
			symDifference = set1.symDifference(set2);
			intersection = set1.intersection(set2);
			difference = set1.difference(set2);
		} catch (Exceptions e) {
			//Won't ever happen since we'll never accept more than 10 input elements
		}
		printSet(difference, "difference");
		printSet(intersection, "intersection");
		printSet(union, "union");
		printSet(symDifference, "sym. diff.");
	}

	private void printSet(Set set, String name){
		int size = set.size();
		out.printf("%s = { ", name);
		for(int i = 0; i < size; i++){
			Identifier print = set.get();
			System.out.print(print.toString() + " ");
			set.remove(print);
		}
		out.printf("} \n");
	}

	private void start() {
		Scanner in = new Scanner(System.in);
		processInput(in);
		processSets();
		start();
	}

	public static void main(String[] argv){
		new Main().start();
	}      
}