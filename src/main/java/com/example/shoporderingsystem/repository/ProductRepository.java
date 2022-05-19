package com.example.shoporderingsystem.repository;

import com.example.shoporderingsystem.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductRepository implements ProductRepositoryInterface{

    private SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Product elem) {
        try {
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    Product product = null;
                    product = new Product(elem.getName(),elem.getPrice(),elem.getQuantity(),elem.getCompany());
                    session.save(product);
                    tx.commit();
                } catch (RuntimeException ex) {
                    if (tx != null)
                        tx.rollback();
                }
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
    }

    @Override
    public void delete(Product elem) {

    }

    @Override
    public void update(Product elem) {

    }

    @Override
    public Product findById(Integer id) {
        Product product = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                product = session.createQuery("from Product where Id ='" + id +"'", Product.class)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
            }
            catch (RuntimeException ex) {
                System.err.println("Login error: " + ex.getMessage());
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                products = session.createQuery("from Product", Product.class).list();
                tx.commit();
            }
            catch (RuntimeException ex) {
                System.err.println("Login error: " + ex.getMessage());
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        return products;
    }


}
