package com.C1200.CollegeScoreLib.dao.impl;

import com.C1200.CollegeScoreLib.dao.BaseDao;
import com.C1200.CollegeScoreLib.entity.Major;

/**
 * Created by yang on 17/6/21.
 */
public class MajorDaoImpl extends BaseDaoImpl<Major> implements BaseDao<Major> {
    public int getMajorIdByName(String name){
        if(name == null){
            return 0;
        }
        int id = 0;
        try {
            Major major = getEntry("select * from tb_major where major_name = ?", name);
            if(major != null){
                id = major.getMajor_id();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}
