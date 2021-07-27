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

        String message = "Ajude Not_Neo a chegar ao computador para que ele possa terminar seu projeto final de" +
                " Programacao ";
        String message1 = "Orientada a Objetos. ";
        String par = "Para isso crie um algoritmo com os blocos de logica para navegar o " +
                "labirinto que e seu";
        String message2 = "quarto. O bloco MOVE e usado para movimentar Not_Neo na direcao selecionada," +
                " enquanto o bloco";
        String message3 = "INTERAGIR faz ele interagir com o que estiver na posicao do mapa que ele ocupa." +
                " Os blocos SE e REPETE";
        String message4 = "sao especiais. O bloco REPETE faz com que as acoes dentro dele sejam repetidas " +
                "o numero de vezes";
        String message5 = "selecionadas e as acoes dentro do bloco SE so sao realizadas se a condicao dele " +
                "for verdade.";
        JLabel lvlTxt1 = new JLabel(message);
        int label_w = width-50;
        int label_h = height - 300;
        lvlTxt1.setForeground(Color.GREEN.darker());
        lvlTxt1.setBounds(25, 20, label_w, label_h);
        lvlTxt1.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt1);

        JLabel parTxt = new JLabel(par);
        parTxt.setForeground(Color.GREEN.darker());
        parTxt.setBounds(25, 150, label_w, label_h);
        parTxt.setFont(whiteRabbit.deriveFont(25f));
        add(parTxt);

        JLabel lvlTxt2 = new JLabel(message1);
        lvlTxt2.setForeground(Color.GREEN.darker());
        lvlTxt2.setBounds(25, 70, label_w, label_h);
        lvlTxt2.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt2);

        JLabel lvlTxt3 = new JLabel(message2);
        lvlTxt3.setForeground(Color.GREEN.darker());
        lvlTxt3.setBounds(25, 200, label_w, label_h);
        lvlTxt3.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt3);

        JLabel lvlTxt4 = new JLabel(message3);
        lvlTxt4.setForeground(Color.GREEN.darker());
        lvlTxt4.setBounds(25, 250, label_w, label_h);
        lvlTxt4.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt4);

        JLabel lvlTxt5 = new JLabel(message4);
        lvlTxt5.setForeground(Color.GREEN.darker());
        lvlTxt5.setBounds(25, 300, label_w, label_h);
        lvlTxt5.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt5);

        JLabel lvlTxt6 = new JLabel(message5);
        lvlTxt6.setForeground(Color.GREEN.darker());
        lvlTxt6.setBounds(25, 350, label_w, label_h);
        lvlTxt6.setFont(whiteRabbit.deriveFont(25f));
        add(lvlTxt6);
    }
}
