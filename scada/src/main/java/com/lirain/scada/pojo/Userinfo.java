package com.lirain.scada.pojo;
public class Userinfo {
	
	
	private Integer id_base;
	
	private String name;
	
	private String address;
	
	private String userphone;
	
	private String useropendate;
	
	private String meterno;
	
	private String metermodelno;
	
	private String assetno;
	
	private String parano;
	
	private String priceno;
	
	private String userstate;
	
	private String operatorno;
	
	private Integer usergroup;
	
	private Integer id;
	
	private Integer pId;
	
	private Integer open;
	
	private Integer xflag;
	
	private Integer isParent;
	
	private String userno;

	private Integer ttyno ;
	
	private Integer ttyid ;
	
	private Integer ttytype;
	
	private Integer ttycpy;
	
	private String statecode;
	
	private Integer celltype;
	
	private Integer metrytpe;
	
	private Integer communicateset;
	
	private String idname;
	
	private Integer groupid;
	
	private String groupname;
	
	private Integer ua_flag;
	
	private Integer ub_flag;
	
	private Integer uc_flag;
	
	private Integer ia_flag;
	
	private Integer ib_flag;
	
	private Integer ic_flag;
	
	private Integer dqwggl_flag;
	
	private Integer dqyggl_flag;
	
	private Integer zxygzdn_flag;
	
	private Integer zxwgzdn_flag;
	
	private Integer pn;
	
	private Integer dybb;
	
	private Integer dlbb;
	
	public Userinfo()
	{
		
	}
	
	public Userinfo(String name, String address, String statecode, Integer ttyno, Integer ttyid,Integer ttytype, 
			Integer ttycpy, String userno)
	{
		this.address = address;
		this.statecode = statecode;
		this.name = name;
		this.userno = userno;
		this.ttyid = ttyid;
		this.ttycpy = ttycpy;
		this.ttyno = ttyno;
		this.ttytype = ttytype;
	}
	
	public Userinfo(String name, String address, Integer metrytpe, Integer communicateset, String idname,
			String userno)
	{
		this.name = name;
		this.address = address;
		this.metrytpe = metrytpe;
		this.communicateset = communicateset;
		this.idname = idname;
		this.userno = userno;
	}
	
	public Integer getId_base() {
		return id_base;
	}

	public void setId_base(Integer id_base) {
		this.id_base = id_base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUseropendate() {
		return useropendate;
	}

	public void setUseropendate(String useropendate) {
		this.useropendate = useropendate;
	}

	public String getMeterno() {
		return meterno;
	}

	public void setMeterno(String meterno) {
		this.meterno = meterno;
	}

	public String getMetermodelno() {
		return metermodelno;
	}

	public void setMetermodelno(String metermodelno) {
		this.metermodelno = metermodelno;
	}

	public String getAssetno() {
		return assetno;
	}

	public void setAssetno(String assetno) {
		this.assetno = assetno;
	}

	public String getParano() {
		return parano;
	}

	public void setParano(String parano) {
		this.parano = parano;
	}

	public String getPriceno() {
		return priceno;
	}

	public void setPriceno(String priceno) {
		this.priceno = priceno;
	}

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		this.userstate = userstate;
	}

	public String getOperatorno() {
		return operatorno;
	}

	public void setOperatorno(String operatorno) {
		this.operatorno = operatorno;
	}

	public Integer getUsergroup() {
		return usergroup;
	}

	public void setUsergroup(Integer usergroup) {
		this.usergroup = usergroup;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public Integer getXflag() {
		return xflag;
	}

	public void setXflag(Integer xflag) {
		this.xflag = xflag;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Integer getTtyno() {
		return ttyno;
	}

	public void setTtyno(Integer ttyno) {
		this.ttyno = ttyno;
	}

	public Integer getTtyid() {
		return ttyid;
	}

	public void setTtyid(Integer ttyid) {
		this.ttyid = ttyid;
	}

	public Integer getTtytype() {
		return ttytype;
	}

	public void setTtytype(Integer ttytype) {
		this.ttytype = ttytype;
	}

	public Integer getTtycpy() {
		return ttycpy;
	}

	public void setTtycpy(Integer ttycpy) {
		this.ttycpy = ttycpy;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public Integer getCelltype() {
		return celltype;
	}

	public void setCelltype(Integer celltype) {
		this.celltype = celltype;
	}

	public Integer getMetrytpe() {
		return metrytpe;
	}

	public void setMetrytpe(Integer metrytpe) {
		this.metrytpe = metrytpe;
	}

	public Integer getCommunicateset() {
		return communicateset;
	}

	public void setCommunicateset(Integer communicateset) {
		this.communicateset = communicateset;
	}

	public String getIdname() {
		return idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getUa_flag() {
		return ua_flag;
	}

	public void setUa_flag(Integer ua_flag) {
		this.ua_flag = ua_flag;
	}

	public Integer getUb_flag() {
		return ub_flag;
	}

	public void setUb_flag(Integer ub_flag) {
		this.ub_flag = ub_flag;
	}

	public Integer getUc_flag() {
		return uc_flag;
	}

	public void setUc_flag(Integer uc_flag) {
		this.uc_flag = uc_flag;
	}

	public Integer getIa_flag() {
		return ia_flag;
	}

	public void setIa_flag(Integer ia_flag) {
		this.ia_flag = ia_flag;
	}

	public Integer getIb_flag() {
		return ib_flag;
	}

	public void setIb_flag(Integer ib_flag) {
		this.ib_flag = ib_flag;
	}

	public Integer getIc_flag() {
		return ic_flag;
	}

	public void setIc_flag(Integer ic_flag) {
		this.ic_flag = ic_flag;
	}

	public Integer getDqwggl_flag() {
		return dqwggl_flag;
	}

	public void setDqwggl_flag(Integer dqwggl_flag) {
		this.dqwggl_flag = dqwggl_flag;
	}

	public Integer getDqyggl_flag() {
		return dqyggl_flag;
	}

	public void setDqyggl_flag(Integer dqyggl_flag) {
		this.dqyggl_flag = dqyggl_flag;
	}

	public Integer getZxygzdn_flag() {
		return zxygzdn_flag;
	}

	public void setZxygzdn_flag(Integer zxygzdn_flag) {
		this.zxygzdn_flag = zxygzdn_flag;
	}

	public Integer getZxwgzdn_flag() {
		return zxwgzdn_flag;
	}

	public void setZxwgzdn_flag(Integer zxwgzdn_flag) {
		this.zxwgzdn_flag = zxwgzdn_flag;
	}

	public Integer getDybb() {
		return dybb;
	}

	public void setDybb(Integer dybb) {
		this.dybb = dybb;
	}

	public Integer getDlbb() {
		return dlbb;
	}

	public void setDlbb(Integer dlbb) {
		this.dlbb = dlbb;
	}
	public Integer getPn() {
		return pn;
	}

	public void setPn(Integer pn) {
		this.pn = pn;
	}
	
}
