package helloslick;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class Menu extends BasicGameState {
	
private Image Start;
private Image Controls;
private Image Quit;
private int cordx;
private int cordy;
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
/**public static void main(String[]args){
try {
AppGameContainer m = new AppGameContainer((Game) new Menu());
m.start();
m.setDisplayMode(300, 200, false);
}catch(SlickException e){
e.printStackTrace();	
}
}
**/

@Override
public void init(GameContainer container, StateBasedGame game)throws SlickException {
	Start = new Image("res/Play.png");
	Controls = new Image("res/Controls.png");
	Quit = new Image("res/Quit.png");
}

@Override
public void render(GameContainer container, StateBasedGame game, Graphics g)throws SlickException {
	g.drawString("Welcome to Break a Pong!", 250, 0);
	g.drawString("Cordinats x and y: "+cordx+" "+cordy, 0, 0);
	Start.draw(215,50);
	Controls.draw(215, 200);
	Quit.draw(215,390);
	
}

@Override
public void update(GameContainer container, StateBasedGame game, int delta)throws SlickException {
Input pos = container.getInput();
cordx = pos.getMouseX();
cordy = pos.getMouseY();
if(pos.isKeyDown(pos.KEY_ENTER)){
game.enterState(1);	
}
if(pos.isKeyDown(pos.KEY_Q)){
System.exit(0);	
}
	
}

@Override
public int getID() {
	// TODO Auto-generated method stub
	return 0;
}
	
}

