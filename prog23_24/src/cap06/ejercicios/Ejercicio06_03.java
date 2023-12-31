package cap06.ejercicios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ejercicio06_03 {
	private static VentanaTablaDatos ventanaDatos;
	private static JFrame ventana;
	private static DataSetMunicipios dataset;

	public static void main(String[] args) {
		ventana = new JFrame( "Ejercicio 6.3" );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana.setLocationRelativeTo( null );
		ventana.setSize( 200, 80 );
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new BorderLayout());
		ventana.getContentPane().add(pnlPrincipal);
		
		
		
		JButton bCargaMunicipios = new JButton( "Carga municipios > 200k" );
		ventana.add( bCargaMunicipios );
		
		bCargaMunicipios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargaMunicipios();
			}
		});
		
		ventana.setVisible( true );
	}
	
	private static void cargaMunicipios() {
		try {
			dataset = new DataSetMunicipios( "municipios200k.txt" );
			System.out.println( "Cargados municipios:" );
			for (Municipio m : dataset.getListaMunicipios() ) {
				System.out.println( "\t" + m );
			}
			// TODO Resolver el ejercicio 6.3
		} catch (IOException e) {
			System.err.println( "Error en carga de municipios" );
		}
		ventanaDatos = new VentanaTablaDatos(ventana);
		ventanaDatos.setDatos(dataset);
		ventanaDatos.setVisible(true);
	}
	
}
