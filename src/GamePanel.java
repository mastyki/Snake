import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/ UNIT_SIZE;
    static final int DELAY = 100;
    final int x[] = new int[GAME_UNITS];
   public void startPosition(){

       y[0] = 300;
       for(int i = 1; i< 6; i++){
           y[i] = y[i-1] ;
       }
   }
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        startPosition();
        newApple();
        running  = true;
        timer  = new Timer(DELAY, this);
        timer.start();

    }
    public  void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }
    public void draw (Graphics graphics){
        if (running){
            for (int i = 0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                graphics.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE,SCREEN_HEIGHT);
                graphics.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
            }
            graphics.setColor(Color.red);
            graphics.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // draw Snake
            for (int i = 0; i< bodyParts;i++){
                if (i == 0){
                    graphics.setColor(Color.decode("#FF1493"));
                    graphics.fillRect(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    graphics.setColor(Color.decode("#FF69B4"));
                    graphics.fillRect(x[i],y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            // print Score
            graphics.setColor(Color.red);

            try {
                //create the font to use
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Datcub-eZO2g.ttf")).deriveFont(35f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(customFont);
                graphics.setFont(customFont);
                FontMetrics metrics = getFontMetrics(customFont);
                String message = "Score: ";
                graphics.drawString(message + applesEaten, (SCREEN_WIDTH-metrics.stringWidth(message + applesEaten))/2, customFont.getSize() );
            } catch (IOException e) {
                e.printStackTrace();
                graphics.setFont(new Font("Arial", Font.BOLD, 35));
                FontMetrics metrics = graphics.getFontMetrics();
                String message = "Score: ";
                graphics.drawString(message + applesEaten, (SCREEN_WIDTH-metrics.stringWidth(message + applesEaten))/2,35 );
            } catch(FontFormatException e) {
                e.printStackTrace();
            }
        }
        else {
            graphics.setColor(Color.red);

            try {
                //create the font to use
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Datcub-eZO2g.ttf")).deriveFont(35f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(customFont);
                graphics.setFont(customFont);
                FontMetrics metrics = getFontMetrics(customFont);
                String message = "Score: ";
                graphics.drawString(message + applesEaten, (SCREEN_WIDTH-metrics.stringWidth(message + applesEaten))/2, customFont.getSize() );
            } catch (IOException e) {
                e.printStackTrace();
                graphics.setFont(new Font("Arial", Font.BOLD, 35));
                FontMetrics metrics = graphics.getFontMetrics();
                String message = "Score: ";
                graphics.drawString(message + applesEaten, (SCREEN_WIDTH-metrics.stringWidth(message + applesEaten))/2,35 );
                e.printStackTrace();
            } catch(FontFormatException e) {
                e.printStackTrace();
            }
            try {

                //create the font to use
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Datcub-eZO2g.ttf")).deriveFont(75f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(customFont);
                graphics.setFont(customFont);
                FontMetrics metrics = getFontMetrics(customFont);
                String message = "Game Over :(";
                graphics.drawString(message, (SCREEN_WIDTH-metrics.stringWidth(message))/2, (SCREEN_HEIGHT)/2);
            } catch (IOException e) {
                e.printStackTrace();
                e.printStackTrace();
                graphics.setFont(new Font("Arial", Font.BOLD, 75));
                FontMetrics metrics = graphics.getFontMetrics();
                String message = "Game Over :(";
                graphics.drawString(message, (SCREEN_WIDTH-metrics.stringWidth(message))/2, (SCREEN_HEIGHT)/2 );


            } catch(FontFormatException e) {
                e.printStackTrace();
            }
        }
        /*
        graphics.setFont(new Font("Arial", Font.BOLD, 35));
                FontMetrics metrics = graphics.getFontMetrics();
                String message = "Score: ";
                graphics.drawString(message + applesEaten, (SCREEN_WIDTH-metrics.stringWidth(message + applesEaten))/2,35 );
         */

    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    public void move(){
        for (int i = bodyParts; i > 0;i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
            switch (direction){
                case 'U':
                    y[0] = y[0] - UNIT_SIZE;
                    break;
                case 'D':
                    y[0] = y[0] + UNIT_SIZE;
                    break;
                case 'R':
                    x[0] = x[0] + UNIT_SIZE;
                    break;
                case 'L':
                    x[0] = x[0] - UNIT_SIZE;
                    break;
            }

    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY)){
            applesEaten++;
            bodyParts++;
            newApple();
        }
    }
    public void checkCollision(){
        // checks if head collides with body
        for (int i  = bodyParts; i > 0;i--){
            if((x[0]==x[i]) && (y[0] == y[i])){
                running = false;
            }
        }

        // checs if head collides with borders

        if ((x[0] > SCREEN_WIDTH) || (x[0] < 0) || (y[0] > SCREEN_HEIGHT) || (y[0] < 0) ){
            running = false;
        }
    }
    public void gameOver(Graphics graphics){

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (running){
            move();
            checkApple();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction !='R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction !='L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction !='D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction !='U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
