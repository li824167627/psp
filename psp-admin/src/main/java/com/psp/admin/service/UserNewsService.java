package com.psp.admin.service;

import com.psp.admin.controller.res.bean.RUserNewsBean;
import com.psp.admin.service.res.PageResult;

public interface UserNewsService {

	PageResult<RUserNewsBean> getUserNews(String sid, int page, int pageSize, int stype, String key, String uid);

	boolean add(String sid, String uid, String label, String content);

}
