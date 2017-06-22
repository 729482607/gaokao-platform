package com.C1200.CollegeScoreLib.dao.impl;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.MajorAdmitScore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 17/6/21.
 */
public class MajorAdmitScoreDaoImpl extends BaseDaoImpl<MajorAdmitScore> implements BaseDao<MajorAdmitScore> {
    public List<MajorAdmitScore> getMajorAdmitScoreByAttrs(MajorAdmitScore majorAdmitScore){

        List<MajorAdmitScore> list = new ArrayList<MajorAdmitScore>();
        String sql = "select * from tb_majoradmissionscore where ";
        try {
            sql += super.getSQLqueryString(majorAdmitScore);
            System.out.println(sql);
            list = super.getEntrys(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
