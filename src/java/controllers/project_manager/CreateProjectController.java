package controllers.project_manager;

import dao.implementations.ProjectDAO;
import dao.implementations.UserDAO;
import dto.ProjectDTO;
import entities.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhki
 */
@WebServlet(name = "CreateProjectController", urlPatterns = {"/CreateProjectController"})
public class CreateProjectController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        System.out.println("AGFG");
//        PrintWriter out = response.getWriter();
//        try {
//
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CreateProjectController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CreateProjectController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } catch (Exception ex) {
//            log(ex.getMessage());
//        } finally {
//            out.close();
//        }
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/createProject.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //lay data tu user nhap vao
            String projectName = request.getParameter("name");
            String description = request.getParameter("desc");
            String userCreated = request.getParameter("createdBy");

            User createdBy = new UserDAO().getUserByName(userCreated);
            //kiem tra xem co user khong
            if (createdBy == null) {
                request.setAttribute("error", "User không tồn tại.");
                request.getRequestDispatcher("/views/createProject.jsp").forward(request, response);
                return;
            }
            ProjectDTO prj = new ProjectDTO(projectName, description, createdBy);
            boolean add = new ProjectDAO().add(prj);
            if (add) {
                response.sendRedirect("index.jsp");
            } else {
                request.getRequestDispatcher("/views/createProject.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Cannot add to database: " + e.getMessage());
            request.setAttribute("error", "Có lỗi khi tạo dự án.");
            request.getRequestDispatcher("/views/createProject.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
