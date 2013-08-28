package com.rvsm.alphabetcounter;

/**@author Rajath V S Moodithaya
 * @brief Alphabet Counter accepts an input file
 */
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

public class AlphabetCounter extends PApplet {

	/**
	 * A integer array of size 26 to hold the count of each alphabet  
	 */
	private int[] character = new int[26];
	/**
	 * The number of iterations
	 */
	private int count = 0;
	/**
		 * A PFont object to use a particular font
		 */
	private PFont f;
	/**
			 * A array of strings for storing the text from the file
			 */
	private String lines[];
	/**
	 * String array to hold the command line arguments
	 */
private static String args[];
	/**
				 * A PGraphics object
				 */
	private PGraphics pg;
	/**
					 * A unique ID to identify the version
					 */
	private static final long serialVersionUID = 2L;

	/**
	 * The main function
	 */
	public static void main(String _args[]) {
		args=_args;
		PApplet.main(new String[] { AlphabetCounter.class.getName() });
		
	}

	/**
	 * The draw function. This gets called every time the screen is refreshed,
	 * till the program is exited.
	 */
	public void draw() {
		noFill();
		smooth();
		stroke(200);
		

		for (int i = 0; i < 26; i++) {
			line(i * 30 + 50, 400, i * 30 + 50, 400 - character[i] / 300);
			text((char) (i + 'a'), i * 30 + 50, 450);
			if (count < lines.length) {
				char[] subtext = lines[count++].toCharArray();
				for (char character : subtext) {
					this.character[character - 'a']++;
				}
			}
			if (count % 100 == 0 || count == lines.length) {
				pg.clear();
				pg.beginDraw();
				pg.background(30);
				pg.stroke(255);
				pg.textFont(f, 20);
				pg.textAlign(CENTER);
				pg.text(lines[count - 1], 200, 33);
				pg.text("Completed " + count + " of " + lines.length
						+ " lines.", 200, 66);
				pg.endDraw();
				image(pg, width / 2 - 200, 550);
			}
		}
		
		/**
		 * Uncomment the following block to get connect the points and to get a shaded fill for the same.*/
		/*for (int p = 0; p < character.length-1; p++) {
			line(p * 30 + 50, 400 - character[p] / 300, (p + 1) * 30 + 50,
					400 - character[p + 1] / 300);
		}*/
	}

	/**
	 * This function reads the data from the file specified in the command line argument
	 */
	private void readData() {
		try{
		println(args[0]);
		lines = loadStrings(args[0]);
		}
		catch(NullPointerException e ){
			println("Invalid file. Exiting program\n");
			exit();
		}
		catch(ArrayIndexOutOfBoundsException e ){
			println("Invalid file. Exiting program\n");
			exit();
		}
	}

	/**
	 * This is the setup part, where we initialize the UI and load resources.
	 * This is just called once in every program. The draw method
	 * is called next.
	 */
	public void setup() {
		size(900, 640);
		frame.setTitle("Alphabet Counter");
		background(0);
		readData();
		pg = createGraphics(400, 100);
		frameRate(10000);
		f = createFont("Monospace", 30, true);
	}
}