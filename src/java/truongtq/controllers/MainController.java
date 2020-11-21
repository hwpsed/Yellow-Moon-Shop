/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author harry
 */
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String CREATE_CAKE = "AddCakeController";
    private static final String SEARCH_CAKE = "SearchCakeController";
    private static final String UPDATE_CAKE = "EditCakeController";
    private static final String EDIT_CAKE = "EditController";
    private static final String DELETE_CAKE = "DeleteCakeController";
    private static final String ADD_CART = "AddToCartController";
    private static final String DELETE_CART = "DeleteFromCartController";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String CHECK_LOGIN = "CheckLoginController";
    
 

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
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Search")) {
                url = SEARCH_CAKE;
            } else if (action.equals("Edit")) {
                url = UPDATE_CAKE;
            } else if (action.equals("Edit Cake")) {
                url = EDIT_CAKE;
            } else if (action.equals("AddCake")) {
                url = CREATE_CAKE;
            } else if (action.equals("DeleteCake")) {
                url = DELETE_CAKE;
            } else if (action.equals("Add to Cart")) {
                url = ADD_CART;
            } else if (action.equals("Remove")) {
                url = DELETE_CART;
            } else if (action.equals("Update Cake")) {
                url = UPDATE_CART;
            } else if (action.equals("Next Step")) {
                url = CHECK_LOGIN;
            } else if (action.equals("Finish")) {
                url = CHECK_LOGIN;
            } else {
                request.setAttribute("ERROR", "Your action is invalid");
            }
        } catch (Exception e) {
            LOGGER.error("ERROR at MainController: " + e.getMessage());
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
