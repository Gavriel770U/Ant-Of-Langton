import java.util.*;
import javax.swing.*; 
public class AntFrame extends JFrame{
  final int WINDOW_WIDTH = 1200; 
  final int WINDOW_HEIGHT = 600; 
  public AntFrame(){
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);
    add(new AntPanel()); 
    setVisible(true);
  }
}