document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/') {
        getAllBudgetBookTransactions(1)
            .then(data => {
                try {
                    const tableBody = document.getElementById('transaction-table-body');
                    const transactions = data.content.slice(0, 7);

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
                        <td>${formattedDate}</td>
                    </tr>
                `;
                    tableBody.innerHTML += row;
                });

                renderPagination(data.paging.pageNumber, data.paging.totalPages);

            } catch (error) {
                console.error('API 데이터를 불러오는 중 오류가 발생했습니다:', error);
            }

        });
}

function renderPagination(pageNumber, totalPages) {
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

    // 페이지 번호 버튼을 클릭한 경우
    document.querySelectorAll('.pagination a').forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault();

            const selectedPage = parseInt(this.getAttribute('data-page'));
            if (selectedPage >= 1 && selectedPage <= totalPages) {
                loadBudgetBookTransactions(selectedPage);
            }
        });
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