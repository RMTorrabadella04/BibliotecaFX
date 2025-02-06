package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.libro;
import org.hibernate.Session;

import java.util.List;

public class libroDAOImpl implements libroDAO {
    @Override
    public void create(libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(libro);
        session.getTransaction().commit();
        session.close();
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
        libro librito = session.get(libro.class, ISBN);
        session.close();
        return librito;
    }

    @Override
    public libro findByTitulo(String titulo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        libro librito = session.get(libro.class, titulo);
        session.close();
        return librito;
    }

    @Override
    public libro findByAutor(String autor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        libro librito = session.get(libro.class, autor);
        session.close();
        return librito;
    }

    @Override
    public List<libro> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<libro> libros = session.createQuery("FROM libro", libro.class).list();
        session.close();
        return libros;
    }

}
