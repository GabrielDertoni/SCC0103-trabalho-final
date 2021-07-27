package menus;

import javax.swing.*;
import java.awt.*;

import static game.Resources.whiteRabbit;

public class Tutorial extends Background {
    public Tutorial(int x, int y, int width, int height) {
        super("assets/background.png");

        setLayout(null);

        int btn_w = 200;
        int btn_h = 40;

        ImageIcon btnBg = new ImageIcon("assets/botaoSemTextura.png");
        Image resizedImage = btnBg.getImage().getScaledInstance(btn_w, btn_h, Image.SCALE_SMOOTH);

        //Botao de fechar o popup de configuraçoes
        JButton btnFechar = new JButton("Fechar", new ImageIcon(resizedImage));

        btnFechar.setBounds(x, y, btn_w, btn_h);
        btnFechar.setFont(whiteRabbit.deriveFont(25f));
        btnFechar.setForeground(Color.GREEN.darker());
        btnFechar.setVerticalTextPosition(JLabel.CENTER);
        btnFechar.setHorizontalTextPosition(JLabel.CENTER);

        btnFechar.addActionListener(ActionListener ->
                WindowManager.getInstance().setCurrentWindow(WindowManager.WindowName.MainMenu));

        add(btnFechar);

        String message = "<html>Ajude Not_Neo a chegar ao computador para que ele possa terminar seu projeto final de" +
                " Programação Orientada a Objetos. Para isso crie um algoritmo com os blocos de lógica para navegar o " +
                "labirinto que é seu quarto. O bloco MOVE é usado para movimentar Not_Neo na direção selecionada," +
                " enquanto o bloco INTERAGIR faz ele interagir com o que estiver na posição do mapa que ele ocupa." +
                " Os blocos SE e REPETE são especiais. O bloco REPETE faz com que as ações dentro dele sejam repetidas " +
                "o número de vezes selecionadas e as ações dentro do bloco se só são realizadas se a condição dele " +
                "for verdade.";
        JLabel lvlTxt1 = new JLabel(message);
        int label_w = width-50;
        int label_h = height - 300;
        lvlTxt1.setForeground(Color.GREEN.darker());
        lvlTxt1.setBounds(25, 100, label_w, label_h);
        lvlTxt1.setFont(whiteRabbit.deriveFont(30f));
        add(lvlTxt1);
    }
}
