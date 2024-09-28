    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student List</title>
        <link rel="stylesheet" href="css/new.css"> <!-- Liên kết tới tệp CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
    <div class="container">
        <a href="student-servlet" class="header-link">Danh sách sinh viên lớp C0324M4</a>
        <!-- Tìm kiếm -->
        <form action="student-servlet" method="get" class="search-form">
            <input type="hidden" name="action" value="search">

            <!-- Tìm kiếm theo tên -->
            <input type="text" name="name" placeholder="Tìm theo tên" value="${param.name}">

            <!-- Tìm kiếm theo email -->
            <input type="text" name="email" placeholder="Tìm theo email" value="${param.email}">

            <!-- Tìm kiếm theo lớp -->
            <select name="clazz_id">
                <option value="">Tất cả lớp</option>
                <c:forEach var="clazz" items="${clazzList}">
                    <option value="${clazz.id}" <c:if test="${param.clazz_id == clazz.id}">selected</c:if>>${clazz.class_name}</option>
                </c:forEach>
            </select>

            <!-- Tìm kiếm theo điểm -->
            <input type="number" name="minPoint" placeholder="Điểm tối thiểu" value="${param.minPoint}">
            <input type="number" name="maxPoint" placeholder="Điểm tối đa" value="${param.maxPoint}">

            <button type="submit">Tìm kiếm</button>
        </form>

        <a href="student-servlet?action=create" class="btn-create">Thêm sinh viên mới</a>
        <table class="table">
            <thead>
            <tr>
                <%-- <th>ID</th> --%>
                <th>Họ và tên</th>
                <th>Email</th>
                <th>Lớp</th>
                <th>Điểm</th>
                <th>Xếp loại</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <%-- Hiển thị thông báo lỗi nếu có --%>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <strong>Lỗi:</strong> ${errorMessage}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:forEach var="s" items="${studentList}">
                <tr>
                        <%-- <td>${s.id}</td> --%>
                    <td>${s.name}</td>
                    <td>${s.email}</td>
                    <td>
                        <!-- Tìm và hiển thị tên lớp -->
                        <c:forEach var="clazz" items="${clazzList}">
                            <c:if test="${clazz.id == s.clazz_id}">
                                ${clazz.class_name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${s.point}</td>
                    <td>
                        <c:choose>
                            <c:when test="${s.point >= 9}">Xuất sắc</c:when>
                            <c:when test="${s.point >= 8}">Giỏi</c:when>
                            <c:otherwise>Trung bình</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="student-servlet?action=update&id=${s.id}" class="btn-action">Sửa</a>
                        <a href="student-servlet?action=delete&id=${s.id}" class="btn-action btn-delete"
                           onclick="return confirm('Bạn có muốn xóa sinh viên ${s.name}?');">
                            Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Phân trang -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="student-servlet?page=${currentPage - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                        <a class="page-link" href="student-servlet?page=${i}">${i}</a>
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="student-servlet?page=${currentPage + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>

    </div>
    </body>
    </html>
