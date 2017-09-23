package colegaGrupo.coleguita;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ConexionFacebook C = new ConexionFacebook();
    	inicio V = new inicio(C );      // creamos una ventana
        V.setVisible(true);
    }
    public int metodo(int a) {
    	return a*2;
    }
    
    
}
