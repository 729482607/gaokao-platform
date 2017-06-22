package com.C1200.CollegeScoreLib.service;

import com.C1200.CollegeScoreLib.dao.impl.MajorAdmitScoreDaoImpl;
import com.C1200.CollegeScoreLib.dao.impl.MajorDaoImpl;
import com.C1200.CollegeScoreLib.entity.MajorAdmitScore;

import java.util.List;

/**
 * Created by yang on 17/6/21.
 */
public class MajorService{
    private MajorDaoImpl majorDao = new MajorDaoImpl();
    private MajorAdmitScoreDaoImpl majorAdmitScoreDao = new MajorAdmitScoreDaoImpl();

    public int getMajorIdByName(String name){
        int id = majorDao.getMajorIdByName(name);
        return id;
    }

    public List<MajorAdmitScore> getMajorAdmitScoreByAttrs(MajorAdmitScore majorAdmitScore){
        List<MajorAdmitScore> list =  majorAdmitScoreDao.getMajorAdmitScoreByAttrs(majorAdmitScore);
        if(list != null && list.size() > 0){
            return list;
        }

        return null;
    }
}
