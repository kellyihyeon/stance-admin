document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('transferAmountForm').addEventListener('submit', function (event) {
            const transferAmountRegistrationModal = new bootstrap.Modal(document.getElementById('transferAmountRegistrationModal'));
            event.preventDefault();

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
                        window.location.href = '/budget-book-registration';
                    } else {
                        response.json().then(errorData => {
                            throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`)
                        })
                    }
                })
                .catch(error => {
                    console.error('계좌이체 내역 등록 중 오류 발생:', error);
                });

        });
    }

})