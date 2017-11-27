import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj3aMiguel {

	public static void main(String[] args) throws FileNotFoundException{

		//open and read the file
		JFileChooser chooser = new JFileChooser( );
		chooser.showOpenDialog(null);
		File fileObj = chooser.getSelectedFile( );

		Scanner scanner = new Scanner(fileObj);

		while(scanner.hasNextLine()){
			String res = scanner.nextLine();
			String[] lines = res.split(",");

			String line1 = lines[0];
			String line2 = lines[1];
			String line3 = lines[2] + " " + lines[3];
			String zipcode = lines[4];

			System.out.println(line1);
			System.out.println(line2);
			System.out.println(line3 + " " + zipcode);
			System.out.println(getBarCode(zipcode) + "\n");
		}



	}

	public static String getBarCode(String zipcode){

		String[] codes = {"||:::", ":::||", "::|:|", "::||:", ":|::|",
				":|:|:", ":||::", "|:::|", "|::|:", "|:|::"};

		Integer zipIntSum = 0;
		
		String res = "";
		String line = "|";

		String[] zip = zipcode.split("-");
		String oneHalf = zip[0];
		String twoHalf = zip[1];
		
		for(int i = 0; i < oneHalf.length(); i++){
			char characters = oneHalf.charAt(i);
			int toNumoneHalf = Integer.valueOf(characters + "");
			
			res += codes[toNumoneHalf];
			
			zipIntSum += toNumoneHalf;
			
		}
		
		for(int i = 0; i < twoHalf.length(); i++){
			char characters = twoHalf.charAt(i);
			int toNumtwoHalf = Integer.valueOf(characters + "");
			
			res += codes[toNumtwoHalf];
			
			zipIntSum += toNumtwoHalf;
			
		}

		int checksum = (10 - zipIntSum % 10) %10;
		String checksumString = codes[checksum];
		
		String retValue = line + "" + res + "" + checksumString + "" + line;
		

		return retValue;
	}
}
