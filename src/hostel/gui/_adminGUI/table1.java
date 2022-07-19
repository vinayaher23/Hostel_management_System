package hostel.gui._adminGUI;

import hostel.hstlmodels.logmodlin;
import hostel.hstlservices.Loginservice;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class table1 extends JPanel {
	protected static JTable table;
	private RowSorter<DefaultTableModel> rsetr;
	protected static DefaultTableModel tmodl;
	private TableColumn tColmodl;
	//private JTable table;
	
	public table1() {
		setBackground(new Color(204, 255, 204));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 614, 98);
		add(scrollPane);
		
		tmodl=new DefaultTableModel() {
			public boolean isCellEditable(int row,int colm) {//---      returns if a cell editable or not
				if(colm==0|colm==4|colm==5)//------do not allow to edit login ID which is primarykey
					return false;
				else if(row==0) 
					return false;
				else
					return true;
			}
		};
		tmodl.addColumn("ID");
		tmodl.addColumn("Login Name");
		tmodl.addColumn("Password");
		tmodl.addColumn("Status");
		tmodl.addColumn("Action 1");
		tmodl.addColumn("Action 2");
		
		//table.setModel(tmodl);
			
		ArrayList<logmodlin> arl=Loginservice.SlctAllLogin();
		for(logmodlin lm:arl) {
		//	System.out.println(lm.getId()+lm.getUnam()+lm.getPasswd()+lm.getStat());
			Object[] row= {lm.getId(),lm.getUnam(),lm.getPasswd(),lm.getStat(),"Update","Delete"};
		//	for(int i=0;i<row.length;i++)System.out.println(row[i]);
				tmodl.addRow(row);
		}
		
		table = new JTable(tmodl);
		table.setToolTipText("You cannot 'Update' or 'Delete' First row In this Table");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				int x=me.getX(),y=me.getY();
				int row=table.rowAtPoint(new Point(x,y));
				int col=table.columnAtPoint(new Point(x, y));
				if(col==4 && row!=0) {// -- user clicked on Action Column 'update'
					//System.out.println("you clicked to Update");
					ArrayList<Object> arry=new ArrayList<Object>();
					//Object[]arr=new String[table.getColumnCount()-1];		// 4-1=3 columns contains data to update
					for(int i=0;i<table.getColumnCount()-1;i++)	// 4-1=3 columns contains data to update
						arry.add(table.getValueAt(row, i));
					if(JOptionPane.showConfirmDialog(table1.this,"Are You Sure to Update Data...?","Confirm",JOptionPane.WARNING_MESSAGE)==0) 
						updaterow(row,arry);// ----calling method 'update' to update edited row both database and in jtable
				}
				if(col==5 && row!=0) {//---------user clicked to delete corresponding row
					int delID=Integer.parseInt(String.valueOf(table.getValueAt(row, 0))) ;
					//JOptionPane.showMessageDialog(null, "The id of Clicked row is :"+delID);
					if(JOptionPane.showConfirmDialog(table1.this,"Are You Sure to Delete Data...?","Confirm",JOptionPane.WARNING_MESSAGE)==0) {//----confirm before Deleting Datas
						tmodl.removeRow(table.getSelectedRow());
						DeleteRecord(delID);
					}
				}
			}
		});
		table.setBackground(new Color(102, 153, 255));
		table.getColumnModel().getColumn(4).setCellRenderer(new LabelCellRenderer());
		table.getColumnModel().getColumn(5).setCellRenderer(new LabelCellRenderer());
		scrollPane.setViewportView(table);
		
		tColmodl=table.getColumnModel().getColumn(3);
		JComboBox<String >jc=new JComboBox<String>();
		jc.addItem("Active");
		jc.addItem("In Active");
		tColmodl.setCellEditor(new DefaultCellEditor(jc));//---- setting combobox to Status column of jtable
		tColmodl.setPreferredWidth(100);
		
	}
	protected void DeleteRecord(int delID) {///------------to Delete row of Jtable and same row in Database. 
		Loginservice.deleteLogin(delID);
	}
	protected void updaterow(int row, ArrayList<Object> arry) {		// ------------------------to update a Jtable row  
		logmodlin lm=new logmodlin();
			lm.setId(Integer.parseInt(String.valueOf(arry.get(0))));
			lm.setUnam(String.valueOf(arry.get(1)).trim());
			lm.setPasswd(String.valueOf(arry.get(2)).trim());
			lm.setStat(String.valueOf(arry.get(3)).trim());
		Loginservice.updateLogin(lm);	
	}
}
class LabelCellRenderer extends DefaultTableCellRenderer {
   @Override
    public Component getTableCellRendererComponent(JTable table, Object oValue, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, oValue, isSelected, hasFocus, row, column);
        String value = (String) oValue;
        JLabel label = (JLabel) c;
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        label.setBackground(Color.pink);
        label.setHorizontalTextPosition(SwingUtilities.CENTER);
        label.setHorizontalAlignment(SwingUtilities.CENTER);
        label.setText(value);
        return label;
    }
  
}
