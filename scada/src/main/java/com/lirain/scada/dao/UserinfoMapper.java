package com.lirain.scada.dao;

import java.util.List;

import com.lirain.scada.pojo.Userinfo;

public interface UserinfoMapper {
	
	Userinfo selectByPrimaryKey(Integer id);
	
	List<Userinfo> getAll();
	
	List<Userinfo> getAllResident();
	
	Integer getMaxidofpid(Integer pId);
	
	void addUserinfo(Userinfo userinfo);
	
	List<Userinfo> getUserdesbyuserno(String userno);
	
	void updateTtyinfobyusrno(Userinfo userinfo);
	
	void updateCpyinfobyusrno(Userinfo userinfo);
	
	void updatePersoninfobyusrno(Userinfo userinfo);
	
	void delUserbyuserno(String userno);
	
	void delUserbypId(Integer pId);
	
	List<Userinfo> getUsernoByStcodeAndAddress(Userinfo userinfo);
	
	List<Userinfo> getUsernoByAddress(Userinfo userinfo);
	
	List<Userinfo> getUserinfobyPid(Userinfo userinfo);
	
	List<Userinfo> getUserinfoByAddress(String address);
}
