package com.lirain.scada.mina;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;

@Component
public class DataLibraryProcess {
	
//	 @Qualifier("userInfo")
	//@Resource(name="userInfo")  
	 @Autowired
	 private UserinfoService userInfo;	
	 
	public static Logger logger = Logger.getLogger(DataLibraryProcess.class);
	
	
	int VoltageRatio=1;
	int CurrentRatio=1;
	private boolean terminal = false;
	private int saveok = 0;
	public String usernum =null;
	String zoneStr=null;
	String termStr=null;
	
	public boolean Verify(byte[] address){
		
		 System.out.println("--------------Verify 数据解析！-----------------------"); 
		 
		 
	  Userinfo info =userInfo.getUserById(3);
		logger.info(JSON.toJSONString(info));
		//Userinfo info =userInfo.getUserById(3);
//		
//		Userinfo info2 =userInfo.getUserById(8);
//		
//		logger.info(JSON.toJSONString(info));
//		logger.info(JSON.toJSONString(info2));
		

		int zoneNum = BCD2Hex(address[0])+(BCD2Hex(address[1])*100);
		zoneStr= Integer.toString(zoneNum);
		
		int termNum = (address[2]&0xff)+((address[3]&0xff)*256);
		//System.out.printf("TermNum:%x ",termNum); 
		termStr= Integer.toString(termNum);
		//System.out.println(zoneStr+"     "+termStr); 
		
		//String usernum2 = userinfoService.getUsernoByStcodeAndAddress("000020207019",null);
		
		
		//System.out.println("-------------------------------------");
		//System.out.println(usernum2);
		if(usernum == null)
		{
			
			logger.info(zoneStr+" "+termStr+"未注册！！！");
			terminal = false;
			
			
		}else{
			System.out.println("处理的usernum:"+usernum);
//			System.out.println(username+"上线");
			terminal = true;
			
		}
		return terminal;
		
	}
	public int SaveData(String[] data,int pn)
	{ 	int ret =0;
		//Datarecord darecord = new Datarecord();
		//UserinfoService userinfoService  = (UserinfoService)beanFactory.getBean("userinfoService");
		//String usernum = userinfoService.getUsernoByStcodeAndAddress(emeterNum,null);
		//getSonUsernoByStcodeAndAddressAndpn();
//		UserinfoService userinfoService  = (UserinfoService)beanFactory.getBean("userinfoService");
//		usernum = userinfoService.getSonUsernoByStcodeAndAddressAndpn(termStr,zoneStr,pn);
//		nowdata.setUserno(usernum);	
//		datarecord.setUserno(usernum);
//		System.out.println(usernum+"写入数据库");
//		
//		nowdata.setUserno(usernum);	
//		//	nowdata.setUserno(userinfo.getUserno());
//		nowdata.setOnline(1);   //表上线
//		
//		GetVoltageCurrentRatio(usernum);
//		
//		data[18]=dataProess(data[18]);
//		nowdata.setZxygzdn(data[18]);          //实时显示正向有功总电能
//		datarecord.setZxygzdn(data[18]);
//		
//		data[23]=dataProess(data[23]);
//		nowdata.setZxwgzdn(data[23]);          //实时显示正向有功总电能
//		datarecord.setZxwgzdn(data[23]);
//			
//	    
//	    for(int i=0;i<3;i++)
//	    {
//	    	data[12+i] = dataProessU(data[12+i]);
//	    }
//	    for(int i=0;i<3;i++)
//	    {
//	    	data[15+i] = dataProessI(data[15+i]);
//	    }
//	    
//	    nowdata.setUa(data[12]);  //
//	    datarecord.setUa(data[12]);
//	    
//	    nowdata.setUb(data[13]);
//	    datarecord.setUb(data[13]);
//		
//	    nowdata.setUc(data[14]);
//	    datarecord.setUc(data[14]);
//		
//	    nowdata.setIa(data[15]);
//	    datarecord.setIa(data[15]);
//	    
//	    nowdata.setIb(data[16]);
//	    datarecord.setIb(data[16]);
//	    
//	    nowdata.setIc(data[17]);
//	    datarecord.setIc(data[17]);
//	    
//	    data[0]=dataProessW(data[0]);
//	    nowdata.setDqyggl(data[0]);  //当前有功功功率
//	    datarecord.setDqyggl(data[0]);
//	    
//	    data[4]=dataProessW(data[4]);
//	    nowdata.setDqwggl(data[4]);	 //当前无功功率
//	    datarecord.setDgwggl(data[4]); 
//	    
//	
//		NowdataService nowdataService = (NowdataService)beanFactory.getBean("nowdataService");			
//		nowdataService.updateInfobyUserno(nowdata);
//		
//		DatarecordService datarecordService = (DatarecordService)beanFactory.getBean("datarecordService");	
//		datarecordService.addNewData(datarecord);
	
	
		return ret;
	
	}
	public String dataProess(String tmp)//格式转换异常处理
	{
		
		try{
			tmp=String.valueOf(Float.parseFloat(tmp));
		}catch(NumberFormatException e)
		{
			System.out.println("异常："+e);
			tmp=null;	
		}
		return tmp;
	}
	public String dataProessW(String tmp)//格式转换异常处理
	{
		
		try{
			tmp=String.valueOf(Float.parseFloat(tmp)*CurrentRatio*VoltageRatio);
		}catch(NumberFormatException e)
		{
			System.out.println("异常："+e);
			tmp=null;
		}
		return tmp;
	}
	public String dataProessU(String tmp)//电压格式转换异常处理
	{
		
		try{
			tmp=String.valueOf(Float.parseFloat(tmp)*VoltageRatio);
		}catch(NumberFormatException e)
		{
			System.out.println("异常："+e);
			tmp=null;
		}
		return tmp;
	}
	public String dataProessI(String tmp)//电流格式转换异常处理
	{
		
		try{
			tmp=String.valueOf(Float.parseFloat(tmp)*CurrentRatio);
		}catch(NumberFormatException e)
		{
			System.out.println("异常："+e);
			tmp=null;
		}
		return tmp;
	}
	
	
	
	public int SaveDataTest(StringBuffer data,String emeterNum,String oderNum)
	{
		saveok= 0;
//		Datarecord darecord = new Datarecord();
//		UserinfoService userinfoService  = (UserinfoService)beanFactory.getBean("userinfoService");
//		String usernum = userinfoService.getUsernoByStcodeAndAddress(emeterNum,null);
//		nowdata.setUserno(usernum);	
//		darecord.setUserno(usernum);
//		if(oderNum.equals("9010"))
//		{
//			nowdata.setZxygzdn(data.toString());
//		    darecord.setZxygzdn(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 1;
//		}else if(oderNum.equals("9110"))
//		{
//			nowdata.setZxwgzdn(data.toString());
//		    darecord.setZxwgzdn(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 2;
//		}else if(oderNum.equals("b611"))
//		{
//			nowdata.setUa(data.toString());
//		    darecord.setUa(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 3;
//		}else if(oderNum.equals("b612"))
//		{
//			nowdata.setUb(data.toString());
//		    darecord.setUb(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 4;
//		}else if(oderNum.equals("b613"))
//		{
//			nowdata.setUc(data.toString());
//		    darecord.setUc(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 5;
//		}else if(oderNum.equals("b621"))
//		{
//			nowdata.setIa(data.toString());
//		    darecord.setIa(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 6;
//		}else if(oderNum.equals("b622"))
//		{
//			nowdata.setIb(data.toString());
//		    darecord.setIb(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 7;
//		}else if(oderNum.equals("b623"))
//		{
//			nowdata.setIc(data.toString());
//		    darecord.setIc(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 8;
//		}
//		else if(oderNum.equals("b630"))
//		{
//			nowdata.setDqyggl(data.toString());
//		    darecord.setDqyggl(data.toString());
//			System.out.println(oderNum+"写入数据库");
//			saveok= 9;
//		}
//		else if(oderNum.equals("b640"))
//		{
//			nowdata.setDqwggl(data.toString());
//		    darecord.setDgwggl(data.toString()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//			System.out.println(oderNum+"写入数据库");
//			saveok= 10;
//		}
//		
//		NowdataService nowdataService = (NowdataService)beanFactory.getBean("nowdataService");			
//		nowdataService.updateInfobyUserno(nowdata);
//		
//		DatarecordService datarecordService = (DatarecordService)beanFactory.getBean("datarecordService");	
//		datarecordService.addNewData(darecord);
		
		

		return saveok;
	}
	
	public int GetVoltageCurrentRatio(String userno)
	{
		
//		UserinfoService userinfoService  = (UserinfoService)beanFactory.getBean("userinfoService");
//		//Userinfo userObj = userinfoService.getUserinfoByaddress(emeterNum);
//		Userinfo userObj = userinfoService.getUserinfobyuserno(userno);
//		VoltageRatio = userObj.getDybb();
//		CurrentRatio = userObj.getDlbb();
//		System.out.println("VoltageRatio："+VoltageRatio);
//		System.out.println("CurrentRatio："+CurrentRatio);
		
		return 1;
	
		
	}
	public byte[] GetEmeterNum(byte[] address)
	{
		byte[]emeterAddr = new byte[6];
		int zoneNum = BCD2Hex(address[0])+(BCD2Hex(address[1])*100);
		String zoneStr= Integer.toString(zoneNum);
		
		int termNum = (address[2]&0xff)+((address[3]&0xff)*256);
		//System.out.printf("%x ",termNum); 
		String termStr= Integer.toString(termNum);
//		UserinfoService userinfoService  = (UserinfoService)beanFactory.getBean("userinfoService");
//		String emeterNum = userinfoService.getSonByStcodeAndAddress(termStr,zoneStr);
//		System.out.println("emeterNum："+emeterNum);
//		try {
//			
//			//System.out.println(emeterNum+"------float:"+Float.parseFloat(emeterNum));
//			byte[] midbytes=emeterNum.getBytes("UTF8");
//			int a=0,b=0;
//			for(int i=5,j=midbytes.length-1;i>=0;i--)
//			{
////				emeterAddr[i]=(byte) ((midbytes[j++]-0x30)*16+(midbytes[j++]-0x30));
////				System.out.println("-----emeterAddr--"+emeterAddr[i]+"----midbytes[i*2]-"+midbytes[i]);
//				if(j<0)
//				{
//					a=0;
//					
//				}else{
//					a=midbytes[j--]-0x30;
//				}
//				if(j<0)
//				{
//					b=0;
//				}else{
//					b=(midbytes[j--]-0x30)*10;
//					
//				}
//				emeterAddr[i] = (byte) (a+b);
//				System.out.print(emeterAddr[i]+" ");
//				
//				
//			}
//			System.out.println();
//			
//			
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		return emeterAddr;
	}
	
	private	byte BCD2Hex(byte address)   
	{   
		byte t_h,t_l;   
	       
	    t_h = (byte) ((address& 0xf0) >> 4);   
	    t_l = (byte) (address&0x0f);   
	       
	    address = (byte) (t_h*10 + t_l);   
	       
	    return(address);   
	}  

	
	

}
