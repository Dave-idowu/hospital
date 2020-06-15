/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.service;

import com.steve.model.HibernateUtil;
import com.steve.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

/**
 *
 * @author USER
 */
public class LoginService {
    public boolean authenticateUser(String userId, String password){
        User user = getUserByUserId(userId);
        if(user!=null && user.getUserId().equals(userId) && user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
    
    public User getUserByUserId(String userId){
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            Query query =  session.createQuery("from User where userId = '"+ userId +"'");
            user = (User) query.uniqueResult();
            tx.commit();
        }
        catch(Exception ex){
            if(tx == null){
                tx.rollback();
                ex.printStackTrace();
            }
        }
        finally{
            session.close();
        }
        return user;
    }
    
    public List<User> getListOfUsers(){
        List<User> list = new ArrayList<>();
        Session session = HibernateUtil.openSession();
        Transaction tx = null;
        User user = null;
        try{
            tx = session.getTransaction();
            tx.begin();
            list =  session.createQuery("from User").list();
            tx.commit();
        }
        catch(Exception ex){
            if(tx == null){
                tx.rollback();
                ex.printStackTrace();
            }
        }
        finally{
            session.close();
        }
       return list; 
    }
}
