package com.psp.admin.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.psp.admin.cache.dao.AreaCache;
import com.psp.admin.controller.res.bean.RAreaListBean;
import com.psp.admin.model.AreaBean;
import com.psp.admin.persist.dao.AreaDao;
import com.psp.admin.service.AreaService;
import com.psp.util.StringUtil;

@Service
public class AreaServiceImpl implements AreaService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AreaDao areaImpl;

	@Autowired
	AreaCache areaCacheImpl;

	@Override
	public RAreaListBean getAreas() {
		RAreaListBean result = new RAreaListBean();
		String areapcd = areaCacheImpl.getAreaPCD();
		JSONArray areaList = new JSONArray();
		if (!StringUtil.isEmpty(areapcd)) {
			areaList = JSON.parseArray(areapcd);
		} else {
			List<AreaBean> lists = areaImpl.selectListByPCD();
			if (lists != null && lists.size() > 0) {
				for (AreaBean pArea : lists) {
					if (pArea.getSubArea() != null && pArea.getSubArea().size() > 0) {

						JSONObject pAreaList = new JSONObject();
						pAreaList.put("label", pArea.getName());
						pAreaList.put("value", pArea.getId());
						JSONArray pSubList = new JSONArray();
						for (AreaBean cArea : pArea.getSubArea()) {
							JSONObject cArealist = new JSONObject();
							cArealist.put("label", cArea.getName());
							cArealist.put("value", cArea.getId());
							pSubList.add(cArealist);
							if (cArea.getSubArea() != null && cArea.getSubArea().size() > 0) {
								JSONArray cSubList = new JSONArray();
								for (AreaBean dArea : cArea.getSubArea()) {
									JSONObject dAreaList = new  JSONObject();
									dAreaList.put("label", dArea.getName());
									dAreaList.put("value", dArea.getId());
									cSubList.add(dAreaList);
								}
								cArealist.put("children", cSubList);
							}
						}
						pAreaList.put("children", pSubList);
						areaList.add(pAreaList);
					}
				}
				areaCacheImpl.setAreaPCD(JSON.toJSONString(areaList));
			}
		}

		result.setArea(areaList);
		return result;
	}
	
}
