
package com.hcentive.hackathon.core.dao;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.hcentive.hackathon.core.domain.Incident;

/**
 * 
 * 
 * @author Nitin.Gupta
 */
@Component
public class MorphiaDataStore extends BaseDataStore<Object, Object> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vinayak.marketsense.core.dao.mongodb.BaseMongoDao#init()
	 */
	@PostConstruct
	@Override
	public void init() {
		
		super.init();
		morphia.map(Incident.class);
		
		ds.ensureIndexes(); //creates all defined with @Indexed
		ds.ensureCaps();
	}
}
