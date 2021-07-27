package blocks;

import menus.Background;

import interpreter.Stmt;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public abstract class BaseBlock extends Background {
	
	public enum Method {
		ADD, REMOVE
	}
	
	public enum Mode {
		STATEMENT, EDITOR
	}

	int nInstructions = 0;
	int listPos;
	int depth;
	int posX;
	int posY;
	int width;
	int height;
	BaseBlock father;
	BlockEditor editor;
	Mode mode;

	protected List<BaseBlock> blocks;

    public BaseBlock(BaseBlock father, BlockEditor editor, int posX, int posY, int largura, int altura, Mode mode, int listPos, int depth, String imgName) {
		super(imgName);
		
		blocks = new ArrayList<BaseBlock>();

		this.father = father;
		this.editor = editor;
		this.posX = posX;
		this.posY = posY;
		this.height = altura;
    	this.width = largura;
		this.listPos = listPos;
		this.depth = depth;
		this.mode = mode;
    	
		setLayout(null);
		setBounds(posX, posY, width, height);
		setSize(new Dimension(width, height));
    }

	public void addBlock(BaseBlock block) {
		blocks.add(block);
		
		if(mode == Mode.EDITOR){
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
		blocks.remove(block);
		nInstructions--;
		
		int update = -(block.excludeChildren() + 1);
		
		if(mode == Mode.EDITOR){
			remove(block);
			if(nInstructions > 0) updateIndex(block.listPos, update, -block.height, Method.REMOVE);
		}else{
			editor.remove(block);
			editor.updateIndex(block.listPos, update, -block.height, Method.REMOVE);
			updateDimension(-(block.width - 450), -block.height, Method.REMOVE);
		}

		repaint();
		updateUI();
		return update;
	}

	public int calculateUpgrade(){
		int upgrade = 0;
		
		for(int i = 0; i < nInstructions; i++){
			if(blocks.get(i).nInstructions > 0) upgrade += blocks.get(i).calculateUpgrade();
			upgrade++;
		}

		return upgrade;
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

		for(i = 0; i < nInstructions && blocks.get(i).listPos < listPos; i++);

		if(mode == Method.ADD){
			if(i == nInstructions || nInstructions < 1) return;
			if(((blocks.get(i).listPos > listPos) && (blocks.get(i - 1).listPos < listPos - 1))) blocks.get(i - 1).updateIndex(listPos, update, heigth, mode);
		}else if(mode == Method.REMOVE){
			if(nInstructions <= 0) return;
			if(i - 1 >= 0 && blocks.get(i - 1).nInstructions > 0) blocks.get(i - 1).updateIndex(listPos, update, heigth, mode);
		}

		for(; i < nInstructions; i++){
			if(blocks.get(i).nInstructions > 0) blocks.get(i).updateIndex(listPos, update, heigth, mode);
			blocks.get(i).listPos += update;
			blocks.get(i).posY = (blocks.get(i).listPos * 80) + 5;
			blocks.get(i).setLocation(blocks.get(i).posX, blocks.get(i).posY);
		}
		updateUI();
	}

	private void updateDimension(int width, int height, Method flag) {
		this.width += width;
		this.height += height;
		
		if(mode != Mode.EDITOR){
			father.updateDimension(width, height, flag);
			updateUI();
		}
	}			

	public abstract Stmt toStmt();
}
