<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách Mặt bằng</title>
</head>
<body>
<h1>Danh sách Mặt bằng</h1>

<!-- Form tìm kiếm -->
<form action="matbang" method="get">
    <input type="text" name="loaiMatBang" placeholder="Loại mặt bằng" value="${param.loaiMatBang}"/>
    <input type="number" name="giaTien" placeholder="Giá tiền" value="${param.giaTien}"/>
    <input type="number" name="tang" placeholder="Tầng" value="${param.tang}"/>
    <input type="date" name="startDate" placeholder="Ngày bắt đầu" value="${param.startDate}"/>
    <input type="date" name="endDate" placeholder="Ngày kết thúc" value="${param.endDate}"/>
    <button type="submit">Tìm kiếm</button>
</form>

<a href="matbang?action=create">Thêm mới Mặt bằng</a>

<table border="1">
    <thead>
    <tr>
        <th>Mã mặt bằng</th>
        <th>Trạng thái</th>
        <th>Diện tích</th>
        <th>Tầng</th>
        <th>Loại mặt bằng</th>
        <th>Giá tiền</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="matBang" items="${matBang}">
        <tr>
            <td>${matBang.maMatBang}</td>
            <td>${matBang.trangThai}</td>
            <td>${matBang.dienTich}</td>
            <td>${matBang.tang}</td>
            <td>${matBang.loaiMatBang}</td>
            <td>${matBang.giaTien}</td>
            <td>${matBang.ngayBatDau}</td>
            <td>${matBang.ngayKetThuc}</td>
            <td>
                <a href="matbang?action=delete&maMatBang=${matBang.maMatBang}" onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng này?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
