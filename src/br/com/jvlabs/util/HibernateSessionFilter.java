package br.com.jvlabs.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;

public class HibernateSessionFilter implements Filter {
	Logger logger = Logger.getLogger(HibernateSessionFilter.class);

	public void destroy() {
		HibernateUtil.closeSessionFactory();
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {

		try {
			HibernateUtil.openSession();
			fc.doFilter(req, resp);
		}catch(JDBCConnectionException e){
			logger.error("A conexao com o banco falhou!", e);
			 HttpServletResponse res = (HttpServletResponse) resp;
			 res.sendRedirect("/conexaoErro.html");
			 return;
		}finally {
			HibernateUtil.closeCurrentSession();
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}
