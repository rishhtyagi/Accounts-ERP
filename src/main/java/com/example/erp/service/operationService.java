package com.example.erp.service;

import com.example.erp.bean.Bill;
import com.example.erp.dao.BillDao;
import com.example.erp.dao.implementation.BillDaoImpl;

public class operationService {
    BillDao billDao = new BillDaoImpl();
    public boolean createBill(Bill bill){
        return billDao.create(bill);
    }
    public boolean updateBill(Integer bill_id, Bill bill){
        System.out.println("Inside update SERVICE");
        return billDao.update(bill_id, bill);
    }
    public Bill readBill(int bill_id){
        System.out.println("Inside read SERVICE");
        return billDao.read(bill_id);
    }
    public boolean deleteBill(int bill_id){
        System.out.println("Inside delete SERVICE");
        return billDao.delete(bill_id);
    }

}
