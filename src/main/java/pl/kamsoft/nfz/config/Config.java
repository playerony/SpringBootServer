package pl.kamsoft.nfz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.playerony.libraryV2.dao.AuthorDao;
import com.playerony.libraryV2.dao.BookDao;
import com.playerony.libraryV2.dao.RoleDao;
import com.playerony.libraryV2.dao.UserDao;
import com.playerony.libraryV2.impl.AuthorDaoImpl;
import com.playerony.libraryV2.impl.BookDaoImpl;
import com.playerony.libraryV2.impl.RoleDaoImpl;
import com.playerony.libraryV2.impl.UserDaoImpl;

@Configuration
@ComponentScan({"staz", "dao"})
public class Config {
	@Bean
	public BookDao bookDao() {
		return new BookDaoImpl();
	}
	
	@Bean
	public AuthorDao authorDao() {
		return new AuthorDaoImpl();
	}
	
	@Bean
	public RoleDao roleDao() {
		return new RoleDaoImpl();
	}
	
	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@GLORA2.kamsoft.local:1521/SZKOL");
		driverManagerDataSource.setUsername("PWOJTASINSKI");
		driverManagerDataSource.setPassword("wojtasinski");
		
		return driverManagerDataSource;
	}
}
