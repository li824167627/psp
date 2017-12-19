package com.psp.admin.model.excel;

import com.psp.util.excel.ExcelAnnotation;

/**
 * 导入客户字段
 * @author chengl
 *
 */
public class UserInfoBean {  
    @ExcelAnnotation(exportName = "销售")  
    private String seller;  
    @ExcelAnnotation(exportName = "公司名称")  
    private String company;  
    @ExcelAnnotation(exportName = "客户")  
    private String user;  
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}  
