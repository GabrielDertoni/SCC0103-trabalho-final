package menus;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tutorial extends Background {
    public Tutorial(int x, int y, int width, int height) {
        super("assets/background.png");


        Font whiteRabbit = null;
        try {
            whiteRabbit = Font.createFont(Font.TRUETYPE_FONT, new File("assets/WHITRABT.TTF"));
        } catch (FontFormatException| IOException e) {
            e.printStackTrace();
        }

        setLayout(null);

        int btn_w = 200;
        int btn_h = 40;

        ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");
        Image resizedImage = btnBg.getImage().getScaledInstance(btn_w, btn_h, Image.SCALE_SMOOTH);

        //Botao de fechar o popup de configuraçoes
        JButton btnFechar = new JButton("Fechar", new ImageIcon(resizedImage));

        btnFechar.setBounds(20, 20, btn_w, btn_h);
        btnFechar.setFont(whiteRabbit.deriveFont(25f));
        btnFechar.setForeground(Color.GREEN.darker());
        btnFechar.setVerticalTextPosition(JLabel.CENTER);
        btnFechar.setHorizontalTextPosition(JLabel.CENTER);

        btnFechar.addActionListener(ActionListener -> {
            WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu);
        });

        add(btnFechar);

        JLabel lvlTxt1 = new JLabel("Inserir tutorial aqui.");
        int label_w = width-50;
        int label_h = 20;
        lvlTxt1.setForeground(Color.GREEN.brighter());
        lvlTxt1.setBounds(25, (int)(0.4*height), label_w, label_h);
        lvlTxt1.setFont(whiteRabbit.deriveFont(18f));
        add(lvlTxt1);
    }
}
