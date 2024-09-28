package com.example.crud.controller;

import com.example.crud.model.Clazz;
import com.example.crud.model.Student;
import com.example.crud.service.ClazzService;
import com.example.crud.serviceImpl.ClazzServiceImpl;
import com.example.crud.service.IStudentService;
import com.example.crud.serviceImpl.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student-servlet")
public class StudentServlet extends HttpServlet {
    private final IStudentService iStudentService = new StudentService();
    private final ClazzService clazzService = new ClazzServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "update":
                showUpdateForm(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            case "search":
                searchStudents(req, resp);
                break;
            default:
//                List<Clazz> clazzList = clazzService.findAll();
//                req.setAttribute("clazzList", clazzList);  // Đặt danh sách lớp vào request
//                req.setAttribute("studentList", iStudentService.findAll());
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
//                requestDispatcher.forward(req, resp);
                findAll(req, resp);
        }
    }

    private void showUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                throw new NumberFormatException("ID parameter is missing.");
            }

            int id = Integer.parseInt(idParam);
            Student student = iStudentService.findStudentById(id);

            if (student != null) {
                List<Clazz> clazzList = clazzService.findAll(); // Lấy danh sách lớp
                req.setAttribute("clazzList", clazzList);      // Đặt clazzList trong request
                req.setAttribute("student", student);          // Đặt đối tượng student trong request

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("update.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                req.setAttribute("errorMessage", "Student not found.");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid student ID format.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
            requestDispatcher.forward(req, resp);
        }
    }


    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }


    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Clazz> clazzList = clazzService.findAll();
        req.setAttribute("clazzList", clazzList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("create.jsp");
        requestDispatcher.forward(req, resp);
    }



    private void findAll(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int page = 1;
        int pageSize = 5; // Số lượng sinh viên trên mỗi trang

        String pageParam = req.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        List<Student> studentList = iStudentService.findStudents(page, pageSize);
        List<Clazz> clazzList = clazzService.findAll();

        req.setAttribute("clazzList", clazzList);
        req.setAttribute("studentList", studentList);

        int totalStudents = iStudentService.getTotalStudentCount();
        int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addNewStudent(req, resp);
                break;
            case "update":
                updateStudent(req, resp);
                break;
            case "delete":
                deleteStudent(req, resp);
                break;
            case "view":
//                findAll(req, resp);
                break;
            default:
                findAll(req, resp);
        }
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            iStudentService.deleteStudent(id);
            req.setAttribute("successMessage", "Student deleted successfully.");
            resp.sendRedirect("student-servlet");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }



    private void addNewStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int clazz_id = Integer.parseInt(req.getParameter("clazz_id"));
        double point = Double.parseDouble(req.getParameter("point"));

        if (name != null && (name.length() > 50 || name.matches(".*\\d.*"))) {
            throw new RuntimeException("Tên không hợp lệ: không được chứa số và không dài quá 50 ký tự.");
        }
        Student student = new Student(name, email, clazz_id, point);
        iStudentService.addNewStudent(student);
        req.setAttribute("successMessage", "Student added successfully.");
        resp.sendRedirect("student-servlet");
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            int clazz_id = Integer.parseInt(req.getParameter("clazz_id"));
            double point = Double.parseDouble(req.getParameter("point"));

            if (name != null && (name.length() > 50 || name.matches(".*\\d.*"))) {
                throw new RuntimeException("Tên không hợp lệ: không được chứa số và không dài quá 50 ký tự.");
            }
            Student student = new Student(id, name, email, clazz_id, point);
            iStudentService.updateStudent(student);

            req.setAttribute("successMessage", "Student updated successfully.");
            resp.sendRedirect("student-servlet");
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid input format.");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("error.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private void searchStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy các tham số tìm kiếm từ request
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String clazzIdParam = req.getParameter("clazz_id");
        String minPointParam = req.getParameter("minPoint");
        String maxPointParam = req.getParameter("maxPoint");

        Integer clazzId = null;
        Double minPoint = null;
        Double maxPoint = null;

        // Chuyển đổi các giá trị số từ String sang Integer/Double
        if (clazzIdParam != null && !clazzIdParam.isEmpty()) {
            clazzId = Integer.parseInt(clazzIdParam);
        }
        if (minPointParam != null && !minPointParam.isEmpty()) {
            minPoint = Double.parseDouble(minPointParam);
        }
        if (maxPointParam != null && !maxPointParam.isEmpty()) {
            maxPoint = Double.parseDouble(maxPointParam);
        }

        // Gọi service để thực hiện tìm kiếm nâng cao
        List<Student> studentList = iStudentService.searchStudents(name, email, clazzId, minPoint, maxPoint);

        // Lấy danh sách lớp để hiển thị trong form
        List<Clazz> clazzList = clazzService.findAll();
        req.setAttribute("clazzList", clazzList);

        // Đặt danh sách sinh viên tìm kiếm được vào request
        req.setAttribute("studentList", studentList);

        // Chuyển tiếp request tới list.jsp để hiển thị kết quả tìm kiếm
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }

}
