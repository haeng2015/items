package com.panasign.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-11-24
 */
public class IntegerUrils {
    /**
     * 将list字符串数组集合，根据tag拼成一串，比如tag=‘，’，结果串为“'string1'，'string2'，'string3'”,
     * 此方法适用于sql中：“select * from xxx where x in (适用于此处);”
     * @param list
     * @param tag 做分隔符的字符
     * @return
     * @author: Wu.Liang
     */
    public static String parseListToStringForSQL(List<Integer> list){
    	String str="";
    	if(list!=null && list.size()>0){
    		for(int i =0; i<list.size(); i++){
    			str = str +list.get(i);
    			if(i!=list.size()-1){
    				str = str+",";
    			}
    		}
    	}
    	return str;
    }
    
    public static void main(String[] args) {
    	List<Integer> list = new ArrayList<Integer>();
    	list.add(1);
    	list.add(1);
    	System.out.println(parseListToStringForSQL(list));
    }
}
