package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.Speaker;
import com.User;

import serverCode.Client;

public class add_speaker extends JPanel {
	JTextField nametf, domaintf, durationtf, capacitytf, datetf, pricetf;
	
	JLabel name, domain, duration, capacity, date, price;
	
	JButton submit;
	 
	JSpinner spin;
	
	JPanel p1;
	
	Image ic;
	

	
	Client client;
	
	public add_speaker(final int id) {
		// TODO Auto-generated constructor stub
	
		
		setSize(700, 550);
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		JLabel back = new JLabel();
		back.setIcon(new ImageIcon(ic));
		setLayout(new GridLayout(12,2));
		client = Client.getInstance();
		name = new JLabel("Enter name:");
		nametf = new JTextField(); nametf.setOpaque(false);
		domain = new JLabel("Enter years of experience in this domain:");
		domaintf = new JTextField(); domaintf.setOpaque(false);
		duration = new JLabel("Enter email id:");
		durationtf = new JTextField(); durationtf.setOpaque(false);
		capacity = new JLabel("Enter Highest qualification:");
		capacitytf = new JTextField(); capacitytf.setOpaque(false);
		date = new JLabel("Enter current workplace:");
		datetf = new JTextField(); datetf.setOpaque(false);
		
				
		submit = new JButton("submit");
		
	
		//add(back);
		repaint();
		add(name);			add(nametf);
		add(duration);		add(durationtf);
		add(domain);		add(domaintf);		
		add(capacity);		add(capacitytf);
		add(date);			add(datetf);
		add(submit);
		
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				client.sendData(new Speaker(nametf.getText().toString(), 
						capacitytf.getText().toString(),
						durationtf.getText().toString(),											
						datetf.getText().toString(),
						id,
						Integer.parseInt(domaintf.getText().toString())));
				//add_speaker.this.setVisible(false);	
				submit.setEnabled(false);				
								
			}
		});
		
		setVisible(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(ic, 0,0,null);
	}
	

}
