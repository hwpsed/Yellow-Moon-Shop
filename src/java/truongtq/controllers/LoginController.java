/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import truongtq.daos.AccountDAO;
import truongtq.dtos.AccountDTO;
import truongtq.dtos.RegistrationErrorObj;

/**
 *
 * @author harry
 */
public class LoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private static final String ERROR = "error.jsp";
    private static final String USER = "index.jsp";
    private static final String INVALID = "login.jsp";
    private static final String ADMIN = "index.jsp";

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
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            boolean valid = true;
            RegistrationErrorObj errorObj = new RegistrationErrorObj();

            if (username.length() == 0) {
                errorObj.setUsernameError("Username can't be blank");
                valid = false;
            }
            if (password.length() == 0) {
                errorObj.setPasswordError("Password can't be blank");
                valid = false;
            }

            if (valid) {
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.checkLogin(username, password);
                if (dto == null) {
                    request.setAttribute("ERROR", "Invalid Username or Password");
                } else {

                    HttpSession session = request.getSession(true);
                    session.setAttribute("ACCOUNT", dto);
                    session.setAttribute("ROLE", dto.getRole());
                    session.setAttribute("FULLNAME", dto.getName());
                    session.setAttribute("USERNAME", dto.getUsername());
                    
                    if (dto.getRole().equals("user")) {
                        url = USER;
                    } else if (dto.getRole().equals("admin")) {
                        url = ADMIN;
                    }else {
                        request.setAttribute("ERROR", "Your role is invalid");
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }
        } catch (SQLException ex) {
            LOGGER.error("LoginController_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("LoginController_Naming" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("LoginController_ClassNotFound" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("LoginController_NoAlgorithm" + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
