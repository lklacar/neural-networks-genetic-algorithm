package rs.ac.uns.ftn.nansi.panels;

import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.nansi.world.Car;
import rs.ac.uns.ftn.nansi.world.GameWorld;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class InformationPanel extends JPanel {

    private static final long serialVersionUID = -6926107357361257974L;

    public InformationPanel(GameWorld gameWorld) {

        setBorder(BorderFactory.createTitledBorder("Population information:"));
        setLayout(new MigLayout("", "[440px]", "[278px]"));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, "cell 0 0,grow");

        final DefaultListModel<String> model = new DefaultListModel<String>();
        final JList<String> list = new JList<String>();
        list.setModel(model);
        scrollPane.setViewportView(list);

        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {
                    model.clear();
                    int i = 1;
                    for (Car c : gameWorld.getAllCars()) {
                        model.addElement("ID: " + Integer.toString(i) + "   "
                                + Double.toString(c.getNetwork().getFitness()));
                        i++;
                    }
                } catch (Exception ignored) {

                }

            }
        }, 0, 500);

    }

}
