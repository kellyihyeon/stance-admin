document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('cardPaymentRegistrationModal').addEventListener('shown.bs.modal', function () {
            getCurrentMembers()
                .then(data => {
                    const cardHolderContainer = document.getElementById('cardHolderContainer');
                    cardHolderContainer.innerHTML = '';

                    data.forEach(currentMember => {
                            const checkbox = document.createElement('div');
                            checkbox.classList.add('form-check');
                            checkbox.innerHTML = `
                                <input class="form-check-input" type="checkbox" value="${currentMember.memberId}" id="cardHolder${currentMember.memberId}">
                                <label class="form-check-label" for="cardHolder${currentMember.memberId}">${currentMember.memberName}</label>
                             `;
                        cardHolderContainer.appendChild(checkbox);
                        }
                    );

                })
                .catch(error => console.error('/members/current 호출 중 에러 발생:', error));
        });
    }
});