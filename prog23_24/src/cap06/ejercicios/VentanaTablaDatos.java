package cap06.ejercicios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class VentanaTablaDatos extends JFrame{
	private JTable tablaDatos;
	private MiTableModel modeloDatos;
	private DataSetMunicipios datosMunis;

	public VentanaTablaDatos(JFrame ventOrigen) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		
		
		tablaDatos = new JTable();
		add( new JScrollPane(tablaDatos), BorderLayout.CENTER);
		
		JPanel pInferior = new JPanel();
		JButton bAnyadir = new JButton("Añadir");
		JButton bBorrar = new JButton("Borrar");
		pInferior.add(bAnyadir);
		pInferior.add(bBorrar);
		
//		Paso 5:
		bBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int filaSel = tablaDatos.getSelectedRow();
				if (filaSel >= 0) {
					datosMunis.quitar(datosMunis.getListaMunicipios().get(filaSel).getCodigo());
					modeloDatos.borrarFila(filaSel);
				}
			}
			
		});
		
		add(pInferior, BorderLayout.SOUTH);
		
//		Paso 2
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				ventOrigen.setVisible(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				ventOrigen.setVisible(true);
			}
			
		});
	}
	

	
	
	public void setDatos(DataSetMunicipios dataSet) {
//		System.out.println(dataSet.getListaMunicipios());
		datosMunis = dataSet;
		modeloDatos = new MiTableModel();
		tablaDatos.setModel(modeloDatos);
		
//		Paso 4
		TableColumn col = tablaDatos.getColumnModel().getColumn(0);
		col.setMaxWidth(50);
		col = tablaDatos.getColumnModel().getColumn(2);
		col.setMinWidth(150);
		col.setMaxWidth(150);
		
//		Paso 7
		tablaDatos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == 2) {
					JProgressBar jb = new JProgressBar((int) value, 100000, 5000000);
					return jb;
//					return new JCheckBox("prueba");
				}
				return comp;
			}
			
		});
		
	}
	
	private class MiTableModel implements TableModel {
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return String.class;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return datosMunis.getListaMunicipios().size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
		
		private static final String[] cabeceras = {"Codigo","Nombre","Habitantes","Provincia","Autonomía"};
		
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			return cabeceras[columnIndex];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			if (columnIndex == 0) {
				return false;
			}
			return true;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				return datosMunis.getListaMunicipios().get(rowIndex).getCodigo();
			case 1:
				return datosMunis.getListaMunicipios().get(rowIndex).getNombre();
			case 2:
				return datosMunis.getListaMunicipios().get(rowIndex).getHabitantes();
			case 3:
				return datosMunis.getListaMunicipios().get(rowIndex).getProvincia();
			case 4:
				return datosMunis.getListaMunicipios().get(rowIndex).getAutonomia();
			default:
				return null;
			}
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			switch (columnIndex) {
			case 0:
				datosMunis.getListaMunicipios().get(rowIndex).setCodigo((Integer) aValue);
				break;
			case 1:
				datosMunis.getListaMunicipios().get(rowIndex).setNombre((String) aValue);
				break;
			case 2:
				try {
					datosMunis.getListaMunicipios().get(rowIndex).setHabitantes(Integer.parseInt((String) aValue));
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(VentanaTablaDatos.this, "Numero de habitantes erróneo");
				}
				
				break;
			case 3:
				datosMunis.getListaMunicipios().get(rowIndex).setProvincia((String) aValue);
				break;
			case 4:
				datosMunis.getListaMunicipios().get(rowIndex).setAutonomia((String) aValue);
				break;
			}
		}

//		Paso 5
		ArrayList<TableModelListener> listaEsc = new ArrayList<>();
		
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			listaEsc.add(l);
		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			listaEsc.remove(l);
		}
		
		public void fireTableChanged( TableModelEvent e) {
			for (TableModelListener l: listaEsc) {
				l.tableChanged(e);
			}
		}
		
		public void borrarFila(int fila) {
			fireTableChanged(new TableModelEvent(modeloDatos,fila,datosMunis.getListaMunicipios().size()));
		}
	}
}
