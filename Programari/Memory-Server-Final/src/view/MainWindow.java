package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import controller.ButtonController;
import controller.ComboListener;
import controller.MousePopupListener;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame{
	
	/**
	 * La classe MainWindow és la encarregada de crear i modificar els elements que componen la finestra gràfica que és el
	 * menú principal del menú principal del nostre programa. Aquest s'encarregarà de mostrar les diferents opcions com a pestanyes.
	 */
	private JButton Inserir;
	private JButton Registrar;
	private JButton Memoria;
	private JButton Concentracio;
	
	private JTextArea jtaList2;
	private JTextArea jtaList3;
	
	private JPanel topVisualitza;
	private JPanel botVisualitza;
	private JPanel jp;
	private JPanel topTop;
	private JPanel hi;
	private JPanel md;
	private JPanel jbot;
	
	private JLabel horaInici;
	private JLabel durada;
	private JLabel iniciCompeticio;
	private JLabel temps;
	private JLabel min2;
	private JLabel tempsCompeticio;
	private JLabel restants;
	private JLabel partides;
	private JLabel jugador;
	boolean primera = false;
	
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	
	private JTextField horaI;
	private JTextField minutsI;
	private JTextField segonsI;
	private JTextField minutsD;
	private JTextField segonsD;
	
	private JTabbedPane CRS;
	
	private JComboBox nameList;

	private JScrollPane scrollPane;
	private JScrollPane scrollBigPane;
	
	private DefaultTableModel tableModel = new DefaultTableModel();
	private JTable table = new JTable(tableModel);
	private MyRender myRender = new MyRender();
	private DefaultTableModel tableModel2 = new DefaultTableModel();
	private JTable resultTable = new JTable(tableModel2);
	
	private String[] noms;
//	private MousePopupListener mousePopup;
	private classMouseAdapter mouseAdapter;
	private ComboListener comboListener;
	
	private ArrayList<String> memoria = new ArrayList<String>();
	private ArrayList<String> concentracio = new ArrayList<String>();
	
	private grafphicView grafic;
	
	private JLabel numPartides;
	private JLabel jugadorUno;
	
	public MainWindow(){
		CRS = new JTabbedPane();
		
		//creem el panel
		JPanel jpRegister = new JPanel();
		jpRegister.setLayout(new GridLayout(2, 1));
		
		//Editem el panel top
		topVisualitza = new JPanel(new GridLayout(2,1));
		topVisualitza.setBorder(BorderFactory.createLineBorder(Color.black));
		
		Inserir = new JButton("Inserir");
		Inserir.setPreferredSize(new Dimension(20,45));
		topTop = new JPanel(new MigLayout("","150[][][][]","[]10[]"));
		topTop.setBackground(Color.white);
		
		horaInici = new JLabel("Hora de Inici");
		durada = new JLabel("Minuts de Durada");
		iniciCompeticio = new JLabel("Inici de la competici�:");
		
		text2 = new JTextField(10);
		
		jp = new JPanel(new MigLayout("","20[]50[]","[][]"));
		jp.setBackground(Color.white);
		jp.add(iniciCompeticio);

		temps= new JLabel("00:00:00");
		temps.setFont(new Font("",Font.BOLD,50));
		
		horaI = new JTextField(2);
		minutsI = new JTextField(2);
		segonsI = new JTextField(2);
		minutsD = new JTextField(2);
		segonsD = new JTextField(2);
		
		hi = new JPanel(new FlowLayout());
		hi.add(horaI);
		hi.add(new JLabel(":"));
		hi.add(minutsI);
		hi.add(new JLabel(":"));
		hi.add(segonsI);
		hi.setBackground(Color.white);
		
		md = new JPanel(new FlowLayout());
		md.add(minutsD);
		md.add(new JLabel(":"));
		md.add(segonsD);
		md.setBackground(Color.white);
		
		
		jp.add(temps,"span 3");
		topTop.add(horaInici);
		topTop.add(hi);
		topTop.add(Inserir, "span 1 2");
		topTop.add(new JLabel(""),"wrap");
		topTop.add(durada);
		topTop.add(md);
				
		topVisualitza.add(topTop);
		topVisualitza.add(jp);
		jpRegister.add(topVisualitza);
		
		//editem el panell d'abaix
		botVisualitza = new JPanel(new MigLayout("","20[]50[]50[]","30[]50[]20[]"));
		botVisualitza.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		tempsCompeticio = new JLabel("Temps de Competicio Restant: ");
		restants = new JLabel("Restants");
		partides = new JLabel ("Partides Realitzades: ");
		numPartides = new JLabel("0");
		jugador = new JLabel("Jugador N�1:" );
		min2 = new JLabel("00:00:00");
		min2.setFont(new Font("",Font.BOLD,50));
		jugadorUno = new JLabel("");

		botVisualitza.add(tempsCompeticio);
		botVisualitza.add(min2,"wrap");
		botVisualitza.add(partides);
		botVisualitza.add(numPartides,"wrap");
		botVisualitza.add(jugador);
		botVisualitza.add(jugadorUno);
		botVisualitza.setBackground(Color.lightGray);
		jpRegister.add(botVisualitza);
				
		CRS.addTab("Configurar i Visualitzar Competicio", jpRegister);
		
	
		// panell dos aqui fem el registrar
		JPanel jpDelete= new JPanel(new GridBagLayout());
		JPanel jtop = new JPanel(new MigLayout("","20[][]","10[]10[]10[]"));
		jbot = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		jtop.setBorder(BorderFactory.createLineBorder(Color.black));
		jbot.setBorder(BorderFactory.createLineBorder(Color.black));
		
		text3 = new JTextField(10);
		text4 = new JTextField(10);
		text5 = new JTextField(10);
		Registrar = new JButton("Registrar");
		JPanel jpButton = new JPanel(new GridLayout(1,2));
		jpButton.add(new JLabel(" "));
		jpButton.add(Registrar);
		
		JLabel registrar = new JLabel("       Registrar:");
		registrar.setHorizontalAlignment(JLabel.CENTER);
		registrar.setFont(new Font("Registrar",Font.BOLD,20));
		JLabel nickname = new JLabel("Nickname:");
		JLabel password = new JLabel("Password:");
		
	    jtop.add(registrar,"dock north");
	    jtop.add(new JLabel("Nom: "));
	    jtop.add(text5);
	    jtop.add(nickname);
	    jtop.add(text3);
	    jtop.add(password);
	    jtop.add(text4,"wrap");
	    jtop.add(jpButton,"dock south");
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx=0.5;
	    c.ipady=30;
	    c.gridwidth=2;
	    c.gridx = 1;
	    c.gridy = 0;
		jpDelete.add(jtop,c);
		
		tableModel.addColumn("Nickname");
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Blocked");
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		//table.setDefaultRenderer(Object.class, myRender);
		mouseAdapter = new classMouseAdapter(table, this);
		table.addMouseListener(mouseAdapter);
	//	mouseAdapter.setMousePopup(mousePopup);

		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		jbot.add(scrollPane);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 285;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		jpDelete.add(jbot, c);
		
		CRS.addTab("Gestionar Usiaris", jpDelete);
		
		//AQUI SE ACABA!!!
		
		//panell tres - List Puntuacio total
		JPanel jpClassificacio = new JPanel(new BorderLayout());

		tableModel2.addColumn("Nickname");
		tableModel2.addColumn("Total Points");
		resultTable.setFillsViewportHeight(true);
		resultTable.setEnabled(false);

		
		scrollBigPane = new JScrollPane(resultTable);
		scrollBigPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBigPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollBigPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		jpClassificacio.add(scrollBigPane);
		
		CRS.addTab("Mostrar Classificaci�", jpClassificacio);
				
		//panell tres - List cars	
		//GRAFIC LO DEMES ESTA EN graphicView
		JPanel Grafic = new JPanel(new BorderLayout());
		grafic = new grafphicView(memoria,concentracio);
		Grafic.add(grafic, BorderLayout.CENTER);
		Grafic.setBackground(Color.white);
		String[] noms = {"Clientes"};
		nameList = new JComboBox(noms);
		nameList.addActionListener(comboListener);
		//nameList.setSelectedIndex(4);
		JPanel jpNorth = new JPanel(new GridLayout(2,1));
		jpNorth.add(nameList);
		Memoria = new JButton("Memoria");
		Concentracio = new JButton("Concentracio");
		JPanel jpEast = new JPanel(new GridLayout(1,2));
		jpEast.add(Memoria);
		jpEast.add(Concentracio);
		jpNorth.add(jpEast);
		Grafic.add(jpNorth, BorderLayout.NORTH);

				
		CRS.addTab("Mostrar Grafic", Grafic);
		CRS.setEnabledAt(1, false);
		CRS.setEnabledAt(2, false);
		CRS.setEnabledAt(3, false);
					

		this.getContentPane().add(CRS);
		
		this.setSize(610, 500);
		this.setTitle("MemoTournament - **SERVER**");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	/**
	 * El procediment InformationController s'encarrega d'assignar l'etiqueta "Information" i un listener al botó jbInformation, 
	 * per tal de que, en cas de que l'usuari el premi, es mostri una finestra gràfica amb la informació relativa a la puntuació.
	 * @param cB: paràmetre del tipus ButtonController que permetrà gestionar les accions dels diferents botons de la finestra.
	 */
	public void registreControladorBotons(ButtonController cB){
		Inserir.addActionListener(cB);
		Registrar.addActionListener(cB);
		Memoria.addActionListener(cB);
		Concentracio.addActionListener(cB);
		
	}
	
	public void setTempsInici(Date temps){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		this.temps.setText(dateFormat.format(temps));
	}
	
	public void setTempsDurada(Date temps){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		this.min2.setText(dateFormat.format(temps));
		this.jp.setBackground(Color.lightGray);
		this.topTop.setBackground(Color.lightGray);
		this.botVisualitza.setBackground(Color.white);
		this.md.setBackground(Color.lightGray);
		this.hi.setBackground(Color.lightGray);
		this.horaI.setBackground(Color.lightGray);
		this.minutsI.setBackground(Color.lightGray);
		this.segonsI.setBackground(Color.lightGray);
		this.minutsD.setBackground(Color.lightGray);
		this.segonsD.setBackground(Color.lightGray);

	}
	
	/**
	 * Aquest procediment ens permet bloquejar l'accés al botó inserir i als camps de registre de la hora d'inici i la durada
	 * i activa la resta de pestanyes del menú.
	 */
	public void enableBotoInserir(){
		this.Inserir.setEnabled(false);
		this.horaI.setText("");
		this.horaI.setEditable(false);
		this.minutsI.setText("");
		this.minutsI.setEditable(false);
		this.segonsI.setText("");
		this.segonsI.setEditable(false);
		this.minutsD.setText("");
		this.minutsD.setEditable(false);
		this.segonsD.setText("");
		this.segonsD.setEditable(false);
		this.CRS.setEnabledAt(1, true);
		this.CRS.setEnabledAt(2, true);
		this.CRS.setEnabledAt(3, true);

	}
	
	public String getNickname(){
		return text3.getText().toString();
	}
	
	public String getContrasena(){
		return text4.getText().toString();
	}
	
	public String getNom(){
		return text5.getText().toString();
	}
	
	/**
	 * La funció horaIniciMinusDurada ens permet emmagatzemar la informació inserida per l'usuari que defineix l'hora 
	 * d'inici de la competició i quant durarà aquesta.
	 * @return: String que conté la informació de la hora d'inici de la competició i de la durada que tindrà aquesta
	 */
	public String[] horaIniciMinutsDurada(){
		String[] s = new String[5];
		
		s[0]=horaI.getText();
		s[1]=minutsI.getText();
		s[2]=segonsI.getText();
		s[3]=minutsD.getText();
		s[4]=segonsD.getText();
		
		return s;

	}
	
	/**
	 * Aquest procediment ens permet activar la part de la finestra principal que ens permet gestionar l'hora de inici del torneig.
	 */
	public void enableWindows(){
		this.Inserir.setEnabled(true);
		this.horaI.setEditable(true);
		this.minutsD.setEditable(true);
		this.minutsI.setEditable(true);
		this.segonsD.setEditable(true);
		this.segonsI.setEditable(true);
		this.horaI.setBackground(Color.white);
		this.minutsI.setBackground(Color.white);
		this.segonsI.setBackground(Color.white);
		this.minutsD.setBackground(Color.white);
		this.segonsD.setBackground(Color.white);
		this.jp.setBackground(Color.white);
		this.topTop.setBackground(Color.white);
		this.botVisualitza.setBackground(Color.lightGray);
		this.md.setBackground(Color.white);
		this.hi.setBackground(Color.white);
	}
	
	/**
	 * El procediment cleanRegister com el seu nom indica ens permet netejar els camps de text que permeten inserir la 
	 * hora de inici de la competició.
	 */
	public void cleanRegister(){
		this.text5.setText("");
		this.text4.setText("");
		this.text3.setText("");
	}
	
	/**
	 * setDataTableRegister ens permet afegir una nova fila a la taula de la opció de ranking
	 * @param result: variable que conté la informació d'una fila de la taula a mostrar.
	 */
	public void setDataTableRegister(Object[] result){
		tableModel.addRow(result);		
	}
	
	/**
	 * El procediment resetTableRefister permet resetejar la taula que es mostra a la opció de ranking del menú.
	 */
	public void resetTableRegister(){
		tableModel.setRowCount(0);
	}
		

	/**
	 *  Procediment que permet canviar l'estat de l'usuar de la taula seleccionat de  no bloquejat a bloquejat.
	 */
	public void bloquearSelectedRow(){
		tableModel.setValueAt("Bloquejat", table.getSelectedRow(), 2);		
	}
	
	/**
	 * Procediment que permet canviar l'estat de l'usuar de la taula seleccionat de bloquejat a no bloquejat.
	 */
	public void desBloquearSelectedRow(){
		tableModel.setValueAt("No Bloquejat", table.getSelectedRow(), 2);		
	}
	
	/**
	 * Aquest funció s'encarrega obtneir el valor de la taula que defineix si l'usuari al que pertany està bloquejato o no.
	 * @return boolea que informav si l'usuari està bloquejat o no.
	 */
	public boolean isBlocked(){
		if("No Bloquejat".equals(tableModel.getValueAt(table.getSelectedRow(), 2))){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Aquesta funció s'encarrega de retornar una String amb el Nickname de l'usuari seleccionat prèviament.
	 * @return: String amb el nickname de l'usuari seleccionat.
	 */
	public String getTableNickname(){
		return (String) table.getValueAt(table.getSelectedRow(), 0);
	}
	
	/**
	 * Aquest procediment s'encarrega d'eliminar de la taula una de les files, la qual ha estat seleccionada prèviament. 
	 */
	public void deleteSelectedRow(){
		tableModel.removeRow(table.getSelectedRow());
	}
	
	/**
	 *Aquest procediment s'encarrega de colocar els valors de puntuació de la llista a 0. 
	 */
	public void resetTablePoints(){
		tableModel2.setRowCount(0);
	}
	
	public void setDataTablePoints(Object[] result){
		tableModel2.addRow(result);	
	}
	
	public void actualizarJugador(){
		this.jugadorUno.setText(tableModel2.getValueAt(0, 0).toString());
	}
	
	public void setNoms(String[] noms){
		this.noms = noms;
		this.nameList.removeAllItems();
		for(int i=0;i<noms.length;i++){
			this.nameList.addItem(noms[i]);
		}
	}
	
	public String getNicknameTable(){
		return tableModel.getValueAt(table.getSelectedRow(), 0).toString();
	}
	
	public void setExternalListeners(MousePopupListener mousePopup,ComboListener comboListener){
		this.mouseAdapter.setMousePopup(mousePopup);
		this.comboListener = comboListener;
		this.nameList.addActionListener(comboListener);
	}
	
	public JTable getTablaClassificacio(){
		return resultTable;
	}
	
	public ArrayList<String> getMemoria() {
		return memoria;
	}


	public void setMemoria(ArrayList<String> memoria) {
		this.memoria = memoria;
	}


	public ArrayList<String> getConcentracio() {
		return concentracio;
	}


	public void setConcentracio(ArrayList<String> concentracio) {
		this.concentracio = concentracio;
	}
	
	public void cambiarGrafic(ArrayList<String> memoria){
		this.grafic.setMemoria(memoria);
		this.grafic.repaint();
	}
		
	public String getNicknameComboBox(){
		return (String)nameList.getSelectedItem();
	}
	
	public JLabel getNumPartides(){
		return numPartides;
	}
	public void setNumPartides(int numPartides){
		this.numPartides.setText(Integer.toString(numPartides));
	}
	
}
