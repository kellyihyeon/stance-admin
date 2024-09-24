document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('cardPaymentRegistrationModal').addEventListener('shown.bs.modal', function () {
            const cardHolderSelectBox = document.getElementById('cardHolderId');

            getCurrentMembers()
                .then(data => {
                    data.forEach(member => {
                            const option = document.createElement('option');
                            option.value = member.memberId;
                            option.textContent = member.memberName;
                            cardHolderSelectBox.appendChild(option)
                        }
                    );
                })
                .catch(error => console.error('/members/current 호출 중 에러 발생: ', error))

            console.log(document.getElementById('cardHolderId').value)
        });

        document.getElementById('cardPaymentForm').addEventListener('submit', function (event) {
            const cardPaymentRegistrationModal = new bootstrap.Modal(document.getElementById('cardPaymentRegistrationModal'));
            event.preventDefault();

            const bodyData = {
                cardHolderId: document.getElementById('cardHolderId').value,
                expenseCategory: document.getElementById('expenseCategory').value,
                cardUsageLocation: document.getElementById('cardUsageLocation').value,
                amount: document.getElementById('cardPaymentCost').value,
                expenseDate: document.getElementById('cardPaymentDate').value,
                description: document.getElementById('cardPaymentMemo').value
            };

            const url = '/account-transactions/withdrawals/card-payment';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData)
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('카드 지출이 등록되었어요!');
                        cardPaymentRegistrationModal.hide();
                        window.location.href = '/budget-book-registration';
                    } else {
                        response.json().then(errorData => {
                            throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`)
                        })
                    }
                })
                .catch(error => {
                    console.error('카드 지출 등록 중 오류 발생:', error);
                });
        });

    }
});