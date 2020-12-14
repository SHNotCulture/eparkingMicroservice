package com.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapUtil<T> {

	/**
	 * List<map> mapper װ�� ΪList<?>
	 * @param maplist
	 * @param and
	 * @return
	 * @throws Exception
	 */
	public  List<T> mapperList(List<Map> maplist,Class<T> t) throws Exception{
		List<Object> rtnlist=new ArrayList<>();
		if(maplist==null||maplist.size()==0){
			return (List<T>)rtnlist;
		}
		for(Map map:maplist){
			Object tobj=t.newInstance();
			for(Object key:map.keySet()){
				Field field =t.getDeclaredField((String) key);
				field.setAccessible(true);
				field.set(tobj, map.get(key));
			}
			rtnlist.add(tobj);
		}
		return (List<T>)rtnlist;
	}
	
	/**
	 * ����Mapper  ���ݶ���
	 * @param map
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public <T>T mapperObj(Map map,Class<T> t) throws Exception{
		if(map==null||map.size()==0){
			return t.newInstance();
		}
			Object tobj=t.newInstance();
			for(Object key:map.keySet()){
				Field field =t.getDeclaredField((String) key);
				field.setAccessible(true);
				field.set(tobj, map.get(key));
			}
		return (T) tobj;
	}
}
