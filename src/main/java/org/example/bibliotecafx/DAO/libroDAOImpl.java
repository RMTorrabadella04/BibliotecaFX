package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.libro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class libroDAOImpl implements libroDAO {

    @Override
    public void create(libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(libro);  // Se guarda el libro con el autor como String
            tx.commit();  // Confirmar la transacción
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();  // Si hay un error, revertir cambios
            }
            e.printStackTrace();  // Imprimir el error
        } finally {
            session.close();  // Cerrar la sesión
        }
    }

    @Override
    public void update(libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(libro);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(libro);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public libro findByISBN(Integer ISBN) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // HQL query para buscar por ISBN
        libro librito = session.createQuery("FROM libro WHERE isbn = :ISBN", libro.class)
                .setParameter("ISBN", ISBN)
                .uniqueResult();
        session.close();
        return librito;
    }

    @Override
    public libro findByTitulo(String titulo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // HQL query para buscar por título
        libro librito = session.createQuery("FROM libro WHERE titulo = :titulo", libro.class)
                .setParameter("titulo", titulo)
                .uniqueResult();
        session.close();
        return librito;
    }

    @Override
    public List<libro> findByAutor(String autorNombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // HQL query para buscar libros por nombre del autor
        List<libro> libros = session.createQuery("FROM libro WHERE autor = :autorNombre", libro.class)
                .setParameter("autorNombre", autorNombre)
                .list(); // Devuelve una lista de libros
        session.close();
        return libros;
    }

    @Override
    public List<libro> findAll() {
        List<libro> libros = null;
        // Usamos try-with-resources para asegurarnos de que la sesión se cierre correctamente
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Creamos la consulta para obtener todos los autores
            libros = session.createQuery("FROM libro", libro.class).list();
        } catch (HibernateException e) {
            // Manejo de excepción en caso de error
            e.printStackTrace();
        }
        return libros;
    }
}
