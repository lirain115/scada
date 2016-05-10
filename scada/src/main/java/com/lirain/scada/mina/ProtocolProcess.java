package com.lirain.scada.mina;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Formatter;


public class ProtocolProcess {
	
	public static DataLibraryProcess DatalibPro =new DataLibraryProcess();
	
	private static final int BUFSIZE = 100;
	private static final int ADRSIZE = 4;
	 DateFormat df =null;
	static byte[] address =new byte[ADRSIZE];
	 byte[] EmeterNum = new byte[6];
	 public boolean terminal = false;
	 byte[] dataBuf = new byte[6];
	 byte[] oderBuf = new byte[2];
	 byte[] EmeterNumBuf = new byte[6];
	 int datalen =0;
	 byte[] orderID =new byte[2];
	 
	 public int sendMsgSize=0;
	 public byte[] sendBuf = new byte[50];
	 private static String[] fianlData =new String[28];

	 //DataLibraryProcess DatalibPro;
	 
	 public int ProtocolSendData3761(int orderID)
	 {
		 //68 32 00 32 00 68 4B 84 51 5C FD 02 0C 61 01 01 40 14 3E 16
		 //int sendMsgSize=0;
		  sendBuf[0]=0x68;
		  sendBuf[1]=0x32;
		  sendBuf[2]=0x00;
		  sendBuf[3]=sendBuf[1];
		  sendBuf[4]=sendBuf[2];
		  sendBuf[5]=0x68;
		  sendBuf[6]=0x4B;
		  for(int i=0;i<4;i++)
		  {
			  sendBuf[i+7]=address[i];
			  
		  }
		  sendBuf[11]=0x02;
		  sendBuf[12]=0x0C;
		  sendBuf[13]=0x61;
		  sendBuf[14]=0x01;    //序号
		  sendBuf[15]=0x01;    //
		  
		  if(orderID == 25)	  
		  {
			  sendBuf[16]=0x01;    // F25
			  sendBuf[17]=0x03;    //
		  }else if(orderID == 129)
		  {
			  sendBuf[16]=0x01;    // F129
			  sendBuf[17]=0x10;    // 
		  }else if(orderID == 130)
		  {
			  sendBuf[16]=0x02;    // F130
			  sendBuf[17]=0x10;    // 
		  }
		  sendBuf[18]=0x00;
			for (int i=6;i<18;i++)
			{	
				sendBuf[18] = (byte) (sendBuf[18]+sendBuf[i]);//check
				
			}
			
			sendBuf[19]=0x16;
			sendMsgSize = 20;
			return orderID; //F25数据采集
	 }
	 
	 public int ProtocolSendData(byte[] orderID)
	 {
		 int sendMsgSize=0;
				 
		  sendBuf[0]=0x68;
		  sendBuf[1]=(byte) 0xD2;
		  sendBuf[2]=0x00;
		  sendBuf[3]=sendBuf[1];
		  sendBuf[4]=sendBuf[2];
		  sendBuf[5]=0x68;
		  sendBuf[6]=0x4B;
		  for(int i=0;i<4;i++)
		  {
			  sendBuf[i+7]=address[i];
			  
		  }
//		  sendBuf[8]=0x51;
//		  sendBuf[9]=(byte) 0xc9;
//		  sendBuf[10]=(byte) 0xc1;
		  sendBuf[11]=0x02;
		  sendBuf[12]=0x10;
		  sendBuf[13]=0x61;
		  sendBuf[14]=0x00;
		  sendBuf[15]=0x00;
		  sendBuf[16]=0x01;
		  sendBuf[17]=0x00;
		  sendBuf[18]=0x02;
		  sendBuf[19]=0x4B;
		  sendBuf[20]=0x1E;
		  sendBuf[21]=0x1E;
		  sendBuf[22]=0x12;
		  sendBuf[23]=0x00;
		  sendBuf[24]=(byte) 0xFE;
		  sendBuf[25]=(byte) 0xFE;
		  sendBuf[26]=(byte) 0xFE;
		  sendBuf[27]=(byte) 0xFE;
		  sendBuf[28]=0x68;
		  sendBuf[29]=EmeterNum[5];//0x19;
		  sendBuf[30]=EmeterNum[4];
		  sendBuf[31]=EmeterNum[3];
		  sendBuf[32]=EmeterNum[2];
		  sendBuf[33]=EmeterNum[1];
		  sendBuf[34]=EmeterNum[0];
		  sendBuf[35]=0x68;
		  sendBuf[36]=0x01;
		  sendBuf[37]=0x02;
		  sendBuf[38]=(byte) (orderID[0]+0x33);
		  sendBuf[39]=(byte) (orderID[1]+0x33);
		  //////////////////////////////////
		  sendBuf[40] =0;
		  for(int i=28;i<40;i++)
		  {
			  sendBuf[40] = (byte) (sendBuf[40]+sendBuf[i]);//check 97 
		  }
		 // System.out.printf("97校验---%x\n",sendBuf[40]);
		 // sendBuf[40]=(byte) 0xA2;
		  
		  
		  sendBuf[41]=0x16;
		  for(int i=42;i<58;i++)
		  {
			  sendBuf[i]=0x00;
		  }
		  sendBuf[58]= 0;
		  for(int i=6;i<58;i++)
		  {
			  sendBuf[58] = (byte) (sendBuf[58]+sendBuf[i]);//check  
		  }
		  //System.out.printf("376.1校验---%x\n",sendBuf[58]);
		  //sendBuf[58]=(byte) 0x88;
		  sendBuf[59]=0x16;
		  sendMsgSize =60;
		 
		 return sendMsgSize; 
	 }

	 public void test()
	 {
		 System.out.println("--------------DataProcess 数据解析！-----------------------"); 
	 }

	 
	public int DataProcess(byte[] receiveBuf,int recvMsgSize)
	{
		int savaOk=0;
       // int temaddress = 0;
		int len = 0 ;
		int check = 0 ;
   	 	oderBuf = new byte[2];
   	 	EmeterNumBuf = new byte[6];
    	dataBuf = new byte[6];
    	byte[] ratio = new byte[2];
    
    	//EmeterNum = new byte[6];
		System.out.println("--------------DataProcess 数据解析！-----------------------");
	
	if (receiveBuf[0] == 0x68)             //登陆注册命令解析
	{
		len =((((receiveBuf[2]&0xff)<<8)+(receiveBuf[1]&0xff))>>2)+8;
		//int size = (receiveBuf[1]>>2|((receiveBuf[2]&0x03)<<6))+(receiveBuf[2]>>2)*8;
		//if ((receiveBuf[5] == 0x68)&&(receiveBuf[len-1] == 0x16))
		String emeterNum=null;
		{
			for (int i=6;i<len-2;i++)
			{
				check = (byte) (check+receiveBuf[i]);
			}
			//if (check == receiveBuf[len-2])
			if(true)
			{
				//check = 0;				
				if((receiveBuf[6]&0xff) == 0xC9)
				{
					System.out.println("接收到注册数据");
						sendBuf[0]=0x68;
						sendBuf[1]=0x32;
						sendBuf[2]=0x00;
						sendBuf[3]=0x32;
						sendBuf[4]=0x00;
						sendBuf[5]=0x68;
						sendBuf[6]=0x4B;
						for(int i=7;i<11;i++){
							sendBuf[i] = receiveBuf[i];
							address[i-7] = receiveBuf[i];
						}
						sendBuf[11]=0x02;
						sendBuf[12]=0x00;
						sendBuf[13]=(byte) (receiveBuf[13] - 0x10);
						sendBuf[14]=0x00;
						sendBuf[15]=0x00;
						sendBuf[16]=0x01;
						sendBuf[17]=0x00;
						sendBuf[18]=0x00;
						for (int i=6;i<18;i++)
    					{	
							sendBuf[18] = (byte) (sendBuf[18]+sendBuf[i]);//check
							
    					}
						
						sendBuf[19]=0x16;
						sendMsgSize = 20;
						//地址鉴定
						//terminal = DatalibPro.Verify(address);
						//DatalibPro.GetEmeterNum(address);
						if(terminal)
						{
						//   EmeterNum = DatalibPro.GetEmeterNum(address);//获取电表号
						   savaOk=10;
						}
				}else if((receiveBuf[6]&0xff) == 0x88)        //数据接收解析接收透传或376.1
				{
					System.out.println("接收数据！ "); 
					
				if(receiveBuf[21]==0x68)
					{
						EmeterNumBuf[0]=receiveBuf[22];
						EmeterNumBuf[1]=receiveBuf[23];
						EmeterNumBuf[2]=receiveBuf[24];
						EmeterNumBuf[3]=receiveBuf[25];
						EmeterNumBuf[4]=receiveBuf[26];
						EmeterNumBuf[5]=receiveBuf[27];
						String[] temp =new String[6];
				    	for(int i=0;i<6;i++)
	                    {    
				    		
							if((EmeterNumBuf[i]&0xff) < 0x10)
							{
								temp[i] = String.format("0%x",EmeterNumBuf[i]) ;
							}
							else{
								temp[i] =String.format("%x",EmeterNumBuf[i]) ;
								
							}
							
	                    }
		
				    	emeterNum =String.format("%s%s%s%s%s%s", temp[5],temp[4],temp[3],temp[2],temp[1],temp[0]) ;
				    	System.out.println("emeterNum： "+emeterNum); 
						datalen = receiveBuf[30];
						oderBuf[0]=(byte) (receiveBuf[31] - 0x33);
						oderBuf[1]=(byte) (receiveBuf[32]- 0x33);
						String oderNum =String.format("%x%x", oderBuf[1],oderBuf[0]) ;
				    	System.out.println("oderNum： "+oderNum); 

						dataBuf[0]=receiveBuf[33];
						dataBuf[1]=receiveBuf[34];
						dataBuf[2]=receiveBuf[35];
						dataBuf[3]=receiveBuf[36];
	                	//float value =(float) 47.76
	                	StringBuffer data=new StringBuffer(dataBuf.length*2+1);
	                	
	                //	DatalibPro.GetVoltageCurrentRatio(emeterNum);//获得变比
	                //	System.out.println("GetVoltageCurrentRatio: "+DatalibPro.VoltageRatio+"----"+DatalibPro.CurrentRatio); 
	                	
	                	if(datalen == 6)
	                	{
		                	for(int i=3;i>=0;i--)
		                	{
		                		dataBuf[i]=(byte) (dataBuf[i]-0x33);
		                		//System.out.printf("6--%d--%x ",i,dataBuf[i]);
		                		
		                		if(dataBuf[i]!=0)
		                		{
			                		data.append((byte)((dataBuf[i]& 0xf0)>>4));
			                		data.append((byte)(dataBuf[i]& 0x0f));
			                        if(i==1)
			                        {
			                        	data.append('.');
			                        }
		                		}else if(i==2)
		                		{
		                			data.append('0');
		                		}
		                		
		                	}
	                	}else if(datalen == 5)
	                	{
	                		//System.out.println("TEST before： "+dataBuf[0]+dataBuf[1]+dataBuf[2]);
	                		temp =new String[6];
	                		/*for(int i=0;i<3;i++)
	                		{
	                		System.out.printf("%x ",dataBuf[i]);
	                		}
	                		System.out.println("--------------");*/
	                		dataBuf[0]=(byte) (dataBuf[0]-0x33);
	                		dataBuf[1]=(byte) (dataBuf[1]-0x33);
	                		dataBuf[2]=(byte) (dataBuf[2]-0x33);
	                		/*for(int i=0;i<3;i++)
	                		{
	                		System.out.printf("%x ",dataBuf[i]);
	                		}
	                		System.out.println("------------------");*/
	                		
	                		if(dataBuf[2]!=0)
                			{
	                			temp[2] = String.format("%x",dataBuf[2]);
                				data.append(temp[2]);
                			}else if(dataBuf[2]==0)
                			{
                				data.append('0');
                			}
	                		data.append('.');
	                		for(int i=1;i>=0;i--)
	                		{
	                					
	                			if(dataBuf[i]<0x10)
		                		{	                			
		                			temp[i] = String.format("%x",dataBuf[i]);
		                			data.append("0"+temp[i]);
		                			
		                		}else{
		                			temp[i] = String.format("%x",dataBuf[i]);
		                			data.append(temp[i]);
		                		}
	                		}
	                		
	                		
	                		
	                	}else if(datalen == 4)
	                	{
	                		temp =new String[6];
	                	/*	for(int i=0;i<2;i++)
	                		{
	                		System.out.printf("%x ",dataBuf[i]);
	                		}
	                		System.out.println("------------------");*/
	                		dataBuf[0]=(byte) (dataBuf[0]-0x33);
	                		dataBuf[1]=(byte) (dataBuf[1]-0x33);
	                		/*for(int i=0;i<2;i++)
	                		{
	                		System.out.printf("%x ",dataBuf[i]);
	                		}
	                		System.out.println("------------------");*/
	                		
	                		if((dataBuf[0]==0)&&(dataBuf[1]==0))
	                		{
	                			data.append('0');	
	                		}else{
		                		if(oderBuf[0]>0x20)
		                		{
		                			if(dataBuf[1]==0)
			                		{
		                				data.append('0');
			                		}else 
			                		{
			                			temp[1] = String.format("%x",dataBuf[1]);
			                			data.append(temp[1]);
			                		}
		                			
		                			    data.append('.');
		                			
		                			    
		                		}else{
		                			if(dataBuf[1]!= 0)
				                		{
				                			temp[1] = String.format("%x",dataBuf[1]);
				                			data.append(temp[1]);
				                			
				                		}
		                			}
		                		
		                		if((dataBuf[0]<0x10)&&(dataBuf[0]>0))
		                		{
		                			temp[0] = String.format("%x",dataBuf[0]);
		                			data.append("0"+temp[0]);
		                		}else if(dataBuf[0]==0){
		                			
		                			data.append('0');
		                		}
		                		else{
		                			temp[0] = String.format("%x",dataBuf[0]);
		                			data.append(temp[0]);
		                		}
	     
	                		}
	                		try {
								byte[] midbytes=data.toString().getBytes("UTF8");
								System.out.println("-----------TEST--------------------------"); 
								double dataVlue =0;
								int j=0;
								double vlue=1;
								for(int i=midbytes.length-1;i>=0;i--)
								{
									if(midbytes[i]==0x2e)
									{
										switch(i)
										{
											case 0:vlue=1;
											break;
											case 1:vlue=0.1;
											break;
											case 2:vlue=0.01;
											break;
											case 3:vlue=0.001;
											break;
										}
									}
									dataVlue = dataVlue+(midbytes[i]-0x30)*(10^j);
									j++;
								}
								//dataVlue=dataVlue*vlue;
								System.out.printf("%f",dataVlue);
								System.out.println("-----------TEST--END------------------------"); 
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                	}
	                	System.out.println("数据： "+data); 
	                	//savaOk = DatalibPro.SaveDataTest(data,emeterNum,oderNum);
					}
					//ProtocolSendData(sendBuf);
				}else if((receiveBuf[6]&0xff) == 0xA8)        //376.1
				{
					//System.out.printf("-------- %x    %x\n",receiveBuf[1],receiveBuf[2] ); 
					//System.out.printf("-------- %x    %x\n",receiveBuf[1]>>2,(receiveBuf[2]&0x03)<<6 ); 
					
					System.out.printf("0xA8数据长度： %d\n",len); 
					int FN=receiveBuf[17]*8+Get8421Code(receiveBuf[16]);          //FN
					int pn=(receiveBuf[15]-1)*8+Get8421Code(receiveBuf[14]);      //PN
					
					System.out.printf("-------- FN : %d   PN: %d\n",FN,pn); 
					
					
					if(FN==25)
					{
						
						
//						dataBuf[0]=receiveBuf[26];
//						dataBuf[1]=receiveBuf[27];
//						dataBuf[2]=receiveBuf[28];
//						System.out.println("当A相有功功率： "+ChangeData(dataBuf,3)); 
						
						for(int i=0;i<8;i++)//7相当前有无功功率总ABC相
						{
							dataBuf[0]=receiveBuf[23+i*3];
							dataBuf[1]=receiveBuf[24+i*3];
							dataBuf[2]=receiveBuf[25+i*3];
							fianlData[i]=ChangeData(dataBuf,3);
							
							System.out.printf("*ID:%d", i);
							System.out.println("当前数据： "+fianlData[i]);	
						}	
						for(int i=0;i<7;i++)//7相当总功率因数电压总ABC相
						{
							dataBuf[0]=receiveBuf[47+i*2];
							dataBuf[1]=receiveBuf[48+i*2];
							fianlData[i+8]=ChangeData(dataBuf,2);
							
							System.out.printf("*ID:%d", i+8);
							System.out.println("当前数据： "+fianlData[i+8]);	
							
						}
						
						for(int i=0;i<3;i++)//3当前电流ABC
						{
							dataBuf[0]=receiveBuf[61+i*3];
							dataBuf[1]=receiveBuf[62+i*3];
							dataBuf[2]=receiveBuf[63+i*3];
							fianlData[i+8+7]=ChangeData(dataBuf,3);
							
							System.out.printf("*ID:%d", i+15);
							System.out.println("当前数据： "+fianlData[i+15]);		
							
						}	
						savaOk=129;
					}else if(FN==129)
					{
						
						
						for(int i=0;i<5;i++)//
						{
							dataBuf[0]=receiveBuf[24+i*5];
							dataBuf[1]=receiveBuf[25+i*5];
							dataBuf[2]=receiveBuf[26+i*5];
							dataBuf[3]=receiveBuf[27+i*5];
							dataBuf[4]=receiveBuf[28+i*5];
							
							fianlData[i+18]=ChangeData(dataBuf,5);
							
							System.out.printf("*ID:%d", i);
							System.out.println("当前数据： "+fianlData[i+18]);	
						}
						
						
						//DatalibPro.SaveData(fianlData,pn);
						//DatalibPro.GetVoltageCurrentRatio("10000100");//获得变比
							
						savaOk=130;
					}else if(FN==130)
					{
						
						
						for(int i=0;i<5;i++)//
						{
							dataBuf[0]=receiveBuf[24+i*4];
							dataBuf[1]=receiveBuf[25+i*4];
							dataBuf[2]=receiveBuf[26+i*4];
							dataBuf[3]=receiveBuf[27+i*4];
							
							fianlData[i+23]=ChangeData(dataBuf,6);
							
							System.out.printf("*ID:%d", i);
							System.out.println("当前数据： "+fianlData[i+23]);	
						}
						
						
					//	DatalibPro.SaveData(fianlData,pn);
						//DatalibPro.GetVoltageCurrentRatio("10000100");//获得变比
							
						savaOk=10;
					}
					
				}//A8
			  }
			}
		}
	
	return savaOk;
	}
	
	private String ChangeData(byte[] dataBuf,int type)
	{
		String dataValue=null;
		//dataBuf[3]=receiveBuf[36];
    	//float value =(float) 47.76
		//System.out.printf("dataBuf.length数据长度： %d\n",dataBuf.length); 
		if(type==3)
		{
	    	StringBuffer data=new StringBuffer(dataBuf.length*2+1);
	    	String[] temp =new String[6];
	    	for(int i=2;i>=0;i--)
	    	{
	    		//temp[i] = String.format("%x",dataBuf[i]);
	    		if((dataBuf[i]<0x10)&&(dataBuf[i]>0))
        		{
        			temp[i] = String.format("%x",dataBuf[i]);
        			data.append("0"+temp[i]);
        			
        		}else{
        			temp[i] = String.format("%x",dataBuf[i]);
        			data.append(temp[i]);
        		}
	    		if(i==2)
	    		{
	    			data.append('.');
	    		}
	    	}
//			data.append(temp[2]);
//			data.append('.');
//			data.append(temp[1]);
//			data.append(temp[0]);
			dataValue =data.toString();
		}else if(type==2)
		{
			//System.out.printf("当前正向有功数据： "+dataBuf[0]+"-------------"+dataBuf[1]); 
			//System.out.printf("当前正向有功数据：%x   %x",dataBuf[0],dataBuf[1]); 
			StringBuffer data=new StringBuffer(dataBuf.length*2+1);
			String[] temp =new String[6];
			int[] tmp=new int[2];
			tmp[1] = (dataBuf[1]<<4) | ((dataBuf[0]&0xf0)>>4);
			tmp[0] = dataBuf[0]&0x0f;
			for(int i=1;i>=0;i--)
	    	{
//	    		if((tmp[i]<0x10)&&(tmp[i]>0))
//        		{
//        			temp[i] = String.format("%x",tmp[i]);
//        			data.append("0"+temp[i]);
//        		}else{
        			temp[i] = String.format("%x",tmp[i]);
        			data.append(temp[i]);
        	//	}
	    		if(i==1)
	    		{
	    			data.append('.');
	    		}
	    	}
			dataValue =data.toString();	
		}else if(type==4)
		{
			//System.out.printf("当前正向有功数据：%x   %x    %x \n",dataBuf[0],dataBuf[1],dataBuf[2]); 
			byte tmp0 = (byte) (dataBuf[1]&0x0f);
			byte tmp1 = (byte) ((dataBuf[1]&0xf0)>>4);
			StringBuffer data=new StringBuffer(dataBuf.length*2+1);
			String[] temp =new String[6];
			for(int i=2;i>=0;i--)
	    	{
	    		//temp[i] = String.format("%x",dataBuf[i]);
				if(i==1)
	    		{
					data.append(tmp1);
	    			data.append('.');
	    			data.append(tmp0);
	    			
	    		}else if((dataBuf[i]<0x10)&&(dataBuf[i]>0))
        		{
        			temp[i] = String.format("%x",dataBuf[i]);
        			data.append("0"+temp[i]);
        		}else{
        			temp[i] = String.format("%x",dataBuf[i]);
        			data.append(temp[i]);
        		}
	    		
	    	}
			
			dataValue =data.toString();	
		}else if(type==5)
		{
    	StringBuffer data=new StringBuffer(dataBuf.length*2+1);
    	String[] temp =new String[6];
    	for(int i=4;i>=0;i--)
    	{
    		//temp[i] = String.format("%x",dataBuf[i]);
    		if((dataBuf[i]<0x10)&&(dataBuf[i]>0))
    		{
    			temp[i] = String.format("%x",dataBuf[i]);
    			data.append("0"+temp[i]);
    			
    		}else{
    			temp[i] = String.format("%x",dataBuf[i]);
    			data.append(temp[i]);
    		}
    		if(i==2)
    		{
    			data.append('.');
    		}
    	}
//		data.append(temp[2]);
//		data.append('.');
//		data.append(temp[1]);
//		data.append(temp[0]);
		dataValue =data.toString();
	}else if(type==6)
		{
    	StringBuffer data=new StringBuffer(dataBuf.length*2+1);
    	String[] temp =new String[6];
    	for(int i=3;i>=0;i--)
    	{
    		//temp[i] = String.format("%x",dataBuf[i]);
    		if((dataBuf[i]<0x10)&&(dataBuf[i]>0))
    		{
    			temp[i] = String.format("%x",dataBuf[i]);
    			data.append("0"+temp[i]);
    			
    		}else{
    			temp[i] = String.format("%x",dataBuf[i]);
    			data.append(temp[i]);
    		}
    		if(i==1)
    		{
    			data.append('.');
    		}
    	}
//		data.append(temp[2]);
//		data.append('.');
//		data.append(temp[1]);
//		data.append(temp[0]);
		dataValue =data.toString();
	}
		//System.out.println("当前正向有功数据： "+data.toString()); 
		//dataValue =data.toString();
		
		
		
		
		return dataValue;
	}
	
	
	
	
	
	private int Get8421Code(int value)
	{
		int nun=0;
		switch (value) {
		case 0x01:
			nun =1;
			break;
		case 0x02:
			nun =2;
			break;
		case 0x04:
			nun =3;
			break;
		case 0x08:
			nun =4;
			break;
		case 0x10:
			nun =5;
			break;
		case 0x20:
			nun =6;
			break;
		case 0x40:
			nun =7;
			break;
		case 0x80:
			nun =8;
			break;

		default:
			break;
		}
		
		return nun;
	}
	
	public void serverSendData()
	{
		System.out.print("地址");
		for(int i=0;i<4;i++)
		{
			if((address[i]&0xff) < 0x10)
			{
				System.out.printf("0%x ",address[i]); 
			}
			else{
				System.out.printf("%x ",address[i]); 
			}
		}
		System.out.println();
		
		
	}

}
