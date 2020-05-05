package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.Seminar;

import serverCode.Client;

public class add_seminar_GUI extends JPanel{
	
	JTextField nametf, domaintf, durationtf, capacitytf, datetf, pricetf;
			
	JLabel name, domain, duration, capacity, date, price;
	
	JButton submit;
	 
	JSpinner spin;
	
	JPanel p1;
	
	Image ic;
	
	Client client;
	public add_seminar_GUI(final Client_GUI c) {
		
		setSize(700, 550);
		ic = new ImageIcon(this.getClass().getResource("/back2.jpeg")).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT);
		JLabel back = new JLabel();
		back.setIcon(new ImageIcon(ic));
		setBackground(new Color(255,255,255));
		setLayout(new GridLayout(12,2));
		System.out.println("before init");
		client = Client.getInstance();
		System.out.println("after init");
		name = new JLabel("Enter name:");
		nametf = new JTextField(); nametf.setOpaque(false);
		domain = new JLabel("Enter domain:");
		domaintf = new JTextField(); domaintf.setOpaque(false);
		duration = new JLabel("Enter duration:");
		durationtf = new JTextField(); durationtf.setOpaque(false);
		capacity = new JLabel("Enter capacity:");
		capacitytf = new JTextField(); capacitytf.setOpaque(false);
		date = new JLabel("Enter date:");
		datetf = new JTextField(); datetf.setOpaque(false);
		price = new JLabel("Enter price:");
		pricetf = new JTextField(); pricetf.setOpaque(false);
				
		submit = new JButton("submit");
		
	
		//add(back);
		repaint();
		add(name);			add(nametf);
		add(domain);		add(domaintf);
		add(duration);		add(durationtf);
		add(capacity);		add(capacitytf);
		add(date);			add(datetf);
		add(price);			add(pricetf);
		add(submit);
		
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date d = null;
				try {
					d = dt.parse(datetf.getText().toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				Date date = new Date(d.getYear(), d.getMonth(), d.getDate());				
				
				client.sendData(new Seminar(nametf.getText().toString(), domaintf.getText().toString(),
						Float.parseFloat(durationtf.getText().toString()), Integer.parseInt(capacitytf.getText().toString()),
						Float.parseFloat(pricetf.getText().toString()), date));
				System.out.println(nametf.getText().toString() + "sent!!!");
				
				int id = (int)client.readData();
				add_seminar_GUI.this.setVisible(false);
				
				c.p2.setVisible(false);
				c.p2 = new add_speaker(id);
				c.add(c.p2, BorderLayout.CENTER);
				c.p2.setVisible(true);
								
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
