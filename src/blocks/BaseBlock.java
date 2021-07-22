package blocks;


import interpreter.Stmt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public abstract class BaseBlock extends JPanel{
	public enum Mode {
		DRAGGABLE, STATIC
	}

    private volatile int draggedAtX, draggedAtY;
	int nInstructions = 0;
	int largura, altura;

	protected List<BaseBlock> blocks;

    public BaseBlock(int posX, int posY, int largura, int altura, Color color, Mode mode) {
		blocks = new ArrayList<BaseBlock>();

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

	public void addBlock(BaseBlock block) {
		blocks.add(block);
		add(block);
	}
    
	public void removeBlock(BaseBlock block){
		blocks.remove(block);
		remove(block);
	}

	public abstract Stmt toStmt();
}
