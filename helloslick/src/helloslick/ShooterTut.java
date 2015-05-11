package helloslick;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
 
public class ShooterTut extends BasicGame{
 
    // Background image
    Image background = null;
    
    // Foreground images
    Image hero_plane = null;
    Image enemy_plane = null;
    Image hero_bullet = null;
    Image enemy_bullet = null;
    
    // Bounding boxes for collision detection
    Rectangle hero_bound = null;
    Rectangle enemy_bound = null;
    Rectangle hero_bullet_bound = null;
    Rectangle enemy_bullet_bound = null;
    
    // Set up the move speeds
    float hero_move_velovity = 0.3f;
    float enemy_move_velovity = 0.4f;
    float bullet_move_velovity = 0.55f;
    
    boolean paused = false;
    boolean hero_bullet_fired = false;
    boolean enemy_bullet_fired = false;
    boolean hero_exploding = false;
    boolean enemy_exploding = false;

    Sound fx = null;

    Image paused_text = null;
    Image gameover_text = null;
    Image youwon_text = null;
    
    private Animation explosion;


    public ShooterTut()
    {
        super("Basic Shooter Tutorial");
    }
 
    @Override
    public void init(GameContainer gc) 
            throws SlickException {
        // Load the images
        background = new Image("data/land.jpg");
        hero_plane = new Image("data/heroplane.png");
        enemy_plane = new Image("data/enemyplane.png");
        hero_bullet = new Image("data/bullet.png");
        enemy_bullet = new Image("data/bullet.png");
        paused_text = new Image("data/pause_text.png");
        gameover_text = new Image("data/gameover_text.png");
        youwon_text = new Image("data/youwon_text.png");
        
        // Construct the bounding boxes
        hero_bound = new Rectangle(450, 500, hero_plane.getWidth(), hero_plane.getHeight());
        enemy_bound = new Rectangle(350, 75, enemy_plane.getWidth(), enemy_plane.getHeight());
        hero_bullet_bound = new Rectangle(1, 1, hero_bullet.getWidth(), hero_bullet.getHeight());
        enemy_bullet_bound = new Rectangle(1, 1, enemy_bullet.getWidth(), enemy_bullet.getHeight());        

        // Load the shooting sound
        fx = new Sound("data/shot.wav");
        
        // Load the explosion frames/images
        SpriteSheet sheet = new SpriteSheet("data/explosion.png",32,32);
        explosion = new Animation();
        explosion.setAutoUpdate(true);
        for (int frame=0;frame<3;frame++) {
            explosion.addFrame(sheet.getSprite(frame,0), 150);
        }
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
            throws SlickException     
    {
        Input input = gc.getInput();
        
        // keeps the scrolling smooth regardless of the FPS affecting the update rate
        float hip = hero_move_velovity * delta;

        // Escape key quits the game
        if(input.isKeyDown(Input.KEY_ESCAPE)) gc.exit();

        // P key will pause/unpause the game but still allow Escape key to quit
        if(input.isKeyDown(Input.KEY_P)) {
            if(paused == false) paused = true;
            else paused = false;
        }
        
        // Jump over the other key presses
        if(paused == true) return;
        
        if(input.isKeyDown(Input.KEY_LEFT)) {
            hero_bound.setX(hero_bound.getX() - hip);
        }
 
        if(input.isKeyDown(Input.KEY_RIGHT)) {
            hero_bound.setX(hero_bound.getX() + hip);
        }
 
        if(input.isKeyDown(Input.KEY_UP)) {
            hero_bound.setY(hero_bound.getY() - hip);
        }
 
        if(input.isKeyDown(Input.KEY_DOWN)) {
            hero_bound.setY(hero_bound.getY() + hip);
        }

        if(input.isKeyDown(Input.KEY_SPACE) && (hero_bullet_fired == false)) {
            fx.play();
            hero_bullet_fired = true;
            hero_bullet_bound.setX(hero_bound.getX());
            hero_bullet_bound.setY(hero_bound.getY());
        }
        
        // Some simple AI for the enemy to shoot at the hero
        if( ((hero_bound.getX() > (enemy_bound.getX() - 45)) && 
                (hero_bound.getX() < (enemy_bound.getX() + 55))) && 
                (enemy_bullet_fired == false)) {
            fx.play();
            enemy_bullet_fired = true;
            enemy_bullet_bound.setX(enemy_bound.getX());
            enemy_bullet_bound.setY(enemy_bound.getY());
        }

        if(hero_bullet_fired == true) {
            // Move the bullet up
            float bhip = bullet_move_velovity * delta;
            hero_bullet_bound.setY(hero_bullet_bound.getY() - bhip);      
            
            // Check if the bullet went off the screen 
            if(hero_bullet_bound.getY() < 0) {
                hero_bullet_bound.setX(1);
                hero_bullet_bound.setY(1);
                hero_bullet_fired = false;
            }
            
            // Check if the hero bullet hit something 
            if(hero_bullet_bound.intersects(enemy_bound)) {         
                hero_bullet_bound.setX(1);
                hero_bullet_bound.setY(1);
                hero_bullet_fired = false;
                enemy_exploding = true;
            }
        }

        if(enemy_bullet_fired == true) {
            // Move the bullet down
            float bhip = bullet_move_velovity * delta;
            enemy_bullet_bound.setY(enemy_bullet_bound.getY() + bhip);      
            
            // Check if the bullet went off the screen 
            if(enemy_bullet_bound.getY() > 600) {
                enemy_bullet_bound.setX(1);
                enemy_bullet_bound.setY(1);
                enemy_bullet_fired = false;
            }
            
            // Check if the enemy bullet hit something 
            if(enemy_bullet_bound.intersects(hero_bound)) {    
                enemy_bullet_bound.setX(1);
                enemy_bullet_bound.setY(1);
                enemy_bullet_fired = false;
                hero_exploding = true;
            }
        }
    }
 
    public void render(GameContainer gc, Graphics g) 
            throws SlickException 
    {
        background.draw(0, 0);
        
        hero_bullet.draw(hero_bullet_bound.getX(), hero_bullet_bound.getY());
        enemy_bullet.draw(enemy_bullet_bound.getX(), enemy_bullet_bound.getY());

        // If the hero was hit it should be exploding 
        if(hero_exploding == true) {
            explosion.draw(hero_bound.getX(), hero_bound.getY());
            gameover_text.draw(300, 300);
        }
        else { // Otherwise draw it
            hero_plane.draw(hero_bound.getX(), hero_bound.getY());           
        }

        // If the enemy was hit it should be exploding 
        if(enemy_exploding == true) {
            explosion.draw(enemy_bound.getX(), enemy_bound.getY());
            youwon_text.draw(300, 300);
        }
        else { // Otherwise draw it
            enemy_plane.draw(enemy_bound.getX(), enemy_bound.getY());            
        }

        // Tell the player the game is paused
        if(paused == true) {
            paused_text.draw(300, 300);
        }
        
    }
 
    public static void main(String[] args) 
            throws SlickException
    {
         AppGameContainer app = 
            new AppGameContainer(new ShooterTut());
 
         app.setDisplayMode(800, 600, false);
         app.start();
    }
}
