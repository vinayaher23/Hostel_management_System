package hostel.gui._adminGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import hostel.gui.ablcGUI.hstlablcgui;
import hostel.gui.attendanceGUI.hstlattendgui;
import hostel.gui.blockGui.hstlblockgui;
import hostel.gui.floorGUI.hstlfloorgui;

import hostel.gui.rmGrantGui.hstlgrandgui;
import hostel.gui.roomGUI.hstlroomgui;
import hostel.gui.studentGUI.hstlstudentgui;
import hostel.gui.visitorsGUI.hstlvistiorgui;
import java.awt.Color;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class hstladmin {

	private JFrame frmAdministratprHome;
	protected static JTabbedPane MainTabbedPane;
	private JButton btnLogIn;
	public static JButton viewLogTbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");// set J-TATOO look and feel
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");// i like this
			//UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");// for Mac - os Look and feel
		}
		catch(Exception unused) {}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Dimension screensz=Toolkit.getDefaultToolkit().getScreenSize();// setting full_screen size
				
				try {
					hstladmin window = new hstladmin();
                                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
					window.frmAdministratprHome.setBounds(0, 0,screensz.width,screensz.height);// initializing full screen.
					window.frmAdministratprHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public hstladmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratprHome = new JFrame();
		frmAdministratprHome.setResizable(false);
		frmAdministratprHome.getContentPane().setBackground(new Color(0, 139, 139));
		frmAdministratprHome.setTitle("ADMINISTRATOR HOME");
		frmAdministratprHome.setBounds(100, 100, 1046, 715);
		//frmAdministratprHome.setUndecorated(true);
	//	frmAdministratprHome.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		frmAdministratprHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frmAdministratprHome.getContentPane().setLayout(null);
		
		 
		Dimension screensz1=Toolkit.getDefaultToolkit().getScreenSize();
		//tabbedPane.setVisible(false);
		
		JButton btquit = new JButton("Quit");
		btquit.setLocation(36, 412);
		btquit.setSize(131, 23);
		btquit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null,"Are you sure to quit","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE))==0) System.exit(0);
			}
		});
		frmAdministratprHome.getContentPane().add(btquit);
		MainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		MainTabbedPane.setBounds(210, 0, 1229, 766);
		frmAdministratprHome.getContentPane().add(MainTabbedPane);
		MainTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		MainTabbedPane.setBackground(new Color(0, 153, 153));
		MainTabbedPane.setVisible(false);
		
		hstlattendgui hsatt=new hstlattendgui();
		MainTabbedPane.addTab("ATTENDANCE",null,hsatt,null);
		MainTabbedPane.setBackgroundAt(0, new Color(0, 204, 204));
		
		hstlvistiorgui hvis=new hstlvistiorgui();			// initializing and adding panel for visitors
		MainTabbedPane.addTab("VISITORS",null,hvis,null);
		MainTabbedPane.setBackgroundAt(1, new Color(0, 204, 204));
		
		hstlstudentgui hstu=new  hstlstudentgui();	// initializing and adding panel for Student
		MainTabbedPane.addTab("STUDENT",null,hstu,null);
		MainTabbedPane.setBackgroundAt(2, new Color(0, 204, 204));
		
		try {
			hstlablcgui hablc=new hstlablcgui();// initializing and adding panel for ablc
			MainTabbedPane.addTab("ABLC", null,hablc,null);
			MainTabbedPane.setBackgroundAt(3, new Color(0, 204, 204));
		} catch (Exception e) {	}
		
		
		hstlgrandgui hgr=new hstlgrandgui();// initializing and adding panel for Grand
		MainTabbedPane.addTab("ROOM GRAND", null,hgr,null);
		MainTabbedPane.setBackgroundAt(4, new Color(0, 204, 204));
		
		hstlroomgui hr=new hstlroomgui();	// initializing and adding panel for room
		MainTabbedPane.addTab("ROOM",null,hr,null);
		MainTabbedPane.setBackgroundAt(5, new Color(0, 204, 255));
		
		hstlfloorgui hf=new hstlfloorgui();	// initializing and adding panel for floor
		hf.setBackground(new Color(255, 192, 203));
		MainTabbedPane.addTab("FLOOR",null,hf,null);
		MainTabbedPane.setBackgroundAt(6, new Color(0, 204, 204));
		
		
		hstlblockgui hb= new hstlblockgui();// initilaizing and adding panel for block
		hb.setBackground(new Color(0, 100, 0));
		MainTabbedPane.addTab("BLOCK", null,hb,null);
		MainTabbedPane.setBackgroundAt(7, new Color(0, 204, 204));
		
		btnLogIn = new JButton("LogIn");/// -- log in
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
					try {
						login frame = new login();
						frame.setVisible(true);
						btnLogIn.setEnabled(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			}
		});
		btnLogIn.setBounds(36, 70, 131, 23);
		frmAdministratprHome.getContentPane().add(btnLogIn);
		
		viewLogTbl = new JButton("View Login Table");//------------to view login table
		viewLogTbl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
					try {
						loginView frame = new loginView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			}
		});
		viewLogTbl.setBounds(36, 220, 131, 23);
		frmAdministratprHome.getContentPane().add(viewLogTbl);
		viewLogTbl.setVisible(false); 
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
