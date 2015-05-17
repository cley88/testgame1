package helloslick;

import org.newdawn.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame{
	public static final String gamename = "MyGameName";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int xSize = 500;
	public static final int ySize = 600;

	public Main(String gamename) {
		super(gamename);
		this.addState(new Menu());
		this.addState(new Play());
		this.addState(new In_Game());
		// TODO Auto-generated constructor stub
	}


	

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
	this.getState(menu).init(container, this);
	this.enterState(menu);	
	}
	public static void main(String[] args) {
	AppGameContainer app;
	try {
	app = new AppGameContainer(new Main(gamename));
	app.setDisplayMode(xSize, ySize, false);
	app.setTargetFrameRate(60);
	app.start();
	}catch(SlickException e) {
	e.printStackTrace();	
	}
		
	}

}
