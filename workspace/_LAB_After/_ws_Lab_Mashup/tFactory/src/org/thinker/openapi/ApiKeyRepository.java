package org.thinker.openapi;


public interface ApiKeyRepository {

	public abstract void create(ApiKeyVO vo) throws ApiKeyException;

	public abstract ApiKeyVO read(String pk) throws ApiKeyException;

	public abstract void update(String pk)throws ApiKeyException; 
}