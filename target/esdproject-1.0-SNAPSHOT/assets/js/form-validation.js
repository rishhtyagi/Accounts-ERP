let create_form = document.getElementById('create-validation');
let update_form = document.getElementById('update-validation');
let read_form = document.getElementById('read-validation');
let delete_form = document.getElementById('delete-validation');

create_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (create_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('description', document.getElementById('description').value);
        form_data.append('amount', document.getElementById('amount').value);
        form_data.append('date', document.getElementById('date').value);
        form_data.append('status', document.getElementById('status').value);

        let response = await fetch('api/operation/create', {
            method: 'POST',
            body: form_data
        });
        let result = await response;
        if (result['status'] === 200) {
            document.getElementById("give_create_status").innerHTML = "Successful";
        } else {
            document.getElementById("give_create_status").innerHTML = "Failed";
        }
        console.log(result);
    } else {
        create_form.classList.add('was-validated');
    }
};

update_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (update_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('bill_id', document.getElementById('bill_id_update').value);
        form_data.append('amount', document.getElementById('new_amount').value);
        form_data.append('status', document.getElementById('new_status').value);

        let response = await fetch('api/operation/update', {
            method: 'POST',
            body: form_data
        });
        let result = await response;
        console.log(result);
        if (result['status'] === 200) {
            document.getElementById("give_update_status").innerHTML = "Successful";
        } else {
            document.getElementById("give_update_status").innerHTML = "Failed";
        }
    } else {
        create_form.classList.add('was-validated');
    }
};

read_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (read_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('bill_id', document.getElementById('bill_id_read').value);

        let response = await fetch('api/operation/read', {
            method: 'POST',
            body: form_data
        });
        let result = await response;
        console.log(result);
        if (result['status'] === 200) {
            // document.getElementById("course-success").style.display = "none";
            // document.getElementById("course-alert").style.display = "block";
            document.getElementById("give_read_status").innerHTML = "Successful";
        } else {
            // document.getElementById("course-alert").style.display = "none";
            // document.getElementById("course-success").style.display = "block";
            document.getElementById("give_read_status").innerHTML = "Failed";
        }
    } else {
        create_form.classList.add('was-validated');
    }
};

delete_form.onsubmit = async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (delete_form.checkValidity() === true) {
        let form_data = new FormData();
        form_data.append('bill_id', document.getElementById('bill_id_delete').value);

        let response = await fetch('api/operation/delete', {
            method: 'POST',
            body: form_data
        });
        let result = await response;
        console.log(result);
        console.log(result['body']);
        if (result['status'] === 200) {
            document.getElementById("give_read_status").innerHTML = "Successful";
        } else {
            document.getElementById("give_read_status").innerHTML = "Failed";
        }
    } else {
        create_form.classList.add('was-validated');
    }
};