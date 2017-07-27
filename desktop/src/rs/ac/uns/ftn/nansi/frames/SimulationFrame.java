package rs.ac.uns.ftn.nansi.frames;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import lombok.val;
import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.nansi.NansiProject;
import rs.ac.uns.ftn.nansi.desktop.settings.SimulationSettings;
import rs.ac.uns.ftn.nansi.panels.InformationPanel;
import rs.ac.uns.ftn.nansi.panels.OptionsPanel;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private static final long serialVersionUID = -5532287799605820495L;

    private NansiProject simulationHolder;
    private SimulationSettings settings;

    private void setupUi() {

        final Container container = getContentPane();

        getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));

        JPanel menuPanel = new JPanel();

        // menuPanel.setBackground(Color.WHITE);
        getContentPane().add(menuPanel, "flowx,cell 0 0,alignx left,growy");

        simulationHolder = new NansiProject(settings);
        val gameWorld = simulationHolder.getGameWorld();

        LwjglAWTCanvas canvas = new LwjglAWTCanvas(simulationHolder);
        container.add(canvas.getCanvas(), BorderLayout.CENTER);
        getContentPane().add(canvas.getCanvas(), "cell 0 0,grow");
        menuPanel.setLayout(new MigLayout("", "[251px][24px]", "[481px][][]"));

        OptionsPanel displayOptions = new OptionsPanel(settings, gameWorld);
        menuPanel.add(displayOptions, "cell 0 0,alignx left,aligny top");
        
        InformationPanel informationPanel = new InformationPanel(gameWorld);
        menuPanel.add(informationPanel, "cell 0 1 1 2,aligny center,grow");
    }

    public SimulationFrame(SimulationSettings settings) {
        this.settings = settings;

        setTitle("Neural Network cars");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setupUi();
        pack();

        setSize(1024, 768);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

}
