package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {
	
	private String getUsuario(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();	
		if (cookie == null) return "<deslogado>";	
		cookie.setMaxAge(10 * 60);		
		System.out.println("Tempo cookie: " + cookie.getMaxAge());
		return cookie.getValue();	    
	}

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)	throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;		
		HttpServletResponse resp = (HttpServletResponse) response;		
		
		String uri = req.getRequestURI();			
	    String usuario = getUsuario(req, resp);	    
	    
		
		System.out.println("O usuário " + usuario +  " está acessando a URI: " + uri);
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}

}
