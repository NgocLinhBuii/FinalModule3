package com.example.finalmodule3.contoller;

import com.example.finalmodule3.model.MatBang;
import com.example.finalmodule3.service.IMatBangService;
import com.example.finalmodule3.service.MatBangService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class MatBangController extends HttpServlet {
    private IMatBangService matBangService = new MatBangService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            findAll(request, response);
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "search":
                search(request, response);
                break;
            default:
                findAll(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            findAll(request, response);
            action = "";
        }

        switch (action) {
            case "create":
                addMatBang(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                findAll(request, response);
                break;
        }
    }


    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("matbang_add.jsp").forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MatBang> matBang = matBangService.findAll();
        request.setAttribute("matBang", matBang);
        request.getRequestDispatcher("matbang_list.jsp").forward(request, response);
    }

    private void addMatBang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMatBang = request.getParameter("maMatBang");
        String trangThai = request.getParameter("trangThai");
        String dienTichStr = request.getParameter("dienTich"); // Đổi về String
        String tangStr = request.getParameter("tang");
        String loaiMatBang = request.getParameter("loaiMatBang");
        String giaTienStr = request.getParameter("giaTien");
        String ngayBatDauStr = request.getParameter("ngayBatDau");
        String ngayKetThucStr = request.getParameter("ngayKetThuc");

        // Kiểm tra xem các tham số có null không
        if (maMatBang == null || trangThai == null || dienTichStr == null || tangStr == null ||
                loaiMatBang == null || giaTienStr == null || ngayBatDauStr == null || ngayKetThucStr == null) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("matbang_add.jsp").forward(request, response);
            return;
        }

        // Chuyển đổi các tham số sang kiểu dữ liệu phù hợp
        float dienTich = Float.parseFloat(dienTichStr);
        int tang = Integer.parseInt(tangStr);
        float giaTien = Float.parseFloat(giaTienStr);
        LocalDate ngayBatDau = LocalDate.parse(ngayBatDauStr);
        LocalDate ngayKetThuc = LocalDate.parse(ngayKetThucStr);

        // Tạo đối tượng MatBang
        MatBang matBang = new MatBang(maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc);

        // Kiểm tra mã mặt bằng đã tồn tại
        if (matBangService.existsByMaMatBang(matBang.getMaMatBang())) {
            request.setAttribute("error", "Mã mặt bằng vừa thêm đã tồn tại");
            request.getRequestDispatcher("matbang_add.jsp").forward(request, response);
        } else if (ngayBatDau.isAfter(ngayKetThuc) || ngayBatDau.plusMonths(6).isAfter(ngayKetThuc)) {
            request.setAttribute("error", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc ít nhất là 6 tháng.");
            request.getRequestDispatcher("matbang_add.jsp").forward(request, response);
        } else {
            matBangService.save(matBang);
            response.sendRedirect(request.getContextPath() + "/matbang?action=list");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMatBang = request.getParameter("maMatBang");
        matBangService.delete(maMatBang);
        response.sendRedirect(request.getContextPath() + "/matbang?action=list");
    }

    // Phương thức tìm kiếm
    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loaiMatBang = request.getParameter("loaiMatBang");
        Float giaTien = request.getParameter("giaTien") != null ? Float.parseFloat(request.getParameter("giaTien")) : null;
        Integer tang = request.getParameter("tang") != null ? Integer.parseInt(request.getParameter("tang")) : null;
        LocalDate startDate = request.getParameter("startDate") != null ? LocalDate.parse(request.getParameter("startDate")) : null;
        LocalDate endDate = request.getParameter("endDate") != null ? LocalDate.parse(request.getParameter("endDate")) : null;

        List<MatBang> results = matBangService.searchWithConditions(loaiMatBang, giaTien, tang, startDate, endDate);
        request.setAttribute("results", results);
        request.getRequestDispatcher("matbang_search.jsp").forward(request, response);
    }
}
