document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/event-applicant') {
        document.getElementById('applicantRegisterModal').addEventListener('shown.bs.modal', function () {
            const status = 'ACTIVE';
            getEventsByStatus(status)
                .then(data => {
                    const eventSelectBox = document.getElementById('eventId');

                    data.forEach(event => {
                            const option = document.createElement('option')
                            option.value = event.eventId;
                            option.textContent = `${event.eventName} (${event.eventDescription})`;

                            eventSelectBox.appendChild(option);
                        }
                    );
                })
                .catch(error => console.error('/events/ACTIVE 호출 중 에러 발생:', error));

            getCurrentMembers()
                .then(data => {
                    const applicantContainer = document.getElementById('applicantContainer');
                    applicantContainer.innerHTML = '';

                    data.forEach(member => {
                            const checkbox = document.createElement('div');
                            checkbox.classList.add('form-check');
                            checkbox.innerHTML = `
                                <input class="form-check-input" type="checkbox" value="${member.memberId}" id="member${member.id}">
                                <label class="form-check-label" for="member${member.id}">${member.memberName}</label>
                             `;
                        applicantContainer.appendChild(checkbox);
                        }
                    );

                });
        });

        document.getElementById('eventApplicantForm').addEventListener('submit', function (event) {
            const applicantRegisterModal = new bootstrap.Modal(document.getElementById('applicantRegisterModal'));
            event.preventDefault();

            const selectedApplicants = [];
            const checkedBoxes = document.querySelectorAll('#applicantContainer input[type="checkbox"]:checked');

            checkedBoxes.forEach(checkedBox => {
                selectedApplicants.push(checkedBox.value)
            });

            const bodyData = {
                eventId: document.getElementById('eventId').value,
                applicantIds: selectedApplicants,
                description: document.getElementById('description').value
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
                        window.location.href = '/event-applicant';
                    }
                })
                .catch(error => {
                    console.error('이벤트 신청자 추가 중 오류 발생:', error);
                });
        });
    }

});