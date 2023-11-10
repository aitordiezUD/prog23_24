package escribePersonas;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class CSVescribirLeerPersonas {
	public static void main(String[] args) {
		ArrayList<Personas> p = new ArrayList<Personas>();
		p.add(new Personas(72834331, "Aitor", 15));
		p.add(new Personas(34763874, "Pablo",12));
		
		File f  = new File("personas2.csv");
		FileWriter fw = null;
		PrintWriter pw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		
		try {
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			for (int i = 0; i<p.size(); i++) {
				pw.println(p.get(i).getDni() + ";" + p.get(i).getEdad() + ";" + p.get(i).getNombre());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String s = br.readLine();
			while(s!=null) {
				System.out.println(s);
				s=br.readLine();
			}
		}catch(Exception e) {
			
		}finally {
			try {
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		File f2 = new File("PersonasPrueba.dat");
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(f2);
			oos = new ObjectOutputStream(fos);
			
			for (int i = 0;i<p.size();i++) {
				oos.writeObject(p.get(i));
			}
		}catch (Exception e) {
			System.out.println("Error escribiendo");
		}
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(f2);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			System.out.println("Empezando a leer archivo binario");
			while (o!= null) {
				Personas pers = (Personas) o;
				System.out.println(pers);
				System.out.println(ois.readObject());
				try {
					o = ois.readObject();
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception Finalizado");
					break;
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
		
		
		
	}
	
}
