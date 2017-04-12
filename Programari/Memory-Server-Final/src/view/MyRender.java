package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyRender extends DefaultTableCellRenderer implements TableCellRenderer 
{ 
	
	private boolean bloqued;
	 public MyRender() 
	    {
	        super();
	        setOpaque(true);
	        this.bloqued = false;
	    } 
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
    {
    	if(bloqued==false){
    		setText(value.toString());
    		setBackground(Color.white);
    	}else{
    		setBackground(Color.lightGray);            
    	}
    	return this;
    }
    
    public void setBloqued(boolean bloqued){
    	this.bloqued = bloqued;
    }   

}
