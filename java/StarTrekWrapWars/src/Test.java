import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Test {
	static enigma.console.Console cn = Enigma.getConsole("StarTrek", 100, 35, 20, 7);
	static KeyListener klis;
	static int keypr;
	static int rkey;
	static int totalRow = 23;
	static char[][] mazeArr = new char[totalRow][];
	static Queue input = new Queue(15);
	static Treasure[] treasures = new Treasure[0];
	static Device[] devices = new Device[0];
	static ComputerTest[] computers = new ComputerTest[0];
	public static Player player;
	static int speed;
	static Color white = new Color(224,224,224);
	static Color mazeBackgroundColor = new Color(20,20,20);
	static TextAttributes color = new TextAttributes(white, Color.BLACK);

	public static void main(String[] args) throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		startingScreen();

        Clip clip = AudioSystem.getClip();
        
		keypr = 0;
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		
		boolean menu = true;
		while(menu) {
			consoleClear();
			//System.out.println(cn.getTextAttributes().getBackground());
			AudioInputStream menuAudio = AudioSystem.getAudioInputStream(new File("menumusic.wav"));
			clip.close();
			clip.open(menuAudio);
			clip.start();
			System.out.println("\n\n");
			System.out.println("         _.-'~~~~~~`-._                                                    \r\n"
					+ "        /      ||      \\                                                   \r\n"
					+ "       /       ||       \\             \r\n"
					+ "      |        ||        |                                \r\n"
					+ "      | _______||_______ |                                                 \r\n"
					+ "      |/ ----- \\/ ----- \\|                                                 \r\n"
					+ "     /  (     )  (     )  \\                                                \r\n"
					+ "    / \\  ----- () -----  / \\                                               \r\n"
					+ "   /   \\      /||\\      /   \\                                              \r\n"
					+ "  /     \\    /||||\\    /     \\    \r\n"
					+ " /       \\  /||||||\\  /       \\                                            \r\n"
					+ "/_        \\o========o/        _\\                                           \r\n"
					+ "  `--...__|`-._  _.-'|__...--'                                             \r\n"
					+ "          |    `'    |             ");
			
			cn.getTextWindow().setCursorPosition(65, 3);
			System.out.println("         _.-'~~~~~~`-._");
			cn.getTextWindow().setCursorPosition(65, 4);
			System.out.println("        /      ||      \\");
			cn.getTextWindow().setCursorPosition(65, 5);
			System.out.println("       /       ||       \\");
			cn.getTextWindow().setCursorPosition(65, 6);
			System.out.println("      |        ||        |");
			cn.getTextWindow().setCursorPosition(65, 7);
			System.out.println("      | _______||_______ |");
			cn.getTextWindow().setCursorPosition(65, 8);
			System.out.println("      |/ ----- \\/ ----- \\|");
			cn.getTextWindow().setCursorPosition(65, 9);
			System.out.println("     /  (     )  (     )  \\");
			cn.getTextWindow().setCursorPosition(65, 10);
			System.out.println("    / \\  ----- () -----  / \\");
			cn.getTextWindow().setCursorPosition(65, 11);
			System.out.println("   /   \\      /||\\      /   \\");
			cn.getTextWindow().setCursorPosition(65, 12);
			System.out.println("  /     \\    /||||\\    /     \\");
			cn.getTextWindow().setCursorPosition(65, 13);
			System.out.println(" /       \\  /||||||\\  /       \\");
			cn.getTextWindow().setCursorPosition(65, 14);
			System.out.println("/_        \\o========o/        _\\");
			cn.getTextWindow().setCursorPosition(65, 15);
			System.out.println("  `--...__|`-._  _.-'|__...--'");
			cn.getTextWindow().setCursorPosition(65, 16);
			System.out.println("          |    `'    |");
			
			cn.getTextWindow().setCursorPosition(38, 8);
			System.out.println("  ********************");
			cn.getTextWindow().setCursorPosition(38, 9);
			System.out.println(" *                  **");
			cn.getTextWindow().setCursorPosition(38, 10);
			System.out.println("******************** *");
			cn.getTextWindow().setCursorPosition(38, 11);
			System.out.println("*       MENU       * *");
			cn.getTextWindow().setCursorPosition(38, 12);
			System.out.println("*                  * *");
			cn.getTextWindow().setCursorPosition(38, 13);
			System.out.println("*  1. Start Game   * *");
			cn.getTextWindow().setCursorPosition(38, 14);
			System.out.println("*                  * *");
			cn.getTextWindow().setCursorPosition(38, 15);
			System.out.println("*  2. Game Info    * *");
			cn.getTextWindow().setCursorPosition(38, 16);
			System.out.println("*                  * *");
			cn.getTextWindow().setCursorPosition(38, 17);
			System.out.println("*  3. Exit         * *");
			cn.getTextWindow().setCursorPosition(38, 18);
			System.out.println("*                  **");
			cn.getTextWindow().setCursorPosition(38, 19);
			System.out.println("********************");

			while (true) {
				if (keypr == 1) {
					//System.out.print("");	//BUNU SAKIN SİLME
					if (rkey == KeyEvent.VK_1) {
						clip.stop();
						clip.close();
						consoleClear();
						startGame();
					} else if (rkey == KeyEvent.VK_2) {
						
						consoleClear();
						rkey = 0;
						gameInfo();
						Thread.sleep(5000);
					}
					else if (rkey == KeyEvent.VK_3)
						System.exit(0);
		
					keypr = 0;
					break;
				}
				System.out.print(""); //BUNU SAKIN SİLME
				
			}

		}
		

	}
	
	static void gameInfo() {
		int y= 3;
		char[][] arr =  {{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
                {'#',' ',' ',' ',' ',' ',' ','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#',' ',' ','#','#','#'},
                {'#',' ',' ',' ','#',' ','#',' ','#',' ',' ',' ',' ','#',' ',' ','#',' ','#'},
                {'#',' ',' ',' ','#',' ','#',' ',' ','#',' ',' ','#','#','#',' ','#',' ','#'},
                {'#',' ','#','#','#',' ','#',' ',' ','#',' ',' ','#',' ','#',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ','#'},
                {'#',' ','#',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#','#',' ','#','#','#','#','#','#',' ',' ',' ',' ','#',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ','#','#',' ',' ','#',' ',' ','#',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ','#',' ',' ','#','#',' ','#','#','#',' ','#'},
                {'#',' ',' ','#','#',' ','#',' ','#',' ',' ',' ','#',' ',' ',' ','#',' ','#'},
                {'#',' ','#','#','#',' ','#',' ',' ',' ',' ',' ','#',' ',' ',' ','#',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ','#',' ',' ',' ',' ',' ','#'},
                {'#',' ','#',' ','#',' ',' ',' ',' ','#',' ',' ',' ',' ',' ',' ',' ',' ','#'},
                {'#','#','#',' ','#','#','#','#','#','#',' ',' ',' ',' ','#',' ',' ',' ','#'},
                {'#',' ',' ',' ',' ',' ',' ','#','#',' ',' ','#',' ',' ','#',' ',' ',' ','#'},
                {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.print("• The game is played in a game ");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.print(" field including walls.");
		for (int i = 0; i < arr.length; i++) {
			cn.getTextWindow().setCursorPosition(55, y);
			y++;
			for (int j = 0; j < arr[0].length; j++) {
				cn.setTextAttributes(colorSelection(arr[i][j]));
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.print("• There are two competitors:");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.print(" Player (P) and Computer (C)");
		cn.getTextWindow().setCursorPosition(2,12);
		System.out.print(" and they inserted the maze randomly.");
		cn.setTextAttributes(colorSelection('P'));
		cn.getTextWindow().setCursorPosition(57, 5);
		System.out.print('P');
		cn.setTextAttributes(colorSelection('C'));
		cn.getTextWindow().setCursorPosition(71, 18);
		System.out.print('C');
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.print("• There are some treasures/numbers in the game,");
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.print(" which the players collect to increase their scores.");
		cn.setTextAttributes(colorSelection('1'));
		cn.getTextWindow().setCursorPosition(62, 7);
		System.out.print('1');
		cn.setTextAttributes(colorSelection('2'));
		cn.getTextWindow().setCursorPosition(66, 14);
		System.out.print('2');
		cn.setTextAttributes(colorSelection('3'));
		cn.getTextWindow().setCursorPosition(56, 20);
		System.out.print('3');
		cn.setTextAttributes(colorSelection('4'));
		cn.getTextWindow().setCursorPosition(70, 10);
		System.out.print('4');
		cn.setTextAttributes(colorSelection('5'));
		cn.getTextWindow().setCursorPosition(56, 13);
		System.out.print('5');
		
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		
		cn.getTextWindow().setCursorPosition(2, 18);
		System.out.print("• The aim of the game is ");
		cn.getTextWindow().setCursorPosition(2, 20);
		System.out.print(" gaining the highest end-game score.");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2,12);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.print("                                                 ");
		cn.getTextWindow().setCursorPosition(2, 16); 
		System.out.print("                                                    ");
		cn.getTextWindow().setCursorPosition(2, 18);
		System.out.print("                                           ");
		cn.getTextWindow().setCursorPosition(2, 20);
		System.out.print("                                           ");
		
		cn.setTextAttributes(colorSelection(' '));
		cn.getTextWindow().setCursorPosition(57, 5);
		System.out.print(' ');
		cn.setTextAttributes(colorSelection('P'));
		cn.getTextWindow().setCursorPosition(58, 5);
		System.out.print('P');
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		cn.getTextWindow().setCursorPosition(2, 2);
		System.out.println("Game Elements");
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.println("Player");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.print("• Player uses cursor keys to control P.");
		cn.getTextWindow().setCursorPosition(2, 7);
		System.out.print(" Up cursor(↑): Move up");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.print(" Left cursor(←): Move left");
		cn.getTextWindow().setCursorPosition(2, 9);
		System.out.print(" Down cursor(↓): Move down");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.print(" Right cursor(→): Move right");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 12);
		System.out.print("• Player has a backpack (size of 8 elements).");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.print("• Player uses WASD keys to ");
		cn.getTextWindow().setCursorPosition(2, 15);
		System.out.print(" remove an element from the backpack.");
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.print(" W: Place up");
		cn.getTextWindow().setCursorPosition(2, 17);
		System.out.print(" A: Place left");
		cn.getTextWindow().setCursorPosition(2, 18);
		System.out.print(" S: Place down");
		cn.getTextWindow().setCursorPosition(2, 19);
		System.out.print(" D: Place right");
		cn.getTextWindow().setCursorPosition(2, 21);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("• Player has an energy for ");
		cn.getTextWindow().setCursorPosition(2, 22);
		System.out.print(" quick movement (2 times faster).");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.println("                                           ");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 7);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 9); 
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 12);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 15);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 17);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 18);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 19);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 21);
		System.out.print("                                             ");
		cn.getTextWindow().setCursorPosition(2, 22);
		System.out.print("                                             ");
		
		cn.setTextAttributes(colorSelection(' '));
		cn.getTextWindow().setCursorPosition(71, 18);
		System.out.print(' ');
		cn.setTextAttributes(colorSelection('C'));
		cn.getTextWindow().setCursorPosition(71, 17);
		System.out.print('C');
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.println("Computer");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.println("• Computer controls all C robots.");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.println("• Treasures are 2 times valuable for computer.");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.println("• C robot selects a target,");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.println(" then tries to go to that target directly.");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 12);
		System.out.println("Numbers (1-5): Treasure elements");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.println("• 1-3 : Static numbers. They cannot move.");
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.println("• 4-5 : Moving numbers. They move randomly.");
		cn.setTextAttributes(colorSelection(' '));
		cn.getTextWindow().setCursorPosition(70, 10);
		System.out.print(' ');
		cn.setTextAttributes(colorSelection(' '));
		cn.getTextWindow().setCursorPosition(56, 13);
		System.out.print(' ');
		cn.setTextAttributes(colorSelection('4'));
		cn.getTextWindow().setCursorPosition(71, 10);
		System.out.print('4');
		cn.setTextAttributes(colorSelection('5'));
		cn.getTextWindow().setCursorPosition(56, 14);
		System.out.print('5');
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.println("                   ");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.println("                                              ");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.println("                                              ");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.println("                                               ");
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.println("                                               ");
		cn.getTextWindow().setCursorPosition(2, 12);
		System.out.println("                                               ");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.println("                                               ");
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.println("                                               ");
		
		cn.getTextWindow().setCursorPosition(2, 4);
		System.out.println("Other treasures:");
		cn.getTextWindow().setCursorPosition(2, 6);
		System.out.println("• = : Trap device. It stops the numbers and C robots.");
		cn.getTextWindow().setCursorPosition(2, 8);
		System.out.println("• * : Warp device. It warps the numbers and C robots.");
		cn.setTextAttributes(colorSelection('='));
		cn.getTextWindow().setCursorPosition(62, 5);
		System.out.print('=');
		cn.setTextAttributes(colorSelection('*'));
		cn.getTextWindow().setCursorPosition(68, 10);
		System.out.print('*');
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 10);
		System.out.println("• Computer robots and numbers");
		cn.getTextWindow().setCursorPosition(2, 12);
		System.out.println(" cannot detect/avoid trap/warp devices");
		cn.getTextWindow().setCursorPosition(2, 14);
		System.out.println("• Trap and warp devices do not affect human player");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 16);
		System.out.println("• Trap and warp devices have an effect area:");
		cn.getTextWindow().setCursorPosition(2, 18);
		System.out.println(" Square of the device and 8 neighbor squares");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cn.getTextWindow().setCursorPosition(2, 20);
		System.out.println("• Trap and warp devices have a duration: ");
		cn.getTextWindow().setCursorPosition(2, 22);
		System.out.println(" They are active for 25 seconds after activation,");
		cn.getTextWindow().setCursorPosition(2, 24);
		System.out.println(" then they disappear.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	static void startingScreen() {
		String start = "\r\n\n\n\n\n\n\n"
				+ "\t\t\t ░██████╗████████╗░█████╗░██████╗░░░████████╗██████╗░███████╗██╗░░██╗\r\n"
				+ "\t\t\t ██╔════╝╚══██╔══╝██╔══██╗██╔══██╗░░╚══██╔══╝██╔══██╗██╔════╝██║░██╔╝\r\n"
				+ "\t\t\t ╚█████╗░░░░██║░░░███████║██████╔╝░░░░░██║░░░██████╔╝█████╗░░█████═╝░\r\n"
				+ "\t\t\t ░╚═══██╗░░░██║░░░██╔══██║██╔══██╗░░░░░██║░░░██╔══██╗██╔══╝░░██╔═██╗░\r\n"
				+ "\t\t\t ██████╔╝░░░██║░░░██║░░██║██║░░██║░░░░░██║░░░██║░░██║███████╗██║░╚██╗\r\n"
				+ "\t\t\t ╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚═╝░░░░░╚═╝░░░╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝\r\n"
				+ "\r\n"
				+ "\t\t░██╗░░░░░░░██╗░█████╗░██████╗░██████╗░░░░██╗░░░░░░░██╗░█████╗░██████╗░░██████╗\r\n"
				+ "\t\t░██║░░██╗░░██║██╔══██╗██╔══██╗██╔══██╗░░░██║░░██╗░░██║██╔══██╗██╔══██╗██╔════╝\r\n"
				+ "\t\t░╚██╗████╗██╔╝███████║██████╔╝██████╔╝░░░╚██╗████╗██╔╝███████║██████╔╝╚█████╗░\r\n"
				+ "\t\t░░████╔═████║░██╔══██║██╔══██╗██╔═══╝░░░░░████╔═████║░██╔══██║██╔══██╗░╚═══██╗\r\n"
				+ "\t\t░░╚██╔╝░╚██╔╝░██║░░██║██║░░██║██║░░░░░░░░░╚██╔╝░╚██╔╝░██║░░██║██║░░██║██████╔╝\r\n"
				+ "\t\t░░░╚═╝░░░╚═╝░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░░░░░░╚═╝░░░╚═╝░░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░";
		for (int i = 0; i < 6; i++) {
			cn.getTextWindow().setCursorPosition(0, 0);
			cn.setTextAttributes(new TextAttributes(new Color(randomInt(0, 255),randomInt(0, 255),randomInt(0, 255)),Color.BLACK));
			System.out.println(start);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			consoleClear();
		}
		cn.setTextAttributes(new TextAttributes(white,Color.BLACK));
	}
	
	static void spawningPlayer() {
		int rX = 0,rY=0;
		do{
			rY = (int) (Math.random()*23);
			rX = (int) (Math.random()*55);
		}
		while(mazeArr[rY][rX] !=' ');
		mazeArr[rY][rX] = 'P';
		player.setPosX(rX);
		player.setPosY(rY);
	}
	static void startGame() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		int rndX=0, rndY=0;
		for (int i = 0; i < 15; i++) input.enqueue(itemGenerator());
		int rnd = randomInt(1, 5);
		Scanner scanner;
		switch (rnd) {
		case 1: {
			scanner = new Scanner(new File("maze.txt"));
			break;
		}case 2: {
			scanner = new Scanner(new File("maze2.txt"));
			break;
		}case 3: {
			scanner = new Scanner(new File("maze3.txt"));
			break;
		}case 4: {
			scanner = new Scanner(new File("maze4.txt"));
			break;
		}
		default:
			scanner = new Scanner(new File("maze5.txt"));
		}
		
		for (int row = 0; scanner.hasNextLine() && row < totalRow; row++) {
			mazeArr[row] = scanner.nextLine().toCharArray();
		}
		cn.getTextWindow().setCursorPosition(0, 0);
		do{
			rndY = (int) (Math.random()*23);
			rndX = (int) (Math.random()*55);
		}
		while(mazeArr[rndY][rndX] !=' ');
		
		player = new Player(rndX,rndY);
		
		for (int i = 0; i < mazeArr.length; i++) {
			for (int j = 0; j < mazeArr[i].length; j++) {
				if(player.getPosX() == j && player.getPosY() == i) mazeArr[i][j] = 'P';
			}
		}
		
		spawningMap('C');
		
		for (int i = 0; i < 20; i++) {
			 spawningMap(itemGenerator());
		}
		
		long Timer1 =  System.currentTimeMillis();
		long Queuetime1 =  System.currentTimeMillis();
		
		int i = 0;
		while(player.getLife() > 0) {
			if (keypr == 1) {
				playerMove(player, mazeArr);
			    removeBackpack();
               if (rkey == KeyEvent.VK_ESCAPE) break;
				keypr = 0;
			}
			
			long Timer2 =  System.currentTimeMillis();
			cn.getTextWindow().setCursorPosition(57, 22);
			System.out.println("Time    :    "+(Timer2-Timer1)/1000);
			if(Timer2-Queuetime1>3000) {
				generatingItems(input);
				Queuetime1 = System.currentTimeMillis();
			}
			displayQueue(input);
			displayBackPack(player.getBackpack());
			
			if(player.getEnergyTime()>0) {
				if(i%2 == 0) {
					computerEvents();
					treasureMove();
				}
				if(i%4 == 0)
					player.setEnergyTime(player.getEnergyTime()-1);
				for (int j = 0; j < devices.length; j++) {
					
					if(i%4 == 0)devices[j].setRemainingTime(devices[j].getRemainingTime() -1);
					devices[j].deviceEvent();
				}
				displayScreen();
				Thread.sleep(250);
			}
			else {
				computerEvents();
				treasureMove();
				for (int j = 0; j < devices.length; j++) {
					
					if(i%2 == 0)devices[j].setRemainingTime(devices[j].getRemainingTime() -1);
					devices[j].deviceEvent();
				}
				
				displayScreen();
				
				Thread.sleep(500);
			}
			i++;
		}
		consoleClear();
		cn.setTextAttributes(new TextAttributes(new Color(205,38,38),Color.BLACK));
		System.out.println("\r\n\n\n\n"
				+ "\t\t\t░██████╗░░█████╗░███╗░░░███╗███████╗░░░█████╗░██╗░░░██╗███████╗██████╗░\r\n"
				+ "\t\t\t██╔════╝░██╔══██╗████╗░████║██╔════╝░░██╔══██╗██║░░░██║██╔════╝██╔══██╗\r\n"
				+ "\t\t\t██║░░██╗░███████║██╔████╔██║█████╗░░░░██║░░██║╚██╗░██╔╝█████╗░░██████╔╝\r\n"
				+ "\t\t\t██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░░░██║░░██║░╚████╔╝░██╔══╝░░██╔══██╗\r\n"
				+ "\t\t\t╚██████╔╝██║░░██║██║░╚═╝░██║███████╗░░╚█████╔╝░░╚██╔╝░░███████╗██║░░██║\r\n"
				+ "\t\t\t░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝░░░╚════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝");
		cn.setTextAttributes(new TextAttributes(Color.WHITE,Color.BLACK));
		System.out.print("\r\n\n\n"
				+ "\t\t\t░██████╗░█████╗░░█████╗░██████╗░███████╗██╗\r\n"
				+ "\t\t\t██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝╚═╝\r\n"
				+ "\t\t\t╚█████╗░██║░░╚═╝██║░░██║██████╔╝█████╗░░░░░\r\n"
				+ "\t\t\t░╚═══██╗██║░░██╗██║░░██║██╔══██╗██╔══╝░░░░░\r\n"
				+ "\t\t\t██████╔╝╚█████╔╝╚█████╔╝██║░░██║███████╗██╗\r\n"
				+ "\t\t\t╚═════╝░░╚════╝░░╚════╝░╚═╝░░╚═╝╚══════╝╚═╝");
		int finalScore = player.getScore() - ComputerTest.score;
		int tmp = finalScore;
		int digitCount = 0;
		while(tmp>0) {
			tmp = tmp/10;
			digitCount++;
		}
		Stack digitStack = new Stack(digitCount);
		for (int j = 0; j < digitCount; j++) {
			digitStack.push(finalScore%10);
			finalScore /= 10;
		}
		if (player.getScore() - ComputerTest.score>0) {
			int x_ = 0;
			while(!digitStack.isEmpty()) {
				numberToAsciiString((int) digitStack.pop(), 55+x_, 13);
				x_ += 8;

			}
			
			Thread.sleep(5000);
		}else {
			numberToAsciiString(0,55,13);
			Thread.sleep(5000);
		}
		cn.setTextAttributes(new TextAttributes(white,Color.BLACK));
	}
	static void numberToAsciiString(int digit,int x, int y) {
		switch (digit) {
		case 0: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██║░░██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██║░░██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚════╝░");
			break;
		}
		case 1: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░███╗░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░████║░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔██║░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚═╝██║░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("███████╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚══════╝");
			break;
		}
		case 2: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚════██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░███╔═╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══╝░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("███████╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚══════╝");
			break;
		}
		case 3: {
			System.out.print("██████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚════██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚═══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚═════╝░");
			break;
		}
		case 4: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░██╗██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░██╔╝██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔╝░██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("███████║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚════██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░░░░╚═╝");
			break;
		}
		case 5: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("███████╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔════╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚════██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚═════╝░");
			break;
		}
		case 6: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔═══╝░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚════╝░");
			break;
		}
		case 7: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("███████╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚════██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░░░██╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░░██╔╝░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░██╔╝░░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░░╚═╝░░░");
			cn.getTextWindow().setCursorPosition(x, y++);
			break;
		}
		case 8: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚════╝░");
			break;
		}
		case 9: {
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╗░\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("██╔══██╗\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("╚██████║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚═══██║\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░█████╔╝\r\n");
			cn.getTextWindow().setCursorPosition(x, y++);
			System.out.print("░╚════╝░");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + digit);
		}
	}
	static void removeBackpack() throws InterruptedException {
		
		int cursorY = player.getPosY();
		int cursorX = player.getPosX();
		if(!player.getBackpack().isEmpty()) {
			
			if (rkey == KeyEvent.VK_S) 
				cursorY++;
			else if (rkey == KeyEvent.VK_W) 
				cursorY--;
			else if (rkey == KeyEvent.VK_A) 
				cursorX--;
			else if (rkey == KeyEvent.VK_D) 
				cursorX++;	
			
			if(mazeArr[cursorY][cursorX] == ' ') {
				
				if((char)player.getBackpack().peek() == '=' || (char)player.getBackpack().peek() == '*') {
					definingDevices(cursorX,cursorY,(char)player.getBackpack().peek());
					mazeArr[cursorY][cursorX] = player.removeBackpack();
				}
				else player.removeBackpack();
			}
		}
		
	}
	static void playerMove(Player player, char[][] mazeArr) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		player.stealBackpack();
		int cursorY = player.getPosY();
		int cursorX = player.getPosX();
		
		if (rkey == KeyEvent.VK_DOWN && (mazeArr[cursorY+1][cursorX] != '#' && mazeArr[cursorY+1][cursorX] != 'C')) 
			cursorY++;
		else if (rkey == KeyEvent.VK_UP && (mazeArr[cursorY-1][cursorX] != '#' && mazeArr[cursorY-1][cursorX] != 'C')) 
			cursorY--;
		else if (rkey == KeyEvent.VK_LEFT && (mazeArr[cursorY][cursorX-1] != '#' && mazeArr[cursorY][cursorX-1] != 'C')) 
			cursorX--;
		else if (rkey == KeyEvent.VK_RIGHT && (mazeArr[cursorY][cursorX+1] != '#' && mazeArr[cursorY][cursorX+1] != 'C')) 
			cursorX++;
		
		if((player.getPosX() != cursorX || player.getPosY() != cursorY)&& !(mazeArr[cursorY][cursorX]!=' ' && player.getBackpack().isFull())) {
		
			mazeArr[player.getPosY()][player.getPosX()] = ' ';
			if(mazeArr[cursorY][cursorX]!=' ') {
				AudioInputStream scoreAudio = AudioSystem.getAudioInputStream(new File("scoremusic.wav"));
				Clip scoreClip = AudioSystem.getClip();
				scoreClip.open(scoreAudio);
				scoreClip.start();
			    player.addBackpack(mazeArr[cursorY][cursorX]);
			    
			    if(mazeArr[cursorY][cursorX] == '*' || mazeArr[cursorY][cursorX] == '=') {
			    	for (int i = -1; i < 2; i++) {
						for (int j = -1; j < 2; j++) {
							if(Test.mazeArr[cursorY + i][cursorX + j] == '4' || Test.mazeArr[cursorY + i][cursorX + j] == '5') 
								Test.treasures[Test.indexTreasureFinder(cursorX + j, cursorY + i)].setMoveable(true);
							else if(Test.mazeArr[cursorY + i][cursorX + j] == 'C')
								Test.computers[Test.indexComputerFinder(cursorX + j, cursorY + i)].setMoveable(true);
						}
					}
			    	devices = removeItemFromArray(devices, indexDeviceFinder(cursorX, cursorY));
			    }
			    else if(mazeArr[cursorY][cursorX] != 'C') 
			    	treasures = removeItemFromArray(treasures, indexTreasureFinder(cursorX, cursorY));
			}
			mazeArr[cursorY][cursorX] = 'P';
			player.setPosX(cursorX);
			player.setPosY(cursorY);
		}
	}
	
	
	static void displayQueue(Queue input) {
		cn.getTextWindow().setCursorPosition(57, 0);
		System.out.println("Input\n");
		arrangeColorForItems((Character)input.peek());
		cn.getTextWindow().setCursorPosition(57, 1);
		System.out.println("<<<<<<<<<<<<<<<");
		cn.getTextWindow().setCursorPosition(57, 2);
		for (int i = 0; i < input.size(); i++) {
			arrangeColorForItems((Character)input.peek());
			System.out.print(input.peek());
			input.enqueue(input.dequeue());
		}
		arrangeColorForItems((Character)input.peek());
		cn.getTextWindow().setCursorPosition(57, 3);
		System.out.println("<<<<<<<<<<<<<<<");
		
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
	}
	static void generatingItems(Queue input) throws InterruptedException {
		spawningMap((Character)input.dequeue());
		input.enqueue(itemGenerator());
	}
	static String stringLife(int life) {
		String result = "";
		for (int i = 0; i < life; i++) {
			result += " ♥";
		}
		return result;
	}
	static void displayScreen() {
		cn.getTextWindow().setCursorPosition(0, 0);
		for (int i = 0; i < mazeArr.length; i++) {
			for (int j = 0; j < mazeArr[i].length; j++) {

				arrangeColorForItems(mazeArr[i][j]);
				System.out.print(mazeArr[i][j]);
			}
			System.out.println();
		}

		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		cn.getTextWindow().setCursorPosition(57, 16);
		System.out.print("Energy time: "+ player.getEnergyTime() + "  ");
		cn.getTextWindow().setCursorPosition(57, 17);
		System.out.print("P.Score  :"+ player.getScore() + "  ");
		cn.getTextWindow().setCursorPosition(57, 18);
		System.out.print("P.Life  :");
		color = new TextAttributes(Color.RED);
		cn.setTextAttributes(color);
		System.out.print(stringLife(player.getLife()) + "                   ");
		color = new TextAttributes(white, Color.BLACK);
		cn.setTextAttributes(color);
		cn.getTextWindow().setCursorPosition(57, 20);
		System.out.print("C.Score  :"+ ComputerTest.score + "  ");
	}
	 static void arrangeColorForItems(char c) {
			switch (c) {
			case '1': {
				color = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}
			case '2': {
				color = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}case '3': {
				color = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}case '4': {
				color = new TextAttributes(Color.BLUE,mazeBackgroundColor);
				break;
			}case '5': {
				color = new TextAttributes(Color.MAGENTA,mazeBackgroundColor);
				break;
			}case '*': {
				color = new TextAttributes(Color.YELLOW,mazeBackgroundColor);
				break;
			}case '=': {
				color = new TextAttributes(Color.YELLOW,mazeBackgroundColor);
				break;
			}
			case '#':{
				color = new TextAttributes(new Color(80,80,80),new Color(80,80,80));
				break;
			}
			case 'C':{
				color = new TextAttributes(Color.RED,mazeBackgroundColor);
				break;
			}
			case 'P':{
				color = new TextAttributes(Color.GREEN,mazeBackgroundColor);
				break;
			}
			case ' ':{
				color = new TextAttributes(white,mazeBackgroundColor);
				break;
			}
				
			default:
			}
			cn.setTextAttributes(color);
		
	}

	static void consoleClear() {
		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.println(
				"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
		cn.getTextWindow().setCursorPosition(0, 0);
	}
	
	public static int randomInt(int min, int max) {
        if(min == max) {
            return min;
        }
        else {
            return (int) (Math.random()*(max-min)+min);
        }
    }
	public static char itemGenerator() { //Random generate treasure with their probability
        char treasure = ' ';
        int rnd = randomInt(1,41);
        if (rnd <= 12)
            treasure = '1';
        else if (rnd >= 13 && rnd <= 20)
            treasure = '2';
        else if (rnd >= 21 && rnd <= 26)
            treasure = '3';
        else if (rnd >= 27 && rnd <= 31)
            treasure = '4';
        else if (rnd >= 32 && rnd <= 35)
            treasure = '5';
        else if (rnd >= 36 && rnd <= 37)
            treasure = '=';
        else if (rnd == 38)
            treasure = '*';
        else 
            treasure = 'C';

        return treasure;
    }
	
	static void treasureMove() throws InterruptedException {
		for (int i = 0; i < treasures.length; i++) {
			if(treasures[i] != null && !treasures[i].isInBackpack())
			treasures[i].move();
		}
	}
	
	static void definingTreasures(int rX,int rY, char item) throws InterruptedException {
		Treasure newTreasure = new Treasure(rX,rY,item);
		treasures=addItemToArray(treasures,newTreasure);
	}
	static void definingDevices(int rX, int rY, char item) {
		Device newDevice = new Device(rX,rY,item);
		devices = addItemToArray(devices,newDevice);
	}
	static void definingComputers(int rX, int rY) {
		ComputerTest newDevice = new ComputerTest(rX,rY);
		computers = addItemToArray(computers,newDevice);
	}
	static void spawningMap(char item) throws InterruptedException{
		int rX = 0,rY=0;
		do{
			rY = (int) (Math.random()*23);
			rX = (int) (Math.random()*55);
		}
		while(mazeArr[rY][rX] !=' ');
		mazeArr[rY][rX] = item;
		if(item == '*' || item == '=') definingDevices(rX, rY, item);
		else if(item == 'C') definingComputers(rX, rY);
		else definingTreasures(rX, rY, item);
		
	}
	static void displayBackPack(Stack Backpack) {
		color = new TextAttributes(new Color(255, 215, 0), Color.BLACK);
		cn.setTextAttributes(color);
		
		cn.getTextWindow().setCursorPosition(59, 5);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 6);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 7);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 8);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 9);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 10);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 11);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 12);
		System.out.print("|   |");
		cn.getTextWindow().setCursorPosition(59, 13);
		System.out.print("+---+");
		cn.getTextWindow().setCursorPosition(59, 14);
		System.out.print("P.Backpack");
		
		Stack temp = new Stack(Backpack.size());
		int i = 12;
		while(!Backpack.isEmpty()) {
			temp.push(Backpack.pop());
		}
		
		while(!temp.isEmpty()) {
			arrangeColorForItems((Character)temp.peek());
			cn.getTextWindow().setCursorPosition(61, i);
			System.out.print(temp.peek());
			Backpack.push(temp.pop());
			i--;
		}
	}
	static Device[] addItemToArray (Device[] array, Device theElementToBeAdded) {

        Device[] tempArray = new Device[array.length+1];

        for (int i = 0; i < array.length; i++) {

            tempArray[i] = array[i];
        }

        tempArray[tempArray.length-1] = theElementToBeAdded;

        return tempArray;

        
    }
	
	
	static ComputerTest[] addItemToArray (ComputerTest[] array, ComputerTest theElementToBeAdded) {

		ComputerTest[] tempArray = new ComputerTest[array.length+1];

        for (int i = 0; i < array.length; i++) {

            tempArray[i] = array[i];
        }

        tempArray[tempArray.length-1] = theElementToBeAdded;

        return tempArray;

        
    }
	
	 static Treasure[] addItemToArray (Treasure[] array, Treasure theElementToBeAdded) {

	        Treasure[] tempArray = new Treasure[array.length+1];

	        for (int i = 0; i < array.length; i++) {

	            tempArray[i] = array[i];
	        }

	        tempArray[tempArray.length-1] = theElementToBeAdded;

	        return tempArray;

	    }
	 static ComputerTest[] removeItemFromArray (ComputerTest[] array, int indexOfTheElementToBeDeleted) {

		 ComputerTest[] tempArray = new ComputerTest[array.length-1];

	        for (int k = 0; k < array.length; k++) {

	            if (k < indexOfTheElementToBeDeleted) {
	                tempArray[k] = array[k];
	            }

	            else if (k > indexOfTheElementToBeDeleted) {
	                tempArray[k - 1] = array[k];
	            }
	        }

	        return tempArray;

	    }
	 static Device[] removeItemFromArray (Device[] array, int indexOfTheElementToBeDeleted) {

		 Device[] tempArray = new Device[array.length-1];

	        for (int k = 0; k < array.length; k++) {

	            if (k < indexOfTheElementToBeDeleted) {
	                tempArray[k] = array[k];
	            }

	            else if (k > indexOfTheElementToBeDeleted) {
	                tempArray[k - 1] = array[k];
	            }
	        }

	        return tempArray;

	    }
	 static Treasure[] removeItemFromArray (Treasure[] array, int indexOfTheElementToBeDeleted) {

	        Treasure[] tempArray = new Treasure[array.length-1];

	        for (int k = 0; k < array.length; k++) {

	            if (k < indexOfTheElementToBeDeleted) {
	                tempArray[k] = array[k];
	            }

	            else if (k > indexOfTheElementToBeDeleted) {
	                tempArray[k - 1] = array[k];
	            }
	        }

	        return tempArray;

	    }
	 static int indexDeviceFinder(int pX , int pY) {
		 for (int i = 0; i < devices.length; i++) {
			if(devices[i].getPosX() == pX && devices[i].getPosY() == pY) return i;
		}
		 return -1;
	 }
	 static int indexTreasureFinder(int pX , int pY) {
		 for (int i = 0; i < treasures.length; i++) {
			if(treasures[i].getPosX() == pX && treasures[i].getPosY() == pY) return i;
		}
		 return -1;
	 }
	 static int indexComputerFinder(int pX , int pY) {
		 for (int i = 0; i < computers.length; i++) {
			if(computers[i].getPosX() == pX && computers[i].getPosY() == pY) return i;
		}
		 return -1;
	 }
	 
	 static TextAttributes colorSelection(char ch) {
		 TextAttributes tmpColor = new TextAttributes(white,Color.BLACK);
		 switch (ch) {
			case '1': {
				tmpColor = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}
			case '2': {
				tmpColor = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}case '3': {
				tmpColor = new TextAttributes(Color.PINK,mazeBackgroundColor);
				break;
			}case '4': {
				tmpColor = new TextAttributes(Color.BLUE,mazeBackgroundColor);
				break;
			}case '5': {
				tmpColor = new TextAttributes(Color.MAGENTA,mazeBackgroundColor);
				break;
			}case '*': {
				tmpColor = new TextAttributes(Color.YELLOW,mazeBackgroundColor);
				break;
			}case '=': {
				tmpColor = new TextAttributes(Color.YELLOW,mazeBackgroundColor);
				break;
			}
			case '#':{
				tmpColor = new TextAttributes(new Color(80,80,80),new Color(80,80,80));
				break;
			}
			case 'C':{
				tmpColor = new TextAttributes(Color.RED,mazeBackgroundColor);
				break;
			}
			case 'P':{
				tmpColor = new TextAttributes(Color.GREEN,mazeBackgroundColor);
				break;
			}
			case ' ':{
				tmpColor = new TextAttributes(white,mazeBackgroundColor);
				break;
			}
				
			default:
			}
		 return tmpColor;
	 }
	 
	 static void computerEvents() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		 for (int i = 0; i < computers.length; i++) {
			computers[i].move(mazeArr);
		}
	 }
}
