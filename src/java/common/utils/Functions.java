/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.utils;

import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author NGHIA
 */
public class Functions {
    public static boolean AuthenticatePath(HttpServletRequest request, String role)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        if(session==null) return false;
        User user = (User)session.getAttribute("user");
        if(user==null) return false;
        return ("Project Manager".equals(user.getRole()));
    }
}
