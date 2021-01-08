let login_form = document.getElementById('login-validation');

login_form.addEventListener("submit", async (e) => {

    e.preventDefault();
    e.stopPropagation();
    let response = await fetch('api/accounts/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: document.getElementById('email').value,
            password: document.getElementById('password').value
        })
    });
    window.sessionStorage["curr_email"] = document.getElementById('email').value;
    let result = await response;
    console.log(response);

    if(result['status'] === 200){
        location.href = "dashboard.html";
    }
    else{
        document.getElementById("test_right_password").innerHTML = "Wrong Email Id or Password";
    }
});