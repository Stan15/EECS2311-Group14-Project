package tab2xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
// testing if i push properly
public class Main {
	ArrayList<String> fileLines = new ArrayList<>();
	String[][] parsedInfo;	//String[bar][info]
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is a change to the file to deemo git pulling");
		try {
			Main convert = new Main();
			convert.readLines("passenger - a thousand matches tabs.txt");
			convert.parseTabInfo();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readLines(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while (true) {
			String line = reader.readLine(); //I removed trim() was here(when it is null, it makes nullpointerexception)
			if (line==null)
				return;
			if (!line.equals("")) {
				line = line.trim(); //I added trim here to avoid nullPointerexception
				   if(!line.equals("")) {
						fileLines.add(line);   
				   }
			}
		}
	}
	
	
	/**
	 * Per each new string in a single bar must be separated by at least a new line
	 * the strings must be either in the forward or backward order E B G D A E
	 * each bar must end on the same line it started
	 * lets call each bunch of bars arranged right next to each other horizontally a bar bunch
	 * @param currentBar
	 * @throws IOException
	 */
	public void parseTabInfo() throws IOException {
		HashMap<String, String> prevLineInfo = null;

		int currentBarNum = 0;
		for (int fileLineIdx=0; fileLineIdx<this.fileLines.size(); fileLineIdx++) {
			
			HashMap<String, String> currLineInfo = getLineInfo(this.fileLines.get(fileLineIdx), prevLineInfo, currentBarNum);	//generate the corresponding data from this line up to just before the next bar (if another bar is on the same line) and tell me the type of information recieved(a bar with notes or other types) string represented by that line, all the music info of that line, and a new line containing none of the data that was just parsed (i.e, delete the content of the line you just parsed, up to the next bar(if another bar is on the same line), so that the next bar can be easily reached). returns null if the information on the line doesnt make sense
			String lineInfoType = currLineInfo.get("type");
			if (lineInfoType=="bar" && currLineInfo.get("barEnded")=="true")	//if we just finished reading the bars that appear in the first bar bunch
				currentBarNum = Integer.valueOf(currLineInfo.get("enndingBar"));
			
			//if this line doesn't make sense, move to the next (empty lines don't make sense too)
			if (lineInfoType=="meaningless")
				continue;
			
			//if there's an instruction line in the middle of a bar, it is an error
			if (lineInfoType=="instr" && !(prevLineInfo.get("type")=="bar" && currLineInfo.get("barEnded")=="false"))
				throw new InvalidTabFormatException("Invalid tablature format at line "+ (fileLineIdx+1));
			
			//making guitar tabs work in reverse by detecting which is the lower e and upper E
			if (lineInfoType=="bar" && prevLineInfo.get("type")=="bar" && prevLineInfo.get("bar-line-name")=="?") {
				 if (currLineInfo.get("bar-line-name")=="B")
					 prevLineInfo.put("bar-line-name", "e");
				 else if (currLineInfo.get("bar-line-name")=="B")
					 prevLineInfo.put("bar-line-name", "E");
				 parseLineInfo(prevLineInfo);
			}
			parseLineInfo(currLineInfo);
		}

	}
	
	
	HashMap<String, String> getLineInfo(String line, HashMap<String, String> prevLineInfo, int currentBarNum) {
		HashMap<String, String> lineInfo = new HashMap<String, String>();
		Set<Character> symbolsSet = new HashSet<Character>();
		Set<Character> lineNameSet = new HashSet<Character>();
		Set<Character> barSymbolsSet = new HashSet<Character>();
		Set<Character> instrSymbolsSet = new HashSet<Character>();
		
		int prevBarNum = currentBarNum; 
		
		boolean barStarted = false;
		int distance = 0;
		
		char lineName = ' ';
		char prevSymb = ' ';
		for(int i = 0; i<line.length(); i++) {
	      char symbol = line.charAt(i);
	      
	      
	      //----------ending an ongoing bar--------------
	      //the bar ends when it was already started and we have a "|" and the previous character was something in the bar. 
	      if (barStarted && symbol=='|' && (prevSymb!='|' && barSymbolsSet.contains(prevSymb))) {
	    	  barStarted = false;
	      }
	      
	      //----------starting a new bar---------------------
	      //handling when a new bar starts but doesn't have the line name provided next to it (i.e E|--------|------) the second bar starts using the same name as the first
	      //this is only permitted if the line name has previously been defined
	      if (!barStarted && lineName!=' ' && symbol == '|' && i+1<line.length() && barSymbolsSet.contains(line.charAt(i+1))) {
	    	  barStarted = true;
	    	  currentBarNum++;
	      }else if (!barStarted && symbol=='|' && lineNameSet.contains(prevSymb)) {	//if we have a "|" and the previous symbol was one of the line names, we are starting a bar
	    	  lineName = prevSymb;
	    	  barStarted = true;
	    	  currentBarNum++;
	      }
	      
	      //if there's a double "|" e.g "||", ignore the duplicate ones (don't count it toward distance)
	      if (symbol=='|' && prevSymb=='|') {
	    	  prevSymb = symbol;
	    	  continue;
	      }
	      
	      //we ignore unknown characters. they don't count toward  distance. it makes calculating the distance between bars easier
	      if (symbolsSet.contains(symbol))
	    	  distance++;
	      else
	    	  continue;
	      
	      
	      if (barStarted) {
	    	  //set line name for the bar
	    	  lineInfo.put("type", "bar");
	    	  this.parsedInfo[currentBarNum][]
	    	  //if we changed bars
	    	  if (currentBarNum!=prevBarNum) {
	    		  
	    	  }
	    	  lineInfo.put("bar-line-name", String.valueOf(lineName));
	      }
	    	  
	      

	      prevBarNum = currentBarNum; 
	    }
		return new HashMap<String, String>();
	}

}
