<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>SingUp</title>
	<link rel="stylesheet" href="index.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	
	<style type="text/css">
		/* .box {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
			border: 1px solid transparent;
			border-radius: 20px;
			padding: 30px;
			box-shadow: rgba(0, 0, 0, 0.3) 0px 19px 38px, rgba(0, 0, 0, 0.30) 0px 15px 12px;
		} */

		.nameError {
			font-size: 20px;
			color: red;
			position: relative;
			display: none;
		}

		#Role,
		#module {
			padding: 5px;
			width: 182px;
			height: 30px;
		}

		.module-box {
			display: none;
			width: 70%;
			height: 55px;
			background-color: #fff;
			margin: 15px 0;
			border-radius: 55px;
			/* display: grid; */
			grid-template-columns: 15% 70% 15%;
			padding: 0 0.4rem;
			grid-template-columns: 15% 70% 15%;
			justify-content: space-evenly;
			align-items: center;
		}

		main {
			width: 80%;
			flex-grow: 1;
			margin: 50px auto;
			display: flex;
		}

		.image {
			flex-basis: 55%;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			
		}

		.image>div {
			padding: 50px 0 0;
		}

		.content {
			flex-basis: 45%;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
			background-color: #a0bdff;
			border: 1px solid black;
			border-radius: 20px;
		}

		img {
			width: 100%;
		}

		h1 {
			font-size: 80px;
			text-align: center;
			padding: 10px 0;
			margin: 10px;
		}

		form {
			display: flex;
			align-items: center;
			justify-content: space-evenly;
			flex-direction: column;
			overflow: hidden;
			grid-column: 1 / 2;
			grid-row: 1 / 2;
			transition: 0.2s 0.7s ease-in-out;
			width: 100%;
		}

		.input-field input, select, option {
			height: 30px;
			width: 240px;
			display: inline-block;
			background: none;
			outline: none;
			border: none;
			line-height: 1;
			font-weight: 600;
			font-size: 1.1rem;
			color: #333;
			padding: 5px;
			margin-right:20px ;
			
		}

		.input-field label{
			text-align: center;
			line-height: 55px;
			font-size: 1.1rem;
		}

		 .input-field input::placeholder {
			color: #222;
			font-weight: 500;
			align-items: center;
		}

		.input-field {
			width: 70%;
			height: 55px;
			background-color: #fff;
			margin: 15px 0;
			border-radius: 55px;
			display: grid;
			grid-template-columns: 15% 70% 15%;
			padding: 0 0.4rem;
			display: flex;
			justify-content: center;
			align-items: center;
		}
		.input-field i {
   		 text-align: center;
   		 line-height: 55px;
  		  font-size: 1.1rem;
		/*   margin-left: 20px; */
		}
	</style>

</head>

<body>
	<main>
		<section class="image">
			<img src="./images/signup.jpeg" alt="Sign In">
		</section>
		<section class="content">
			<h1>Sign Up</h1>
			<form>
				<div class="input-field">
					<!-- <label size="4px">Name</label> -->
					<input type="text" name="name" id="name" placeholder="name" required autocomplete="off">
				</div>
				<div class="nameError">User Exists ! Give Unick Name</div>
				<div class="input-field">
					<!-- <label size="4px">Password</label> -->
					<input type="password" name="password" id="password" placeholder="password"  required autocomplete="off">
					<i class="far fa-eye" id="togglePassword" style="cursor: pointer;"></i>
				</div>
				<div class="input-field">
					<!-- <label size="4px">Role </label></td> -->
					<select name="Role" id="Role" required="required" style="width: 240px;" >
					<option value="" disabled selected style="color:#333;">Role</option>
						<option value="Tester">Tester</option>
						<option value="Developer">Developer </option>
					</select>
				</div>
				<div class="input-field">
					<!-- <label size="4px">Age</label> -->
					<input type="number" name="age" id="age" placeholder="Age" >
				</div>
				<div class="input-field">
					<!-- <label size="4px">Location</label> -->
					<input type="text" name="location" id="location" required placeholder="Location" >
				</div>
				<div class="module-box">
					<!-- <label size="4px">Module </label> -->
					<select name="module" id="module" style="width: 240px;">
					 <option value="" disabled selected style="color:#333;">Module</option>
						<option value="backend">Backend</option>
						<option value="frontend">FrontEnd</option>
						<option value="UI">UI</option>
					</select>
				</div>
				<button type="submit" value="Submit">Submit</button>
			</form>
		</section>
	</main>
</body>
<script type="text/javascript">
	let visible = false;
document.getElementById("togglePassword").onclick = () => {
    if (visible) {
        document.getElementById("password").setAttribute("type", "password");
        document.getElementById("togglePassword").setAttribute("class", "far fa-eye");
        visible = false;
    } else {
        document.getElementById("password").setAttribute("type", "text");
        document.getElementById("togglePassword").setAttribute("class", "far fa-eye-slash");
        visible = true;
    }
}
	var signUp = document.querySelector("form");
	var Name = document.getElementById("name");
	var Password = document.getElementById("password");
	var Age = document.getElementById("age");
	var Place = document.getElementById("location");
	var Role = document.getElementById("Role");
	var NameErrorMessage = document.querySelector(".nameError");
	var Module = document.getElementById("module");
	var ModuleBox = document.querySelector(".module-box");

	Name.addEventListener("focusout", NameCheck);

	Role.addEventListener("focusout", viewModule);
	function viewModule() {
		if (Role.value == "Developer") {
			ModuleBox.style.display = "flex";
		} else if (Role.value == "Tester") {
			ModuleBox.style.display = "none";
		}
	}

	function NameCheck(event) {
		event.preventDefault();
		console.log("start");
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "isNameExists", true);
		xhr.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
		xhr.send("name=" + Name.value);
		xhr.onload = function () {
			console.log("response :" + xhr.responseText);
			if (xhr.responseText == "true") {
				Name.style.borderColor = "red";
				NameErrorMessage.style.display = "block";

			} else if (xhr.responseText == "false") {
				Name.style.borderColor = "transparent";
				NameErrorMessage.style.display = "none";
			}

		}
	}

	function setModule(event) {
		let id = sessionStorage.getItem("UserId");
		console.log("setModule");
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "setModule", true);
		xhr.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
		xhr.send("module=" + Module.value + "&UserID=" + id);
		xhr.onload = function () {
			console.log("response :" + xhr.responseText);
			if (xhr.responseText == "true") {
				console.log("setmodule success");

			} else if (xhr.responseText == "false") {
				console.log("setmodule error");
			}

		}
	}

	signUp.addEventListener("submit", SignUp);

	function SignUp(event) {
		event.preventDefault();
		console.log("start");
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "signUp", true);
		xhr.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
		xhr.send("name=" + Name.value + "&password=" + Password.value + "&age="
			+ Age.value + "&location=" + Place.value + "&Role="
			+ Role.value + "&module=" + Module.value);

		xhr.onload = function () {
			let details = JSON.parse(xhr.response);
			console.log(details.detaile.UserId);
			sessionStorage.setItem("name", Name.value);
			sessionStorage.setItem("Role", details.detaile.Role);
			sessionStorage.setItem("UserId", details.detaile.UserId);

			if (details.detaile.Role == "Developer") {
				setModule();
				window.location = "./DeveloperPage"
			} else if (details.detaile.Role == "Tester") {
				window.location = "./TesterPage"
			}
		}
	}

</script>

</html>