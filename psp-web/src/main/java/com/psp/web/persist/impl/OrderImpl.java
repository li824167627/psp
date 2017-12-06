package com.psp.web.persist.impl;

import org.springframework.stereotype.Repository;

import com.psp.web.persist.dao.OrderDao;

@Repository
public class OrderImpl extends BaseImpl implements OrderDao {
	
	final String NAME_SPACE = NAME_SPACE_HEADER + ".OrderMapper";


}
