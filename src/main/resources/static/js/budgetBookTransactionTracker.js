document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/') {
        getAllBudgetBookTransactions(1)
            .then(data => {
                try {
                    const tableBody = document.getElementById('transaction-table-body');
                    const transactions = data.content.slice(0, 15);

                    tableBody.innerHTML = '';
                    transactions.forEach(transaction => {
                        const formattedDate = removeSeconds(`${transaction.createdAt}`)
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
                        <td>${formattedDate}</td>
                    </tr>
                `;
                        tableBody.innerHTML += row;
                    });
                } catch (error) {
                    console.error('API 데이터를 불러오는 중 오류가 발생했습니다:', error);
                }
            });

        document.getElementById('view-all-transactions-button').addEventListener('click', function () {
            window.location.href = '/budget-book-transaction-tracker';
        });
    }

    if (path === '/budget-book-transaction-tracker') {
        var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
        tooltipTriggerList.forEach(function (tooltipTriggerEl) {
            new bootstrap.Tooltip(tooltipTriggerEl)
        })

        document.getElementById('standardDateForm').addEventListener('submit', function (event) {
            event.preventDefault();
            const balanceUpdatePermissionModal = new bootstrap.Modal(document.getElementById('balanceUpdatePermissionModal'));
            balanceUpdatePermissionModal.show();
        });

        const balanceUpdatePermissionModal = new bootstrap.Modal(document.getElementById('balanceUpdatePermissionModal'));
        const redirectUrl = '/budget-book-transaction-tracker';

        document.getElementById('balanceUpdatePermissionForm').addEventListener('submit', function (event) {
            console.log('Check balance update permission.')
            event.preventDefault();

            const inputPermissionKey = document.getElementById('balanceUpdatePermissionPassword').value;
            runProcessAfterPermissionKeyValidation(
                inputPermissionKey,
                () => updateBalance(balanceUpdatePermissionModal, redirectUrl)
            );
        });

        loadBudgetBookTransactions(1);
    }

});

function loadBudgetBookTransactions(page) {
    getAllBudgetBookTransactions(page)
        .then(data => {
            try {
                const tableBody = document.getElementById('transaction-table-body');
                const transactions = data.content;

                tableBody.innerHTML = '';
                transactions.forEach(transaction => {
                    const formattedDate = removeSeconds(`${transaction.createdAt}`)

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
                        <td>${transaction.memo}</td>
                        <td>${formattedDate}</td>
                    </tr>
                `;
                    tableBody.innerHTML += row;
                });

                generatePageButtons(data.paging.pageNumber, data.paging.totalPages);
                loadPageData(data.paging.totalPages, loadBudgetBookTransactions);

            } catch (error) {
                console.error('API 데이터를 불러오는 중 오류가 발생했습니다:', error);
            }

        });
}


function getTransactionType(type) {
    return type === "입금" ? "deposit" : "withdrawal";
}

function getAllBudgetBookTransactions(page) {
    const url = `/account-transactions?page=${page}`;

    return fetch(url)
        .then(response => response.json())
        .then(data => {
            return data;
        })
}

function updateBalance(balanceUpdatePermissionModal, redirectUrl) {
    const url = '/account-transactions/balance';
    const bodyData = {
        fromTransactionDate: document.getElementById('standardDate').value
    }

    fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bodyData)
    })
        .then(response => {
            if (response.status === 204) {
                alert('잔액이 업데이트 되었습니다.');
                balanceUpdatePermissionModal.hide();
                window.location.href = redirectUrl;
            } else {
                console.log('status = ', response.status)
                response.json().then(errorData => {
                    throw new Error(`Error ${response.status}: ${errorData.message || 'Unknown error'}`);
                })
            }
        })
        .catch(error => {
            console.error('잔액 업데이트 중 오류 발생:', error);
        });
}