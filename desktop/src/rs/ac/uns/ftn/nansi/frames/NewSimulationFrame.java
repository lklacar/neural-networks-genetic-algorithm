package rs.ac.uns.ftn.nansi.frames;

import lombok.val;
import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.world.InterpolationType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSimulationFrame extends JFrame {

    private static final long serialVersionUID = -7208953175439450443L;
    private JTextField populationSizeTxt;
    private JTextField hiddenLayerCountTxt;
    private JTextField neuronCountTxt;
    private JTextField leftAngleTxt;
    private JTextField nextAngleTxt;
    private JTextField rightAngleTxt;
    private JTextField roadWidthTxt;

    public NewSimulationFrame() {

        setTitle("New Simulation");

        getContentPane().setLayout(
                new MigLayout("", "[grow][grow]", "[][][][][][][][][]"));

        JLabel lblNewLabel = new JLabel("Population size: ");
        getContentPane().add(lblNewLabel, "cell 0 0,alignx trailing");

        populationSizeTxt = new JTextField();
        populationSizeTxt.setText("50");
        getContentPane().add(populationSizeTxt, "cell 1 0,growx");
        populationSizeTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Hidden layer count:");
        getContentPane().add(lblNewLabel_1, "cell 0 1,alignx trailing");

        hiddenLayerCountTxt = new JTextField();
        hiddenLayerCountTxt.setText("4");
        getContentPane().add(hiddenLayerCountTxt, "cell 1 1,growx");
        hiddenLayerCountTxt.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Neurons per hidden layer:");
        getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");

        neuronCountTxt = new JTextField();
        neuronCountTxt.setText("100");
        getContentPane().add(neuronCountTxt, "cell 1 2,growx");
        neuronCountTxt.setColumns(10);

        JLabel lblLeftAngleOffset = new JLabel("Left sensor angle offset:");
        getContentPane().add(lblLeftAngleOffset, "cell 0 3,alignx trailing");

        leftAngleTxt = new JTextField();
        leftAngleTxt.setText("-80");
        getContentPane().add(leftAngleTxt, "cell 1 3,growx");
        leftAngleTxt.setColumns(10);

        JLabel lblNextSensorOffset = new JLabel("Next sensor offset:");
        getContentPane().add(lblNextSensorOffset, "cell 0 4,alignx trailing");

        nextAngleTxt = new JTextField();
        nextAngleTxt.setText("20");
        getContentPane().add(nextAngleTxt, "cell 1 4,growx");
        nextAngleTxt.setColumns(10);

        JLabel lblRightSensorAngle = new JLabel("Right sensor angle offset:");
        getContentPane().add(lblRightSensorAngle, "cell 0 5,alignx trailing");

        rightAngleTxt = new JTextField();
        rightAngleTxt.setText("80");
        getContentPane().add(rightAngleTxt, "cell 1 5,growx");
        rightAngleTxt.setColumns(10);

        JLabel lblRoadWidth = new JLabel("Road width: ");
        getContentPane().add(lblRoadWidth, "cell 0 6,alignx trailing");

        roadWidthTxt = new JTextField();
        roadWidthTxt.setText("10");
        getContentPane().add(roadWidthTxt, "cell 1 6,growx");
        roadWidthTxt.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Interpolation type: ");
        getContentPane().add(lblNewLabel_3, "cell 0 7,alignx right");

        final JRadioButton rdbtnLinearInterpolation = new JRadioButton(
                "Linear interpolation");
        getContentPane().add(rdbtnLinearInterpolation, "flowx,cell 1 7");

        JButton startSimulationBtn = new JButton("Start simulation");
        getContentPane().add(startSimulationBtn, "cell 0 8 2 1,growx");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 327);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(startSimulationBtn);

        final JRadioButton rdbtnLagrangeInterpolation = new JRadioButton(
                "Lagrange interpolation");
        getContentPane().add(rdbtnLagrangeInterpolation, "cell 1 7");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnLinearInterpolation);
        buttonGroup.add(rdbtnLagrangeInterpolation);

        rdbtnLinearInterpolation.setSelected(true);

        startSimulationBtn.addActionListener(e -> {
            SimulationSettings settings = new SimulationSettings();

            try {
                settings.setPopulationSize(Integer
                        .parseInt(populationSizeTxt.getText()));

                settings.setHiddenLayerCount(Integer
                        .parseInt(hiddenLayerCountTxt.getText()));

                settings.setNeuronsPerHiddenLayer(Integer
                        .parseInt(neuronCountTxt.getText()));

                settings.setLeftAngle(Integer.parseInt(leftAngleTxt
                        .getText()));

                settings.setNextAngle(Integer.parseInt(nextAngleTxt
                        .getText()));

                settings.setRightAngle(Integer.parseInt(rightAngleTxt
                        .getText()));

                settings.setRoadWidth(Integer.parseInt(roadWidthTxt
                        .getText()));

                InterpolationType interpolationType = InterpolationType.LINEAR;
                if (rdbtnLinearInterpolation.isSelected())
                    interpolationType = InterpolationType.LINEAR;
                else if (rdbtnLagrangeInterpolation.isSelected())
                    interpolationType = InterpolationType.LAGRANGE;

                settings.setInterpolationType(interpolationType);

                setVisible(false);

                val simulationFrame = new SimulationFrame(settings);
                simulationFrame.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "All fields must be integers.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        });
    }

}
