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
    }

});