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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.Seminar;
import com.User;

import serverCode.Client;

public class new_registration_GUI extends JPanel {
	JTextField nametf, mobileNotf, emailIdtf, sidtf, qualificationstf;
	
	JLabel name, mobileNo, emailId, sid, qualifications;
	
	JButton submit;
	 
	JSpinner spin;
	
	DefaultTableModel model;
	
	JTable t1;
	
	JPanel p1,p2;
	
	JComboBox c1;
	Image ic;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(ic, 0,0,this);
	}
	
	public  new_registration_GUI() {
		
		final Client client = Client.getInstance();

		
		setSize(700, 550);;
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		setLayout(new BorderLayout());
		repaint();
		name = new JLabel("Enter name:");
		nametf = new JTextField(20);
		mobileNo = new JLabel("Enter mobile number:"); 
		mobileNotf = new JTextField(20);	
		sid = new JLabel("Select seminar ID:");	
		sidtf = new JTextField(20);
		sidtf.setEnabled(false);
		emailId = new JLabel("Enter email ID:");
		emailIdtf = new JTextField(20);
		qualifications = new JLabel("Select qualification:");
		qualificationstf = new JTextField(20);
		
				
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
               
        p1 = new JPanel();
        p1.setVisible(true);
        p2 = new JPanel(); 
        p2.setLayout(new GridBagLayout());
        p2.setOpaque(false);
       
     //   p2.setVisible(false);
        
        String s1[] = { "Choose qualification", "Post graduate", "Graduate", "other"};  
        c1 = new JComboBox(s1);
        
        try {            
            Vector<Seminar> Rs = (Vector<Seminar>) client.readData();
            Iterator<Seminar> itr = Rs.iterator();	
            System.out.println(Rs);
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
                p2.setVisible(true);                
                revalidate();                 
            }
        });
        
        JScrollPane pg = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pg.setPreferredSize(new Dimension(getWidth()-50, 100));
        p1.add(pg);
        p1.setOpaque(false);
		
        add(p1, BorderLayout.NORTH);
		
        GridBagConstraints c = new GridBagConstraints();
        c.fill=GridBagConstraints.HORIZONTAL;
        c.weightx=0.5;
        c.ipady=30;
        c.gridx=0; c.gridy=0;	p2.add(name,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1; c.gridy=0;	p2.add(nametf,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0; c.gridy=1;	p2.add(mobileNo,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1; c.gridy=1;	p2.add(mobileNotf,c);
        c.gridx=0; c.gridy=2;	p2.add(emailId,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1; c.gridy=2;	p2.add(emailIdtf,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0; c.gridy=3; 	p2.add(sid,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1; c.gridy=3;	p2.add(sidtf,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0; c.gridy=4;	p2.add(qualifications,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=1; c.gridy=4;	p2.add(c1,c);
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0; c.gridy=6;	p2.add(submit,c);
        
        p2.setOpaque(false);       
		add(p2,BorderLayout.CENTER);
		repaint();
		submit.addActionListener(new ActionListener() {			
			
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(nametf.getText().toString());
				client.sendData(new User(nametf.getText().toString(), mobileNotf.getText().toString(),
						emailIdtf.getText().toString(), Integer.parseInt(sidtf.getText().toString()),
						c1.getSelectedIndex()));	
			}
		});
		
		setVisible(true);
		 p2.revalidate();
		 revalidate();
	}
	
}
