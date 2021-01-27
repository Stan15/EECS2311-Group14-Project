package Guitar;


import java.util.ArrayList;
import java.util.HashMap;

import converter.Instructions;

public class GuitarBar {
	private ArrayList<String> lines;
	private ArrayList<Instructions> instructions;

	GuitarBar(ArrayList<String> lines) {
		this.lines = lines;
	}

	/**
	 * this converts a line in a bar to a more friendly format for conversion to musicXML
	 * @return i haven't decided the format which will be friendly for conversion yet, so idk.
	 * @param line this is a string containing the information for a line in a bar.
	 * @param info this is a  HashMap that contains other information about the bar line, such as time signature and such.
	 */
	public void parseBarLine(String line, HashMap<String, String> info) {

		//some instructions are made up of multiple characters, so i am using the sliding window algorithm to continue to collect characters
		//as long as there are still characters to collect for this instruction

		//The left window is the start of the instruction and the right is the end. 
		int leftWindow = 0;
		int rightWindow = 0;
		boolean collectingInstruction = false;
		while(!(leftWindow==rightWindow && rightWindow==line.length())) {
			int windowSize = rightWindow-leftWindow;
			String instruction; //This contains the instruction and its distance from 
			
			//If the cahracter we are currently looking at with the right pointer is not a BAR_INSTRUCTION character
			//then we stop collecting instructions
			if (!Instructions.BAR_INSTRUCTION_SET.contains(line.charAt(rightWindow)+"")) {
				collectingInstruction = false;
			}else {
				collectingInstruction = true;
			}

			//If we just finished collecting an instruction, store the starting distance of the instruction and the instruction itself
			if (windowSize!=0 && !collectingInstruction) {
				instruction = leftWindow + line.substring(leftWindow, rightWindow);
			}

			//------algorithm for sliding the window--------
			//When there is no more info needed to execute the specific instruction (or more generally, when we are not
			//executing an instruction), we "close the window".
			if (!collectingInstruction) {
				rightWindow++;
				leftWindow = rightWindow;
			}else {
				rightWindow++;
			}
		}
	}
}
