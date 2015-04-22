package com.fenlonsky.campaign.base.dao.mybatis.utils;

/**
 * 主要是处理mybatis查询出来的数据条数，因为我是使用了spring-data中封装后的Page（接口）和Pageable（接口）类来做的分页，
 * 其查询和返回是分开的具体可以去看源码，以前自己封装的时候是将总条数放到pageable中的，请求数据和返回数据都是pageable，
 * 但是在spring-data中分页请求带着pageable对象来的，分页返回数据返回page对象，其总条数是放在page中的，
 * 所以在mybatis使用拦截器分页
 * 时候无法将总条数直接存放到pageable中，所以这里我使用了ThreadLocal来管理这个数据。
 * 
 * @ClassName: DataUtil 主要是做拦截器计算查出来的total数据存放问题
 * @Description: 主要是做拦截器计算查出来的total数据存放问题
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年4月22日 下午6:24:51
 * 
 */
public class DataUtil {
	
	private static ThreadLocal<Long> tl = new ThreadLocal<Long>(); // map
	
	public static Long getTotalCount()
	{
		Long total = tl.get();
		return total;
	}
	
	public static void setTotalCount(Long total)
	{
		tl.set(total);
	}
	
	public static void remove()
	{
		tl.remove();
	}
}
