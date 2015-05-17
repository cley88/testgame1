package helloslick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
	g.drawString("Press P to Pause the game!", 250, 0);
	g.drawString("And select Options!",250, 50);	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
	Input in = container.getInput();
	if(in.isKeyDown(in.KEY_P)){
	game.enterState(2);	
	}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
