package blocks;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class BaseBlock extends JPanel{
	
	public static final int NOT_STATIC = 0;
	public static final int STATIC = 1;

	public enum Mode {
		DRAGGABLE, STATIC
	}
	
    private volatile int draggedAtX, draggedAtY;
	int nInstructions = 0;
	int largura, altura;

    public BaseBlock(int posX, int posY, int largura, int altura, Color color, Mode mode) {
		
    	this.altura = altura;
    	this.largura = largura;
    	
    	setBounds(posX, posY, this.largura, this.altura);
		setPreferredSize(new Dimension(this.largura, this.altura));
		setBackground(color); 
		
		if(mode == Mode.DRAGGABLE) {
	    	//Movimentaçao do bloco, permite que ele seja arrastado
	        addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
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
		}
        //Limites do painel no qual o componente esta
        /*
        int limit_x = this.getRootPane().getX();
        int limit_y = this.getRootPane().getY();*/
        
    }
    
	public void removeBlock(BaseBlock block){
		
	}

}
