function getCurrentYearMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    return { year, month };
}

function loadContent(page) {
    let content = document.getElementById('content');
    switch (page) {
        case 'monthlyFeeRate':
            const { year, month } = getCurrentYearMonth();
            const url = `/membership-fee/deposite-rate?year=${year}&month=${month}`
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    content.innerHTML =
                        `<h1>이달의 회비 입금률 보기</h1>
                            <p>${data.year}년</p>
                            <p>${data.month}월</p>
                            <p>입금률: ${data.depositRate}%</p>
                            <p>회원수: ${data.totalMembers}명</p>
                            <p>입금자: ${data.paidMembers}명</p>`;

                })
                .catch(error => {
                    console.error('Error fetching monthly fee rate:', error);
                    content.innerHTML = '<h1>Error</h1><p>Failed to load monthly fee rate.</p>';
                });
            break;

        case 'feeList':
            fetch('/api/fee-list')
                .then(response => response.json())
                .then(data => {
                    content.innerHTML = '<h1>회비 입금 명단 보기</h1>';
                    let list = '<ul>';
                    data.fees.forEach(fee => {
                        list += `<li>${fee.name} - ${fee.amount}</li>`;
                    });
                    list += '</ul>';
                    content.innerHTML += list;
                })
                .catch(error => {
                    console.error('Error fetching fee list:', error);
                    content.innerHTML = '<h1>Error</h1><p>Failed to load fee list.</p>';
                });
            break;
        case 'registerAccount':
            content.innerHTML = `
                <h1>계좌 등록하기</h1>
                <form id="registerAccountForm">
                    <label for="accountName">Account Name:</label>
                    <input type="text" id="accountName" name="accountName" required>
                    <button type="submit">Register</button>
                </form>
            `;
            document.getElementById('registerAccountForm').addEventListener('submit', function(event) {
                event.preventDefault();
                const accountName = document.getElementById('accountName').value;
                fetch('/api/register-account', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ name: accountName }),
                })
                    .then(response => response.json())
                    .then(data => {
                        content.innerHTML = `<h1>계좌 등록 완료</h1><p>Account ID: ${data.id}</p>`;
                    })
                    .catch(error => {
                        console.error('Error registering account:', error);
                        content.innerHTML = '<h1>Error</h1><p>Failed to register account.</p>';
                    });
            });
            break;

        default:
            content.innerHTML = '<h1>Welcome to the Dashboard</h1><p>내용을 보려면 왼쪽 메뉴에서 항목을 선택하세요.</p>';
    }
}
