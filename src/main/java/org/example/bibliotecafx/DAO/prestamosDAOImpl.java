package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.prestamo;
import org.example.bibliotecafx.entities.socio;
import org.hibernate.Session;

import java.util.List;

public class prestamosDAOImpl implements prestamosDAO {
    @Override
    public void registrarPrestamo(prestamo prestamo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(prestamo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<prestamo> listarPrestamos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<prestamo> prestamos = session.createQuery("FROM prestamo", prestamo.class).list();
        session.close();
        return prestamos;
    }

    @Override
    public List<prestamo> listarHistorialPrestamos(socio socio) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<prestamo> prestamos = session.createQuery("FROM prestamo WHERE socio = :socio", prestamo.class).setParameter("socio", socio).list();
        session.close();
        return prestamos;
    }
}
