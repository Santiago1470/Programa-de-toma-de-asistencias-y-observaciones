
package modelo;

public class UsuarioExistente extends Exception{

    public UsuarioExistente() {
    }

    public UsuarioExistente(String string) {
        super(string);
    }

    public UsuarioExistente(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public UsuarioExistente(Throwable thrwbl) {
        super(thrwbl);
    }

    public UsuarioExistente(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
