package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.ImageObserver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.Seminar;
import com.User;

import serverCode.Client;

public class payment_GUI extends JPanel implements ImageObserver {
	
	JTextField nametf, sidtf, methodTypetf, coupontf;	
	JLabel name, title1, title2, sid, methodType, coupon, cdetails;	
	JButton submit;	
	JPanel p1,p2, methodPanel;	
	DefaultTableModel model;	
	JTable t1;
	JComboBox<String> c1; 
	Image ic;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(ic, 0,0,this);
	}
	
	public  payment_GUI()  {
		
		final Client client = Client.getInstance();

		
		setSize(700, 550);
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		setLayout(new BorderLayout());
		
		name = new JLabel("Enter emailID:");
		coupon = new JLabel("CODE:");
		coupontf = new JTextField(10); 
		cdetails = new JLabel("details");
		methodType = new JLabel("select payment method:");
		methodTypetf = new JTextField(); 
		sid = new JLabel("Enter seminar ID:");
		sidtf = new JTextField("choose seminar from above table"); 
		sidtf.setEnabled(false);
		title1 = new JLabel("Online payment");
		title2 = new JLabel("Coupon Codes");
		nametf = new JTextField(20); 
		submit = new JButton("submit");
		
		
		model = new DefaultTableModel();			
		model.addColumn("sid");
		model.addColumn("seminar name");
        model.addColumn("domain");
        model.addColumn("duration");
        model.addColumn("capacity");
        model.addColumn("price");
        model.addColumn("date"); 
        t1 = new JTable(model);
        
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
                sidtf.setText(t1.getValueAt(t1.getSelectedRow(), 0).toString());             
                revalidate();                 
            }
        });
       
		
        JScrollPane pg = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pg.setPreferredSize(new Dimension(getWidth()-50, 100));
        pg.setOpaque(false);
        pg.getViewport().setOpaque(false);
        
        String s1[] = { "Choose method", "Credit / Debit card", "Net banking", "PayPal"};  
        c1 = new JComboBox(s1);   
        methodPanel = new JPanel();
        methodPanel.setPreferredSize(new Dimension(300, 100));
        methodPanel.setOpaque(false);
        c1.setOpaque(false);
        c1.addActionListener(new ActionListener() {
        	
			
			public void actionPerformed(ActionEvent e) {
				
				methodPanel.removeAll();				
				switch(c1.getSelectedIndex()) {
				case 2:
					methodPanel.setLayout(new FlowLayout());

					methodPanel.add(new JLabel("<html><br>In order to complete your transaction, we will transfer you over to <br>Adyen's secure servers." +
							"<br>Select your bank from the drop-down list and click proceed to continue<br> with your payment.</html>"));

					methodPanel.add(new JComboBox<Object>(new Object[] {"Please choose one", "BOI", "SBI", "BOM", "ICICI", "HDFC", "PNB"}));
					break;
				case 3:
					methodPanel.setLayout(new FlowLayout());
					methodPanel.add(new JLabel("<html><br>In order to complete your transaction, we will transfer you over to <br>PayPal's secure servers.\n" + 
							"<br><br>" + 
							"Unfortunately, Paypal does not process payments in INR.<br>Your purchase will be made in USD.\n" + 
							"<br><br>" + 
							"The price you will be charged by Paypal: $7.</html>"));					
					break;					
				
				case 1 :{
					methodPanel.setLayout(new GridLayout(5,2));
					methodPanel.add(new JLabel("Name on card:"));
					methodPanel.add(new JTextField());
					methodPanel.add(new JLabel("NCard number:"));
					methodPanel.add(new JTextField());
					methodPanel.add(new JComboBox<Object>(new Object[] {"MM", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
					methodPanel.add(new JComboBox<Object>(new Object[] {"YYYY", 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030}));
					methodPanel.add(new JLabel("Security code:"));
					methodPanel.add(new JTextField());					
				break;
				}
				
			}
				methodPanel.revalidate();
				
			}
		});                
       
		p1 = new JPanel();
		p1.setSize(300,400);
		p1.setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		p1.setOpaque(false);
		p2 = new JPanel();
		p2.setSize(300,400);
		p2.setLayout(new GridBagLayout());
		p2.setOpaque(false);
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.weightx=0.5;
		c.gridy=0; c.gridx=0;
		p1.add(title1,c);
		c.gridy=2; c.gridx=0;
		p1.add(name,c);	
		c.gridy=2; c.gridx=1;
		p1.add(nametf,c);
		c.gridy=3; c.gridx=0;
		p1.add(sid,c);
		c.gridy=3; c.gridx=1;
		p1.add(sidtf,c);
		c.gridy=4; c.gridx=0;		
		p1.add(methodType,c);
		c.gridy=4; c.gridx=1;
		c.ipadx=30;
		p1.add(c1,c);
		
		c.gridwidth=3;
		c.weightx=0.0;
		c.ipady=40;
		c.gridx=0; c.gridy=5;
		p1.add(methodPanel,c);
		
		c.fill = GridBagConstraints.NONE;	
		c.weightx=0.5;
		c.gridy=6; c.gridx=0;		
		p1.add(submit,c);
		
		//for p2
		c.weightx=0.5; c.fill = GridBagConstraints.NONE;	
		c.gridy=0; 
		p2.add(title2,c); 
		c.gridy=2; 
		c.weightx=0.5; c.fill = GridBagConstraints.NONE;	
		p2.add(coupon,c);
		c.gridy=3; 
		c.weightx=0.5; c.fill = GridBagConstraints.HORIZONTAL;	
		p2.add(coupontf,c);
	    c.fill = GridBagConstraints.REMAINDER;		 
		c.gridy=4; 		
		c.gridheight=10;
		p2.add(cdetails,c);

		add(pg, BorderLayout.NORTH);
		add(p1,BorderLayout.CENTER);
		add(p2,BorderLayout.EAST);
		
		submit.addActionListener(new ActionListener() {			
			
			public void actionPerformed(ActionEvent arg0) {	
				//JOptionPane.showMessageDialog(null,"Payment successful!");
				
				client.sendData(new User(nametf.getText(), Integer.parseInt(sidtf.getText()), c1.getSelectedItem().toString()));
				
				String s;
				try {
				 s = (String)client.readData();
				}catch (Exception e) {
					// TODO: handle exception
					s= "some error occured";
				}
				
				JOptionPane.showMessageDialog(payment_GUI.this,s);   
				submit.setEnabled(false);
			}
		});
		repaint();
		revalidate();
		setVisible(true);
	}

}
