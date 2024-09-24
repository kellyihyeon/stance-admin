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

    }
});