document.addEventListener('DOMContentLoaded', function () {

    function getCurrentYearMonth() {
        const now = new Date();
        const year = now.getFullYear();
        const month = now.getMonth() + 1;
        return { year, month };
    }

    function fetchDepositStatus() {
        const { year, month } = getCurrentYearMonth();
        const url = `/membership-fee/deposite-rate?year=${year}&month=${month}`

        return fetch(url)
            .then(response => response.json())
            .then(data => {
                return data;
            });
    }

    function getAllAccountTransactions() {
        const url = `/account-transactions`;

        return fetch(url)
            .then(response => response.json())
            .then(data => {
                return data;
            })
    }

    function setupMembershipFeeReport() {

        function generatePaidMembersUrl() {
            const {year, month} = getCurrentYearMonth();
            return `/membership-fee/COMPLETED?year=${year}&month=${month}`
        }

        function generateUnPaidMembersUrl() {
            const {year, month} = getCurrentYearMonth();
            return `/membership-fee/NOT_COMPLETED?year=${year}&month=${month}`
        }

        function getMembershipFeeReport(url) {
            return fetch(url)
                .then(response => response.json())
                .then(data => {
                    const checker = document.getElementById('membership-fee-report');
                    checker.innerHTML = '';

                    data.forEach(members => {
                        const row = `
                <tr>
                    <td>${members.memberName}</td>
                    <td>${members.amount}</td>
                    <td>${members.memberStatus}</td>
                    <td>${members.depositStatus}</td>
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


    function getTransactionType(type) {
        return type === "입금" ? "deposit" : "withdrawal";
    }

    const dashboardMenu = document.getElementById('dashboardMenu');
    const dashboardSubmenu = document.getElementById('dashboardSubmenu');

    dashboardMenu.addEventListener('click', function () {
        dashboardSubmenu.style.display = dashboardSubmenu.style.display === 'block' ? 'none' : 'block';
    });

    fetch('/accounts/balance')
        .then(response => response.json())
        .then(data => {
            // 카드 1에 데이터 삽입
            document.querySelector('#balance').textContent = ` ₩ ${data.balance}원`;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            document.querySelector('#balance').textContent = '데이터를 불러오는 데 실패했습니다.';
        });

    fetchDepositStatus()
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

    var ctx1 = document.getElementById('donutChart1').getContext('2d');
    new Chart(ctx1, {
        type: 'doughnut',
        data: {
            labels: ['Category A', 'Category B', 'Category C'],
            datasets: [{
                label: 'Categories',
                data: [300, 50, 100],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
            }]
        },
        options: {
            responsive: true,
        }
    });

    var ctx2 = document.getElementById('donutChart2').getContext('2d');
    new Chart(ctx2, {
        type: 'doughnut',
        data: {
            labels: ['Category X', 'Category Y', 'Category Z'],
            datasets: [{
                label: 'Categories',
                data: [120, 150, 200],
                backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
            }]
        },
        options: {
            responsive: true,
        }
    });

    document.getElementById('view-all-button').addEventListener('click', function () {
        window.location.href = '/path-to-all-account-transactions';
    });
});
