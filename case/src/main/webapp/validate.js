function validateForm() {
    var name = document.forms["studentForm"]["name"].value;
    var email = document.forms["studentForm"]["email"].value;
    var errorDiv = document.getElementById("errorDiv");

    if (name.length > 50 || /\d/.test(name)) {
        errorDiv.innerHTML = '<div class="alert-custom">' +
            '<span class="closebtn" onclick="this.parentElement.style.display=\'none\';">&times;</span>' +
            '<strong>Lỗi:</strong> Tên không được chứa số và không dài quá 50 ký tự.' +
            '</div>';
        return false;
    }

    // Giả sử có logic kiểm tra email tồn tại
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        errorDiv.innerHTML = '<div class="alert-custom">' +
            '<span class="closebtn" onclick="this.parentElement.style.display=\'none\';">&times;</span>' +
            '<strong>Lỗi:</strong> Email không đúng định dạng.' +
            '</div>';
        return false;
    }

    return true;
}