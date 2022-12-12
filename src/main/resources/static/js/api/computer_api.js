async function deleteComputerByNameA(data, token) {
    return await fetch("/admin/deleteComputerByNameA", {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}
async function deleteComputerByNameU(data, token) {
    return await fetch("/user/deleteComputerByNameU", {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}

async function updateComputer(data, token) {
    return await fetch("/admin/updateComputer", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)

    });
}

async function userGetComputerByName(name, token) {
    return await fetch(`/user/userGetComputerByName/${name}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function adminGetComputerByName(name, token) {
    return await fetch(`/admin/adminGetComputerByName/${name}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function isComputerExistByName(data, token) {
    return await fetch("/admin/isComputerExistByName",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function createComputer(data, token) {
    return await fetch("/admin/createComputer",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllComputersForAdmin(token) {
    return await fetch(`/admin/getAllComputersForAdmin`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}
async function getAllComputersForUser(token) {
    return await fetch(`/user/getAllComputersForUser`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}