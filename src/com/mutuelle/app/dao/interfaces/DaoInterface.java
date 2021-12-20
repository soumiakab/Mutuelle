package com.mutuelle.app.dao.interfaces;

import java.util.List;

public interface DaoInterface<T> {

	public int delete(int id);

	
	public List<T> findAll();

	public T findById(int id);
	
	public T findByName(String name);

	
	public int insert(T object);

	public int update(T object);
}
