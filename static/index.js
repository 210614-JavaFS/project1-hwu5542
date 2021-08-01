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
    deleteSignUpForm();
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
    deleteSignUpForm();
    let profileInfo = await response.json();
    document.getElementById("registerPrompt").innerHTML = profileInfo.user_role + " " + profileInfo.ers_username + ", " + "please use the navbar to perform your desired actions.";
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
    deleteSignUpForm();
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

function deleteSignUpForm() {
  let signUpForm = document.getElementById("signUpForm");
  signUpForm.innerHTML = " ";

  signUpForm = document.getElementById("loginNavbarAnchor");
  signUpForm.innerHTML = " ";

  let profileImgNavbar = document.createElement("img");
  profileImgNavbar.setAttribute("src", "https://github.com/mdo.png");
  profileImgNavbar.setAttribute("alt", "mdo");
  profileImgNavbar.setAttribute("width", "32");
  profileImgNavbar.setAttribute("height", "32");
  profileImgNavbar.setAttribute("class", "rounded-circle");

  signUpForm.appendChild(profileImgNavbar);

  signUpForm = document.getElementById("loginNavbarMenu");
  signUpForm.style.width = "100px";
  signUpForm.innerHTML = " ";

  let profileList1 = document.createElement("button");
  profileList1.setAttribute("class", "dropdown-item");
  profileList1.setAttribute("id", "profile");
  profileList1.innerHTML = "Profile";
  signUpForm.appendChild(profileList1);

  let profileList2 = document.createElement("hr");
  profileList2.setAttribute("class", "dropdown-divider");
  signUpForm.appendChild(profileList2);

  let profileList3 = document.createElement("button");
  profileList3.setAttribute("class", "dropdown-item");
  profileList3.setAttribute("id", "signOut");
  profileList3.innerHTML = "Sign out";
  signUpForm.appendChild(profileList3);

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
  let managerServList1 = document.createElement("button");
  managerServList1.setAttribute("class", "dropdown-item");
  managerServList1.setAttribute("id", "viewAllRequest");
  managerServList1.innerHTML = "View All Request";

  let managerServList2 = document.createElement("button");
  managerServList2.setAttribute("class", "dropdown-item");
  managerServList2.setAttribute("id", "processRequest");
  managerServList2.innerHTML = "Process Request";

  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  navbarService.appendChild(managerServList);

  let viewAllRequestButton = document.getElementById("viewAllRequest");
  viewAllRequestButton.onclick = viewAllRequestFunc;
  let processRequestButton = document.getElementById("processRequest");
  processRequestButton.onclick = processRequest;
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
  managerServList2.setAttribute("id", "viewPendingRequest");
  managerServList2.innerHTML = "View Pending Request";

  let managerServList3 = document.createElement("a");
  managerServList3.setAttribute("class", "dropdown-item");
  managerServList3.setAttribute("id", "submitNewRequest");
  managerServList3.innerHTML = "Submit New Request";

  managerServList.appendChild(managerServList1);
  managerServList.appendChild(managerServList2);
  managerServList.appendChild(managerServList3);
  navbarService.appendChild(managerServList);

  let viewPastTicketsButton = document.getElementById("viewPastTickets");
  viewPastTicketsButton.onclick = viewPastTickets;
  let viewPendingRequestButton = document.getElementById("viewPendingRequest");
  viewPendingRequestButton.onclick = viewPendingRequest;
  let submitNewRequestButton = document.getElementById("submitNewRequest");
  submitNewRequestButton.onclick = submitNewRequest;
}

async function viewAllRequestFunc(){
  let response = await fetch(URL + 'manager/all', {
    method:'POST'
  });
  if (response.status==201){
    let pageFrontContainer = document.getElementById("pageFrontContainer");
    pageFrontContainer.innerHTML = "<br><h2>All Requests</h2><br><br>";

    let tableContainer = document.createElement("div");
    tableContainer.setAttribute("class", "table-responsive");
    let tableAllRequest = document.createElement("table");
    tableAllRequest.setAttribute("class", "table table-striped table-sm");
    let thead = document.createElement("thead");
    let tr = document.createElement("tr");
    
    let th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Reimb ID";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Reimb Amount";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Submit Time";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Resolved Time";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Description";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Author";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Resolver";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Status";
    tr.appendChild(th);

    th = document.createElement("th");
    th.setAttribute("scope","col");
    th.innerText = "Type";
    tr.appendChild(th);

    thead.appendChild(tr);
    tableAllRequest.appendChild(thead);
    tableContainer.appendChild(tableAllRequest);

    let profileInfoGroup = await response.json();

    pageFrontContainer.appendChild(tableContainer);
  } else {
    console.log("Get User Requests Fail");
  }
}

async function processRequest(){

}


async function viewPastTickets(){

}

async function viewPendingRequest(){

}

async function submitNewRequest(){

}

autoLogin();