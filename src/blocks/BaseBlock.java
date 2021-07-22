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
	public enum Method {
		ADD, REMOVE
	}
	
	public enum Mode {
		DRAGGABLE, DRAGGABLE_Y, STATIC
	}

    private volatile int draggedAtX, draggedAtY;
	int nInstructions = 0;
	int posX, posY, width, height;
	BaseBlock father;
	Mode mode;

	protected List<BaseBlock> blocks;

    public BaseBlock(int posX, int posY, int largura, int altura, Color color, Mode mode) {
		blocks = new ArrayList<BaseBlock>();

		this.posX = posX;
		this.posY = posY;
		this.height = altura;
    	this.width = largura;
    	
    	setBounds(posX, posY, width, height);
		setPreferredSize(new Dimension(width, height));
		setLocation(posX, posY);
		setBackground(color); 
		
		this.mode = mode;
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
		}else if(mode == Mode.DRAGGABLE_Y) {
	    	//Movimentaçao do bloco, permite que ele seja arrastado apenas pelo eixo Y
	        addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					draggedAtY = e.getY();
				}
	        });
	
	        addMouseMotionListener(new MouseMotionAdapter(){
	            public void mouseDragged(MouseEvent e){
	                setLocation(getLocation().x,
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

	public void addBlock(BaseBlock block, int newHeight) {
		nInstructions++;

		setPreferredSize(new Dimension(width, height));
		block.setLocation(posX, posY);
		if(father != null && father.mode != Mode.STATIC) father.updateHeight(newHeight, Method.ADD);

		blocks.add(block);
		add(block);
		updateUI();
	}
    
	public void removeBlock(BaseBlock block) {
		nInstructions--;
		height -= block.height;

		setPreferredSize(new Dimension(width, height));
		setLocation(posX, posY);
		if(father != null && father.mode != Mode.STATIC) father.updateHeight(block.height, Method.REMOVE);

		blocks.remove(block);
		remove(block);
		updateUI();
	}

	private void updateHeight(int newHeight, Method flag) {
		if(flag == Method.ADD)	height += newHeight;
		if(flag == Method.REMOVE)	height -= newHeight;
		
		setPreferredSize(new Dimension(width, height));

		if(father != null && father.mode != Mode.STATIC) father.updateHeight(newHeight, flag);
	}

	public abstract Stmt toStmt();
}
