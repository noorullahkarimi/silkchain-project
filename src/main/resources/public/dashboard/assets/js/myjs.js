// this function load the navbar
function loadNavbar() {
    $.ajax({
        url: `/dashboard/api/v1/navbar`,
        method: 'GET',
        success: function(data) {
            const tableBody = $('#load-nav');
            tableBody.empty(); // Clear previous content
            tableBody.append(data);
            setActiveNavbarItem(); // Call the function after loading the navbar
        },
        error: function(xhr, status, error) {
            console.error("An error occurred: " + error);
        }
    });
}

function setActiveNavbarItem() {
    const currentPath = window.location.pathname;
    const navbarItems = document.querySelectorAll('.nav-link');

    navbarItems.forEach(item => {
        if (item.getAttribute('href') === currentPath) {
            item.parentElement.classList.add('active');
        }
    });
}

// this function send the request for changing
function changerow(id, batchId) {

    $.ajax({
        url: `/dashboard/api/v1/changerow/` + id + `/`+ batchId,
        method: 'GET',
        success: function(data) {
            document.getElementById("table-flow").style.display = "none";
            document.getElementById("submit-wallet").style.display = "block";
            console.log("this is the data");
            console.log(data);

            const flowmeter = data.flowmeter;
            const wallets = data.wallets;

            // Clear previous inputs
            $('#inputs-container').empty();

            // Populate inputs and wallet addresses
            flowmeter.inputs.forEach((inputData, index) => {
                const inputHtml = `
                    <div class="row">
                        <div class="col-md-6 pr-1">
                            <div class="form-group">
                                <label>Input ${index + 1}</label>
                                <input type="number" name="Input${index}" id="input-${index}" class="form-control" readonly value="${inputData.input}" placeholder="per liter">
                            </div>
                        </div>
                        <div class="col-md-6 pr-1">
                            <div class="form-group">
                                <label>Wallet Address ${index + 1}</label>
                                <select id="wallet-${index}" name="walletAddress${index}" class="form-control wallet-select" placeholder="Choose one">
                                    ${wallets.map(wallet => `<option value="${wallet}" ${inputData.walletAddress === wallet ? 'selected' : ''}>${wallet}</option>`).join('')}
                                </select>
                            </div>
                        </div>
                    </div>
                `;
                $('#inputs-container').append(inputHtml);
            });

            // Initialize select2 for the wallet dropdowns
            $('.wallet-select').select2({
                placeholder: 'Select a wallet address',
                allowClear: true
            });

            // Set other flowmeter data
            $("#output").val(flowmeter.output);
            $("#batch-id").val(flowmeter.batchId);
            $("#devicecode").val(flowmeter.devicecode);
            $("#hidden-id").val(flowmeter.id);
        },
        error: function(xhr, status, error) {
            console.error("An error occurred: " + error);
        }
    });
}

// Handle form submission
$('#update-form').submit(function(event) {
    event.preventDefault();

    const inputs = [];
    $('#inputs-container .row').each(function(index, element) {
        const inputVal = $(element).find(`#input-${index}`).val();
        const walletVal = $(element).find(`#wallet-${index}`).val();
        inputs.push({ input: parseFloat(inputVal), walletAddress: walletVal });
    });

    const updatedFlowmeter = {
        inputs: inputs,
        output: parseFloat($('#output').val()),
        batchId: $('#batch-id').val(),
        devicecode: $('#devicecode').val()
    };

    const id = $('#flowmeter-id').val(); // Assume you have a hidden field for flowmeter id

    $.ajax({
        url: `/api/v1/record/update/${id}`,
        method: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(updatedFlowmeter),
        success: function(response) {
            console.log("Update successful:", response);
        },
        error: function(xhr, status, error) {
            console.error("An error occurred: " + error);
        }
    });
});
// function showUser(){
//     $.ajax({
//         url: `/dashboard/api/v1/showuser`,
//         method: 'GET',
//         success: function(data) {
//             console.log(data);
//             // const tableBody = $('#load-nav');
//             // tableBody.empty(); // Clear previous content
//             //
//             // tableBody.append(data);
//             document.getElementById("user-form-cart").style.display = "none";
//             document.getElementById("user-profile-cart").style.display = "block";
//             $("#wallet-address").val(data.walletAddress);
//             $("#email").val(data.email);
//             $("#first-name").val(data.name);
//             $("#last-name").val(data.lastname);
//             $("#address").val(data.address);
//             $("#city").val(data.city);
//             $("#country").val(data.country);
//         },
//         error: function(xhr, status, error) {
//             console.error("An error occurred: " + error);
//         }
//     });
// }
// function showUserData(){
//     $.ajax({
//         url: `/dashboard/api/v1/showuserdata`,
//         method: 'GET',
//         success: function(data) {
//             console.log(data);
//             // const tableBody = $('#load-nav');
//             // tableBody.empty(); // Clear previous content
//             //
//             // tableBody.append(data);
//
//             document.getElementById("address-wallet-user").innerHTML = "<label> Wallet Address : </label>" + data.walletAddress;
//             document.getElementById("name-user-dashboard").innerHTML = "<label> Full Name : </label>" + data.name + " " + data.lastname;
//             document.getElementById("address-user-dashboard").innerHTML = "<label> Address : </label>" + data.address;
//
//             // تولید QR کد و نمایش آن
//             const qrcodeDiv = document.getElementById("show-wallet-qr-code");
//             qrcodeDiv.innerHTML = ""; // پاک کردن QR کد قبلی
//             new QRCode(qrcodeDiv, data.walletAddress);
//         },
//         error: function(xhr, status, error) {
//             console.error("An error occurred: " + error);
//         }
//     });
// }
function showUserData() {
    $.ajax({
        url: `/dashboard/api/v1/showuserdata`,
        method: 'GET',
        success: function(data) {
            const user = data.user;
            const flowmeters = data.flowmeters;

            // نمایش اطلاعات کاربر
            document.getElementById("address-wallet-user").innerHTML = "<label> Wallet Address : </label>" + user.walletAddress;
            document.getElementById("name-user-dashboard").innerHTML = "<label> Full Name : </label>" + user.name + " " + user.lastname;
            document.getElementById("address-user-dashboard").innerHTML = "<label> Address : </label>" + user.address;

            // تولید QR کد و نمایش آن
            const qrcodeDiv = document.getElementById("show-wallet-qr-code");
            qrcodeDiv.innerHTML = ""; // پاک کردن QR کد قبلی
            new QRCode(qrcodeDiv, user.walletAddress);

            // نمایش داده‌های Flowmeter در جدول
            const tableBody = $('#show-table-oil');
            tableBody.empty(); // Clear previous content

            if (flowmeters.length === 0) {
                tableBody.append('<p>No records found.</p>');
                return;
            }

            let table = '<table class="table">';
            table += '<thead><tr><th>Wallet Address</th><th>Input</th></tr></thead><tbody>';

            flowmeters.forEach(flowmeter => {
                const [walletAddress, input] = flowmeter.split(',');
                table += `<tr>
                    <td>${walletAddress}</td>
                    <td>${input}</td>
                </tr>`;
            });

            table += '</tbody></table>';
            tableBody.append(table);
        },
        error: function(xhr, status, error) {
            console.error("An error occurred: " + error);
        }
    });
}

const ALERT_SUCCESS = 'alert alert-success';
const ALERT_DANGER = 'alert alert-danger';
function showToast(message, type) {
    const toastContainer = document.getElementById('toast-container');
    const toast = document.createElement('div');
    toast.className = `alert ${type} toast`;

    toast.innerHTML = `
            <button type="button" aria-hidden="true" class="close" data-dismiss="alert">
                <i class="nc-icon nc-simple-remove"></i>
            </button>
            <span>
                <b> Success - </b> ${message}
            </span>
        `;

    toastContainer.appendChild(toast);

    // Show the toast
    setTimeout(() => {
        toast.classList.add('show');
    }, 100);

    // Hide the toast after 3 seconds
    setTimeout(() => {
        toast.classList.remove('show');
        setTimeout(() => {
            toastContainer.removeChild(toast);
        }, 500);
    }, 3000);
}
// function showToast(message, type) {
//     const toastContainer = document.getElementById('toast-container');
//     const toast = document.createElement('div');
//     toast.classList.add('toast', type);
//     toast.innerText = message;
//
//     toastContainer.appendChild(toast);
//
//     // Show the toast
//     setTimeout(() => {
//         toast.classList.add('show');
//     }, 100);
//
//     // Hide the toast after 3 seconds
//     setTimeout(() => {
//         toast.classList.remove('show');
//         setTimeout(() => {
//             toastContainer.removeChild(toast);
//         }, 500);
//     }, 3000);
// }
