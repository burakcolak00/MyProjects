import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;

import enigma.console.Console;

public class TimerCounter extends Thread{
	static Console cn = Main.cn;
	
	public static int currentDifficulty = 1;
	
	public static int countDown = 20;
	
	public static boolean halfUsable;
	public static boolean doubleDipUsable;

	public String answer;
	
	public KeyListener klis;
	public int keypr;
	public int rkey;
	
	public int onesec;
	
	public TimerCounter(){
		answer = "";
	}
	
	public void run() {
		keypr = 0;
		klis=new KeyListener() {
		     public void keyTyped(KeyEvent e) {}
		     public void keyPressed(KeyEvent e) {
		        if(keypr==0) {
		           keypr=1;
		           rkey=e.getKeyCode();
		        }
		     }
		     public void keyReleased(KeyEvent e) {}
		};
		cn.getTextWindow().addKeyListener(klis);
		
		boolean flag = true;
		
		while(flag) {
	         
			while ( countDown >= 0) {
        		if(keypr==1) {    // if keyboard button pressed
    	            if(rkey==KeyEvent.VK_Z) answer = "z";
    	            else if(rkey==KeyEvent.VK_X) answer = "x";
    	            else if(rkey==KeyEvent.VK_A) answer = "a";
    	            else if(rkey==KeyEvent.VK_B) answer = "b";
    	            else if(rkey==KeyEvent.VK_C) answer = "c";
    	            
    	            
    	            else if(rkey==KeyEvent.VK_D) answer = "d";
    	            else if(rkey==KeyEvent.VK_E) answer = "e";
    	            
    	            if(rkey==KeyEvent.VK_SPACE) {
    	        	   	String str;         
    	        	   	str=cn.readLine();     // keyboardlistener running and readline input by using enter 
    	        	   	cn.getTextWindow().setCursorPosition(5, 20);
    	        	   	cn.getTextWindow().output(str);
    	           	}

    	           	keypr=0;    // last action  
    	           	flag = false;
    	   			break;
    	        }
        		//if (onesec > 0) onesec--;else
        		{
        			//onesec = 500000000;
        			int cursorX = cn.getTextWindow().getCursorX();
	    			int cursorY = cn.getTextWindow().getCursorY();
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY() - 6);
	    			System.out.print("----------------------------\n");
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY());
	    			System.out.print("|                          |\n");
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY());
	    			System.out.print("|                          |\n");
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY());
	    			System.out.print("|                          |\n");
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY());
	    			System.out.print("|                          |\n");
	    			cn.getTextWindow().setCursorPosition(47, cn.getTextWindow().getCursorY());
	    			System.out.print("----------------------------");
	    		
	    			cn.getTextWindow().setCursorPosition(cursorX, cursorY);
	    		
	    			cn.getTextWindow().setCursorPosition(49, cn.getTextWindow().getCursorY() - 5);
	    			
	    			switch (currentDifficulty) {
	    		    case 1: {
	    		        System.out.print("Money: $0");
	    		        break;
	    		    }
	    		    case 2: {
	    		        System.out.print("Money: $20,000");
	    		        break;
	    		    }
	    		    case 3: {
	    		        System.out.print("Money: $100,000");
	    		        break;
	    		    }
	    		    case 4: {
	    		        System.out.print("Money: $250,000");
	    		        break;
	    		    }
	    		    case 5: {
	    		        System.out.print("Money: $500,000");
	    		        break;
	    		    }
	    		    default:
	    		        throw new IllegalArgumentException("Unexpected value: " + currentDifficulty);
	    		    }
	    			
	    			cn.getTextWindow().setCursorPosition(49, cn.getTextWindow().getCursorY() + 1);
	    			
	    			System.out.print("Remaining time: ");
	    			
	    			if(countDown > 9) {
	    				System.out.print(countDown + "s");
	    			}
	    			else 
	    				System.out.print(countDown + " s");
	    		
	    			cn.getTextWindow().setCursorPosition(49, cn.getTextWindow().getCursorY() + 1);
	    			if (halfUsable) System.out.print("50%");
	    			else System.out.print("-");
	    		
	    			cn.getTextWindow().setCursorPosition(49, cn.getTextWindow().getCursorY() + 1);
	    			if (doubleDipUsable) System.out.print("Double Dip");
	    			else System.out.print("-");
	    			
	    			cn.getTextWindow().setCursorPosition(cursorX, cursorY);
	    			
	    			countDown--;
	    			try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
        		}
    			
    		}
    		if (countDown < 0) {
    			System.out.println("\n> Time is over! Game Over...");
        		System.out.println("\n> Press Enter to continue...");
        		Main.isTimeOver = true;
        		break;
    		}
		}
		
		
		}
    
  
}

