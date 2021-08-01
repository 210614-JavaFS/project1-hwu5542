const URL = 'http://localhost:8080/project1/';

let signUpButton = document.getElementById("signUpButton");
let loginButton = document.getElementById("loginButton");

signUpButton.onclick = signUp;
loginButton.onclick = login;

function getUserCred(newUsername, newPassword, newFirstName = "", newLastName = "", newEmail = ""){
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

async function signUp(){
  event.preventDefault();

  let newUsername = document.getElementById("floatingUsername").value;
  let newPassword = document.getElementById("floatingPassword").value;
  let cred = getUserCred(newUsername, newPassword);
  let response = await fetch(URL + 'signUp', {
    method:'POST',
    body:JSON.stringify(cred)
  });
  if (response.status==201){
    deleteLoginForm();
    document.getElementById("registerPrompt").innerHTML = "Please update your profile as soon as possible."
    employeeService();
  } else {
    console.log("New user not added");
  }
}

async function autoLogin(){
  let response = await fetch(URL + 'login/auto', {
    method:'POST'
  })
  if (response.status==201){
    deleteLoginForm();
    let profileInfo = await response.json();
    document.getElementById("registerPrompt").innerHTML = profileInfo.user_role + " " + profileInfo.user_first_name + ", " + "please use the navbar to perform your desired actions.";
    if (profileInfo.user_role == "Manager") {
      managerService();
    } else {
      employeeService();
    }
  }
}

async function login(){
  event.preventDefault();

  let usrn = document.getElementById("LoginUsername").value;
  let pswd = document.getElementById("LoginPassword").value;
  let cred = getUserCred(usrn, pswd);
  let response = await fetch(URL + 'login', {
    method:'POST',
    body:JSON.stringify(cred)
  });
  if (response.status==201){
    deleteLoginForm();
    let profileInfo = await response.json();
    document.getElementById("registerPrompt").innerHTML = profileInfo.user_role + " " + profileInfo.user_first_name + ", " + "please use the navbar to perform your desired actions.";
    if (profileInfo.user_role == "Manager") {
      managerService();
    } else {
      employeeService();
    }
  } else {
    console.log("User login fail");
  }
}

async function signOut() {
  let response = await fetch(URL + 'signOut', {
    method:'POST'
  });
  window.location.replace('http://localhost:8080/static/index.html');
}

async function profile() {
    window.location.replace('http://localhost:8080/static/profile.html');
}

function deleteLoginForm() {
  let loginForm = document.getElementById("signUpPage");
  loginForm.innerHTML = " ";

  loginForm = document.getElementById("loginNavbarAnchor");
  loginForm.innerHTML = " ";

  let profileImgNavbar = document.createElement("img");
  profileImgNavbar.setAttribute("src", "https://github.com/mdo.png");
  profileImgNavbar.setAttribute("alt", "mdo");
  profileImgNavbar.setAttribute("width", "32");
  profileImgNavbar.setAttribute("height", "32");
  profileImgNavbar.setAttribute("class", "rounded-circle");

  loginForm.appendChild(profileImgNavbar);

  loginForm = document.getElementById("loginNavbarMenu");
  loginForm.style.width = "100px";
  loginForm.innerHTML = " ";

  let profileList1 = document.createElement("a");
  profileList1.setAttribute("class", "dropdown-item");
  profileList1.setAttribute("id", "profile");
  profileList1.innerHTML = "Profile";
  loginForm.appendChild(profileList1);

  let profileList2 = document.createElement("hr");
  profileList2.setAttribute("class", "dropdown-divider");
  loginForm.appendChild(profileList2);

  let profileList3 = document.createElement("a");
  profileList3.setAttribute("class", "dropdown-item");
  profileList3.setAttribute("id", "signOut");
  profileList3.innerHTML = "Sign out";
  loginForm.appendChild(profileList3);

  let profileButton = document.getElementById("profile");
  profileButton.onclick = profile;
  let signOutButton = document.getElementById("signOut");
  signOutButton.onclick = signOut;
}

async function managerService() {
  let navbarService = document.getElementById("servicesPlaceHolder");

  let managerServTag = document.createElement("a");
  managerServTag.setAttribute("data-toggle", "dropdown");
  managerServTag.setAttribute("class", "nav-item nav-link dropdown-toggle");
  managerServTag.innerHTML = "Services";
  navbarService.appendChild(managerServTag);

  let managerServList = document.createElement("div");
  managerServList.setAttribute("class", "dropdown-menu");
  let managerServList1 = document.createElement("a");
  managerServList1.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "viewAllRequest");
  managerServList1.innerHTML = "View All Request";
  let managerServList2 = document.createElement("a");
  managerServList2.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "processRequest");
  managerServList2.innerHTML = "Process Request";
  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  navbarService.appendChild(managerServList);

  let viewAllRequestButton = document.getElementById("viewAllRequest");
  viewAllRequestButton.onclick = viewAllRequest();
  let processRequestButton = document.getElementById("processRequest");
  processRequestButton.onclick = processRequest();
}

async function employeeService() {
  let navbarService = document.getElementById("servicesPlaceHolder");

  let managerServTag = document.createElement("a");
  managerServTag.setAttribute("data-toggle", "dropdown");
  managerServTag.setAttribute("class", "nav-item nav-link dropdown-toggle");
  managerServTag.innerHTML = "Services";
  navbarService.appendChild(managerServTag);

  let managerServList = document.createElement("div");
  managerServList.setAttribute("class", "dropdown-menu");
  let managerServList1 = document.createElement("a");
  managerServList1.setAttribute("class", "dropdown-item");
  managerServList1.innerHTML = "View Past Tickets";
  managerServList1.setAttribute("id", "viewPastTickets");
  let managerServList2 = document.createElement("a");
  managerServList2.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "viewPendingRequest");
  managerServList2.innerHTML = "View Pending Request";
  let managerServList3 = document.createElement("a");
  managerServList3.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "submitNewRequest");
  managerServList3.innerHTML = "Submit New Request";
  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  managerServList.appendChild(managerServList3);
  navbarService.appendChild(managerServList);

  let viewPastTicketsButton = document.getElementById("viewPastTickets");
  viewPastTicketsButton.onclick = viewPastTickets();
  let viewPendingRequestButton = document.getElementById("viewPendingRequest");
  viewPendingRequestButton.onclick = viewPendingRequest();
  let submitNewRequestButton = document.getElementById("submitNewRequest");
  submitNewRequestButton.onclick = submitNewRequest();
}

function viewAllRequest(){

}

function processRequest(){

}


function viewPastTickets(){

}

function viewPendingRequest(){

}

function submitNewRequest(){

}

autoLogin();