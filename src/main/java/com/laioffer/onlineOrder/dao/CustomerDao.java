package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.Authorities;
import com.laioffer.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//data tier相关
//Repository 也是component一种

@Repository
public class CustomerDao {
    //Dao DATA access object COMMUNICATE WITH DB
    //SessionFactory
    @Autowired
    private SessionFactory sessionFactory;

    public void signUp(Customer customer) {
        //give authority when sighUp
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setEmail(customer.getEmail());


        //create SESSION to CRUD DB
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction(); //create transaction
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit(); //commit; begin,...do some work, commit

        } catch(Exception ex){
            ex.printStackTrace(); //to check which method
            if (session != null) session.getTransaction().rollback();
            //Roller back will delete the half which is successed
        } finally {
            //NO EXCEPTION -> CLOSE EXCEPTION TO MAKE SURE THREAD IS AVAILABLE IN DB FOR OTHERS TO USE
            if (session != null){
                session.close();
            }
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {
            //这个方法表达的和上面一样，只不过在try里创建，然后自动关闭
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }
}
