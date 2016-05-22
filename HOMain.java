import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

public class HOMain extends JFrame
  implements MouseListener, MouseMotionListener, KeyListener
{
  HOPP p;
  int width = 1280; int height = 750;

  public static void main(String[] args) {
    new HOMain();
  }

  public HOMain() {
    setDefaultCloseOperation(3);
    setSize(this.width, this.height);
    setLocationRelativeTo(null);
    setTitle("Homework Organizer");
    setFocusTraversalKeysEnabled(false);

    this.p = new HOPP(this.width, this.height);
    add(this.p);

    setResizable(false);
    setVisible(true);

    Action updateCursorAction = new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        HOMain.this.p.forever();
        HOMain.this.p.repaint();
      }
    };
    addMouseMotionListener(this);
    addMouseListener(this);
    addKeyListener(this);
    this.p.addKeyListener(this);

    new Timer(20, updateCursorAction).start();
  }

  public void mouseClicked(MouseEvent me)
  {
  }

  public void mouseEntered(MouseEvent me)
  {
  }

  public void mouseExited(MouseEvent me)
  {
  }

  public void mousePressed(MouseEvent me) {
    this.p.mouseDown(me.getX(), me.getY());
  }

  public void mouseReleased(MouseEvent me) {
    this.p.mouseUp(me.getX(), me.getY());
  }

  public void mouseDragged(MouseEvent me)
  {
  }

  public void mouseMoved(MouseEvent me) {
    this.p.mouseMoved(me.getX(), me.getY());
  }

  public void keyPressed(KeyEvent ke) {
    this.p.keyDown(ke.getKeyCode());
  }

  public void keyReleased(KeyEvent ke) {
    this.p.keyUp(ke.getKeyCode());
  }

  public void keyTyped(KeyEvent ke)
  {
  }
}