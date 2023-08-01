

function ChangeWindow(ele){
	let type =ele.id
	if(type == "Create_bug"){ 
		 _(".bugVerify-success").style.display = "none"
		 _(".bugAssign-success").style.display = "none"
		  if (_(".create").style.display === "none") {
			  _(".create").style.display = "flex";
			  _(".Viewpage").style.display = "none";
              _(".Verifypage").style.display = "none";
				_(".bugCreat-success").style.display = "none";
				 _(".Assignpage").style.display = "none";
				
			  } else {
				  _(".create").style.display = "none";
			  }
	}
	else if(type == "View"){ 
		_(".bugCreat-success").style.display = "none";
		 _(".bugVerify-success").style.display = "none";
		 _(".bugAssign-success").style.display = "none"
		if (_(".Viewpage").style.display === "none") {
			_(".Viewpage").style.display = "flex";
			_(".create").style.display = "none";
            _(".Verifypage").style.display = "none";
            _(".Assignpage").style.display = "none";
		  } else {
			/*   location.reload(); */
			  _(".Viewpage").style.display = "none";
		  }

	}else if(type == "Verify"){ 
		_(".bugCreat-success").style.display = "none";
		_(".bugAssign-success").style.display = "none";
		 _(".bugVerify-success").style.display = "none";
		if (_(".Verifypage").style.display === "none") {
			_(".Verifypage").style.display = "flex";
			_(".create").style.display = "none";
            _(".Viewpage").style.display = "none";
            _(".Assignpage").style.display = "none";
		  } else {
			  _(".Verifypage").style.display = "none";
		  }

	}
	else if(type == "Assign_bug"){ 
		_(".bugCreat-success").style.display = "none";
		_(".bugAssign-success").style.display = "none";
		 _(".bugVerify-success").style.display = "none";
		if (_(".Assignpage").style.display === "none") {
			_(".Assignpage").style.display = "flex";
			_(".create").style.display = "none";
            _(".Viewpage").style.display = "none";
            _(".Verifypage").style.display = "none";
		  } else {
			  _(".Assignpage").style.display = "none";
		  }

	}
}

var bugId =document.getElementById("bugID");
var bugName =document.getElementById("bugName");
var description =document.getElementById("description");
var Module =document.getElementById("module");
var Priority =document.getElementById("priority");

let getBugData = true;
var isBugAvailable = false;

bugId.addEventListener("focusout", bugIdExists);
bugId.addEventListener("focusin", function () {hideBugIdisExists(".bugIdError")} );

var isbugIdExists = false;
function bugIdExists(){
    let xhr = new XMLHttpRequest();
    xhr.open("POST","isBugIdExists",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("bugId="+ bugId.value);
    xhr.onload = function(){
        if(xhr.responseText == "true"){
			bugIdExists = true;
            _(".bugIdError").style.display = "block"
        }else if(xhr.responseText == "false"){
			bugIdExists = false;
            _(".bugIdError").style.display = "none"
        }
    }
}

bugName.addEventListener("focusout", getUserID);
function getUserID(){
	console.log("Start getUserID fun");
    let xhr = new XMLHttpRequest();
    xhr.open("POST","getID",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("name="+ sessionStorage.getItem("name"));
    xhr.onload = function(){
    	console.log("Responce:"+xhr.responseText)
        if(xhr.responseText.toLowerCase() == "success"){
        	console.log("getSuccess");
        }else if(xhr.responseText.toLowerCase() == "error"){
        	console.log("getError");
        }
    }
}

_(".create").addEventListener("submit", bugCreation);
function bugCreation(event){
	event.preventDefault();
	if(isbugIdExists = true){
	let id = sessionStorage.getItem("UserId");
	console.log("Start bugCreation fun");
    let xhr = new XMLHttpRequest();
    xhr.open("POST","createBugReport",true);bugErrorDiv
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("bugId="+ bugId.value +"&bugName=" + bugName.value+"&description="+description.value+"&module="+Module.value+"&priority="+Priority.value+"&RaisedById="+id);
    xhr.onload = function(){
    	console.log("Responce:"+xhr.responseText)
        if(xhr.responseText.toLowerCase() == "success"){
            _(".create").style.display = "none";
            _(".bugCreat-success").style.display = "block"
             location.reload();
            getBugData = true;
        }else if(xhr.responseText == "error"){
            _(".bugCreat-success").style.display = "none"
        }
    }
		}				
}

document.getElementById("View").addEventListener("click",bugView);
document.getElementById("Verify").addEventListener("click",bugView);
document.getElementById("Assign_bug").addEventListener("click",bugView);


// function bugView(){
// 	var BugData;
// 	if(getBugData === true){	
// 		let id = sessionStorage.getItem("UserId");
// 		let role = sessionStorage.getItem("Role");
// 		console.log("view")
// 	    let xhr = new XMLHttpRequest();
// 	    xhr.open("POST","getBugReports",true);
// 	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
// 	    xhr.send("UserID="+id+"&Role="+role);
// 	    xhr.onload = function(){
// 	        let bugDetails =JSON.parse(xhr.response);
// 	        console.log(bugDetails);
// 	        BugData = bugDetails;
// 	        getBugData = false;
// 	        let table = document.querySelectorAll(".bug-table");
// 	        table.forEach(element => {
// 	        	generateTable(element, BugData);
// 			  });
	        
// 	    }
//     }
    
//     }

// const BUGREPORT =['bugId', 'bugName','description','module', 'priority','status','solution','raisedByName', 'raisedDate','assignedToName','solvedDate']

// function generateTable(table, data){
//  let n = Object.keys(data).length;
//  let count =0;
//      for(let i=0; i< n; i++){
//     	  let row = table.insertRow();
//     	 /* let datakeys = Object.keys(data[i]); */
//     	 if(count % 2 == 0){
//     		 row.style.backgroundColor = "#F5F5F5";
//     	 }
//          for(let key of BUGREPORT){
//         	 count++;
//         	 let cell = row.insertCell();
//         	 let text = document.createTextNode(data[i][key]);
//         	 cell.appendChild(text);
//          }
// }
// }

// function bugIdExistsByUser(bugID, bugErrorDiv){
	
// 	let id = sessionStorage.getItem("UserId");
// 	let role = sessionStorage.getItem("Role");
//     let xhr = new XMLHttpRequest();
//     xhr.open("POST","isBugIdExistByUser",true);
//     xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
//     xhr.send("bugId="+bugID+"&Role="+role+"&userId="+id);
//     xhr.onload = function(){
// 		console.log(" bugIdExistsByUser responseText :"+xhr.responseText);
//         if(xhr.responseText == "true"){
//         	isBugAvailable = true;
//             _(bugErrorDiv).style.display = "none"
//         }else if(xhr.responseText == "false"){
// 			console.log("ulla");
//         	isBugAvailable = false;
//             _(bugErrorDiv).style.display = "block"
//         }
//     }
// }



_(".BUGID").addEventListener("focusout", function (){ bugIdExistsByUser(_(".BUGID").value, ".bugIdisExists-verify")});
_(".BUGID").addEventListener("focusin", function () {hideBugIdisExists(".bugIdisExists-verify")} );


_(".Verifypage").addEventListener("submit", bugVerify)

function bugVerify(event){
	event.preventDefault();
	let Status = document.getElementById("Status");
	if(isBugAvailable == true){
		isBugAvailable = false;
		console.log("isBugAvailable:"+isBugAvailable);
		console.log("verify")
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST","bugVerify",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    xhr.send("bugId="+_(".BUGID").value+"&Status="+Status.value);
	    xhr.onload = function(){
	    	 if(xhr.responseText == "true"){
	    		 getBugData = true;
	    		 let table = document.querySelectorAll(".bug-table");
	 	        table.forEach(element => {
	 	        	 while(element.rows.length > 1) {
	 	        		  element.deleteRow(1);
	 	        		}
	 			  });
	    		 _(".Verifypage").style.display = "none";
	    		 _(".bugVerify-success").style.display = "block"	
	    		 
	         }else if(xhr.responseText == "false"){
	        	 _(".bugVerify-success").style.display = "none"
	         }
	        console.log(xhr.response);
	    }
	    }
    }


    
let BUGId = document.getElementById("BUGID");
var DEVEId = document.getElementById("DeveID");
BUGId.addEventListener("focusout", function () { 
	bugIdExistsByUser(BUGId.value, ".bugIdisExists-assign");
	getDeveloperData();
});
BUGId.addEventListener("focusin", function () {hideBugIdisExists(".bugIdisExists-assign")} );


var getDeveData = true;
function getDeveloperData(){
	if(getDeveData === true){
		console.log("getDeveloperData")
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST","LeastBugDeveloper",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    xhr.send("bugId="+BUGId.value);
	    xhr.onload = function(){
	    	 /* if(xhr.responseText == "true"){
	    		 getBugData = true;
	    		 let table = document.querySelectorAll("table");
	 	        table.forEach(element => {
	 	        	 while(element.rows.length > 1) {
	 	        		  element.deleteRow(1);
	 	        		}
	 			  });
	    		 _(".Verifypage").style.display = "none";
	    		 _(".bugVerify-success").style.display = "block"	
	    		 
	         }else if(xhr.responseText == "false"){
	        	 _(".bugVerify-success").style.display = "none"
	         } */
	        console.log("getDeveloperData :"+xhr.response);
			
	         let DeveDetails =JSON.parse(xhr.response);
			 if(DeveDetails.length != 0){
				getDeveData = false;
				let table = _(".DeveTable");
	         _(".DeveDetails").style.display = "flex";
	        generateDeveloperTable(table, DeveDetails);
			 }
	         
	    }
	}
    }
    
const DEVELOPERDATA =['UserId', 'Name','Location','bugCount']

function generateDeveloperTable(table, data){
 let n = Object.keys(data).length;
 let count =0;
 var tr = document.createElement('tr'); // Header row
 	for(let key of DEVELOPERDATA){
 		 var th = document.createElement('th'); //column
 	    var text = document.createTextNode(key); //cell
 	    th.appendChild(text);
 	    tr.appendChild(th);
 	}
 	table.appendChild(tr);
     for(let i=0; i< n; i++){
    	  let row = table.insertRow();
    	 /* let datakeys = Object.keys(data[i]); */
    	 if(count % 2 == 0){
    		 row.style.backgroundColor = "#F5F5F5";
    	 }
         for(let key of DEVELOPERDATA){
        	 count++;
        	 let cell = row.insertCell();
        	 let text = document.createTextNode(data[i][key]);
        	 cell.appendChild(text);
         }
}
}


DEVEId.addEventListener("focusout", DeveloperIdExists);
DEVEId.addEventListener("focusin", function () {
hideBugIdisExists(".DeveloperIdisExists")
});

var isDeveloperIdExists = false;
function DeveloperIdExists(){
	console.log("DeveloperIdExists");
    let xhr = new XMLHttpRequest();
    xhr.open("POST","isDeveloperExists",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("UserID="+ DEVEId.value);
    xhr.onload = function(){
        if(xhr.responseText == "true"){
			isDeveloperIdExists = true;
            _(".DeveloperIdisExists").style.display = "none"
        }else if(xhr.responseText == "false"){
			isDeveloperIdExists = false;
            _(".DeveloperIdisExists").style.display = "block"
        }
    }
}

_(".Assignpage").addEventListener("submit", bugAssign);

function bugAssign(event){
	event.preventDefault();
	if(isBugAvailable == true && isDeveloperIdExists == true){
		isBugAvailable = false;
	console.log("bugAssign")
    let xhr = new XMLHttpRequest();
    xhr.open("POST","assignBug",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("bugId="+BUGId.value+"&DeveloperId="+DEVEId.value);
    xhr.onload = function(){
    	  if(xhr.responseText == "true"){
    		 getBugData = true;
    		 let table = document.querySelectorAll(".bug-table");
 	        	table.forEach(element => {
 	        	 while(element.rows.length > 1) {
 	        		  element.deleteRow(1);
 	        		}
 			  });
 	        let Devetable =_(".DeveTable");
			
 	        	 while(Devetable.rows.length > 0) {
 	        		Devetable.deleteRow(0);
 	        		}
					 _(".DeveDetails").style.display = "none";
					 getDeveData = true;
    		 _(".Assignpage").style.display = "none";
    		 _(".bugAssign-success").style.display = "block"	
    		 
         }else if(xhr.responseText == "false"){
        	 _(".bugAssign-success").style.display = "none"
         } 
      
    }
}
}