package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dataAccess.RegisteredAdapter;
import domain.Registered;

public class WindowTable extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Registered user;
	private JTable tabla;
	
	public WindowTable(Registered usr) {
		super("Apuestas	realizadas por	"+	usr.getUsername()+":");	
		this.setBounds(100,	100, 700, 200);
		this.user = usr;
		RegisteredAdapter adapt = new RegisteredAdapter(user);
		tabla =	new	JTable(adapt);
		tabla.setPreferredScrollableViewportSize(new Dimension (500, 700));
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		adapt.imprimirApuestas();
		
	}

}
