document.addEventListener('DOMContentLoaded', function () {
    const dashboardMenu = document.getElementById('dashboardMenu');
    const dashboardSubmenu = document.getElementById('dashboardSubmenu');

    dashboardMenu.addEventListener('click', function () {
        dashboardSubmenu.style.display = dashboardSubmenu.style.display === 'block' ? 'none' : 'block';
    });

    let path = window.location.pathname;
    console.log(path);

    if (path === '/') {
        // 잔액 조회
        function getBalance(url) {
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    document.querySelector('#balance').textContent = ` ₩ ${data.balance}원`;
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

        // 입금 및 지출 현황
        var ctx = document.getElementById('salesChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['January', 'February', 'March', 'April'],
                datasets: [{
                    label: 'Sales',
                    data: [12, 19, 3, 5],
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    x: { beginAtZero: true },
                    y: { beginAtZero: true }
                }
            }
        });

        // 모임 통장 전체 내역
        function getTransactionType(type) {
            return type === "입금" ? "deposit" : "withdrawal";
        }

        getAllAccountTransactions()
            .then(data => {
                try {
                    const tableBody = document.getElementById('transaction-table-body');
                    const transactions = data.content.slice(0, 7);

                    tableBody.innerHTML = '';
                    transactions.forEach(transaction => {

                        let transactionType = getTransactionType(transaction.transactionType)
                        let amount = transaction.transactionType === '출금'
                            ? `- ${transaction.amount.toLocaleString()}원`
                            : `${transaction.amount.toLocaleString()}원`;

                        const row = `
                    <tr>
                        <td>${transaction.transactionDate}</td>
                        <td><span class="transaction-type ${transactionType}">${transaction.transactionType}</span></td>
                        <td>${transaction.detailType}</td>
                        <td>${transaction.transactionParty}</td>
                        <td><span class="amount ${transactionType}">${amount}</span></td>
                        <td>${transaction.balance.toLocaleString()}원</td>
                    </tr>
                `;
                        tableBody.innerHTML += row;
                    });
                } catch (error) {
                    console.error('API 데이터를 불러오는 중 오류가 발생했습니다:', error);
                }
            });

        document.getElementById('view-all-button').addEventListener('click', function () {
            window.location.href = '';
        });

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

            function convertDepositStatus(depositStatus) {
                return depositStatus === '미입금' ? "unpaid" : "paid";
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
                    <td><span class="amount">${members.amount}원</span></td>
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

    if (path === '/event-applicant') {
        const eventAreaContainer = document.getElementById('eventAreas');
        const eventRegisterModal = new bootstrap.Modal(document.getElementById('eventRegisterModal'));

        // "이벤트 등록" 버튼을 클릭했을 때 모달 띄우기
        document.getElementById('addEventArea').addEventListener('click', function () {
            eventRegisterModal.show();
        });
        // 이벤트를 제출할 때 필수값 검사하기
        document.getElementById('eventForm').addEventListener('submit', function (event) {
            event.preventDefault();

            const eventRegistrationData = {
                eventItem: document.getElementById('name').value,
                amount: document.getElementById('amount').value,
                dueDate: document.getElementById('dueDate').value,
                status: document.getElementById('status').checked === true ? 'ACTIVE' : 'INACTIVE',
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
                        window.location.href = '/event-applicant';
                    }
                })
                .catch(error => {
                    console.error('이벤트 등록 중 오류 발생:', error);
                });
        });



    }
});

function getCurrentYearMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    return { year, month };
}

function getAllAccountTransactions() {
    const url = `/account-transactions`;

    return fetch(url)
        .then(response => response.json())
        .then(data => {
            return data;
        })
}
