var start = 0;
var showCount = 5;
var end = 5;


function isTokenExist() {
    return localStorage.getItem('token') != null;
}

function logOut() {
    localStorage.removeItem('token');
    window.location.replace(window.location.origin);
}

async function isAuth() {
    if (isTokenExist()) {
        let token = localStorage.getItem('token');
        await authorizedUser(token);
        return true;
    }
    return false;
}

async function getUser() {
    let token = localStorage.getItem('token');
    let res = await getUserByToken(token);
    let body = await res.text();
    return JSON.parse(body);
}

function validateLoginPass(login, password , email) {
    if (!(login.length >= 4 && login.length <= 16)) {
        return "not correct login";
    }
    if (!(password.length >= 4 && password.length <= 16)) {
        return "not correct password";
    }
    if (!email.length >= 4) {
        return "not correct email";
    }

    return true;
}

async function reg() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let mes = document.getElementById("message");
    let result = validateLoginPass(login, password , email);
    if (result === true) {
        let data = {login: login, password: password,email:email};
        let res = await regUser(data);
        if (res.ok) {
            mes.innerHTML = "Check your email address to continue your registration"
            //window.location.replace(window.location.origin);
        } else {
            mes.innerHTML = "this user already exist";
        }

    } else {
        mes.innerHTML = result;
    }
}

async function login() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let mes = document.getElementById("message");
    let data = {login: login, password: password};
    let result = await logUser(data);
    if (result.ok) {
        let body = await result.text();
        let info = JSON.parse(body);
        localStorage.setItem('token', info['token']);
        window.location.replace(window.location.origin);
    } else {
        let body = await result.text();
        let info = JSON.parse(body);
        mes.innerHTML = info['message'];
    }
}

async function generateListOfUsers(result) {
    result.innerText = '';
    if(await isAuth()) {
        if(await isAdmin()) {

            let data = await getAllUsers();

            let showed = data.length;
            if (showed > end) {
                showed = end
            }
            let table=document.createElement("table");
            table.classList.add('user-table');
            let tr=document.createElement("tr");
            let td=document.createElement('td');
            let td2=document.createElement('td');

            let Icons=[];
            Icons[0]=new Image();
            Icons[0].src="../icons/user.png";
            Icons[1]=new Image();
            Icons[1].src='../icons/key.png';
            td.classList.add('user-icon');
            td2.classList.add('user-icon');
            td.appendChild(Icons[0]);
            td2.appendChild(Icons[1]);

            tr.appendChild(td)
            tr.appendChild(td2);
            table.appendChild(tr);


            for (let i = start; i < showed; i++) {
                let divP = div();
                let text = p('login:' + data[i].login + ' role:' + data[i].userRole.name);
                let tr2=document.createElement('tr');
                let td3=document.createElement('td');
                let td4=document.createElement('td');
                if(data[i].userRole.name=="ROLE_USER"){
                    data[i].userRole.name="user";
                }
                else if(data[i].userRole.name=="ROLE_ADMIN"){
                    data[i].userRole.name="admin";
                }
                td3.innerHTML=data[i].login;
                td4.innerHTML=data[i].userRole.name;
                tr2.appendChild(td3);
                tr2.appendChild(td4);
                table.appendChild(tr2);
                divP.appendChild(table);
                result.appendChild(divP);
            }
        }

    }
    else {

    }
}

function genLogReg() {

    let divLogReg = document.querySelector('.logReg');
    let aDivLog = div();
    let aDivReg = div();
    let aLogin = a('/loginin', 'Логин');
    let aReg = a('/reg', 'Регистрация');
    aReg.classList.add('button');
    aLogin.classList.add('button');
    aDivLog.appendChild(aLogin);
    aDivReg.appendChild(aReg);
    divLogReg.appendChild(aDivLog);
    divLogReg.appendChild(aDivReg);
    divLogReg.appendChild(aDivLog);

}

function genLogout() {
    let divLogOut = document.querySelector('.logOut');
    let logoutBtn = button(logOut, 'Выход');
    divLogOut.appendChild(logoutBtn)
}

async function genNext() {
    let div = document.querySelector('.nextPrev');
    let logoutBtn = button(await next, 'Next');
    div.appendChild(logoutBtn)
}

function genPrev() {
    let div = document.querySelector('.nextPrev');
    let logoutBtn = button(prev, 'Prev');
    div.appendChild(logoutBtn)
}

async function next() {
    let result = document.querySelector('.results');
    await fetch("/users", {
        method: "POST",
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        if (end < data.length) {
            start += showCount;
            end += showCount;
        }
        generateListOfUsers(result);
    });
}

async function prev() {
    let result = document.querySelector('.results');
    if (start - showCount >= 0) {
        start -= showCount;
        end -= showCount;
    }
    await generateListOfUsers(result);
}