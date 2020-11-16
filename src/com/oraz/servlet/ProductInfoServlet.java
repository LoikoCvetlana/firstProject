package com.oraz.servlet;

import com.oraz.dto.product.ProductFullDto;
import com.oraz.service.ProductService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product-info")
public class ProductInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        ProductFullDto product = ProductService.getInstance().findById(Long.valueOf(productId));
        req.setAttribute("product", product);

        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("product-info"))
                .forward(req, resp);
    }
}
