const URL = 'http://localhost:8080/project1/';

let signUpButton = document.getElementById("signUpButton");

signUpButton.onclick = signUp;

function getNewUser(){
  let newUsername = document.getElementById("signUpUsername").value;
  let newPassword = document.getElementById("signUpPassword").value;

  let credential = {
      
	ers_users_id:0,
	ers_username:newUsername,
	ers_password:newPassword,
	user_first_name:newFirstName,
	user_last_name:newLastName,
	user_email:newEmail,
	user_role_id:0,
	user_role:""
  }

  return credential;
}

function getNewReimb(){
  let reimb = {
    abc:bcd
  }
}
async function signUp(){
  let cred = getNewUser();
  let response = await fetch(URL + 'signUp', {
    method:'POST',
    body:JSON.stringify(cred)
  });

  if (response.status==201){
    console.log("New user added");
  } else {
    console.log("New user not added");
  }
}
