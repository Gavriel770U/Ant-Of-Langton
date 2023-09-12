public enum AntMovement{
  up(0,-1), down(0,1), right(1,0), left(-1,0);
  int x, y; 
  AntMovement(int x, int y){
    this.x = x; 
    this.y = y; 
  } 
  public int getX(){return this.x;}
  public int getY(){return this.y;} 
}
