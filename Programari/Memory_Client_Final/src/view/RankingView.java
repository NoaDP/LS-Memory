package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class RankingView extends JFrame{
	private JPanel content;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable table = new JTable(tableModel);
	
	public RankingView(){
		content = new JPanel();
		tableModel.addColumn("Nickname");
		tableModel.addColumn("Puntuacio Total");
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		content.add(scrollPane);
		this.setBackground(Color.white);
		this.add(content);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setTitle("**Ranking**");
		this.setVisible(true);
	}
	
	public void setDataTableRegister(Object[] result){
		tableModel.addRow(result);		
	}
	
	public void resetTableRegister(){
		tableModel.setRowCount(0);
	}
	
}
