package com.C1200.CollegeScoreLib.service;

import com.C1200.CollegeScoreLib.dao.impl.MajorAdmissionScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.MajorDaoImpl;
import com.C1200.CollegeScoreLib.entity.MajorAdmissionScore;

import java.util.List;

/**
 * Created by yang on 17/6/21.
 */
public class MajorService{
    private MajorDaoImpl majorDao = new MajorDaoImpl();
    private MajorAdmissionScoreDaoImpl majorAdmissionScoreDao = new MajorAdmissionScoreDaoImpl();

    public int getMajorIdByName(String name){
        int id = majorDao.getMajorIdByName(name);
        return id;
    }

    public List<MajorAdmissionScore> getMajorAdmissionScoreByAttrs(MajorAdmissionScore majorAdmissionScore){
        List<MajorAdmissionScore> list =  majorAdmissionScoreDao.getMajorAdmissionScoreByAttrs(majorAdmissionScore);
        if(list != null && list.size() > 0){
            return list;
        }

        return null;
    }
}
