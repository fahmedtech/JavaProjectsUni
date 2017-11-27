import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Proj3aPaul {

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

		Integer total = 0;
		
		String output = "";
		String line = "|";

		String newZipcode = zipcode.replace("-", "");
		
		int i = 0;
		while(i < newZipcode.length()){
			
			char c = newZipcode.charAt(i);
			int digits = Integer.valueOf(c + "");
			
			total += digits; //adding to total
			output += codes[digits]; //adding to the output
					
			i++;
		}

		//checksum
		int checksum = (10 - total % 10) %10;
		String checksumString = codes[checksum];
		
		//final output value
		String retValue = line + output + checksumString + line;
		

		return retValue;
	}
}
