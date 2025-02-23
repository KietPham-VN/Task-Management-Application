/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import common.enums.AccountRoles;
import entities.AuthenticatedUser;
import entities.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGHIA
 */
public class TeamMemberFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession(false);
    
        if(session==null){
            httpResp.sendRedirect("home");
            return;
        }
        AuthenticatedUser user = (AuthenticatedUser)session.getAttribute("authenticated-user");
        if(user==null){
            httpResp.sendRedirect("home");
            return;
        }
        if(!AccountRoles.TEAM_MEMBER.getRoleName().equals(user.getRole())){
            httpResp.sendRedirect("home");
            return;
        }
        chain.doFilter(request, response);    }

    @Override
    public void destroy() {
    }
    
}
