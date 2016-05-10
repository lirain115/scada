package com.lirain.scada.service;

import java.util.List;

import com.lirain.scada.pojo.Userinfo;


public interface UserinfoService {
	
	
	Userinfo getUserById(int userId);
	/*
	 * 功能：获取所有Userinfo内的记录
	 * 参数：无
	 * 返回值：List<Userinfo>
	 * */
	List<Userinfo> getAll();
	
	/*
	 * 功能：获取所有Resident（普通居民）用户的记录
	 * 参数：无
	 * 返回值：List<Userinfo>
	 * */
	List<Userinfo> getAllResident();
	
	/*
	 * 功能：获取父节点为pId的最大Id值
	 * 参数：pId
	 * 返回值：最大id值
	 * */
	Integer getMaxidofpid(Integer pId);
	
	/*
	 * 功能：添加新的记录
	 * 参数：Userinfo 对象
	 * 返回值：无
	 * */
	void addUserinfo(Userinfo userinfo);
	
	/*
	 * 功能：根据userno获取记录
	 * 参数：userno
	 * 返回值：List<Userinfo>
	 * */
	List<Userinfo> getUserdesbyuserno(String userno);
	
	/*
	 * 功能：根据userno更新记录
	 * 参数：Userinfo对象
	 * 返回值：无
	 * */
	void updateUserinfobyusrno(Userinfo userinfo);
	
	/*
	 * 功能：删除记录，先根据删除userno与该节点下的所有子节点
	 * 参数：Userinfo对象
	 * 返回值：无
	 * */
	void delUser(Userinfo userinfo);
	
	/*
	 * 功能：根据address和statecode获取userno
	 * 参数：address--地址
	 * 		 statecode--区域码
	 * 返回值：String类型的userno
	 * */
	String getUsernoByStcodeAndAddress(String address, String statecode);

	/*
	 * 功能：根据userno获取name
	 * 参数：userno
	 * 返回值：String
	 * */
	String getUsernamebyuserno(String userno);
	
	/*
	 * 功能：根据address和statecode获取子节点的地址码address
	 * 参数：address--地址
	 * 		 statecode--区域码
	 * 返回值：子节点的地址码
	 * */
	String getSonByStcodeAndAddress(String address, String statecode);
	/*
	 * 功能：根据statecode address pn获取表的userno
	 * 参数：userno
	 * 返回值：String
	 * */
	String getSonUsernoByStcodeAndAddressAndpn(String address, String statecode,int pn);
	
	/*
	 * 功能：根据userno获取记录
	 * 参数：userno
	 * 返回值：userinfo对象
	 * */
	Userinfo getUserinfobyuserno(String userno);
	
	/*
	 * 功能：根据address获取记录
	 * 参数：address
	 * 返回值：userinfo对象
	 * */
	Userinfo getUserinfoByaddress(String address);
}
