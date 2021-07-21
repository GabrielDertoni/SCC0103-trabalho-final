package blocks;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class INTERACTBlock {
	
	JPanel father;
	BlocoArrasta block;
	
	public INTERACTBlock(JPanel father) {
		
		this.father = father;
		
		block = new BlocoArrasta(700, 5, 200, 30, Color.RED, BlocoArrasta.NOT_STATIC);
		
        block.add(new JLabel("Interagir"));
		
		JButton removeButton = new JButton("Remover");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				father.remove(block);
				father.repaint();
			}
		});
		
		block.add(removeButton);
	}
	
	public BlocoArrasta getBlock() {
		return block;
	}
}
