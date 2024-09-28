<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Student</title>
    <link rel="stylesheet" href="css/new.css"> <!-- Liên kết tới tệp CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
    </script>
</head>
<body>
<div class="container">
    <h1>Sửa thông tin sinh viên</h1>
    <form action="student-servlet?action=update" method="post" class="form">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${student.id}">

        <!-- Name Field -->
        <label for="name">Họ và tên:</label>
        <input type="text" id="name" name="name" value="${student.name}" required><br>

        <!-- Email Field -->
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${student.email}" required><br>

        <!-- Class Dropdown -->
        <label for="clazz_id">Lớp:</label>
        <select name="clazz_id" id="clazz_id">
            <c:forEach var="clazz" items="${clazzList}">
                <option value="${clazz.id}"
                        <c:if test="${clazz.id == student.clazz_id}">selected</c:if>>
                        ${clazz.class_name}
                </option>
            </c:forEach>
        </select><br>

        <!-- Point Field -->
        <label for="point">Điểm:</label>
        <input type="number" step="0.1" id="point" name="point" value="${student.point}" required><br>

        <!-- Submit Button -->
        <button type="submit" class="btn-submit">Sửa</button>
    </form>

    <!-- Back Button -->
    <a href="student-servlet" class="btn-back">Trở lại danh sách</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
