import java.util.*; 
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

public class AntPanel extends JPanel{

  final int WINDOW_WIDTH = 1200; 
  final int WINDOW_HEIGHT = 600; 
  final int PIXEL_SIZE = 10;

  int changeX = 1; 
  int changeY = 0;
  int dir = 1; // 1 - up, 2 - right, 3 - down, 4 - left (direction) 
   
  int antX = WINDOW_WIDTH/PIXEL_SIZE/2;
  int antY = WINDOW_HEIGHT/PIXEL_SIZE/2;

  int width = WINDOW_WIDTH/PIXEL_SIZE; 
  int height = WINDOW_HEIGHT/PIXEL_SIZE; 

  boolean[][] board = new boolean[width][height];

  public AntPanel(){ 
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setBackground(Color.WHITE); 
    setLayout(null);
  }

  public void moveClockwise(){
    if(dir == 1){
      // up -> right 
      dir = 2; 
      changeX = 0;
      changeY = -1; 
    }
    else if(dir == 2){
      // right -> down 
      dir = 3; 
      changeX = 1;
      changeY = 0;
    }
    else if(dir == 3){
      // down -> left
      dir = 4; 
      changeX = 0; 
      changeY = 1; 
    }
    else if(dir == 4){
      // left -> up 
      dir = 1;
      changeX = -1;
      changeY = 0; 
    }
  }
  
  public void moveCounterClockwise(){
    if(dir == 1){
      // up -> left 
      dir = 4;
      changeX = 0;
      changeY = 1; 
    }
    else if(dir == 2){
      // right -> up 
      dir = 1; 
      changeX = -1;
      changeY = 0; 
    }
    else if(dir == 3){
      // down -> right
      dir = 2; 
      changeX = 0; 
      changeY = -1; 
    }
    else if(dir == 4){
      // left -> down
      dir = 3;
      changeX = 1; 
      changeY = 0; 
    }
  }

  public int euclideanMod(int a, int b)
  {
    return (a%b+b)%b; 
  }

  public void paintComponent(Graphics graphics){
    super.paintComponent(graphics);
    grid(graphics); 
    update(graphics);
    moveAnt();
    repaint(); 
  }

  public void grid(Graphics graphics){
    graphics.setColor(Color.BLACK);
    for(int i=0; i<board.length; i++){
      graphics.drawLine(0, i*PIXEL_SIZE, WINDOW_WIDTH, i*PIXEL_SIZE);
      graphics.drawLine(i*PIXEL_SIZE, 0, i*PIXEL_SIZE, WINDOW_HEIGHT);
    }
  }

  public void update(Graphics graphics){
    for(int x=0; x<board.length; x++){
      for(int y=0; y<board[x].length; y++){
        if(board[x][y]){
          graphics.setColor(Color.BLACK);
          graphics.fillRect(x*PIXEL_SIZE, y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE); 
        }
        else{
          graphics.setColor(Color.WHITE);
          graphics.fillRect(x*PIXEL_SIZE, y*PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        }
      }
    }
    grid(graphics);
  }

  public void moveAnt(){
    if(board[antX][antY]){
      int save = dir; 
      moveCounterClockwise();
      // if(antX+changeX>=width || antX+changeX<0 || antY+changeY>=height || antY+changeY<0){
      //   dir = save; 
      // }
      // else{
        board[antX][antY] = false; 
        antX += changeX; 
        antX = euclideanMod(antX, WINDOW_WIDTH);
        antY += changeY; 
        antY = euclideanMod(antY, WINDOW_HEIGHT);
      //}
    }
    else{
      int save = dir;  
      int savecx = changeX; 
      int savecy = changeY; 
      moveClockwise();
      // if(antX+changeX>=width || antX+changeX<0 || antY+changeY>=height || antY+changeY<0){
      //   dir = save; 
      //   changeX = savecx;
      //   changeY = savecy; 
      // }
      // else{
        board[antX][antY] = true; 
        antX += changeX; 
        antX = euclideanMod(antX, WINDOW_WIDTH);
        antY += changeY; 
        antY = euclideanMod(antY, WINDOW_HEIGHT);
      //}
    }
  }
}