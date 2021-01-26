package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Convert {
	private ArrayList<String> tab = new ArrayList<String>();
	private File inputFile;

	public Convert(String inFile) {
		inputFile = new File(inFile);
		tab = new ArrayList<String>();
		this.readFile();
	}

	private void readFile() {
		Scanner sc = null;
		try {
			sc = new Scanner(inputFile);
			while(sc.hasNextLine()){
				tab.add(sc.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}

	public void buildTabList() throws InvalidTabFormatException{

		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < temp.size(); i++) {
			temp = new ArrayList<>(Arrays.asList(((String) temp.get(i))));
			if(temp.get(i).length() == 0) {
				continue;
			}else {

			}



		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = "/Users/adilhashmi/Desktop/passenger_-_a_thousand_matches_tabs.txt";
		Convert Test = new Convert(filePath);
		System.out.println(Test);
	}
}
