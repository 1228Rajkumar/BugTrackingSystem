function ChangeWindow(ele){
	let type =ele.id 
	if(type == "Add_solution"){ 
        _(".bugStatus-success").style.display = "none"
		  if (_(".Solutionpage").style.display === "none") {
            _(".Viewpage").style.display = "none"
            _(".Statuspage").style.display = "none"
            _(".Solutionpage").style.display = "flex"
            _(".bugSolution-success").style.display = "none"
			  } else {
                _(".Solutionpage").style.display = "none"
			  }
	}
	else if(type == "Status_update"){ 
        _(".bugSolution-success").style.display = "none"
		if (_(".Statuspage").style.display === "none") {
			_(".Statuspage").style.display = "flex"
            _(".Solutionpage").style.display = "none"
            _(".Viewpage").style.display = "none"
            _(".bugStatus-success").style.display = "none"
		  } else {
			_(".Statuspage").style.display = "none"
		  }

	}else if(type == "View"){ 
        _(".bugSolution-success").style.display = "none"
        _(".bugStatus-success").style.display = "none"
		if (_(".Viewpage").style.display === "none") {
			_(".Viewpage").style.display = "flex"
            _(".Statuspage").style.display = "none"
            _(".Solutionpage").style.display = "none"
		  } else {
			_(".Viewpage").style.display = "none"
		  }
    }
}




document.getElementById("View").addEventListener("click",bugView);
document.getElementById("Status_update").addEventListener("click",bugView);
document.getElementById("Add_solution").addEventListener("click",bugView);

let getBugData = true;
var isBugAvailable = false;


_(".BUGID").addEventListener("focusout", function (){ bugIdExistsByUser(_(".BUGID").value, ".bugIdisExists-status")});
_(".BUGID").addEventListener("focusin", function () {hideBugIdisExists(".bugIdisExists-status")} );

_(".Statuspage").addEventListener("submit", bugStatus)
var Status = document.getElementById("Status");
function bugStatus(event){
    event.preventDefault();
    if(isBugAvailable === true){
          isBugAvailable = false;
		console.log("status")
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST","updateStatus",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    xhr.send("bugId="+_(".BUGID").value+"&Status="+Status.value);
	    xhr.onload = function(){
	    	 if(xhr.responseText == "true"){
	    		 getBugData = true;
	    		 let table = document.querySelectorAll("table");
	 	        table.forEach(element => {
	 	        	 while(element.rows.length > 1) {
	 	        		  element.deleteRow(1);
	 	        		}
	 			  });
	    		 _(".Statuspage").style.display = "none";
	    		 _(".bugStatus-success").style.display = "block"	
	    		 
	         }else if(xhr.responseText == "false"){
	        	 _(".bugStatus-success").style.display = "none"
	         }
	        console.log(xhr.response);
	    }
    }
    }

   
var Solution = document.getElementById("solution");
var bugID = document.getElementById("bugId");
bugID.addEventListener("focusout", function (){ bugIdExistsByUser(bugID.value, ".bugIdisExists-solution")});
bugID.addEventListener("focusin", function () {hideBugIdisExists(".bugIdisExists-solution")} );
_(".Solutionpage").addEventListener("submit", bugSolutin);
function bugSolutin(event){
         event.preventDefault();
        if(isBugAvailable === true){
            console.log("solution")
            isBugAvailable = false;
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST","addSolution",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    xhr.send("bugId="+bugID.value+"&solution="+Status.value);
	    xhr.onload = function(){
	    	 if(xhr.responseText == "true"){
	    		 getBugData = true;
	    		 let table = document.querySelectorAll("table");
	 	        table.forEach(element => {
	 	        	 while(element.rows.length > 1) {
	 	        		  element.deleteRow(1);
	 	        		}
	 			  });
	    		 _(".Solutionpage").style.display = "none";
	    		 _(".bugSolution-success").style.display = "block"	
	    		 
	         }else if(xhr.responseText == "false"){
	        	 _(".bugSolution-success").style.display = "none"
	         }
	        console.log(xhr.response);
	    }
    }
    }