package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.autor;
import org.example.bibliotecafx.entities.socio;
import org.hibernate.Session;

import java.util.List;

public class socioDAOImpl implements socioDAO {
    @Override
    public void create(socio socio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(socio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(socio socio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(socio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(socio socio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(socio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public socio findByNumeroTelefono(Integer telefono) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM socio WHERE telefono = :telefono";
        socio sociesito = session.createQuery(hql, socio.class)
                .setParameter("telefono", telefono)
                .uniqueResult();  // Devuelve un solo resultado
        session.close();
        return sociesito;
    }


    @Override
    public socio findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM socio WHERE nombre = :nombre";
        socio sociesito = session.createQuery(hql, socio.class)
                .setParameter("nombre", nombre)
                .uniqueResult();  // Devuelve un solo resultado
        session.close();
        return sociesito;
    }


    @Override
    public List<socio> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<socio> socios = session.createQuery("FROM socio", socio.class).list();
        session.close();
        return socios;
    }
}
