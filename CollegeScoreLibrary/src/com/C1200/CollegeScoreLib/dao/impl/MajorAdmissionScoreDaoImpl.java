package com.C1200.CollegeScoreLib.dao.impl;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.MajorAdmissionScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 17/6/21.
 */
public class MajorAdmissionScoreDaoImpl extends BaseDaoImpl<MajorAdmissionScore> implements BaseDao<MajorAdmissionScore> {
    public List<MajorAdmissionScore> getMajorAdmissionScoreByAttrs(MajorAdmissionScore majorAdmissionScore){

        List<MajorAdmissionScore> list = new ArrayList<MajorAdmissionScore>();
        String sql = "select * from tb_majoradmissionscore where ";
        try {
            sql += super.getSQLqueryString(majorAdmissionScore);
            System.out.println(sql);
            list = super.getEntrys(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
