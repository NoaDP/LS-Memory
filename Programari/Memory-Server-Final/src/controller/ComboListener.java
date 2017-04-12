package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import view.MainWindow;

public class ComboListener implements ActionListener{

	
	private MainWindow vista;
	private ButtonController controler;
	
	public ComboListener(MainWindow vista,ButtonController controler){
		this.controler = controler;
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox comboBox = (JComboBox)e.getSource();
		String nickname = (String)comboBox.getSelectedItem();
		
		if(nickname!=null){
			ArrayList<String> memoria = controler.selectGraficMemoria(nickname);
			ArrayList<String> concentracio = controler.selectGraficConcentracio(nickname);
			controler.setPuntsGrafica(memoria,concentracio,false);
		}
	}

}
