package rs.ac.uns.ftn.nansi.panels;

import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.desktop.utility.IOUtility;
import rs.ac.uns.ftn.nansi.genetic.GeneticAlgorithm;
import rs.ac.uns.ftn.nansi.world.GameWorld;
import rs.ac.uns.ftn.nansi.world.Generator;
import rs.ac.uns.ftn.nansi.world.Road;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {

    private static final long serialVersionUID = 7830452536175849875L;

    public OptionsPanel() {

        setBorder(BorderFactory.createTitledBorder("Options"));

        setMinimumSize(new Dimension(300, 0));
        setMaximumSize(new Dimension(300, 10000));

        setLayout(new MigLayout("", "[grow]",
                "[grow][][][][][][][][][][][][][]"));

        JPanel panel = new JPanel();
        add(panel, "cell 0 0,growx");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();

        final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
                "Display sensors for all");
        rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (rdbtnNewRadioButton_1.isSelected() == true)
                    SimulationSettings.getInstance().setDisplaySensors(1);

            }
        });
        panel.add(rdbtnNewRadioButton_1);

        final JRadioButton rdbtnNewRadioButton = new JRadioButton(
                "Display sensors for first only");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnNewRadioButton.isSelected() == true)
                    SimulationSettings.getInstance().setDisplaySensors(2);
            }
        });
        rdbtnNewRadioButton.setSelected(true);
        panel.add(rdbtnNewRadioButton);

        group.add(rdbtnNewRadioButton);
        group.add(rdbtnNewRadioButton_1);

        final JCheckBox chckbxNewCheckBox = new JCheckBox(
                "Auto next generation");
        chckbxNewCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationSettings.getInstance().setAutoNext(
                        chckbxNewCheckBox.isSelected());

            }
        });
        add(chckbxNewCheckBox, "cell 0 1");

        JButton btnNewSimulation = new JButton("Next generation");
        btnNewSimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                GameWorld.getInstance().nextGenerationReset();

            }
        });
        add(btnNewSimulation, "cell 0 3,growx");

        JButton btnNewButton_5 = new JButton("Generate new road");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameWorld.getInstance().setRoad(
                        Generator.generate(SimulationSettings.getInstance()
                                .getGenerationType()));
                GameWorld.getInstance().nextGenerationReset();
            }
        });
        add(btnNewButton_5, "cell 0 4,growx");

        JButton btnNewButton = new JButton("Import existing population");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    GeneticAlgorithm ga = (GeneticAlgorithm) IOUtility
                            .loadFile();
                    if (ga != null) {
                        GameWorld.getInstance().setGa(ga);
                        GameWorld.getInstance().sameGenerationReset();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error while imporing.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        Box horizontalBox_1 = Box.createHorizontalBox();
        add(horizontalBox_1, "cell 0 5");
        add(btnNewButton, "cell 0 6,growx");

        JButton btnLoad = new JButton("Import existing road");
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Road road = (Road) IOUtility.loadFile();
                if (road != null) {
                    GameWorld.getInstance().setRoad(road);
                    GameWorld.getInstance().sameGenerationReset();
                }
            }
        });
        add(btnLoad, "cell 0 7,growx");

        JButton btnNewButton_3 = new JButton("Export current population");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                IOUtility.saveFile(GameWorld.getInstance().getGa());
            }
        });
        add(btnNewButton_3, "cell 0 9,growx");

        JButton btnNewButton_4 = new JButton("Export current road");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IOUtility.saveFile(GameWorld.getInstance().getRoad());

            }
        });
        add(btnNewButton_4, "cell 0 10,growx");

        Box horizontalBox = Box.createHorizontalBox();
        add(horizontalBox, "cell 0 11");

    }
}
