package com.C1200.CollegeScoreLib.entity;

import java.io.Serializable;

/**
 *
 * @param <T>
 */
public interface BaseEntity extends Serializable{
	/**
	 * 返回主键的主键名
	 * @return
	 */
	public  String getPrimaryKey();
	/**
	 * 主键是否是自增长的
	 * @return
	 */
	public Boolean isPK_Increment();
	
	public String getTableName();
}
