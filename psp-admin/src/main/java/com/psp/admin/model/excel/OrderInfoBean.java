package com.psp.admin.model.excel;

import com.psp.util.excel.ExcelAnnotation;

/**
 * 导入订单字段
 * @author chengl
 *
 */
public class OrderInfoBean {  
    @ExcelAnnotation(exportName = "销售")  
    private String seller;  
    @ExcelAnnotation(exportName = "用户")  
    private String user;  
    @ExcelAnnotation(exportName = "产品名称")  
    private String content;  
    @ExcelAnnotation(exportName = "服务商")  
    private String provider;  
    @ExcelAnnotation(exportName = "金额")  
    private Double money;
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}  
