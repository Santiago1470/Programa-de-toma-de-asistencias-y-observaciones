
package controlador;

import java.util.ArrayList;
import javax.swing.JFrame;
import modelo.Modelo;
import vista.*;

public class ProgramaAsyOb {
    
    public static void main(String[] args) {
        ArrayList<JFrame> ventanas = new ArrayList<>();
        ventanas.add(new VentanaInicio());
        ventanas.add(new VentanaAdmin());
        ventanas.add(new VentanaDocente());
        ventanas.add(new VentanaEstudiante());
        Controlador controlador = new Controlador(new Modelo(), ventanas);
        
        controlador.iniciar();
    }
    
}
