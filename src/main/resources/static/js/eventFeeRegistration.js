document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        let currentSelectedEventId = '';

        document.getElementById('eventFeeRegistrationModal').addEventListener('shown.bs.modal', function () {
            const status = 'ACTIVE'
            const eventSelectBox = document.getElementById('eventId')

            getEventsByStatus(status)
                .then(data => {
                    data.forEach(event => {
                            const option = document.createElement('option')
                            option.value = event.eventId;
                            option.textContent = `${event.eventName} (${event.eventDescription})`;
                            eventSelectBox.appendChild(option);
                        }
                    );

                    // 디폴트 이벤트 및 해당 이벤트의 신청자 설정
                    if (data.length > 0) {
                        eventSelectBox.value = data[0].eventId;
                        createEventApplicantCheckBox(data[0].eventId);
                    }
                })
                .catch(error => console.error('/events/ACTIVE 호출 중 에러 발생:', error));

            document.getElementById('eventId').addEventListener('change', function (event) {
                currentSelectedEventId = event.target.value;
                createEventApplicantCheckBox(currentSelectedEventId);
            });

        });

        let selectedApplicants = [];
        document.getElementById('eventFeeForm').addEventListener('submit', function (event) {
            event.preventDefault();

            selectedApplicants = [];
            const checkedBoxes = document.querySelectorAll('#applicantContainer input[type="checkbox"]:checked');

            if (checkedBoxes.length === 0) {
                alert('입금자를 선택해주세요!');
                return;
            }

            checkedBoxes.forEach(checkedBox => {
                selectedApplicants.push(checkedBox.value)
            });

            const eventFeePermissionModal = new bootstrap.Modal(document.getElementById('eventFeePermissionModal'));
            eventFeePermissionModal.show();
        });

        const eventFeeRegistrationModal = new bootstrap.Modal(document.getElementById('eventFeeRegistrationModal'));
        const redirectUrl = '/budget-book-registration';

        document.getElementById('eventFeePermissionForm').addEventListener('submit', function (event) {
            console.log('Check membership fee permission.')
            event.preventDefault();

            const inputPermissionKey = document.getElementById('eventFeePermissionPassword').value;
            runProcessAfterPermissionKeyValidation(
                inputPermissionKey,
                () => saveEventFeeDeposit(eventFeeRegistrationModal, redirectUrl)
            );
        });

        function saveEventFeeDeposit(eventFeeRegistrationModal, redirectUrl) {
            const bodyData = {
                eventId: currentSelectedEventId,
                depositorIds: selectedApplicants,
                depositDate: document.getElementById('eventFeeDepositDate').value,
                amount: document.getElementById('eventFee').value,
                description: document.getElementById('eventFeeMemo').value
            };

            const url = '/account-transactions/deposits/event-fee';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData)
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('이벤트비가 등록되었어요!');
                        eventFeeRegistrationModal.hide();
                        window.location.href = redirectUrl;
                    } else {
                        response.json().then(errorData => {
                            throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`)
                        })
                    }
                })
                .catch(error => {
                    console.error('이벤트비 등록 중 오류 발생:', error);
                });

        }
    }
});

// 이벤트 신청자 체크박스 만들기
function createEventApplicantCheckBox(eventId) {
    getEventApplicantsByEventId(eventId)
        .then(data => {
            const applicantContainer = document.getElementById('applicantContainer');
            applicantContainer.innerHTML = '';

            data.forEach(applicant => {

                const checkbox = document.createElement('div');
                checkbox.classList.add('form-check');
                checkbox.innerHTML = `
                    <input class="form-check-input" type="checkbox" value="${applicant.memberId}" id="depositor${applicant.memberId}">
                    <label class="form-check-label" for="depositor${applicant.memberId}">${applicant.memberName}</label>
                `;
                applicantContainer.appendChild(checkbox);
            });

        })
        .catch(error => console.error('[GET] /event-applicants 호출 중 에러 발생:', error));
}

function getEventApplicantsByEventId(eventId) {
    const url = `/event-applicants?eventId=${eventId}`;

    return fetch(url)
        .then(response => response.json())
        .then(data => data);
}