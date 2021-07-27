const URL = 'http://localhost:8080/project1/';

let signUpButton = document.getElementById("signUpButton");
let loginButton = document.getElementById("loginButton");

signUpButton.onclick = signUp;
loginButton.onclick = login;

function getUserLogin(newUsername, newPassword){
  let newFirstName = "";
  let newLastName = "";
  let newEmail = "";

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
  event.preventDefault();

  let newUsername = document.getElementById("floatingUsername").value;
  let newPassword = document.getElementById("floatingPassword").value;
  let cred = getUserLogin(newUsername, newPassword);
  let response = await fetch(URL + 'signUp', {
    method:'POST',
    body:JSON.stringify(cred)
  });
  if (response.status==201){
    deleteLoginForm();
  } else {
    console.log("New user not added");
  }
}

async function login(){
  event.preventDefault();

  let usrn = document.getElementById("LoginUsername").value;
  let pswd = document.getElementById("LoginPassword").value;
  let cred = getUserLogin(usrn, pswd);
  let response = await fetch(URL + 'login', {
    method:'POST',
    body:JSON.stringify(cred)
  });
  if (response.status==201){
    deleteLoginForm();
  } else {
    console.log("User login fail");
  }
}

function deleteLoginForm() {
  let loginForm = document.getElementById("signUpPage");
  loginForm.innerHTML = " ";
//  loginForm = document.getElementById("loginNavbar");
//  loginForm.setAttribute("class", "nav-item dropdown")
  loginForm = document.getElementById("loginNavbarAnchor");
  loginForm.innerHTML = " ";

//  let profileAnchorNavbar = document.createElement("a");
//  profileAnchorNavbar.setAttribute("href", "#");
//  profileAnchorNavbar.setAttribute("class", "d-block link-dark text-decoration-none dropdown-toggle");  
//  profileAnchorNavbar.setAttribute("id", "dropdownUser1");
//  profileAnchorNavbar.setAttribute("data-bs-toggle", "dropdown");
//  profileAnchorNavbar.setAttribute("aria-expanded", "false");

  let profileImgNavbar = document.createElement("img");
  profileImgNavbar.setAttribute("src", "https://github.com/mdo.png");
  profileImgNavbar.setAttribute("alt", "mdo");
  profileImgNavbar.setAttribute("width", "32");
  profileImgNavbar.setAttribute("height", "32");
  profileImgNavbar.setAttribute("class", "rounded-circle");
//  profileAnchorNavbar.appendChild(profileImgNavbar);

//  loginForm.appendChild(profileAnchorNavbar);
  loginForm.appendChild(profileImgNavbar);

  loginForm = document.getElementById("loginNavbarMenu");
  loginForm.innerHTML = " ";

  let profileList1 = document.createElement("a");
  profileList1.setAttribute("class", "dropdown-item");
  profileList1.setAttribute("href", "#");
  profileList1.innerHTML = "Profile";
  loginForm.appendChild(profileList1);

  let profileList2 = document.createElement("hr");
  profileList2.setAttribute("class", "dropdown-divider");
  loginForm.appendChild(profileList2);

  let profileList3 = document.createElement("a");
  profileList3.setAttribute("class", "dropdown-item");
  profileList3.setAttribute("href", "#");
  profileList3.innerHTML = "Sign out";
  loginForm.appendChild(profileList3);
}