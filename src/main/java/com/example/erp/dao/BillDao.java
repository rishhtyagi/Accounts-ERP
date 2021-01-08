package com.example.erp.dao;

import com.example.erp.bean.Bill;

public interface BillDao {
    public boolean create(Bill bill);

    public boolean update(Integer bill_id, Bill bill);

    public Bill read(int student_id);

    public boolean delete(int bill_id);
}