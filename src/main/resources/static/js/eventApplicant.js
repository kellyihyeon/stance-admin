document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/event-applicant') {
        const eventSelectBox = document.getElementById('eventSelect');

        getActiveEvents()
            .then(data => {
                data.forEach(event => {
                        const option = document.createElement('option');
                        option.value = event.eventId;
                        option.textContent = `${event.eventName} (${event.eventDescription})`;
                        eventSelectBox.appendChild(option);
                    }
                );

                if (data.length > 0) {
                    eventSelectBox.value = data[0].eventId;
                    createEventApplicantReport(data[0].eventId);
                }
            });


        document.getElementById('eventSelect').addEventListener('change', function (event) {
            let currentSelectedEventId = event.target.value;
            createEventApplicantReport(currentSelectedEventId);
        });


        document.getElementById('applicantRegisterModal').addEventListener('shown.bs.modal', function () {
            const selectedEventId = eventSelectBox.value;

            const modalEventSelect = document.getElementById('modalEventSelect');
            modalEventSelect.innerHTML = '';

            const option = document.createElement('option')
            option.value = selectedEventId;
            option.textContent = document.getElementById('eventSelect').options[document.getElementById('eventSelect').selectedIndex].text;

            modalEventSelect.appendChild(option);
            modalEventSelect.disabled = true;

           getCurrentMembers()
                .then(data => {
                    const applicantContainer = document.getElementById('applicantContainer');
                    applicantContainer.innerHTML = '';

                    data.forEach(member => {
                            const checkbox = document.createElement('div');
                            checkbox.classList.add('form-check');
                            checkbox.innerHTML = `
                                <input class="form-check-input" type="checkbox" value="${member.memberId}" id="member${member.memberId}">
                                <label class="form-check-label" for="member${member.id}">${member.memberName}</label>
                             `;
                        applicantContainer.appendChild(checkbox);
                        }
                    );

                });
        });

        let selectedApplicants = [];
        document.getElementById('eventApplicantForm').addEventListener('submit', function (event) {
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

            const eventApplicantPermissionModal = new bootstrap.Modal(document.getElementById('eventApplicantPermissionModal'));
            eventApplicantPermissionModal.show();
        });

        const applicantRegisterModal = new bootstrap.Modal(document.getElementById('applicantRegisterModal'));
        const redirectUrl = '/event-applicant';

        document.getElementById('eventApplicantPermissionForm').addEventListener('submit', function (event) {
            console.log('Check event applicant permission.')
            event.preventDefault();

            const inputPermissionKey = document.getElementById('eventApplicantPermissionPassword').value;
            runProcessAfterPermissionKeyValidation(
                inputPermissionKey,
                () => saveEventApplicant(applicantRegisterModal, redirectUrl)
            );
        });

        function saveEventApplicant(applicantRegisterModal, redirectUrl) {
            const bodyData = {
                eventId: document.getElementById('modalEventSelect').value,
                applicantIds: selectedApplicants,
                description: document.getElementById('eventApplicantMemo').value
            }

            const url = '/event-applicants';
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bodyData),
            })
                .then(response => {
                    if (response.status === 201) {
                        alert('이벤트 신청자가 추가 되었습니다');
                        applicantRegisterModal.hide();
                        window.location.href = redirectUrl;
                    }
                })
                .catch(error => {
                    console.error('이벤트 신청자 추가 중 오류 발생:', error);
                });
        }

    }

});

function createEventApplicantReport(eventId) {
    getEventApplicantsByEventId(eventId)
        .then(data => {
            const eventApplicantReport = document.getElementById('eventApplicantReport');
            eventApplicantReport.innerHTML = '';

            data.forEach(applicant => {
                const row = `
                            <tr>
                                <td><span class="event-applicant-name">${applicant.memberName}</span></td>
                                <td>${applicant.eventDescription}</td>
                                <td>${removeSeconds(applicant.createdAt)}</td>
                            </tr>
                        `;

                eventApplicantReport.innerHTML += row;
            });
        });
}