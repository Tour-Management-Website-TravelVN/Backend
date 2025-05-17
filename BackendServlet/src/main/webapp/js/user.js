/**
 * Các xử lý cho phần làm việc với người sử dụng
 */

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
			if (name.length < 5 || name.length > 50) {
				validUserName = false;
				message += "Tên đăng nhập có độ dài trong khoảng 5-50 ký tự";
			}
		}
	}

	if (pass == "") {
		validPassword = false;
		message += "\nThiếu mật khẩu";
	} else {
		if (pass.length < 8) {
			validPassword = false;
			message += "\nMật khẩu cần lớn hơn 8 ký tự";
		}
	}
	
	if(message!=""){
		window.alert(message);
		if(!validUserName){
			document.getElementById("txtUserName").focus();
			document.getElementById("txtUserName").select();
		} else if(!validPassword){
			document.getElementById("txtPassword").focus();
			document.getElementById("txtPassword").select();
		}
	}
	
	return validUserName && validPassword;
}

const login = (fn) => {
	if(checkLogin()){
		fn.method = "post"; //Phương thức doPost()
		fn.action = "Login";
		fn.submit();
	}
}