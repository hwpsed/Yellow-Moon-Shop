/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongtq.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import truongtq.daos.AccountDAO;
import truongtq.daos.OrderDAO;
import truongtq.dtos.AccountDTO;
import truongtq.dtos.OrderDTO;

/**
 *
 * @author harry
 */
public class CheckLoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AddOrderDetailController";
    private static final String INVALID = "index.jsp";
    private static final String FORM = "payment.jsp";

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
            AccountDTO user = (AccountDTO) request.getSession().getAttribute("ACCOUNT");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String Date = formatter.format(date);
            String temp = request.getParameter("txtPayment");
            if (user != null) {
                if (user.getRole().equals("user")) {
                    if (temp == null) {
                        url = FORM;
                    } else {
                        String username = (String) request.getSession().getAttribute("USERNAME");
                        float total = Float.parseFloat(request.getParameter("txtTotal"));
                        int payment = Integer.parseInt(request.getParameter("txtPayment"));

                        AccountDAO accountDao = new AccountDAO();
                        AccountDTO accountDto = accountDao.getUserById(username);
                        request.setAttribute("ACC", accountDto);
                        OrderDAO orderDao = new OrderDAO();
                        OrderDTO orderDto = new OrderDTO(username, accountDto.getName(), Date, accountDto.getAddess(), accountDto.getPhone(), total, payment);
                        if (orderDao.createOrderUser(orderDto)) {
                            url = SUCCESS;
                        } else {
                            url = ERROR;
                            request.setAttribute("ERROR", "Add failed");
                        }
                    }
                } else {
                    url = FORM;
                }
            } else {
                String name = request.getParameter("txtName");
                String address = request.getParameter("txtAddress");
                String phone = request.getParameter("txtPhone");
                if (name != null && address != null && phone != null) {
                    int payment = Integer.parseInt(request.getParameter("txtPayment"));
                    float total = Float.parseFloat(request.getParameter("txtTotal"));
                    OrderDTO orderDto = new OrderDTO(name, Date, address, phone, total, payment);
                    OrderDAO orderDao = new OrderDAO();
                    if (orderDao.createOrderGuest(orderDto)) {
                        url = SUCCESS;
                    } else {
                        url = ERROR;
                        request.setAttribute("ERROR", "Add failed");
                    }
                } else {
                    url = FORM;
                }
            }
        } catch (SQLException ex) {
            LOGGER.error("CheckLoginController_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.error("CheckLoginController_Naming" + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("CheckLoginController_NoSuchAlgorithm" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("CheckLoginController_ClassNotFound" + ex.getMessage());
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
