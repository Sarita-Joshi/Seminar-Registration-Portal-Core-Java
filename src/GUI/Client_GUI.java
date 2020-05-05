package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import serverCode.Client;

//import javafx.scene.control.Separator;
//import javafx.scene.shape.Box;



public class Client_GUI extends JFrame{
	
	//declare
	Image ic;
	JLabel back;
	JMenu submit;
	JMenu add;
	JMenu register_for_seminar;
	JMenu payment;
	JMenu check_application;	
	JMenuBar mb;
	JMenu p1;
	JMenuItem add_seminar, add_Application,
		online, coupons,
		payment_list, accepted_appl, remove_appl;
	
	
  JPanel p2;
	
	Client client; 
	public Client_GUI() {
		
		setTitle("Registration portal");
		setSize(800,550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());		
		setLocation(200, 100);
				
		client = Client.getInstance();

		//initialize components
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		back = new JLabel();
		back.setIcon(new ImageIcon(ic));
		mb = new JMenuBar();
		submit = new HorizontalMenu("back");
		submit.setPreferredSize(new Dimension(100,35));
		add = new HorizontalMenu("add new");		
		add.setPreferredSize(new Dimension(100,35));
		payment = new HorizontalMenu("payment");		
		payment.setPreferredSize(new Dimension(100,35));
		check_application = new HorizontalMenu("Application");
		check_application.setPreferredSize(new Dimension(100,35));
		
		
		add_seminar = new JMenuItem("new seminar");
		add_seminar.setPreferredSize(new Dimension(120,35));
		add_Application = new JMenuItem("new application");
		add_Application.setPreferredSize(new Dimension(120,35));
		add.add(add_Application);
		add.add(add_seminar);
		
		online = new JMenuItem("Online Payment");
		online.setPreferredSize(new Dimension(110,35));
		payment.add(online);
		
		payment_list = new JMenuItem("pending payments");
		payment_list.setPreferredSize(new Dimension(150,35));
		accepted_appl = new JMenuItem("applicaton status");
		accepted_appl.setPreferredSize(new Dimension(150,35));
		remove_appl = new JMenuItem("remove applicaton");
		remove_appl.setPreferredSize(new Dimension(150,35));
		check_application.add(payment_list);
		check_application.add(accepted_appl);
		//check_application.add(remove_appl);
				
		p2 = new JPanel();
		p2.setSize(600,400);
		p2.setOpaque(false);
		p2.add(back);
					
		mb.add(submit);		
		mb.add(add);
		mb.add(check_application);		
		mb.add(payment);		
		
		mb.add(javax.swing.Box.createVerticalGlue());
		//mb.setBackground(Color.gray);
		//mb.setPreferredSize(new Dimension(100,getHeight()));
		mb.setLayout(new BoxLayout(mb, BoxLayout.PAGE_AXIS));   
		
		add(mb,BorderLayout.LINE_START);			
	
				//done
		add_seminar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				client.sendData("4");				
				p2.setVisible(false);
				p2 = new add_seminar_GUI(Client_GUI.this);				
				p2.setVisible(true);
				add(p2, BorderLayout.CENTER);
				revalidate();
												
			}
		});		
		
		//done
		add_Application.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				client.sendData("1");
				p2.setVisible(false);
				p2 = new new_registration_GUI();
				p2.setVisible(true);			
				add(p2, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		
		online.addActionListener(new ActionListener() {			
			
			public void actionPerformed(ActionEvent arg0) {
				client.sendData("3");
				p2.setVisible(false);
				p2 = new payment_GUI();
				p2.setVisible(true);
				add(p2, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		//done
		payment_list.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						client.sendData("5");
						p2.setVisible(false);
						p2 = new pending_payments_GUI();
						p2.setVisible(true);
						add(p2, BorderLayout.CENTER);
						revalidate();
					}
				});

		//done
		accepted_appl.addActionListener(new ActionListener() {			
	
			public void actionPerformed(ActionEvent arg0) {
				client.sendData("2");
				System.out.println("2 sent");
				p2.setVisible(false);
				p2 = new accepted_applications_GUI();
				
				p2.setVisible(true);
				add(p2, BorderLayout.CENTER);
				revalidate();
			}
		});

		//done
		remove_appl.addActionListener(new ActionListener() {			
		
			public void actionPerformed(ActionEvent arg0) {
				p2.setVisible(false);
				p2 = new remove_application_GUI();
				p2.setVisible(true);
				add(p2, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		submit.addMenuListener(new MenuListener() {
			
		
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				p2.setVisible(false);
				p2 = new JPanel();
				p2.add(back);
				p2.setVisible(true);
				add(p2, BorderLayout.CENTER);
				revalidate();	
			}
			
			
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		add(p2, BorderLayout.CENTER);		
		repaint();
		setVisible(true);		
	}
	@Override
	public void paintAll(Graphics g) {
		// TODO Auto-generated method stub
		super.paintAll(g);
		g.drawImage(ic, 0,0,null);
	}
	
	
	
	public static void main(String[] args) {
		new Client_GUI();
	}
}
	
	class HorizontalMenu extends JMenu {
	    HorizontalMenu(String label) {
	      super(label);
	      JPopupMenu pm = getPopupMenu();
	      pm.setLayout(new BoxLayout(pm, BoxLayout.PAGE_AXIS));
	      
	    }

	    public Dimension getMinimumSize() {
	      return getPreferredSize();
	    }

	    public Dimension getMaximumSize() {
	      return getPreferredSize();
	    }

	    public void setPopupMenuVisible(boolean b) {
	      boolean isVisible = isPopupMenuVisible();
	      if (b != isVisible) {
	        if ((b == true) && isShowing()) {
	          // Set location of popupMenu (pulldown or pullright).
	          // Perhaps this should be dictated by L&F.
	          int x = 0;
	          int y = 0;
	          Container parent = getParent();
	          if (parent instanceof JPopupMenu) {
	            x = 0;
	            y = getHeight();
	          } else {	        	  
	            x = getWidth();
	            y = 0;
	          }
	          getPopupMenu().show(this, x, y);
	        } else {
	          getPopupMenu().setVisible(false);
	        }
	      }
	    }
	    
	    
	        
	    
	  }
