            //GENERATE TABLE
    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(response => $.each(response, function (i) {
            $('<tr>').html(
                "<td>" + response[i].id + "</td><td>"
                + response[i].username + "</td><td>"
                + response[i].userpassword + "</td><td>"
                + response[i].authority + "</td><td>"
                + '<button type="button" data-toggle="modal" onclick="getUserByIdModal('+ response[i].id +')" class="btn btn-primary">Update</button>'
                + ' <button type="button" id="myBtn" onclick="funcDel(' + response[i].id + ')" class="btn btn-primary">Delete</button>' + "</td>")
                .appendTo('#myTable');
        }));

            //MODAL
    function showFunctionModal(user) {
        $(document).ready(function () {
            $('#myModal').modal();
            $("#userIdInput").val(user.id);
            $("#userNameInput").val(user.username);
            $("#passwordInput").val(user.userpassword);
            $("#roleFormSelect").val(user.authority);
        });
    }

async function getUserByIdModal(id) {
   await fetch('http://localhost:8080/users/' + id)
       .then(user => user.json())
       .then(user => showFunctionModal(user))
}

            //UPDATE

        function funcUpdate() {
            const forms = document.getElementsByTagName('form');
            for (let i = 0; i < forms.length; i++) {
                forms[i].addEventListener('submit', function(e){
                    e.preventDefault();
                    let formData = new FormData(this);
                    ajaxSend(formData);
                    this.reset();
                    window.location.reload();
                });
            }

            const ajaxSend = (formData) => {
                let roleId = 0;
                if(formData.get('role') === 'ROLE_USER')
                    roleId = 1;
                else
                    roleId = 2;
                formData = Object.fromEntries(formData);
                fetch('http://localhost:8080/users/' + roleId, { // файл-обработчик
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json', // отправляемые данные
                    },
                    body: JSON.stringify(formData),
                })
                    .catch(error => console.error(error))
            };
        }
        
            //DELETE

function funcDel(id) {
    fetch('http://localhost:8080/users/' + id, {
        method: 'delete',
    }).then(() => location.reload());
}



