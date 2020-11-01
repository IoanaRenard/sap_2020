package ro.ase.ism.sap;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestFiles {
	
	
	public static void dir(String path) {
		
		File location = new File(path);
		if(location.exists()) {		
			File[] files = location.listFiles();
			for(File file : files) {
				String description = file.isDirectory() ? "<Folder> " : "";
				description += file.getName();
				System.out.println(description);
				
				//go into sub-folders
				/*
				 * if(file.isDirectory()) dir(file.getPath());
				 */
			}		
		}
		else {
			System.out.println("Wrong path");
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		//interact with the file system
		dir("D:\\ase 2020-2021\\sap");
		
		//text files
		
		//creating and opening them
		File messageFile = new File("message.txt");
		if(!messageFile.exists()) {
			messageFile.createNewFile();
		}
		
		//write some text lines
		PrintWriter pWriter = new PrintWriter(messageFile);
		pWriter.println("Hello");
		pWriter.println("How are you");
		
		//close the files
		pWriter.close();
		
		
		//reading from text files
		File inputMessage = new File("message.txt");
		if(!inputMessage.exists()) {
			System.out.println("The input file is missing ***************");
		}
		else {
			FileReader reader = new FileReader(inputMessage);
			BufferedReader bReader = new BufferedReader(reader);
			
			String line = "";
			
			while(line != null) {
				line = bReader.readLine();
				if(line !=null)
					System.out.println("File line: " + line);
			}
			
			/*
			 * while((line = bReader.readLine())!=null) { System.out.println("File line: " +
			 * line); }
			 */
			
			reader.close();
		}
		
		//binary files
		File dataFile = new File("myData.bin");
		if(!dataFile.exists()) {
			dataFile.createNewFile();
		}
		
		//write data primitives into the binary file
		FileOutputStream fos = new FileOutputStream(dataFile);
		DataOutputStream dos = new DataOutputStream(fos);
		int intValue = 23;
		boolean boolValue = true;
		float floatValue = 23.7f;
		double doubleValue = 45.99999999;
		String text = "Hello !";
		dos.writeInt(intValue);
		dos.writeBoolean(boolValue);
		dos.writeFloat(floatValue);
		dos.writeDouble(doubleValue);
		dos.writeUTF(text);
		
		byte[] bytes = new byte[] {0xA, 0x1, 0xB, 0x2};
		dos.write(bytes);
		
		//close the file
		dos.close();
		
		Path path = Paths.get(dataFile.getAbsolutePath());
		System.out.println("File size is " + Files.size(path));
		
		
		//read from a binary file
		FileInputStream fis = new FileInputStream(dataFile);
		DataInputStream dis = new DataInputStream(fis);
		intValue = dis.readInt();
		boolValue = dis.readBoolean();
		floatValue = dis.readFloat();
		doubleValue = dis.readDouble();
		text = dis.readUTF();
		byte[] newByteArray = new byte[4];
		dis.read(newByteArray,0,4);
		
		dis.close();
		
		System.out.println("The double value is " + doubleValue);
		System.out.println("The text value is " + text);
		
		//random access files
		RandomAccessFile raf = 
				new RandomAccessFile("Values.bin", "rw");
		raf.writeInt(10);
		raf.writeInt(20);
		raf.writeInt(30);
		raf.writeInt(40);
		raf.close();
		
		raf = new RandomAccessFile("Values.bin", "r");
		
		//getting the file size
		System.out.println("File size is " + raf.length());
	
		raf.seek(8);
		int theValue = raf.readInt();
		System.out.println("The 3rd value is " + theValue);
		raf.close();
		
		
		System.out.println("The end");
	}

}
