document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;
    console.log(path);

    if (path === '/') {
        // 잔액 조회
        function getBalance(url) {
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    document.querySelector('#balance').textContent = ` ₩ ${data.balance.toLocaleString()}원`;
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    document.querySelector('#balance').textContent = '데이터를 불러오는 데 실패했습니다.';
                });

        }
        getBalance('/accounts/balance');

        // 회비 입금 현황
        function fetchDepositStatus() {
            const { year, month } = getCurrentYearMonth();
            const url = `/membership-fee/deposite-rate?year=${year}&month=${month}`

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const dateElement = document.getElementById('date');
                    dateElement.textContent = `${data.year}년 ${data.month}월`;

                    const paidMembersElement = document.getElementById('paidMembers');
                    paidMembersElement.textContent = `${data.paidMembers}명 / ${data.totalMembers}명`;

                    const progressBar = document.getElementById('progress-bar');
                    const progressLabel = document.getElementById('progress-label');

                    progressBar.style.width = `${data.depositRate}%`;
                    progressLabel.textContent = `${data.depositRate}%`;
                })
                .catch(error => console.error('Error fetching deposit status:', error));
        }
        fetchDepositStatus();

        // 회비 입금 명단
        function setupMembershipFeeReport() {

            function generatePaidMembersUrl() {
                const {year, month} = getCurrentYearMonth();
                return `/membership-fee/COMPLETED?year=${year}&month=${month}`
            }

            function generateUnPaidMembersUrl() {
                const {year, month} = getCurrentYearMonth();
                return `/membership-fee/NOT_COMPLETED?year=${year}&month=${month}`
            }

            function convertMemberStatus(memberStatus) {
                return memberStatus === '정기 회원' ? "active" : 'dormant';
            }

            function getMembershipFeeReport(url) {
                return fetch(url)
                    .then(response => response.json())
                    .then(data => {
                        const checker = document.getElementById('membership-fee-report');
                        checker.innerHTML = '';

                        data.forEach(members => {
                            let depositStatus = convertDepositStatus(members.depositStatus);
                            let memberStatus = convertMemberStatus(members.memberStatus);
                            const row = `
                <tr>
                    <td>${members.memberName}</td>
                    <td><span class="amount">${members.amount.toLocaleString()}원</span></td>
                    <td><span class="member-status ${memberStatus}">${members.memberStatus}</span></td>
                    <td><span class="deposit-status ${depositStatus}">${members.depositStatus}</span></td>
                    <td>${members.depositDate}</td>
                </tr>
                `;
                            checker.innerHTML += row;
                        })
                    })
                    .catch(error => {
                        console.error("Error in getMembershipFeeReport during API call: ", error);
                    });
            }

            getMembershipFeeReport(generatePaidMembersUrl())
                .then(() => {
                    console.log("getMembershipFeeReport loaded successfully.");
                })
                .catch(error => {
                    console.error("Error loading getMembershipFeeReport:", error);
                });

            function updateButtonState(activeButton) {
                const buttons = [document.getElementById('paid-btn'), document.getElementById('unpaid-btn')];
                buttons.forEach(button => {
                    if (button === activeButton) {
                        button.classList.add('active');
                    } else {
                        button.classList.remove('active');
                    }
                });
            }

            const paidButton = document.getElementById('paid-btn');
            const unpaidButton = document.getElementById('unpaid-btn');

            updateButtonState(paidButton);

            paidButton.addEventListener('click', () => {
                updateButtonState(paidButton);
                getMembershipFeeReport(generatePaidMembersUrl())
                    .then(() => { console.log("getMembershipFeeReport loaded successfully as paid-button clicked.");})
                    .catch(error => { console.error("Error loading getMembershipFeeReport as paid-button clicked:", error);});
            });

            unpaidButton.addEventListener('click', () => {
                updateButtonState(unpaidButton);
                getMembershipFeeReport(generateUnPaidMembersUrl())
                    .then(() => { console.log("getMembershipFeeReport loaded successfully as unpaid-button clicked.");})
                    .catch(error => { console.error("Error loading getMembershipFeeReport as unpaid-button clicked:", error);});
            });
        }
        setupMembershipFeeReport();
    }

    // Dashboards - 이벤트비
    if (path === '/event-fee-deposit-tracker') {
        const eventRegisterModal = new bootstrap.Modal(document.getElementById('eventRegisterModal'));

        function getEventFeeDepositTrackerReport(event) {
            const url = `/account-transactions/deposits/event-fee?eventId=${event.eventId}`;
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    const eventFeeDepositTrackerReport = document.getElementById('eventFeeDepositTrackerReport');
                    eventFeeDepositTrackerReport.innerHTML = '';

                    data.forEach(report => {
                        let depositStatus = convertDepositStatus(report.depositStatus);
                        const depositDate = report.depositDate ? report.depositDate : '';

                        const row = `
                            <tr>
                                <td>${report.memberName}</td>
                                <td><span class="amount">${report.amount.toLocaleString()}원</span></td>
                                <td><span class="deposit-status ${depositStatus}">${report.depositStatus}</span></td>
                                <td>${depositDate}</td>
                                <td>${report.dueDate}</td>
                            </tr>
                        `;
                        eventFeeDepositTrackerReport.innerHTML += row;
                    });
                });
        }

        function createActiveEventBoxes() {
            getActiveEvents()
                .then(data => {
                    const eventAreas = document.getElementById('eventAreas');
                    eventAreas.innerHTML = '';

                    let firstEvent = true;
                    const description = document.getElementById('eventDescription');

                    data.forEach(event => {
                        const eventArea = document.createElement('div');
                        eventArea.className = 'event-area';
                        eventArea.setAttribute('data-event-id', event.eventId);

                        if (firstEvent) {
                            eventArea.classList.add('active');
                            getEventFeeDepositTrackerReport(event);
                            description.innerHTML = `<span style="box-shadow: inset 0 -8px 0 #ffffbb;">${event.eventDescription}</span>`;
                            firstEvent = false;
                        }

                        eventArea.innerHTML = `
                            <div class="event-icon">
                                <span class="material-symbols-outlined event-symbols">celebration</span>
                            </div>
                            <div class="event-name">${event.eventName}</div>
                        `;

                        // 이벤트 클릭 시 active 처리
                        eventArea.addEventListener('click', () => {
                            // 모든 이벤트 영역에 active 제거
                            document.querySelectorAll('.event-area').forEach(area => area.classList.remove('active'));
                            // 클릭한 이벤트에 active 추가
                            eventArea.classList.add('active');
                            description.innerHTML = `<span style="box-shadow: inset 0 -8px 0 #ffffbb;">${event.eventDescription}</span>`;
                            getEventFeeDepositTrackerReport(event);
                        });

                        eventAreas.appendChild(eventArea);
                    });

                    // 이벤트 등록 버튼
                    const addEventArea = document.createElement('div');
                    addEventArea.className = 'event-area add-event';
                    addEventArea.id = 'addEventArea';
                    addEventArea.innerHTML = `
                        <div class="event-icon">
                            <span class="material-symbols-outlined event-symbols">add_box</span>
                        </div>
                        <div class="event-name">등록</div>
                    `;
                    eventAreas.appendChild(addEventArea);
                })
                .catch(error => {
                    console.error('이벤트 목록을 가져오는 중 오류 발생:', error);
                });
        }
        createActiveEventBoxes();

        // 이벤트 위임을 통해 동적으로 추가된 요소에도 클릭 이벤트 바인딩
        document.getElementById('eventAreas').addEventListener('click', function (event) {
            if (event.target.closest('#addEventArea')) {
                eventRegisterModal.show();
            }
        });

        document.getElementById('eventRegisterModal').addEventListener('shown.bs.modal', function () {
            addEventListenerWithActiveStatus();
        });

        document.getElementById('eventForm').addEventListener('submit', function (event) {
            event.preventDefault();

            const permissionKeyCheckerModal = new bootstrap.Modal(document.getElementById('permissionKeyCheckerModal'));
            permissionKeyCheckerModal.show();
        });

        const redirectUrl = '/event-fee-deposit-tracker'
        processValidatePermissionKey(eventRegisterModal, redirectUrl);

    }
});

function getCurrentYearMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    return { year, month };
}

function convertDepositStatus(depositStatus) {
    return depositStatus === '미입금' ? "unpaid" : "paid";
}

function getEventsByStatus(status) {
    const url = `/events/${status}`;
    return fetch(url)
        .then(response => response.json())
        .then(data => {
            return data;
        });
}

function getCurrentMembers() {
    const url = '/members/current';
    return fetch(url)
        .then(response => response.json())
        .then(data => data);
}

function getActiveEvents() {
    const url = `/events/ACTIVE`
    return fetch(url)
        .then(response => response.json())
        .then(data => data);
}

function removeSeconds(dateTime) {
    const date = new Date(dateTime);
    return date.toISOString().slice(0, 16).replace('T', ' ');
}

function addEventListenerWithActiveStatus() {
    const statusCheckbox = document.getElementById('status');
    const statusLabel = document.getElementById('statusLabel');

    statusCheckbox.addEventListener('change', function () {
        if (statusCheckbox.checked) {
            statusLabel.textContent = 'on';
        } else {
            statusLabel.textContent = 'off';
        }
    });

    return statusCheckbox;
}

function callEventRegistrationApi(eventRegisterModal, redirectUrl) {
    const statusCheckbox = document.getElementById('status')
    const status = !!statusCheckbox.checked;

    const eventRegistrationData = {
        eventItem: document.getElementById('eventItem').value,
        amount: document.getElementById('amount').value,
        dueDate: document.getElementById('dueDate').value,
        status: status === true ? 'ACTIVE' : 'INACTIVE',
        description: document.getElementById('description').value
    };

    fetch('/events', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(eventRegistrationData),
    })
        .then(response => {
            if (response.status === 201) {
                alert('이벤트가 등록되었습니다');
                eventRegisterModal.hide();
                window.location.href = redirectUrl;
            }
        })
        .catch(error => {
            console.error('이벤트 등록 중 오류 발생:', error);
        });
}

function generatePageButtons(pageNumber, totalPages) {
    const page = pageNumber + 1;
    const paginationContainer = document.getElementById('paginationContainer');
    paginationContainer.innerHTML = '';

    // Previous 버튼 만들기
    const prevItem = document.createElement('li');
    prevItem.classList.add('page-item');
    prevItem.classList.toggle('disabled', page === 1);
    prevItem.innerHTML = `<a class="page-link" href="#" data-page="${page - 1}"><</a>`;
    paginationContainer.appendChild(prevItem);

    // 페이지 번호들
    for (let i = 1; i <= totalPages; i++) {
        const pageItem = document.createElement('li');
        pageItem.classList.add('page-item');
        pageItem.classList.toggle('active', i === page);

        pageItem.innerHTML = `<a class="page-link" href="#" data-page="${i}">${i}</a>`;
        paginationContainer.appendChild(pageItem);
    }

    // Next 버튼 만들기
    const nextItem = document.createElement('li');
    nextItem.classList.add('page-item');
    nextItem.classList.toggle('disabled', page === totalPages);
    nextItem.innerHTML = `<a class="page-link" href="#" data-page="${page + 1}">></a>`;
    paginationContainer.appendChild(nextItem);
}

function loadPageData(totalPages, loadDataFunc) {
    // 페이지 번호 버튼을 클릭한 경우
    document.querySelectorAll('.pagination a').forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault();

            const selectedPage = parseInt(this.getAttribute('data-page'));
            if (selectedPage >= 1 && selectedPage <= totalPages) {
                loadDataFunc(selectedPage);
            }
        });
    });
}

function validatePermissionKey(permissionKey) {
    const url = '/auth/role/system-admin';
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(permissionKey),
    })
        .then(response => response);
}

function processValidatePermissionKey(targetModal, redirectUrl) {
    document.getElementById('permissionKeyCheckerForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const inputPermissionKey = document.getElementById('password').value;

        validatePermissionKey(inputPermissionKey)
            .then(response => {
                if (response.status === 200) {
                    callEventRegistrationApi(targetModal, redirectUrl);
                } else {
                    alert('관리자 코드가 일치하지 않아요!');
                }
            })
            .catch(error => {
                console.error('관리자 코드 확인 중 오류 발생:', error);
            });
    });
}

function runProcessAfterPermissionKeyValidation(onSuccess) {
    document.getElementById('permissionKeyCheckerForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const inputPermissionKey = document.getElementById('password').value;
        validatePermissionKey(inputPermissionKey)
            .then(response => {
                if (response.status === 200) {
                    onSuccess();
                } else {
                    alert('관리자 코드가 일치하지 않아요!');
                }
            })
            .catch(error => {
                console.error('관리자 코드 확인 중 오류 발생:', error);
            });
    });
}