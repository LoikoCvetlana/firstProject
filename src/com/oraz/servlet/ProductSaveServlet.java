package com.oraz.servlet;

import com.oraz.dto.product.CreateProductDto;
import com.oraz.dto.product.ProductBasicDto;
import com.oraz.service.MaterialService;
import com.oraz.service.ProductService;
import com.oraz.util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product-save")
public class ProductSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("materials", MaterialService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPathUtil.get("product-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateProductDto productDto = CreateProductDto.builder()
                .name(req.getParameter("name"))
                .article(req.getParameter("article"))
                .picture(req.getParameter("picture"))
                .value(Double.valueOf(req.getParameter("value")))
                .materialId(Long.valueOf(req.getParameter("material")))
                .build();
        ProductBasicDto sevedProduct = ProductService.getInstance().save(productDto);
        resp.sendRedirect("/product-info?id=" + sevedProduct.getId());
    }
}
