document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('bankDepositForm').addEventListener('submit', function (event) {
            event.preventDefault();

            const bankDepositPermissionModal = new bootstrap.Modal(document.getElementById('bankDepositPermissionModal'));
            bankDepositPermissionModal.show();
        });

        const bankDepositRegistrationModal = new bootstrap.Modal(document.getElementById('bankDepositRegistrationModal'));
        const redirectUrl = '/budget-book-registration';

        document.getElementById('bankDepositPermissionForm').addEventListener('submit', function (event) {
            console.log('Check bank deposit permission.')
            event.preventDefault();

            const inputPermissionKey = document.getElementById('bankDepositPermissionPassword').value;
            runProcessAfterPermissionKeyValidation(
                inputPermissionKey,
                () => saveBankDeposit(bankDepositRegistrationModal, redirectUrl)
            );
        });

        function saveBankDeposit(bankDepositRegistrationModal, redirectUrl) {
            const bodyData = {
                type: document.getElementById('bankDepositType').value,
                depositSource: document.getElementById('depositSource').value,
                amount: document.getElementById('bankDepositCash').value,
                depositDate: document.getElementById('bankDepositDate').value
            }

            const url = '/account-transactions/deposits/bank';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData)
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('은행 입금비가 등록되었어요!');
                        bankDepositRegistrationModal.hide();
                        window.location.href = redirectUrl;
                    } else {
                        response.json().then(errorData => {
                            throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`)
                        })
                    }
                })
                .catch(error => {
                    console.error('은행 입금비 등록 중 오류 발생:', error);
                });
        }

    }

});