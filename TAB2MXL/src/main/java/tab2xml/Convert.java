package tab2xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Convert {
	ArrayList<String> fileLines = new ArrayList<>();
	String[][] converted;	//String[bar][stringKey]

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Convert convert = new Convert();
			convert.readLines("passenger - a thousand matches tabs.txt");
			convert.readMyFile(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void readLines(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while (true) {
			String line = reader.readLine().trim();
			if (line==null)
				return;
			if (line!="")
				fileLines.add(line);
		}
	}
	
	
	/**
	 * Per each new string in a single bar must be separated by at least a new line
	 * the strings must be either in the forward or backward order E B G D A E
	 * @param currentBar
	 * @throws IOException
	 */
	public void readMyFile(int currentBar) throws IOException {
		String[] previousStringBarInfo = null;

		int currentLineIdx = 0;
		boolean goToNextBar = false;
		while (!goToNextBar) {
			if (currentLineIdx>=this.fileLines.size())
				return;
			String[] stringBarInfo = parseStringBar(previousStringBarInfo);	//generate the corresponding data from this line up to just before the next bar (if another bar is on the same line) and tell me the string represented by that line, all the music info of that line, and a new line containing none of the data that was just parsed (i.e, delete the content of the line you just parsed, up to the next bar(if another bar is on the same line), so that the next bar can be easily reached). returns null if full bar strings not given
			if (stringBarInfo==null) {
				currentLineIdx++;
				continue;
			}
			if (stringBarInfo[0]=="?") { // (if we are not sure if its the high or low E)
				currentLineIdx++;
				continue;
			}
			if (previousStringBarInfo[0]=="?" && stringBarInfo[0]=="B") { // (if B follows E, we know it is the low e)
				previousStringBarInfo[0] = "e";	//
				fileLines.set(currentLineIdx, stringBarInfo[2]);	//now we are certain what it is, we add it to the list
			} else if (previousStringBarInfo[0]=="?" && stringBarInfo[0]=="A") { // (if A follows E, we know it is the high e)
				previousStringBarInfo[0] = "E";
				fileLines.set(currentLineIdx, stringBarInfo[2]);
			}
			
			//evaluate the info which you received from parsing the line like determine if youre at the end of the bar and set goToNextBar to true
			fileLines.set(currentLineIdx, stringBarInfo[2]);	//set the line to the new line created where the parsed info is deleted so the next bar can be more easily accessed
			//set the info evaluated () be the "previous" info evaluated.
			previousStringBarInfo = stringBarInfo;
			currentLineIdx++;	//move to consider the string in the bar
		
			
		}
		//remove any empty lines that may have been created by parsing all the info on each line 
		for (int i=currentLineIdx; i>=0; i--) {
			if (this.fileLines.get(i) == "") {
				this.fileLines.remove(i);
			}
		}
		
		readMyFile(currentBar+1);
	}
}
