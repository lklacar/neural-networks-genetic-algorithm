package rs.ac.uns.ftn.nansi.desktop;

import rs.ac.uns.ftn.nansi.frames.NewSimulationFrame;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class DesktopLauncher {

    private static void showFrame() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewSimulationFrame();
            }
        });
    }

    private static void setLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

            try {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {

            }
        }
    }

    public static void main(String[] args) {
        setLookAndFeel();

        showFrame();

    }

}
