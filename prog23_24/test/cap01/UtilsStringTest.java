package cap01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UtilsStringTest {

	@Test
	public void testWrapString() {
		String prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
		assertEquals("Comprobacion wrapString","Hol...", UtilsString.wrapString( prueba, 3));
//		if ("Hol...".equals(UtilsString.wrapString( prueba, 3))) {
//			System.out.println( "OK" );
//		} else {
//			System.out.println( "FAIL" );
//		}
		assertEquals("",UtilsString.wrapString( "", 1));
		assertEquals("abc...",UtilsString.wrapString( "abcdefg", 3));
		assertEquals("abcdef...",UtilsString.wrapString( "abcdefg", 6));
		assertEquals("abcdefg",UtilsString.wrapString( "abcdefg", 7));
	}
	
	@Test
	public void testWrapStringNegativo() {
		try {
			UtilsString.wrapString( "abcde", -5);
			fail("No provocada la excepción");
		} catch (Exception e) {
			// TODO: handle exception
//			NADA EXPLICITO - ACABAR ES QUE FUNCIONA
		}
	}
	
//	OTRA MANERA DE PROBAR EXCEPCIONES
	@Test (expected = IndexOutOfBoundsException.class)
	public void testWrapStringNegativo2() {
		UtilsString.wrapString("abcde" , -5);
	}
	
	
	@Test
	public void testQuitarTabsYSaltosLinea() {
		String prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores.";
		String prueba2 = "Hola#Esto es un string con tres líneas#y|varios|tabuladores.";
		assertEquals(prueba2, UtilsString.quitarTabsYSaltosLinea(prueba));
//		if (prueba2.equals(UtilsString.quitarTabsYSaltosLinea(prueba))) {
//			System.out.println( "OK" );
//		} else {
//			System.out.println( "FAIL" );
//			fail("Error en quitartabysaltoslinea");
//		}
		
		assertEquals("", UtilsString.quitarTabsYSaltosLinea(""));
		assertEquals("sin tabs", UtilsString.quitarTabsYSaltosLinea("sin tabs"));
		assertEquals("a|b", UtilsString.quitarTabsYSaltosLinea("a\tb"));
		assertEquals("a#b", UtilsString.quitarTabsYSaltosLinea("a\nb"));
		assertEquals("a|||b", UtilsString.quitarTabsYSaltosLinea("a\t\t\tb"));
		assertEquals("a###b", UtilsString.quitarTabsYSaltosLinea("a\n\n\nb"));
		assertEquals("|#|#", UtilsString.quitarTabsYSaltosLinea("\t\n\t\n"));
	}
	
	@Test
	public void testQuitarTabsYSaltosLineaNull() {
		assertEquals("", UtilsString.quitarTabsYSaltosLinea(null));
	}
}
