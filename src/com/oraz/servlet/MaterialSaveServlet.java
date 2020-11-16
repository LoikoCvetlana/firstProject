package com.oraz.servlet;

import com.oraz.dto.material.CreateMaterialDto;
import com.oraz.dto.material.MaterialDto;
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

@WebServlet("/material-save")
public class MaterialSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         getServletContext()
                .getRequestDispatcher(JspPathUtil.get("material-save"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateMaterialDto MaterialDto = CreateMaterialDto.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .build();
        MaterialDto sevedMaterial = MaterialService.getInstance().save(MaterialDto);
        resp.sendRedirect("/material");
    }
}
