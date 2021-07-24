package blocks;


import interpreter.Stmt;
import menus.Background;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseBlock extends Background {
	public enum Method {
		ADD, REMOVE
	}
	
	public enum Mode {
		DRAGGABLE, DRAGGABLE_Y, STATIC
	}

    private volatile int draggedAtX, draggedAtY;
	int nInstructions = 0, index;
	int posX, posY, width, height;
	BaseBlock father = null;
	Mode mode;

	protected List<BaseBlock> blocks;

    public BaseBlock(int posX, int posY, int largura, int altura, Color color, Mode mode, int index, String imgName) {
		super(imgName);
		
		blocks = new ArrayList<BaseBlock>();

		this.posX = posX;
		this.posY = posY;
		this.height = altura;
    	this.width = largura;
		this.index = index;
    	
		setLayout(null);
		setBounds(posX, posY, width, height);
		setSize(new Dimension(width, height));
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

	public void addBlock(BaseBlock block) {
		nInstructions++;
		width += 40;
		height += 80;

		if(mode != Mode.STATIC) setSize(new Dimension(this.width, this.height));
		if(father != null) father.updateDimension(this.index, Method.ADD);
		
		add(block);
		blocks.add(block);
		updateUI();
	}
    
	public void removeBlock(BaseBlock block) {
		nInstructions--;
		width -= 40;
		height -= 80;

		if(mode != Mode.STATIC) setSize(new Dimension(width, height));
		if(father != null) father.updateDimension(index, Method.REMOVE);

		blocks.remove(block);
		remove(block);
		updateUI();
	}

	private void updateDimension(int index, Method flag) {

		if(flag == Method.ADD) {
			this.width += 40;
			this.height += 80;
		} else if(flag == Method.REMOVE) {
			this.width -= 40;
			this.height -= 80;
		}
		
		if(mode != Mode.STATIC) setSize(new Dimension(this.width, this.height));
		
		for(int i = index + 1; i < nInstructions; i++){
			blocks.get(i).setLocation(blocks.get(i).posX, blocks.get(i).posY + height);;
		}

		if(father != null) father.updateDimension(this.index, flag);
	}

	public abstract Stmt toStmt();
}
