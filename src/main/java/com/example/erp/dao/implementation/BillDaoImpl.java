package com.example.erp.dao.implementation;

import com.example.erp.bean.Bill;
import com.example.erp.bean.Students;
import com.example.erp.dao.BillDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Column;

public class BillDaoImpl implements BillDao {

    @Override
    public boolean create(Bill bill) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(bill);
            System.out.println("Bill created with id:" + id);
//            Students student = new Students(1, "Rishabh", "Tyagi", "rishabht1122@gmail.com", (float) 7.0, 1);
//            session.save(student);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }

    }

    @Override
    public boolean update(Integer bill_id, Bill bill) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            System.out.println("Inside update DAO");
            Integer new_amount = bill.getAmount();
            String new_status = bill.getStatus();
            Query query = session.createQuery("update Bill b set b.amount=:amount, b.status=:status where b.bill_id=:bill_id");
            query.setParameter("bill_id", bill_id);
            query.setParameter("amount", new_amount);
            query.setParameter("status", new_status);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    @Override
    public Bill read(int bill_id) {
        try (Session session = SessionUtil.getSession()) {
            System.out.println("Inside read bill DAO"+ " bill_id="+ bill_id);
            Query query = session.createQuery("from Bill where bill_id=:bill_id");
            query.setParameter("bill_id", bill_id);
            return (Bill) query.getResultList().get(0);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return new Bill();
        }
    }

    @Override
    public boolean delete(int bill_id) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            System.out.println("Inside delete DAO");
            Query query = session.createQuery("delete from Bill b where b.bill_id=:bill_id");
            query.setParameter("bill_id", bill_id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }
}