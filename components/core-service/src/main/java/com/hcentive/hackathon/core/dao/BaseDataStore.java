
package com.hcentive.hackathon.core.dao;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.MapreduceResults;
import org.mongodb.morphia.MapreduceType;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.logging.slf4j.SLF4JLoggerImplFactory;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryFactory;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.BeanInitializationException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

/**
 * @author Nitin.Gupta
 * 
 */
public abstract class BaseDataStore<T, V> implements Datastore {

	protected Datastore ds;
	protected Morphia morphia;

	public void init() {
		
		// register logger
		MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);
		
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("localhost");
			morphia = new Morphia();
			ds = morphia.createDatastore(mongoClient, "hcMonitor");

		} catch (UnknownHostException e) {
			throw new BeanInitializationException(
					"exception occurred while initializing MongoDataStore", e);
		}
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#getKey(java.lang.Object)
	 */
	public <T> Key<T> getKey(T entity) {
		return ds.getKey(entity);
	}

	/**
	 * @param keyOrEntity
	 * @return
	 * @see org.mongodb.morphia.Datastore#exists(java.lang.Object)
	 */
	public Key<?> exists(Object keyOrEntity) {
		return ds.exists(keyOrEntity);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(java.lang.Class,
	 *      java.lang.Object)
	 */
	public <T, V> WriteResult delete(Class<T> clazz, V id) {
		return ds.delete(clazz, id);
	}

	/**
	 * @param clazz
	 * @param ids
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(java.lang.Class,
	 *      java.lang.Iterable)
	 */
	public <T, V> WriteResult delete(Class<T> clazz, Iterable<V> ids) {
		return ds.delete(clazz, ids);
	}

	/**
	 * @param q
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(org.mongodb.morphia.query.Query)
	 */
	public <T> WriteResult delete(Query<T> q) {
		return ds.delete(q);
	}

	/**
	 * @param q
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(org.mongodb.morphia.query.Query,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> WriteResult delete(Query<T> q, WriteConcern wc) {
		return ds.delete(q, wc);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(java.lang.Object)
	 */
	public <T> WriteResult delete(T entity) {
		return ds.delete(entity);
	}

	/**
	 * @param entity
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#delete(java.lang.Object,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> WriteResult delete(T entity, WriteConcern wc) {
		return ds.delete(entity, wc);
	}

	/**
	 * @param clazz
	 * @return
	 * @see org.mongodb.morphia.Datastore#find(java.lang.Class)
	 */
	public <T> Query<T> find(Class<T> clazz) {
		return ds.find(clazz);
	}

	/**
	 * @param clazz
	 * @param property
	 * @param value
	 * @return
	 * @see org.mongodb.morphia.Datastore#find(java.lang.Class,
	 *      java.lang.String, java.lang.Object)
	 */
	public <T, V> Query<T> find(Class<T> clazz, String property, V value) {
		return ds.find(clazz, property, value);
	}

	/**
	 * @param clazz
	 * @param property
	 * @param value
	 * @param offset
	 * @param size
	 * @return
	 * @see org.mongodb.morphia.Datastore#find(java.lang.Class,
	 *      java.lang.String, java.lang.Object, int, int)
	 */
	public <T, V> Query<T> find(Class<T> clazz, String property, V value,
			int offset, int size) {
		return ds.find(clazz, property, value, offset, size);
	}

	/**
	 * @param clazz
	 * @param ids
	 * @return
	 * @see org.mongodb.morphia.Datastore#get(java.lang.Class,
	 *      java.lang.Iterable)
	 */
	public <T, V> Query<T> get(Class<T> clazz, Iterable<V> ids) {
		return ds.get(clazz, ids);
	}

	/**
	 * @param clazz
	 * @param id
	 * @return
	 * @see org.mongodb.morphia.Datastore#get(java.lang.Class, java.lang.Object)
	 */
	public <T, V> T get(Class<T> clazz, V id) {
		return ds.get(clazz, id);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#get(java.lang.Object)
	 */
	public <T> T get(T entity) {
		return ds.get(entity);
	}

	/**
	 * @param clazz
	 * @param keys
	 * @return
	 * @see org.mongodb.morphia.Datastore#getByKeys(java.lang.Class,
	 *      java.lang.Iterable)
	 */
	public <T> List<T> getByKeys(Class<T> clazz, Iterable<Key<T>> keys) {
		return ds.getByKeys(clazz, keys);
	}

	/**
	 * @param keys
	 * @return
	 * @see org.mongodb.morphia.Datastore#getByKeys(java.lang.Iterable)
	 */
	public <T> List<T> getByKeys(Iterable<Key<T>> keys) {
		return ds.getByKeys(keys);
	}

	/**
	 * @param clazz
	 * @param key
	 * @return
	 * @see org.mongodb.morphia.Datastore#getByKey(java.lang.Class,
	 *      org.mongodb.morphia.Key)
	 */
	public <T> T getByKey(Class<T> clazz, Key<T> key) {
		return ds.getByKey(clazz, key);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#getCount(java.lang.Object)
	 */
	public <T> long getCount(T entity) {
		return ds.getCount(entity);
	}

	/**
	 * @param clazz
	 * @return
	 * @see org.mongodb.morphia.Datastore#getCount(java.lang.Class)
	 */
	public <T> long getCount(Class<T> clazz) {
		return ds.getCount(clazz);
	}

	/**
	 * @param query
	 * @return
	 * @see org.mongodb.morphia.Datastore#getCount(org.mongodb.morphia.query.Query)
	 */
	public <T> long getCount(Query<T> query) {
		return ds.getCount(query);
	}

	/**
	 * @param entities
	 * @return
	 * @see org.mongodb.morphia.Datastore#save(java.lang.Iterable)
	 */
	public <T> Iterable<Key<T>> save(Iterable<T> entities) {
		return ds.save(entities);
	}

	/**
	 * @param entities
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#save(java.lang.Iterable,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> Iterable<Key<T>> save(Iterable<T> entities, WriteConcern wc) {
		return ds.save(entities, wc);
	}

	/**
	 * @param entities
	 * @return
	 * @see org.mongodb.morphia.Datastore#save(java.lang.Object[])
	 */
	public <T> Iterable<Key<T>> save(T... entities) {
		return ds.save(entities);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#save(java.lang.Object)
	 */
	public <T> Key<T> save(T entity) {
		return ds.save(entity);
	}

	/**
	 * @param entity
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#save(java.lang.Object,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> Key<T> save(T entity, WriteConcern wc) {
		return ds.save(entity, wc);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.mongodb.morphia.Datastore#merge(java.lang.Object)
	 */
	public <T> Key<T> merge(T entity) {
		return ds.merge(entity);
	}

	/**
	 * @param entity
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#merge(java.lang.Object,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> Key<T> merge(T entity, WriteConcern wc) {
		return ds.merge(entity, wc);
	}

	/**
	 * @param ent
	 * @param ops
	 * @return
	 * @see org.mongodb.morphia.Datastore#update(java.lang.Object,
	 *      org.mongodb.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults update(T ent, UpdateOperations<T> ops) {
		return ds.update(ent, ops);
	}

	/**
	 * @param key
	 * @param ops
	 * @return
	 * @see org.mongodb.morphia.Datastore#update(org.mongodb.morphia.Key,
	 *      org.mongodb.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults update(Key<T> key, UpdateOperations<T> ops) {
		return ds.update(key, ops);
	}

	/**
	 * @param query
	 * @param ops
	 * @return
	 * @see org.mongodb.morphia.Datastore#update(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults update(Query<T> query, UpdateOperations<T> ops) {
		return ds.update(query, ops);
	}

	/**
	 * @param query
	 * @param ops
	 * @param createIfMissing
	 * @return
	 * @see org.mongodb.morphia.Datastore#update(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean)
	 */
	public <T> UpdateResults update(Query<T> query, UpdateOperations<T> ops,
			boolean createIfMissing) {
		return ds.update(query, ops, createIfMissing);
	}

	/**
	 * @param query
	 * @param ops
	 * @param createIfMissing
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#update(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> UpdateResults update(Query<T> query, UpdateOperations<T> ops,
			boolean createIfMissing, WriteConcern wc) {
		return ds.update(query, ops, createIfMissing, wc);
	}

	/**
	 * @param query
	 * @param ops
	 * @return
	 * @see org.mongodb.morphia.Datastore#updateFirst(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations)
	 */
	public <T> UpdateResults updateFirst(Query<T> query, UpdateOperations<T> ops) {
		return ds.updateFirst(query, ops);
	}

	/**
	 * @param query
	 * @param ops
	 * @param createIfMissing
	 * @return
	 * @see org.mongodb.morphia.Datastore#updateFirst(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean)
	 */
	public <T> UpdateResults updateFirst(Query<T> query,
			UpdateOperations<T> ops, boolean createIfMissing) {
		return ds.updateFirst(query, ops, createIfMissing);
	}

	/**
	 * @param query
	 * @param ops
	 * @param createIfMissing
	 * @param wc
	 * @return
	 * @see org.mongodb.morphia.Datastore#updateFirst(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean,
	 *      com.mongodb.WriteConcern)
	 */
	public <T> UpdateResults updateFirst(Query<T> query,
			UpdateOperations<T> ops, boolean createIfMissing, WriteConcern wc) {
		return ds.updateFirst(query, ops, createIfMissing, wc);
	}

	/**
	 * @param query
	 * @param entity
	 * @param createIfMissing
	 * @return
	 * @see org.mongodb.morphia.Datastore#updateFirst(org.mongodb.morphia.query.Query,
	 *      java.lang.Object, boolean)
	 */
	public <T> UpdateResults updateFirst(Query<T> query, T entity,
			boolean createIfMissing) {
		return ds.updateFirst(query, entity, createIfMissing);
	}

	/**
	 * @param q
	 * @return
	 * @see org.mongodb.morphia.Datastore#findAndDelete(org.mongodb.morphia.query.Query)
	 */
	public <T> T findAndDelete(Query<T> q) {
		return ds.findAndDelete(q);
	}

	/**
	 * @param q
	 * @param ops
	 * @return
	 * @see org.mongodb.morphia.Datastore#findAndModify(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations)
	 */
	public <T> T findAndModify(Query<T> q, UpdateOperations<T> ops) {
		return ds.findAndModify(q, ops);
	}

	/**
	 * @param q
	 * @param ops
	 * @param oldVersion
	 * @return
	 * @see org.mongodb.morphia.Datastore#findAndModify(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean)
	 */
	public <T> T findAndModify(Query<T> q, UpdateOperations<T> ops,
			boolean oldVersion) {
		return ds.findAndModify(q, ops, oldVersion);
	}

	/**
	 * @param q
	 * @param ops
	 * @param oldVersion
	 * @param createIfMissing
	 * @return
	 * @see org.mongodb.morphia.Datastore#findAndModify(org.mongodb.morphia.query.Query,
	 *      org.mongodb.morphia.query.UpdateOperations, boolean, boolean)
	 */
	public <T> T findAndModify(Query<T> q, UpdateOperations<T> ops,
			boolean oldVersion, boolean createIfMissing) {
		return ds.findAndModify(q, ops, oldVersion, createIfMissing);
	}

	/**
	 * @param type
	 * @param q
	 * @param map
	 * @param reduce
	 * @param finalize
	 * @param scopeFields
	 * @param outputType
	 * @return
	 * @see org.mongodb.morphia.Datastore#mapReduce(org.mongodb.morphia.MapreduceType,
	 *      org.mongodb.morphia.query.Query, java.lang.String, java.lang.String,
	 *      java.lang.String, java.util.Map, java.lang.Class)
	 */
	public <T> MapreduceResults<T> mapReduce(MapreduceType type, Query q,
			String map, String reduce, String finalize,
			Map<String, Object> scopeFields, Class<T> outputType) {
		return ds.mapReduce(type, q, map, reduce, finalize, scopeFields,
				outputType);
	}

	/**
	 * @param type
	 * @param q
	 * @param outputType
	 * @param baseCommand
	 * @return
	 * @see org.mongodb.morphia.Datastore#mapReduce(org.mongodb.morphia.MapreduceType,
	 *      org.mongodb.morphia.query.Query, java.lang.Class,
	 *      com.mongodb.MapReduceCommand)
	 */
	public <T> MapreduceResults<T> mapReduce(MapreduceType type, Query q,
			Class<T> outputType, MapReduceCommand baseCommand) {
		return ds.mapReduce(type, q, outputType, baseCommand);
	}

	/**
	 * @param kind
	 * @return
	 * @see org.mongodb.morphia.Datastore#createUpdateOperations(java.lang.Class)
	 */
	public <T> UpdateOperations<T> createUpdateOperations(Class<T> kind) {
		return ds.createUpdateOperations(kind);
	}

	/**
	 * @param source
	 * @return
	 * @see org.mongodb.morphia.Datastore#createAggregation(java.lang.Class)
	 */
	public <T, U> AggregationPipeline<T, U> createAggregation(Class<T> source) {
		return ds.createAggregation(source);
	}

	/**
	 * @param kind
	 * @return
	 * @see org.mongodb.morphia.Datastore#createQuery(java.lang.Class)
	 */
	public <T> Query<T> createQuery(Class<T> kind) {
		return ds.createQuery(kind);
	}

	/**
	 * @param example
	 * @return
	 * @see org.mongodb.morphia.Datastore#queryByExample(java.lang.Object)
	 */
	public <T> Query<T> queryByExample(T example) {
		return ds.queryByExample(example);
	}

	/**
	 * @param clazz
	 * @param fields
	 * @see org.mongodb.morphia.Datastore#ensureIndex(java.lang.Class,
	 *      java.lang.String)
	 */
	public <T> void ensureIndex(Class<T> clazz, String fields) {
		ds.ensureIndex(clazz, fields);
	}

	/**
	 * @param clazz
	 * @param name
	 * @param fields
	 * @param unique
	 * @param dropDupsOnCreate
	 * @see org.mongodb.morphia.Datastore#ensureIndex(java.lang.Class,
	 *      java.lang.String, java.lang.String, boolean, boolean)
	 */
	public <T> void ensureIndex(Class<T> clazz, String name, String fields,
			boolean unique, boolean dropDupsOnCreate) {
		ds.ensureIndex(clazz, name, fields, unique, dropDupsOnCreate);
	}

	/**
	 * 
	 * @see org.mongodb.morphia.Datastore#ensureIndexes()
	 */
	public void ensureIndexes() {
		ds.ensureIndexes();
	}

	/**
	 * @param background
	 * @see org.mongodb.morphia.Datastore#ensureIndexes(boolean)
	 */
	public void ensureIndexes(boolean background) {
		ds.ensureIndexes(background);
	}

	/**
	 * @param clazz
	 * @see org.mongodb.morphia.Datastore#ensureIndexes(java.lang.Class)
	 */
	public <T> void ensureIndexes(Class<T> clazz) {
		ds.ensureIndexes(clazz);
	}

	/**
	 * @param clazz
	 * @param background
	 * @see org.mongodb.morphia.Datastore#ensureIndexes(java.lang.Class,
	 *      boolean)
	 */
	public <T> void ensureIndexes(Class<T> clazz, boolean background) {
		ds.ensureIndexes(clazz, background);
	}

	/**
	 * 
	 * @see org.mongodb.morphia.Datastore#ensureCaps()
	 */
	public void ensureCaps() {
		ds.ensureCaps();
	}

	/**
	 * @return
	 * @see org.mongodb.morphia.Datastore#getDB()
	 */
	public DB getDB() {
		return ds.getDB();
	}

	/**
	 * @return
	 * @see org.mongodb.morphia.Datastore#getMongo()
	 */
	public MongoClient getMongo() {
		return ds.getMongo();
	}

	/**
	 * @param c
	 * @return
	 * @see org.mongodb.morphia.Datastore#getCollection(java.lang.Class)
	 */
	public DBCollection getCollection(Class<?> c) {
		return ds.getCollection(c);
	}

	/**
	 * @return
	 * @see org.mongodb.morphia.Datastore#getDefaultWriteConcern()
	 */
	public WriteConcern getDefaultWriteConcern() {
		return ds.getDefaultWriteConcern();
	}

	/**
	 * @param wc
	 * @see org.mongodb.morphia.Datastore#setDefaultWriteConcern(com.mongodb.WriteConcern)
	 */
	public void setDefaultWriteConcern(WriteConcern wc) {
		ds.setDefaultWriteConcern(wc);
	}

	/**
	 * @param queryFactory
	 * @see org.mongodb.morphia.Datastore#setQueryFactory(org.mongodb.morphia.query.QueryFactory)
	 */
	public void setQueryFactory(QueryFactory queryFactory) {
		ds.setQueryFactory(queryFactory);
	}

	/**
	 * @return
	 * @see org.mongodb.morphia.Datastore#getQueryFactory()
	 */
	public QueryFactory getQueryFactory() {
		return ds.getQueryFactory();
	}

}
