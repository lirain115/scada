package com.lirain.scada.mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.lirain.scada.pojo.Userinfo;
import com.lirain.scada.service.UserinfoService;

public class MinaServerHanlder extends IoHandlerAdapter {
	public static Logger logger = Logger.getLogger(MinaServerHanlder.class);
	public static ProtocolProcess DataPro =new ProtocolProcess();
	 @Autowired
	 private UserinfoService userInfo;
	 
	// @Autowired
	
	
	 
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)throws Exception {
		cause.printStackTrace();
	}
	
	
	@Override
	public void messageReceived(IoSession session, Object message)throws Exception {
		 
		logger.info("-------ID:"+session.getId());
		 IoBuffer ioBuffer = (IoBuffer)message;   
	       byte[] receiveBuf = new byte[ioBuffer.limit()];   
	       ioBuffer.get(receiveBuf); 
	       logger.info("收到客户端"+ioBuffer.limit()+"字节");
	       for(int i=0;i<ioBuffer.limit();i++)
	       {
	    	   System.out.printf(" %x ",receiveBuf[i]); 
	       }
	       System.out.println();
	       if((receiveBuf[0]==0x68) && (receiveBuf[5]==0x68))//数据接收校验
           {
           	//int a=receiveBuf[1]>>2;
       		int len =((((receiveBuf[2]&0xff)<<8)+(receiveBuf[1]&0xff))>>2)+8;
       		System.out.printf("\n数据长度:(%x--%x)%d\n",receiveBuf[2],receiveBuf[1],len);
       		//len =(len>>2)+8;
       		//System.out.printf("\n数据长度:%d\n",len);
       		int check=0;
       			for (int i=6;i<len-2;i++)
       			{
       				check = (byte) (check+receiveBuf[i]);
       			}
       			System.out.println();
       			System.out.printf("校验位:%x\n",check&0xff);
       			//System.out.println();
       			//*******************************************************
       			//*****
       			//*****
       			//********************************************************
       			if((receiveBuf[6]&0xff) == 0xC9)
       			{
       				//flag_ack =1;
       			}else
       			{
       				//flag_ack=0;	
       			}
       		
       			if (check == receiveBuf[len-2])
       			{
       				logger.info("--------------数据校验正确！-----------------------");

       				//接收数据处理
       				// sendBuf = new byte[BUFSIZE];
       			int flag_back = DataPro.DataProcess(receiveBuf,len);
				 System.out.println("sendMsgSize: "+DataPro.sendMsgSize+" flag_back: "+flag_back);
					System.out.print("发送数据：");
				 for(int i=0;i<DataPro.sendMsgSize;i++)
               {             
					 DataPro.sendBuf[i]=(byte) (DataPro.sendBuf[i]&0xff);
					if((DataPro.sendBuf[i]&0xff) < 0x10)
					{
               	System.out.printf("0%x ",DataPro.sendBuf[i]); 
					}
					else{
						System.out.printf("%x ",DataPro.sendBuf[i]); 
						
					}
               }
				 System.out.println();
       				
       				//DataPro.test();
       				// System.out.println("sendMsgSize: "+DataPro.sendMsgSize+" flag_back: "+flag_back);
       			}else{
       	           	
       	           	//数据不正确退出
       	        	logger.info("数据不正确退出！");
       	           	//break;
       	           	
       	           }

           }
	       session.write(IoBuffer.wrap(DataPro.sendBuf));   //数据发送
		  
	       Userinfo info =userInfo.getUserById(1);
			logger.info(JSON.toJSONString(info));
	  //String msg=new String(b);

	//  System.out.print("收到客户端发来的消息为" + "  " + msg);
        
        
//		String str = message.toString();
//		 
//	        if( str.trim().equalsIgnoreCase("quit") ) {
//	            session.close(Boolean.TRUE);
//	            return;
//	        }
//	        Date date = new Date();
//	        session.write( date.toString() );
//		logger.info("server -消息已经接收到!"+message);
//		Userinfo info =userInfo.getUserById(3);
//		
//		Userinfo info2 =userInfo.getUserById(8);
//		
//		logger.info(JSON.toJSONString(info));
//		logger.info(JSON.toJSONString(info2));
	}


	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("server -消息已经发出");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("server-session关闭连接断开");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("server-session创建，建立连接");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)throws Exception {
		logger.info("server-服务端进入空闲状态..");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("server-服务端与客户端连接打开...");
		logger.info("server -连接地址："+session.getRemoteAddress());
	}
}
