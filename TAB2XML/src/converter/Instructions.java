package converter;

import java.util.Arrays;
import java.util.HashSet;

public class Instructions {
	public static HashSet<Character> BAR_INSTRUCTION_SET = Instructions.getBarInstructionSet();		//all characters contained in all instructions
	public static HashSet<String> GUITAR_BAR_INSTRUCTION_SET = Instructions.getGuitarBarInstructionSet();	//It is string just to keep consistent with drum instruction set which has some instructions that are multiple characters in length
	public static HashSet<String> DRUM_BAR_INSTRUCTION_SET = Instructions.getDrumBarInstructionSet();	//It is string just to keep with consistency o
	
	
	//gives all characters that can be found in all bar types.
	private static HashSet<Character> getBarInstructionSet() {
		Character[] barInstructionSet = {
				'e','b','g','d','a','h','t',
				'/','\\','(',')'
				};
		return new HashSet<>(Arrays.asList(barInstructionSet));
	}
	
	private static HashSet<String> getGuitarBarInstructionSet() {
		String[] barInstructionSet = {
				"E","B","G","D","A",
				"h","t",
				"/","\\",	//double "\" because "\" is an escape character
				"(",")"
				};
		return new HashSet<>(Arrays.asList(barInstructionSet));
	}
	
	private static HashSet<String> getDrumBarInstructionSet() {
		String[] barInstructionSet = {
				"E","B","G","D","A",
				"h","t",
				"/","\\",	//double "\" because "\" is an escape character
				"(",")"
				};
		return new HashSet<>(Arrays.asList(barInstructionSet));
	}
}
