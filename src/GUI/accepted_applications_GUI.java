package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

import com.Seminar;
import com.User;

import serverCode.Client;


public class accepted_applications_GUI extends JPanel {

	public JLabel title, title2;
	
	DefaultTableModel model, model2;	
	JTable t1, t2;
	JPanel p1, p2;
	Image ic;

	
	public accepted_applications_GUI() {

		final Client client = Client.getInstance();
		
		setSize(700, 550);
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		setLayout(new FlowLayout());			
		title = new JLabel("Select Seminar");
		title2 = new JLabel("Accepted applications are:");
		model = new DefaultTableModel();	
		model2 = new DefaultTableModel();	
		
		model.addColumn("sid");
		model.addColumn("seminar name");
        model.addColumn("domain");
        model.addColumn("duration");
        model.addColumn("capacity");
        model.addColumn("price");
        model.addColumn("date");
        
        model2.addColumn("name");
        model2.addColumn("mobile number");
        model2.addColumn("email ID");
        model2.addColumn("sid");
        model2.addColumn("qualifications");
        
        t1 = new JTable(model);         
               
        p1 = new JPanel();
        p1.setOpaque(false);
        p1.setVisible(true);
        p2 = new JPanel();   
        p2.setVisible(false);
        p2.setOpaque(false);
        
        try {            
            Vector<Seminar> Rs = (Vector<Seminar>) client.readData();
            Iterator<Seminar> itr = Rs.iterator();	
            
			while(itr.hasNext())
			{					
				Seminar u = itr.next();				
                model.addRow(new Object[] { u.id,u.name,u.domain,u.duration,u.capacity,u.price,u.date});              				
			}  
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }       
        
        t1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {                
            	if(event.getValueIsAdjusting()==true)
            		return;   
            	client.sendData(t1.getValueAt(t1.getSelectedRow(), 0).toString());
            	if(model2.getRowCount()!=0) {
            		model2.setRowCount(0);            	
            	} 
                     ArrayList<User> Rs = (ArrayList<User>) client.readData();                     
					 Iterator<User> itr = Rs.iterator();						
						while(itr.hasNext())
						{					
							User u = itr.next();
	                        model2.addRow(new Object[] { u.name,u.mobileNo,u.emailId,u.sid,u.qualifications});              				
						}
							
                t2 = new JTable(model2);
               // t2.setEnabled(false);
                JScrollPane pg1 = new JScrollPane(t2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                pg1.setPreferredSize(new Dimension(600, 60));
                p2.add(pg1);	
                p2.setVisible(true);                
                revalidate();                 
            }
        });
        
        JScrollPane pg = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pg.setPreferredSize(new Dimension(getWidth()-50, 100));
        p1.add(pg);
        
        repaint();
        add(title); 
        add(p1);
        add(title2);
        add(p2); 
				
		setVisible(true);
		
	
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(ic, 0,0,this);
	}
}
