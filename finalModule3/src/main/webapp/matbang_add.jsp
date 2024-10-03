<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới Mặt bằng</title>
</head>
<body>
<div>
    <h1>Thêm mới Mặt bằng</h1>
    <form action="" method="post">
        <input type="hidden" name="action" value="create"/>

        <div>
            <label for="maMatBang">Mã mặt bằng:</label>
            <input type="text" id="maMatBang" name="maMatBang" required pattern="[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}" placeholder="XXX-XX-XX"/>
        </div>

        <div>
            <label for="trangThai">Trạng thái:</label>
            <select id="trangThai" name="trangThai" required>
                <option value="Trống">Trống</option>
                <option value="Hạ tầng">Hạ tầng</option>
                <option value="Đầy đủ">Đầy đủ</option>
            </select>
        </div>

        <div>
            <label for="dienTich">Diện tích (m²):</label>
            <input type="number" id="dienTich" name="dienTich" required min="20"/>
        </div>

        <div>
            <label for="tang">Tầng:</label>
            <input type="number" id="tang" name="tang" required min="1" max="15"/>
        </div>

        <div>
            <label for="loaiMatBang">Loại mặt bằng:</label>
            <select  id="loaiMatBang" name="loaiMatBang" required>
                <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
                <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
            </select>
        </div>

        <div>
            <label for="giaTien">Giá tiền (VNĐ):</label>
            <input type="number" id="giaTien" name="giaTien" required min="1000000"/>
        </div>

        <div>
            <label for="ngayBatDau">Ngày bắt đầu:</label>
            <input type="date"id="ngayBatDau" name="ngayBatDau" required/>
        </div>

        <div>
            <label for="ngayKetThuc">Ngày kết thúc:</label>
            <input type="date" id="ngayKetThuc" name="ngayKetThuc" required/>
        </div>

        <button type="submit">Thêm mới</button>

        <c:if test="${not empty error}">
            <p class="text-danger">${error}</p>
        </c:if>
    </form>
    <a href="">Trở về trang chính</a>
</div>
</body>
</html>
