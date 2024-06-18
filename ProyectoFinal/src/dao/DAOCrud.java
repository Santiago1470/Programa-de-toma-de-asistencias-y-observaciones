
package dao;

import java.util.List;

public interface DAOCrud<T> {
    public boolean agregar(T objeto);
    public List<T> consultarTodo();
    public T consultar(T objeto);
    public boolean actualizar(T objetoNuevo, T objeto);
    public boolean eliminar(T objeto);
}
