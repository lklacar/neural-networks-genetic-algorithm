package rs.ac.uns.ftn.nansi.desktop.utility;

import javax.swing.*;
import java.io.*;

public class IOUtility {

    public static Object loadFile() {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {

                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(file));

                Object o = ois.readObject();
                ois.close();

                return o;

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while exporting.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        return null;

    }

    public static void saveFile(Object object) {
        JFileChooser fileChooser = new JFileChooser();

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(file));

                oos.writeObject(object);

                oos.close();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Error while exporting.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

}
