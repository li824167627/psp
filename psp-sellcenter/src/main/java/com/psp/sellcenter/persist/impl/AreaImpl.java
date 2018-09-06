package com.psp.sellcenter.persist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.psp.sellcenter.model.AreaBean;
import com.psp.sellcenter.persist.dao.AreaDao;

@Repository
public class AreaImpl extends BaseImpl implements AreaDao {

	final String NAME_SPACE = NAME_SPACE_HEADER + ".AreaMapper";

	@Override
	public List<AreaBean> selectListByPCD() {
		return sqlSessionTemplate.selectList(NAME_SPACE + ".selectListByPCD");
	}

	@Override
	public AreaBean selectById(String id) {
		return sqlSessionTemplate.selectOne(NAME_SPACE + ".selectListById", id);
	}

}
