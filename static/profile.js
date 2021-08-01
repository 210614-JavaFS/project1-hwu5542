const URL = 'http://localhost:8080/project1/';

let editProfileButton = document.getElementById("editProfileButton");

editProfileButton.onclick = checkPassword;

async function printProfile() {
    let response = await fetch(URL + 'profile', {
        method:'POST'
      });
      if (response.status==201){
        let profileInfo = await response.json();
        document.getElementById("ers_username").innerHTML = profileInfo.ers_username;
        document.getElementById("first_name").innerHTML = profileInfo.user_first_name;
        document.getElementById("last_name").innerHTML = profileInfo.user_last_name;
        document.getElementById("email").innerHTML = profileInfo.user_email;
        document.getElementById("role").innerHTML = profileInfo.user_role;
      } else {
        console.log("Get User Profile Fail");
      }
}

async function checkPassword() {
    event.preventDefault();
    if (document.getElementById("input_password1").value == document.getElementById("input_password2").value) {
        editProfile();
    } else
        console.log("password not match !");
}

async function editProfile() {
    let newPassword = document.getElementById("input_password1").value;
    let newFirstName = document.getElementById("input_first_name").value;
    let newLastName = document.getElementById("input_last_name").value;
    let newEmail = document.getElementById("input_email").value;
    let cred = getUserCred(newPassword, newFirstName, newLastName, newEmail);
    
    let response = await fetch(URL + 'profile/edit', {
        method:'POST',
        body:JSON.stringify(cred)
    });
    if (response.status==201){
       console.log("Edit Profile Success");
       window.location.replace("http://localhost:8080/static/profile.html");
       printProfile();
    } else {
       console.log("New user not added");
    }
}

function getUserCred(newPassword, newFirstName, newLastName, newEmail){
    let credential = {
      ers_users_id:0,
      ers_username:"",
      ers_password:newPassword,
      user_first_name:newFirstName,
      user_last_name:newLastName,
      user_email:newEmail,
      user_role_id:0,
      user_role:""
    }
  
    return credential;
  }

printProfile();