<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Student</title>
    <link rel="stylesheet" href="css/new.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function validateForm() {
            const name = document.getElementById("name").value;
            const email = document.getElementById("email").value;
            const point = document.getElementById("point").value;

            // Validate name length
            if (name.length > 50) {
                alert("Tên không được dài quá 50 ký tự.");
                return false;
            }

            // Validate email format (basic regex)
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                alert("Email không hợp lệ.");
                return false;
            }

            // Validate points (ensure it's a number and within an acceptable range)
            if (isNaN(point) || point < 0 || point > 10) {
                alert("Điểm phải là một số từ 0 đến 10.");
                return false;
            }

            return true; // If all validations pass
        }

        // Show alert if email error is present in the URL
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('emailError')) {
                alert("Email đã tồn tại.");
            }
        };
    </script>
</head>
<body>
<div class="container">
    <h1>Thêm mới sinh viên</h1>
    <form action="student-servlet?action=create" method="post" class="form" onsubmit="return validateForm();">
        <input type="hidden" name="action" value="create">
        <label for="name">Tên:</label>
        <input type="text" id="name" name="name" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label>Lớp:</label>
        <select name="clazz_id" id="clazz_id">
            <c:forEach var="s" items="${clazzList}">
                <option value="${s.id}">${s.class_name}</option>
            </c:forEach>
        </select><br>
        <label for="point">Điểm:</label>
        <input type="number" step="0.1" id="point" name="point" required><br>
        <button type="submit" class="btn-submit">Tạo mới</button>
    </form>
    <a href="student-servlet" class="btn-back">Trở lại danh sách</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
