package com.revature.services;

import com.revature.daos.ReimburseDAOS;
import com.revature.pojos.Reimburse;

import java.util.List;

public class ReimburseService {
    private ReimburseDAOS dao;

    public ReimburseService(){
        this.dao = new ReimburseDAOS();
    }

    public void saveReimburse(Reimburse reimburse){
        dao.create(reimburse);
    }

    public Reimburse getReimburse(int id){
        return dao.read(id);
    }

    public List<Reimburse> getAllReimburse(){
        return dao.readAll();
    }

    public void updateReimburse(Reimburse reimburse){
        dao.update(reimburse);
    }

    public void deleteReimburse(int id){
        dao.delete(id);
    }
}
