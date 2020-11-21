/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import truongtq.daos.ProductDAO;
import truongtq.dtos.AccountDTO;
import truongtq.dtos.ProductDTO;

/**
 *
 * @author harry
 */
public class EditController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AddCakeController.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            AccountDTO user = (AccountDTO) request.getSession().getAttribute("ACCOUNT");

            if (user.getRole().equals("admin")) {
                int id = Integer.parseInt(request.getParameter("txtId"));
                ProductDAO dao = new ProductDAO();
                ProductDTO dto = dao.getProductById(id);

                HttpSession session = request.getSession(true);
                session.setAttribute("CAKE", dto);
            } else {
                request.setAttribute("ERROR", "Your role is not allow to access");
                request.setAttribute("URL", "SearchCakeController");
            }
        } catch (SQLException ex) {
            LOGGER.error("EditController_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("EditController_Naming" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("EditController_ClassNotFound" + ex.getMessage());
        } finally {
            request.getRequestDispatcher("editCake.jsp").forward(request, response);
        }
    }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
