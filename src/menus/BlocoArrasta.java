package projFinalPOO;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

public class BlocoArrasta extends JPanel{
	
    private volatile int draggedAtX, draggedAtY;


    public BlocoArrasta() {
    	//Movimentaçao do bloco, permite que ele seja arrastado
        addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                setLocation(e.getX() - draggedAtX + getLocation().x,
                        e.getY() - draggedAtY + getLocation().y);
                repaint();
            }
        });
        //Limites do painel no qual o componente esta
        /*
        int limit_x = this.getRootPane().getX();
        int limit_y = this.getRootPane().getY();*/
    }  
}
