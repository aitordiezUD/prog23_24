package cap00.ejercicios;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Ej06SegundaParte extends JFrame{
	DefaultTableModel modelo;
	JTable tabla;	
	
	public static void main(String[] args) {
		new Ej06SegundaParte();
	}
	
	@SuppressWarnings("serial")
	public Ej06SegundaParte() {
		setTitle("Ejercicio 06");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		modelo = new DefaultTableModel(new Object[] {"Nombre","Alta","Rango"},0);
		modelo.addRow(new Object[] {"Aitor","2020","20"});
		modelo.addRow(new Object[] {"Eguiluz","2018","34"});
		modelo.addRow(new Object[] {"Peña","2021","3"});
		modelo.addRow(new Object[] {"Mateo","2015","2"});
		
		
		tabla = new JTable(modelo);
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component comp  = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 2) {
					int valor = Integer.parseInt(value.toString());
					comp= new JProgressBar(0, 100);
					((JProgressBar)comp).setValue(valor);
				}
				
				return comp;
			}
			
			
			
		});
		
		tabla.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
			Component comp;
			int valorAnt;
			JTextField tf;
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
					int column) {
				
				// TODO Auto-generated method stub
				comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
				tf = (JTextField)comp;
				if (column == 1){
					tf = null;
					int valor = Integer.parseInt(value.toString());
					comp = new JSpinner(new SpinnerNumberModel(valor, 0, 2023, 1));
					((JSpinner) comp).addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							valorAnt = (int) ((JSpinner)comp).getValue();
						}
					});
				}
				
				return comp;
			}

			@SuppressWarnings("removal")
			@Override
			public Object getCellEditorValue() {
				// TODO Auto-generated method stub
				if (tf != null) {
					return tf.getText();
				}else {
					return new Integer(valorAnt);
				}
			}
			
			
		});

		
		
		this.getContentPane().add(new JScrollPane(tabla),BorderLayout.CENTER);
		setVisible(true);
	};
}
