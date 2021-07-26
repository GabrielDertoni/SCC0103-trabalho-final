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
	int nInstructions = 0;
	int listPos;
	int posX;
	int posY;
	int width;
	int height;
	BaseBlock father;
	BlockEditor editor;
	Mode mode;

	protected List<BaseBlock> blocks;

    public BaseBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int largura, int altura, Mode mode, int listPos, String imgName) {
		super(imgName);
		
		blocks = new ArrayList<BaseBlock>();

		this.father = father;
		this.editor = editor;
		this.posX = posX;
		this.posY = posY;
		this.height = altura;
    	this.width = largura;
		this.listPos = listPos;
    	
		setLayout(null);
		setBounds(posX, posY, width, height);
		setSize(new Dimension(width, height));
		
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
		blocks.add(block);
		
		if(mode == Mode.STATIC){
			add(block);
		}else{
			editor.updateIndex(block.listPos, 1, 80, Method.ADD);
			editor.add(block);
			updateDimension(40, 80, Method.ADD);
		}
		repaint();
		updateUI();
	}
    
	public int removeBlock(BaseBlock block) {
		int update = -(block.excludeChildren() + 1);
		
		blocks.remove(block);
		nInstructions--;
		
		if(mode == Mode.STATIC){
			remove(block);
			if(nInstructions > 0) updateIndex(block.listPos, update, -block.height, Method.REMOVE);
			for(int i = 0; i < nInstructions; i++) System.out.println(blocks.get(i).listPos);
		}else{
			editor.remove(block);
			editor.updateIndex(block.listPos, update, -block.height, Method.REMOVE);
			updateDimension(-(block.width - 450), -block.height, Method.REMOVE);
			for(int i = 0; i < nInstructions; i++) System.out.println(blocks.get(i).listPos);
		}

		repaint();
		updateUI();
		return update;
	}

	private int excludeChildren(){
		int update = 0;

		for(int i = 0; i < nInstructions; i++){
			if(blocks.get(i).nInstructions > 0){
				update += blocks.get(i).excludeChildren();
				blocks.get(i).nInstructions = 0;
			}
			editor.remove(blocks.get(i));
			update++;
		}

		return update;
	}

	public void updateIndex(int listPos, int update, int heigth, Method mode){
		int i;

		if(nInstructions <= 1) return;
		
		for(i = 0; i < nInstructions && blocks.get(i).listPos < listPos; i++);

		if(mode == Method.ADD){
			if(i == nInstructions) return;
			if((blocks.get(i).listPos > listPos) && (blocks.get(i - 1).listPos < listPos - 1)) blocks.get(i - 1).updateIndex(listPos, update, heigth, mode);
		}else if(mode == Method.REMOVE){
			if(i - 1 >= 0 && blocks.get(i - 1).nInstructions > 0) blocks.get(i - 1).updateIndex(listPos, update, heigth, mode);
		}

		System.out.println("pos = " + i);
		for(; i < nInstructions; i++){
			if(blocks.get(i).nInstructions > 0) blocks.get(i).updateIndex(listPos, update, heigth, mode);
			blocks.get(i).listPos += update;
			blocks.get(i).posY += heigth;
			blocks.get(i).setLocation(blocks.get(i).posX, blocks.get(i).posY);
		}
		updateUI();
	}

	private void updateDimension(int width, int height, Method flag) {
		this.width += width;
		this.height += height;
		
		if(mode != Mode.STATIC){
			father.updateDimension(width, height, flag);
			updateUI();
		}
	}			

	public abstract Stmt toStmt();
}
