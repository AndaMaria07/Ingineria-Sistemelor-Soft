package com.example.shoporderingsystem.repository;

import com.example.shoporderingsystem.domain.Administrator;
import com.example.shoporderingsystem.domain.SalesMan;
import com.example.shoporderingsystem.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepository implements UserRepositoryInterface {

    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByCredentials(String userName, String password) throws Exception {
        User employee = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                employee = session.createQuery("from Administrator where userName='" + userName + "'and password='" + password + "'", User.class)
                        .setMaxResults(1)
                        .uniqueResult();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Login error: " + ex.getMessage());
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        if(employee!=null){
           if(employee.getRole().equals("admin")){
               Administrator administrator = new Administrator(employee.getUserName(), employee.getPassword(), employee.getName(),employee.getRole());
               administrator.setID(employee.getID());
               return administrator;
           }
           else{
               SalesMan salesMan = new SalesMan(employee.getUserName(), employee.getPassword(), employee.getName(),employee.getRole());
               salesMan.setID(employee.getID());
               return salesMan;
           }
        }
        else{
            throw new Exception("Bad credentials");
        }
    }

    @Override
    public void add(User elem) {

    }

    @Override
    public void delete(User elem) {

    }

    @Override
    public void update(User elem) {

    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
