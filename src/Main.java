import menus.WindowManager;

import java.awt.*;

public class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WindowManager.getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
