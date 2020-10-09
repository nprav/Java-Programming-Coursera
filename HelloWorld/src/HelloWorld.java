package src;

import edu.duke.*;

public class HelloWorld {
	public void runHello () {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
	public void runHello2 () {
		FileResource res = new FileResource("file.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
	public static void main(String[] args) {
		System.out.println("Main method output:\n");
		// Make a new instance of the class HelloWorld
		HelloWorld hw = new HelloWorld();
		hw.runHello2();

	}
}
