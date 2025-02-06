package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.autor;
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        autor autorsito = session.get(autor.class, nombre);
        session.close();
        return autorsito;
    }

    @Override
    public List<autor> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<autor> autores = session.createQuery("FROM autor", autor.class).list();
        session.close();
        return autores;
    }
}
