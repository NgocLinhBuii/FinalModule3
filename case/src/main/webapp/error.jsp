<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link rel="stylesheet" href="css/error.css"> <!-- Liên kết tới tệp CSS -->
</head>
<body>
<div class="container">
    <h1 class="error-title">Có lỗi xảy ra</h1>
    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    <a href="student-servlet" class="btn-back">Trở về danh sách</a>
</div>
</body>
</html>
