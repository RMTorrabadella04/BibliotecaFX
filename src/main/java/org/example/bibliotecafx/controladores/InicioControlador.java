package org.example.bibliotecafx.controladores;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InicioControlador {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }

}
