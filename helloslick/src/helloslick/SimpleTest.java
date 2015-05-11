package helloslick;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class SimpleTest extends BasicGame {
	private int xp,yp;
	private boolean up,left,right;
	private boolean down = true;
	private int w,h;
	private int by;
	private int bx = 50;
    public SimpleTest() {
        super("SimpleTest");
    }
    
    public void keypressed(){
   	
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {}

    @Override
    public void update(GameContainer container, int delta)throws SlickException 
    {
    	
    Input input = container.getInput();
    w = input.getMouseX();
    h = input.getMouseY();
    if(input.isKeyDown(Input.KEY_DOWN)){
    yp += 1;
    if(yp >= 380){
    yp -=1;	
    }
    }
    if(input.isKeyDown(Input.KEY_UP)){
    yp -=1;	
    if(yp <= 0) {
    yp +=1;	
    }
    }
    if(is_down()){
    by +=1;    
    if(is_up()){
    by -= 1;	
    }
    }
    }
    
    public boolean is_up(){
    if(by > 478){
    return true;	
    }
    else {
    return false;	
    }
    }
    
    
    public boolean is_down(){
    if(by< 478){
    return true;	
    }
    else {
    return false;	
    }
    }
    
    	
    

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	g.fillRect(0, yp, 20, 100);
    	g.fillOval(bx, by, 20, 30);
        g.drawString("Hello, Slick world!", 0, 100);
        g.drawString("Position x " +w+ " y "+ h , 50, 40);
        
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SimpleTest());
            app.start();
            app.setDisplayMode(500, 300, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}