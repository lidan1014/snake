import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;
//import java.util.random.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 450;
	static final int SCREEN_HEIGHT = 450;
	static final int UNIT_SIZE = 20;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY =500;
	final int x[]= new int[GAME_UNITS];
	final int y[]= new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			g.drawLine(i*UNIT_SIZE, i, i*UNIT_SIZE, SCREEN_HEIGHT);
		}
		
		
		
	
		g.setColor(Color.RED);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		for(int i = 0; i< bodyParts;i++) {
			if(i == 0 ) {
				g.setColor(Color.green);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				
			}
			else {
				g.setColor(Color.green);
			}
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i]= x[i-1];
			y[i]= y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'R':
			y[0] = y[0] + UNIT_SIZE;
			break;
			
		}
	}
	public void checkApple(){
		
	}
	public void checkcollisions(){
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0]== y[i])) {
				running = false;
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false; 
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false; 
		}
		//check if head touches top border
		if(y[0]  < 0) {
			running = false; 
		}
		//check if head touches bottom border 
		if(y[0]  > SCREEN_HEIGHT) {
			running = false;
		}
			
	}
	public void gameOver(Graphics g){
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (running ) {
			move();
			checkApple();
			checkcollisions();
			
			
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT: 
				if(direction != 'R') {
					direction = 'L';
					
				}
				break;
			case KeyEvent.VK_RIGHT: 
				if(direction != 'L') {
					direction = 'R';
					
				}
				break;
			case KeyEvent.VK_UP: 
				if(direction != 'D') {
					direction = 'U';
					
				}
				break;
			case KeyEvent.VK_DOWN: 
				if(direction != 'U') {
					direction = 'D';
					
				}
				break;
			}
		}
	}

}


