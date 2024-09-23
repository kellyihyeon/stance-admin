document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('membershipFeeRegistrationModal').addEventListener('shown.bs.modal', function () {
            const url = '/members/current';
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const depositorContainer = document.getElementById('depositorContainer');
                    depositorContainer.innerHTML = '';

                    data.forEach(depositor => {
                            const checkbox = document.createElement('div');
                            checkbox.classList.add('form-check');
                            checkbox.innerHTML = `
                                <input class="form-check-input" type="checkbox" value="${depositor.memberId}" id="depositor${depositor.id}">
                                <label class="form-check-label" for="depositor${depositor.id}">${depositor.memberName}</label>
                             `;
                            depositorContainer.appendChild(checkbox);
                        }
                    );

                })
                .catch(error => console.error('/members/current 호출 중 에러 발생:', error));
        });

        document.getElementById('membershipFeeForm').addEventListener('submit', function (event) {
            const membershipFeeRegistrationModal = new bootstrap.Modal(document.getElementById('membershipFeeRegistrationModal'));
            event.preventDefault();

            const selectedDepositors = [];
            const checkedBoxes = document.querySelectorAll('#depositorContainer input[type="checkbox"]:checked');

            checkedBoxes.forEach(checkedBox => {
                selectedDepositors.push(checkedBox.value)
            });

            const bodyData = {
                depositorIds: selectedDepositors,
                depositDate: document.getElementById('dueDate').value,
                amount: document.getElementById('amount').value,
                dueDate: document.getElementById('dueDate').value,
                memberType: document.getElementById('membershipType').value,
                description: document.getElementById('description').value
            }

            const url = '/account-transactions/deposits/membership-fee';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData),
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('회비내역이 등록되었습니다');
                        membershipFeeRegistrationModal.hide();
                        window.location.href = '/budget-book-registration';
                    }
                })
                .catch(error => {
                    console.error('회비내역 등록 중 오류 발생:', error);
                });
        });
    }

});