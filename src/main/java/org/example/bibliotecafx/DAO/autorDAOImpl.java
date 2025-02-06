package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.autor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class autorDAOImpl implements autorDAO {
    @Override
    public void create(autor autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(autor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(autor autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(autor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(autor autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(autor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public autor findByNombre(String nombre) {
        autor autorsito = null;
        // Usamos try-with-resources para asegurarnos de que la sesión se cierre correctamente
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Usamos createQuery para hacer una búsqueda por nombre
            autorsito = session.createQuery("FROM autor WHERE nombre = :nombre", autor.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();  // uniqueResult obtiene un único resultado
        } catch (HibernateException e) {
            // Manejo de excepción en caso de error
            e.printStackTrace();
        }
        return autorsito;
    }

    @Override
    public List<autor> findAll() {
        List<autor> autores = null;
        // Usamos try-with-resources para asegurarnos de que la sesión se cierre correctamente
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Creamos la consulta para obtener todos los autores
            autores = session.createQuery("FROM autor", autor.class).list();
        } catch (HibernateException e) {
            // Manejo de excepción en caso de error
            e.printStackTrace();
        }
        return autores;
    }
}
