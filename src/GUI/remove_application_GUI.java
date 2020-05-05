package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.Seminar;

import serverCode.Client;

public class remove_application_GUI extends JPanel{

	JLabel title, title2;
	
	JTextField mobileNotf, emailIdtf;
	
	JLabel mobileNo, emailId;
	
	JButton confirm;
	
	DefaultTableModel model, model2;	
	JTable t1, t2;
	JPanel p1, p2;
	
	Image ic;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(ic, 0,0,this);
	}
	
	public  remove_application_GUI() {
		
		Client client = Client.getInstance();
		
		setSize(700, 550);
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		setLayout(new FlowLayout());			
		title = new JLabel("Select Seminar");
		title2 = new JLabel("Please enter user information to confirm:");
		model = new DefaultTableModel();	
		model2 = new DefaultTableModel();	
		
		mobileNo = new JLabel("Enter mobile number:");
		mobileNotf = new JTextField();
		emailId = new JLabel("Select seminar ID:");
		emailIdtf = new JTextField();
		confirm = new JButton("Confirm");
		
		model.addColumn("sid");
		model.addColumn("seminar name");
        model.addColumn("domain");
        model.addColumn("duration");
        model.addColumn("capacity");
        model.addColumn("price");
        model.addColumn("date");
      
        t1 = new JTable(model);        
               
        p1 = new JPanel();
        p1.setVisible(true);
        
        p1.setOpaque(false);
        
        p2 = new JPanel();   
        p2.setVisible(false);
        
        p2.setOpaque(false);
        
        p2.setLayout(new GridLayout(3,2));
        p2.add(mobileNo);		p2.add(mobileNotf);
		p2.add(emailId);		p2.add(emailIdtf);
		p2.add(confirm);
        
        
        
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
                p2.setVisible(true);                
                revalidate();                 
            }
        });
        
        confirm.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//code to delete application
				System.out.println("yeyy!!");
				revalidate();
				mobileNotf.setText("");
				emailIdtf.setText("");
			}
		});
        
        JScrollPane pg = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pg.setPreferredSize(new Dimension(getWidth(), 100));
        t1.setOpaque(false);
        pg.setOpaque(false);
        p1.add(pg);
        p1.setOpaque(false);
        repaint();
        add(title); 
        add(p1);
        add(title2);
        add(p2);
        				
		setVisible(true);
		
	
	}

}
