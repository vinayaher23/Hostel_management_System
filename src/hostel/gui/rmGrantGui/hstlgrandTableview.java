package hostel.gui.rmGrantGui;

import hostel.hstlservices.grandservices;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;

public class hstlgrandTableview extends JPanel {
	private JTable table;
	private TableRowSorter<TableModel>rsort;
	
	public hstlgrandTableview() {
		setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 870, 129);
		add(scrollPane);
		
		grandservices gs=new grandservices();
		rsort=new TableRowSorter<TableModel>(gs.grandTable());
		table = new JTable(gs.grandTable());
		table.setEnabled(true);
		table.setToolTipText("click each column header to show\r\n as Ascending or Descending order");
		table.setBackground(new Color(204, 255, 204));
		table.setRowSorter(rsort);
		scrollPane.setViewportView(table);

	}
}
