function _(value) {
	return document.querySelector(value);
}
function hideBugIdisExists(value) {
	 _(value).style.display = "none"
}

function bugIdExistsByUser(bugID, bugErrorDiv){
	
	let id = sessionStorage.getItem("UserId");
	let role = sessionStorage.getItem("Role");
    let xhr = new XMLHttpRequest();
    xhr.open("POST","isBugIdExistByUser",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xhr.send("bugId="+bugID+"&Role="+role+"&userId="+id);
    xhr.onload = function(){
		console.log(" bugIdExistsByUser responseText :"+xhr.responseText);
        if(xhr.responseText == "true"){
        	isBugAvailable = true;
            _(bugErrorDiv).style.display = "none"
        }else if(xhr.responseText == "false"){
			console.log("ulla");
        	isBugAvailable = false;
            _(bugErrorDiv).style.display = "block"
        }
    }
}

function bugView(){
	var BugData;
	if(getBugData === true){
		let id = sessionStorage.getItem("UserId");
		let role = sessionStorage.getItem("Role");
		console.log("view")
	    let xhr = new XMLHttpRequest();
	    xhr.open("POST","getBugReports",true);
	    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    xhr.send("UserID="+id+"&Role="+role);
	    xhr.onload = function(){
	        let bugDetails =JSON.parse(xhr.response);
	        console.log(bugDetails);
	        BugData = bugDetails;
	        getBugData = false;
	        let table = document.querySelectorAll(".bug-table");
	        table.forEach(element => {
	        	generateTable(element, BugData);
			  });
	        
	    }
    }
    
    }

const BUGREPORT =['bugId', 'bugName','description','module', 'priority','status','solution','raisedByName', 'raisedDate','assignedToName','solvedDate']

function generateTable(table, data){
 let n = Object.keys(data).length;
 let count =0;
     for(let i=0; i< n; i++){
    	  let row = table.insertRow();
    	 /* let datakeys = Object.keys(data[i]); */
    	 if(count % 2 == 0){
    		 row.style.backgroundColor = "#F5F5F5";
    	 }
         for(let key of BUGREPORT){
        	 count++;
        	 let cell = row.insertCell();
        	 let text = document.createTextNode(data[i][key]);
        	 cell.appendChild(text);
         }
}
}
