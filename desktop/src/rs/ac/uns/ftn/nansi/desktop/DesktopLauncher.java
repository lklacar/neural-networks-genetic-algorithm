package rs.ac.uns.ftn.nansi.desktop;

import lombok.val;
import rs.ac.uns.ftn.nansi.frames.NewSimulationFrame;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class DesktopLauncher {

    private static void showFrame() {
        val newSimulationFrame = new NewSimulationFrame();
        newSimulationFrame.setVisible(true);
    }

    private static void setLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            setLookAndFeel();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        showFrame();
    }

}
