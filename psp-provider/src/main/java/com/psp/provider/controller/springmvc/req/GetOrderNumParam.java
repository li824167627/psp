package com.psp.provider.controller.springmvc.req;


import javax.validation.constraints.Pattern;

/**
 * 获取工单数量
 **/
public class GetOrderNumParam {
	@Pattern(regexp = "^0|1|1|3$", message = "状态错误：0:全部1进行中2已完成3已关闭")
	private String stage; // 搜索工单状态，0:全部1进行中2已完成3已关闭

	public void setStage(String stage) {
 		this.stage = stage;
	}

	public String getStage() {
 		return stage;
	}

}
