
package com.drzk.conf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.drzk.service.impl.ClientMQTT;
import com.drzk.utils.GlobalPark;

/**
 * 自定义配置文件处理类，从数据库获取配置信息 <br>
 * Date: 2018年5月22日 下午5:08:52 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DatabaseConfigurer extends PropertyPlaceholderConfigurer implements ApplicationContextAware {

	private String querySql = "select parameter_code,parameter_value from sys_parameters";
	
	private  ApplicationContext applicationContext;

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

	@Override
	protected Properties mergeProperties() throws IOException {

		Properties properties = new Properties();
		// 获取数据源
		//DataSource dataSource = (DataSource) applicationContext.getBean(dataSourceBeanName);
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				String key = resultSet.getString(1);
				String value = resultSet.getString(2);
				// 存放获取到的配置信息
				if(value==null) {
					value = "";
				}
				properties.put(key, value);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new IOException("load database properties failed.");
		}
		GlobalPark.properties = properties;
		//如果数据库设置需要往云端传数据,则启动云端mqtt
		if("1".equals(properties.getProperty("UPLOAD_DATA_CLOUD"))) {
			ClientMQTT.start();
		}
		return properties;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
