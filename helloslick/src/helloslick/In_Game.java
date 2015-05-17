package helloslick;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class In_Game extends BasicGameState {
	private Image Resume;
	private Image Settings;
	private Image Quit;

	@Override
	public void init(GameContainer container, StateBasedGame game)throws SlickException {
		Resume = new Image("res/resume3.png");
		Settings = new Image("res/settings2.png");
		Quit = new Image("res/quit3.png");
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
	g.drawString("You have paused the game!", 50,0);
	g.drawString("Please select a option!",50,10);
	g.drawImage(Resume, 50, 90);
	g.drawImage(Settings, 50, 190);
	g.drawImage(Quit, 50, 320);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
	Input put = container.getInput();	
	if(put.isKeyDown(put.KEY_R)){
	game.enterState(1);		
	}
	if(put.isKeyDown(put.KEY_B)){
	game.enterState(0);	
	}
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

	



}
