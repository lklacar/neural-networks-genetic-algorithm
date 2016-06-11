package rs.ac.uns.ftn.nansi.panels;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.nansi.world.Car;
import rs.ac.uns.ftn.nansi.world.GameWorld;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = -6926107357361257974L;

	public InformationPanel() {

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
					for (Car c : GameWorld.getInstance().getAllCars()) {
						model.addElement("ID: " + Integer.toString(i) + "   "
								+ Double.toString(c.getNetwork().getFitness()));
						i++;
					}
				} catch (Exception ex) {

				}

			}
		}, 0, 500);

	}

}
