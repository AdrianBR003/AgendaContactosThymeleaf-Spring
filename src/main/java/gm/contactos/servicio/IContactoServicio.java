package gm.contactos.servicio;


import gm.contactos.modelo.Contacto;

import java.util.List;

public interface IContactoServicio {
    public List<Contacto> listarContactos();

    public Contacto buscarContactoPorId(Integer idContacto);

    public void guardarContacto(Contacto contacto); // Si id=null -> insercion || si id!=null -> update

    public void eliminarContacto(Contacto contacto);
}
