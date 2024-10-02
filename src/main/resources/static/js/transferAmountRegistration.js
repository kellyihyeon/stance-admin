document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('transferAmountForm').addEventListener('submit', function (event) {
            event.preventDefault();

            const transferPermissionModal = new bootstrap.Modal(document.getElementById('transferPermissionModal'));
            transferPermissionModal.show();
        });

        const transferAmountRegistrationModal = new bootstrap.Modal(document.getElementById('transferAmountRegistrationModal'));
        const redirectUrl = '/budget-book-registration';

        document.getElementById('transferPermissionForm').addEventListener('submit', function (event) {
            console.log('Check transfer permission.')
            event.preventDefault();

            const inputPermissionKey = document.getElementById('transferPermissionPassword').value;
            runProcessAfterPermissionKeyValidation(
                inputPermissionKey,
                () => saveTransfer(transferAmountRegistrationModal, redirectUrl)
            );
        });

        function saveTransfer(transferAmountRegistrationModal, redirectUrl) {
            const bodyData = {
                expenseCategory: document.getElementById('transferCategory').value,
                recipientName: document.getElementById('recipientName').value,
                bank: document.getElementById('bank').value,
                recipientAccountNumber: document.getElementById('accountNumber').value,
                amount: document.getElementById('transferAmount').value,
                expenseDate: document.getElementById('transferAmountDate').value,
                description: document.getElementById('transferAmountMemo').value,
            };

            const url = 'account-transactions/withdrawals/transfer';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData)
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('계좌이체 내역이 등록되었어요!');
                        transferAmountRegistrationModal.hide();
                        window.location.href = redirectUrl;
                    } else {
                        response.json().then(errorData => {
                            throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`)
                        })
                    }
                })
                .catch(error => {
                    console.error('계좌이체 내역 등록 중 오류 발생:', error);
                });

        }
    }

})