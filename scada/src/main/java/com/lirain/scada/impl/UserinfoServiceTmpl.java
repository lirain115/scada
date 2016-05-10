package com.lirain.scada.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lirain.scada.dao.UserinfoMapper;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;


@Service//("userinfoService")
public class UserinfoServiceTmpl implements  UserinfoService{

	private UserinfoMapper userinfoMapper;
	
	public UserinfoMapper getUserinfoMapper() {
		return userinfoMapper;
	}
	
	@Autowired
	public void setUserinfoMapper(UserinfoMapper userinfoMapper) {
		this.userinfoMapper = userinfoMapper;
	}
	
	@Override
	public List<Userinfo> getAll() {
		// TODO Auto-generated method stub
		return userinfoMapper.getAll();
	}
	@Override
	public Userinfo getUserById(int userId) {
		// TODO Auto-generated method stub
		return userinfoMapper.selectByPrimaryKey(userId);
	}
	
	@Override
	public Integer getMaxidofpid(Integer pId) {
		// TODO Auto-generated method stub
		Integer ret = 0;
		ret = userinfoMapper.getMaxidofpid(pId);
		
		if(ret == null)
			ret = 0;
		
		return ret;
	}
	
	@Override
	public void addUserinfo(Userinfo userinfo) {
		// TODO Auto-generated method stub
		userinfoMapper.addUserinfo(userinfo);
	}

	@Override
	public List<Userinfo> getUserdesbyuserno(String userno) {
		// TODO Auto-generated method stub
		return userinfoMapper.getUserdesbyuserno(userno);
	}

	@Override
	public void updateUserinfobyusrno(Userinfo userinfo) {
		// TODO Auto-generated method stub
		if(userinfo.getXflag() == 0)
		{//tty
			userinfoMapper.updateTtyinfobyusrno(userinfo);
		}
		else if(userinfo.getXflag() == 1)
		{//company
			userinfoMapper.updateCpyinfobyusrno(userinfo);
		}
		else if(userinfo.getXflag() == 2)
		{//person
			userinfoMapper.updatePersoninfobyusrno(userinfo);
		}
		else if(userinfo.getXflag() == 3)
		{//user-group
			
		}
	}

	@Override
	public void delUser(Userinfo userinfo) {
		// TODO Auto-generated method stub
		userinfoMapper.delUserbyuserno(userinfo.getUserno());
		
		if(userinfo.getXflag() == 0 || userinfo.getXflag() == 3)
		{
			userinfoMapper.delUserbypId(userinfo.getId());
		}
		
	}

	@Override
	public String getUsernoByStcodeAndAddress(String address, String statecode) {
		// TODO Auto-generated method stub
		String userno = new String();
		Userinfo datain = new Userinfo();
		datain.setAddress(address);
		datain.setStatecode(statecode);
		List<Userinfo> userinfos;
		
		userno = null;
		
		if(statecode == null)
		{
			userinfos = userinfoMapper.getUsernoByAddress(datain);
		}
		else
		{
			userinfos = userinfoMapper.getUsernoByStcodeAndAddress(datain);
		}
		
		for(Userinfo userinfo : userinfos)
		{
			if(userinfo != null)
			{
				userno = userinfo.getUserno();
			}
		}
		
		return userno;
	}

	@Override
	public String getUsernamebyuserno(String userno) {
		// TODO Auto-generated method stub
		String username = new String();
		
		List<Userinfo> userinfos = userinfoMapper.getUserdesbyuserno(userno);
		for(Userinfo userinfo : userinfos)
		{
			if(userinfo != null)
			{
				username = userinfo.getName();
			}
		}
		return username;
	}

	@Override
	public List<Userinfo> getAllResident() {
		// TODO Auto-generated method stub
		return userinfoMapper.getAllResident();
	}

	

	@Override
	public String getSonByStcodeAndAddress(String address, String statecode) {
		// TODO Auto-generated method stub
		Userinfo datain = new Userinfo();
		datain.setAddress(address);
		datain.setStatecode(statecode);
		List<Userinfo> userinfos = userinfoMapper.getUsernoByStcodeAndAddress(datain);
		String ret_address = new String();
		Userinfo usr = new Userinfo();
		int i = 0;
		
		for(Userinfo userinfo : userinfos)
		{
			if(userinfo != null && userinfo.getXflag() == 0)
			{
				usr.setpId(userinfo.getId());
				List<Userinfo> userinfos2 =  userinfoMapper.getUserinfobyPid(usr);
				for(Userinfo userinfo2 : userinfos2)
				{
					i++;
					ret_address = userinfo2.getAddress();
				}
			}
		}
		if(i > 1 || i == 0)
		{
			System.out.println("Address no son or not only son");
			return null;
		}
		else
			return ret_address;
	}
	
	@Override
	public String getSonUsernoByStcodeAndAddressAndpn(String address, String statecode,int pn) {
		// TODO Auto-generated method stub
		Userinfo datain = new Userinfo();
		datain.setAddress(address);
		datain.setStatecode(statecode);
		List<Userinfo> userinfos = userinfoMapper.getUsernoByStcodeAndAddress(datain);
		String ret_userno = new String();
		Userinfo usr = new Userinfo();
		int i = 0;
		
		for(Userinfo userinfo : userinfos)
		{
			if(userinfo != null && userinfo.getXflag() == 0)
			{
				usr.setpId(userinfo.getId());
				List<Userinfo> userinfos2 =  userinfoMapper.getUserinfobyPid(usr);
				for(Userinfo userinfo2 : userinfos2)
				{
					if(userinfo2.getPn()==pn)
					{
						ret_userno = userinfo2.getUserno();	
					}
					
					i++;
					
				}
			}
		}
		if(i > 1 || i == 0)
		{
			System.out.println("pn no son or not only son");
			return null;
		}
		else
			return ret_userno;
	}

	@Override
	public Userinfo getUserinfoByaddress(String address) {
		// TODO Auto-generated method stub
		List<Userinfo> userinfos = userinfoMapper.getUserinfoByAddress(address);
		
		for(Userinfo userinfo : userinfos)
		{
			if(userinfo != null && userinfo.getXflag() == 1)
				return userinfo;
		}
		
		return null;
	}
	
	@Override
	public Userinfo getUserinfobyuserno(String userno) {
		// TODO Auto-generated method stub
		
		List<Userinfo> userinfos = userinfoMapper.getUserdesbyuserno(userno);
		Userinfo ret_usr = new Userinfo();
		
		for(Userinfo userinfo : userinfos)
		{
			ret_usr = userinfo;
		}
		return ret_usr;
	}
}
