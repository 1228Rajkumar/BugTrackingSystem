<!DOCTYPE html>
<html>

<head>
	<link rel="stylesheet" href="index.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	<style type="text/css">


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
	border-radius: 20px ;
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
    justify-content: center;
    flex-direction: column;
    overflow: hidden;
    grid-column: 1 / 2;
    grid-row: 1 / 2;
    transition: 0.2s 0.7s ease-in-out;
    width: 100%;
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
}

.input-field i {
    text-align: center;
    line-height: 55px;
    font-size: 1.1rem;
}

.pass {
    margin: 12px 0;
    color: #444;
}

.pass:hover {
    color: #4d84e2;
}

#togglePassword {
    text-align: center;
    color: #8a8a8a;
}

.input-field input {
    background: none;
    outline: none;
    border: none;
    line-height: 1;
    font-weight: 600;
    font-size: 1.1rem;
    color: #333;
}   

.input-field input::placeholder {
    color: #222;
    font-weight: 500;
}


		.Error {
			font-size: 18px;
			color: red;
			margin-left: 100px;
			display: none;
		}
	</style>
</head>
<body>
	<main>
        <section class="image">
            <img src="./images/signin.png" alt="Sign In">
        </section>
        <section class="content">
            <h1>Sign In</h1>
            <form class="sign-in-form" id="login">
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="name" name="name" required id="name"">
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" name="password" id="password"
                        required>
                    <i class="far fa-eye" id="togglePassword" style="cursor: pointer;"></i>
                </div>
                <div class="Error">Invalid Details</div>
				<button type="submit" value="Login">Login</button>
                <a class="pass" href="signup.jsp">Don't have an account yet? Sign Up</a>
            </form>
        </section>
    </main>
</body>
<script>
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
	var logIn = document.querySelector("form");
	var Name = document.getElementById("name");
	var Password = document.getElementById("password");
	var errorMessage = document.querySelector(".Error");
	logIn.addEventListener("submit", loginCheck);
	function loginCheck(event) {
		event.preventDefault();
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "LogIn", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		console.log("name=" + Name.value + "&password=" + Password.value);
		xhr.send("name=" + Name.value + "&password=" + Password.value);
		xhr.onload = function () {
			let details = JSON.parse(xhr.response);
			console.log(details.detaile.UserId);
			sessionStorage.setItem("name", Name.value);
			sessionStorage.setItem("Role", details.detaile.Role);
			sessionStorage.setItem("UserId", details.detaile.UserId);
			if (details.detaile.Role.toLowerCase() == "developer") {
				errorMessage.style.display = "none";
				window.location = "./DeveloperPage/index.html"
			} else if (details.detaile.Role.toLowerCase() == "tester") {
				errorMessage.style.display = "none";
				window.location = "./TesterPage/index.html"
			} else if (details.detaile.Role == "null") {
				console.log("Responce null");
				errorMessage.style.display = "block";
			}
		}
	}
</script>

</html>