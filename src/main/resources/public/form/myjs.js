
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
