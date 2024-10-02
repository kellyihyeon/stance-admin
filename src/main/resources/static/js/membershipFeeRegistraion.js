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
                                <input class="form-check-input" type="checkbox" value="${depositor.memberId}" id="depositor${depositor.memberId}">
                                <label class="form-check-label" for="depositor${depositor.memberId}">${depositor.memberName}</label>
                             `;
                            depositorContainer.appendChild(checkbox);
                        }
                    );

                })
                .catch(error => console.error('/members/current 호출 중 에러 발생:', error));

            // 회비 만료일의 디폴트 설정
            const {year, month} = getCurrentYearMonth();
            const fixedDueDate = '25';
            const paddedMonth = month.toString().padStart(2, '0');
            document.getElementById('dueDate').value = `${year}-${paddedMonth}-${fixedDueDate}`;
        });

        let selectedDepositors = [];

        document.getElementById('membershipFeeForm').addEventListener('submit', function (event) {
            event.preventDefault();

            // 초기화 되는 문제를 막기 위해 미리 저장
            selectedDepositors = [];
            const checkedBoxes = document.querySelectorAll('#depositorContainer input[type="checkbox"]:checked');

            if (checkedBoxes.length === 0) {
                alert('입금자를 선택해주세요!');
                return;
            }

            checkedBoxes.forEach(checkedBox => {
                selectedDepositors.push(checkedBox.value)
            });

            const permissionKeyCheckerInMembershipFeeModal = new bootstrap.Modal(document.getElementById('permissionKeyCheckerInMembershipFeeModal'));
            permissionKeyCheckerInMembershipFeeModal.show();
        });

        const membershipFeeRegistrationModal = new bootstrap.Modal(document.getElementById('membershipFeeRegistrationModal'));
        const redirectUrl = '/budget-book-registration';
        runProcessAfterPermissionKeyValidation(() => saveMembershipFeeDeposit(membershipFeeRegistrationModal, redirectUrl));

        function saveMembershipFeeDeposit(membershipFeeRegistrationModal, redirectUrl) {
            const bodyData = {
                depositorIds: selectedDepositors,
                depositDate: document.getElementById('depositDate').value,
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
                    console.log('response data: ', response)
                    if (response.status === 201) {
                        alert('회비내역이 등록되었습니다');
                        membershipFeeRegistrationModal.hide();
                        window.location.href = redirectUrl;
                    } else {
                        console.log('else response: ')
                    }
                })
                .catch(error => {
                    console.error('회비내역 등록 중 오류 발생:', error);
                });
        }

    }

});