package cap00.ejercicios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;


public class PruebaJLabelGrafico extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PruebaJLabelGrafico vent = new PruebaJLabelGrafico();
		vent.setVisible(true);
		vent.mover();
	}
	
	//No static
	private JPanel pJuego;
	private JLabel lCoche;
	
	public PruebaJLabelGrafico() {
		//Configuracion de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600,400);
		
		//Creamos los contenedores
		pJuego = new JPanel();
		pJuego.setLayout(null);
		
		//Creamos los componentes
//		lCoche = new JLabel(new ImageIcon("src/tema0/coche.png"));
		lCoche = new JLabel(new ImageIcon(PruebaJLabelGrafico.class.getResource("coche.png")));
		
		//Asociamos componentes a contenedores
		pJuego.add(lCoche);
		getContentPane().add(pJuego, BorderLayout.CENTER);
		
		
		//Hacer algo con el coche
//		lCoche.setSize(100,100);
		
		
	}
	void mover() {
		lCoche.setBounds(100, 0, 100, 100);
		for(int i = 0; i<100; i++) {
			lCoche.setLocation(lCoche.getX()+1, lCoche.getY()+1);
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
}
