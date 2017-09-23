package colegaGrupo.coleguita;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Clase Ventana
 * Muestra la estructuta que deberia tener una Ventana en Java con la libreria
 * Swing, contiene una etiqueta, un caja de texto y un boton, que tiene la
 * accion de mostrar el texto en la caja por una ventana de mensaje.
 * @author Daniel Alvarez (a3dany)
 */
public class inicio extends JFrame implements ActionListener {

    private JLabel texto;           // etiqueta o texto no editable
    private JTextField caja;        // caja de texto, para insertar datos
    private JButton boton;          // boton con una determinada accion
    private JList<String> listaGrupos;
    private ConexionFacebook C;
    private JPanel izq,der;
    private DefaultListModel<String> model;
    
    public inicio(ConexionFacebook C) {
    	super();                    // usamos el contructor de la clase padre JFrame
    	this.C = C;
        configurarVentana();        // configuramos la ventana
        inicializarComponentes();   // inicializamos los atributos o componentes
    }

    private void configurarVentana() {
        this.setTitle("Esta Es Una Ventana");                   // colocamos titulo a la ventana
        this.setSize(800, 600);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(new BorderLayout());                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }

    private void inicializarComponentes() {
        // creamos los componentes
        texto = new JLabel();
        caja = new JTextField();
        boton = new JButton();
        izq= new JPanel();
        izq.setLayout(new BoxLayout(izq,BoxLayout.Y_AXIS));
        der= new JPanel();
        // configuramos los componentes
        texto.setText("Buscar grupo: ");    // colocamos un texto a la etiqueta
       // texto.setBounds(50, 50, 100, 25);   // colocamos posicion y tamanio al texto (x, y, ancho, alto)
        caja.setBounds(150, 50, 100, 25);   // colocamos posicion y tamanio a la caja (x, y, ancho, alto)
        //
        boton.setText("Mostrar grupos sin dueño");   // colocamos un texto al boton
      //  boton.setBounds(50, 100, 200, 30);  // colocamos posicion y tamanio al boton (x, y, ancho, alto)
        boton.addActionListener(this);      // hacemos que el boton tenga una accion y esa accion estara en esta clase
        // adicionamos los componentes a la ventana
        izq.add(texto);
        izq.add(caja);
        izq.add(boton);
        model = new DefaultListModel<String>();
        listaGrupos = new JList<String>(model);
        der.add(listaGrupos);
        this.getContentPane().add(izq, BorderLayout.WEST);
        this.getContentPane().add(der, BorderLayout.EAST);
        
    }

    public void actionPerformed(ActionEvent e) {
        String aBuscar = caja.getText();                                 // obtenemos el contenido de la caja de texto
        List<String> GruposVacios= C.buscarGrupos(C.configurar(),aBuscar);
        for (String item : GruposVacios) {
        	model.addElement(item);
        
        }
       
        
        
        
        //JOptionPane.showMessageDialog(this, "Hola " + nombre + ".");    // mostramos un mensaje (frame, mensaje)
        
    }
}