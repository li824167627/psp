package com.psp.provider.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.psp.provider.cache.dao.AreaCache;
import com.psp.provider.controller.res.bean.RAreaListBean;
import com.psp.provider.model.AreaBean;
import com.psp.provider.persist.dao.AreaDao;
import com.psp.provider.service.AreaService;
import com.psp.provider.service.impl.res.PageResult;
import com.psp.util.StringUtil;

@Service
public class AreaServiceImpl implements AreaService {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	AreaDao areaImpl;

	@Autowired
	AreaCache areaCacheImpl;

	@Override
	public PageResult<RAreaListBean> getAreas() {
		PageResult<RAreaListBean> result = new PageResult<>();

		String areapcd = areaCacheImpl.getAreaPCD();
		List<RAreaListBean> resultList;
		if (!StringUtil.isEmpty(areapcd)) {
			resultList = JSON.parseArray(areapcd, RAreaListBean.class);
		} else {
			List<AreaBean> lists = areaImpl.selectListByPCD();
			resultList = new ArrayList<>();
			if (lists != null && lists.size() > 0) {
				for (AreaBean pArea : lists) {
					if (pArea.getSubArea() != null && pArea.getSubArea().size() > 0) {
						RAreaListBean pAreaList = new RAreaListBean();
						pAreaList.setLabel(pArea.getName());
						pAreaList.setValue(pArea.getId());
						List<RAreaListBean> pSubList = new ArrayList<>();
						for (AreaBean cArea : pArea.getSubArea()) {
							RAreaListBean cArealist = new RAreaListBean();
							cArealist.setLabel(cArea.getName());
							cArealist.setValue(cArea.getId());
							pSubList.add(cArealist);
							if (cArea.getSubArea() != null && cArea.getSubArea().size() > 0) {
								List<RAreaListBean> cSubList = new ArrayList<>();
								cArealist.setChildren(cSubList);
								for (AreaBean dArea : cArea.getSubArea()) {
									RAreaListBean dAreaList = new RAreaListBean();
									dAreaList.setLabel(dArea.getName());
									dAreaList.setValue(dArea.getId());
									cSubList.add(dAreaList);
								}
							}
						}
						pAreaList.setChildren(pSubList);
						resultList.add(pAreaList);
					}
				}
				areaCacheImpl.setAreaPCD(JSON.toJSON(resultList).toString());
			}
		}

		result.setData(resultList);
		return result;
	}
	
}
