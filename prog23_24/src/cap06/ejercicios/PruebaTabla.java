package cap06.ejercicios;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class PruebaTabla extends JFrame{
	DefaultTableModel modelo;
	JTable tabla;
	
	public PruebaTabla() {
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		modelo = new DefaultTableModel();
		tabla = new JTable(modelo);
		
		modelo.addColumn("Strings");
		modelo.addColumn("Integers");
		
		modelo.addRow(new Object[] {"abc",1});
		modelo.addRow(new Object[] {"def",2});
		add(new JScrollPane(tabla));
		
		tabla.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				comp.setBackground(Color.LIGHT_GRAY);
				if(column == 1) {
					JProgressBar pb = new JProgressBar(0,100);
					pb.setValue(50);
					pb.setForeground(Color.RED);
					return pb;
				}
				
				return comp;
			}
			
		});
		
		tabla.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
			
			JTextField tf;
			JSlider js;
			boolean col1;
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				// TODO Auto-generated method stub
				Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
				col1=false;
				if (column == 1) {
					js = new JSlider(0,10);
					col1=true;
					return js;
				}
				return comp;
			}
			
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				
				if (!col1) {
					return super.getCellEditorValue();
				}else {
					return js.getValue();
				}

			}

			
			
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PruebaTabla();
	}
}
