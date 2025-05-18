/**
 * Các xử lý cho phần làm việc với người sử dụng
 */

$(function() {
	$('#txtUserName').on('input', function() {
		if (!validateUsername($('#txtUserName').val())) {
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$(this).next().text('Tên đăng nhập 8 đến 40 ký tự không chứa dấu cách');
		} else {
			$(this).removeClass('is-invalid');
			$(this).addClass('is-valid');
		}
	});

	$('#txtPassword').on('input', function() {
		if (!validatePwd($('#txtPassword').val())) {
			$(this).addClass('is-invalid');
			$(this).removeClass('is-valid');
			$(this).next().text('Mật khẩu nằm trong khoảng 6-20 ký tự');
		} else {
			$(this).removeClass('is-invalid');
			$(this).addClass('is-valid');
		}
	});

	function validateUsername(name) {
		if (name == '') {
			return false;
		} else {
			if (name.indexOf(' ') != -1) {
				return false;
			} else {
				if (name.length < 8 || name.length > 40) {
					return false;
				}
			}
		}
		return true;
	}
	function validatePwd(pass) {
		if (pass == "") {
			return false;
		} else {
			if (pass.length < 6 || pass.length > 20) {
				return false;
			}
		}
		return true;
	};
	$('form').on('submit', function(event) {
		if (!this.checkValidity()) {
			event.preventDefault();
			event.stopPropagation();
		}
		$(this).addClass('was-validated');
	});
})

function checkLogin() {
	//let pass = window.document.frmLogin.txtPassword.value;
	//let pass = document.forms[0].txtPassword.value;

	let name = document.getElementById("txtUserName").value;
	let pass = document.getElementById("txtPassword").value;

	var message = '';

	var validUserName = true;
	var validPassword = true;

	if (name == '') {
		validUserName = false;
		message += "Thiếu thông tin tài khoản đăng nhập";
	} else {
		if (name.indexOf(' ') != -1) {
			validUserName = false;
			message += "Tên đăng nhập có dấu cách";
		} else {
			if (name.length < 8 || name.length > 40) {
				validUserName = false;
				message += "Tên đăng nhập có độ dài trong khoảng 8-40 ký tự";
			}
		}
	}

	if (pass == "") {
		validPassword = false;
		message += "\nThiếu mật khẩu";
	} else {
		if (pass.length < 6 || pass.length > 20) {
			validPassword = false;
			message += "\nMật khẩu nằm trong khoảng 6-20 ký tự";
		}
	}

	/*
	if (message != "") {
		window.alert(message);
		if (!validUserName) {
			document.getElementById("txtUserName").focus();
			document.getElementById("txtUserName").select();
		} else if (!validPassword) {
			document.getElementById("txtPassword").focus();
			document.getElementById("txtPassword").select();
		}
	}
	*/

	return validUserName && validPassword;
}

const login = (fn) => {
	//if (checkLogin()) {

	fn.method = "post"; //Phương thức doPost()
	fn.action = "Login";
	fn.submit();
	//}
}