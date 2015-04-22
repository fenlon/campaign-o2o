package com.fenlonsky.campaign.base.dao.mybatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.fenlonsky.campaign.base.dao.mybatis.utils.DataUtil;

@Intercepts(value = { @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	
	public static final String BLANK_STR = " ";
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 获取mapper.xml中配置的id
		String id = mappedStatement.getId();
		// 只对满足条件的映射Id处理
		if (id.matches(".+ByPage$")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			// 取得原始SQL语句
			String sql = boundSql.getSql();
			// 查询总条数SQL语句
			String countSql = "select count(*) from (" + sql + ") a";
			
			// 得到调用sql传过来的参数
			Map<?, ?> parameter = (Map<?, ?>) boundSql.getParameterObject();
			Pageable page = (Pageable) parameter.get("page");
			// 得到拦截下来的参数，即连接对象,使用jdbc来查询count
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			// 预处理sql
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			if (rs.next()) {
				// 将总条数放入threadlocal中，在dao层中可以取出
				DataUtil.setTotalCount(rs.getLong(1));
			}
			
			// 改造后带有分页查询的SQL语句
			String sortSql = BLANK_STR + "ORDER BY";
			Sort sort = page.getSort();
			Iterator<Order> iterator = sort.iterator();
			Order order = null;
			while (iterator.hasNext()) {
				order = iterator.next();
				String property = order.getProperty();
				Direction dir = order.getDirection();
				sortSql = sortSql + BLANK_STR + property + BLANK_STR + dir.toString() + ",";
			}
			sortSql = sortSql.substring(0, sortSql.length() - 1);
			String pageSql = sql + sortSql + BLANK_STR + "limit " + page.getOffset() + "," + page.getPageSize();
			// 将修改后的sql重新设置回去
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
		
	}
	
}
