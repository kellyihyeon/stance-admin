document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/event-tracker') {
        loadEvents(1);
    }
});


function getAllEvents(page) {
    const url = `/events?page=${page}`;

    return fetch(url)
        .then(response => response.json())
        .then(data => data);
}

function loadEvents(page) {
    getAllEvents(page)
        .then(data => {
            try {
                const tableBody = document.getElementById('event-table-body');
                const events = data.content;
                tableBody.innerHTML = '';

                events.forEach(event => {
                    const formattedCreatedAt = removeSeconds(`${event.createdAt}`);
                    const status = `${event.status}`;

                    const row = `
                            <tr>
                                <td>${event.eventName}</td>
                                <td>${event.description}</td>
                                <td>${event.amount.toLocaleString()}원</td>
                                <td>${event.dueDate}</td>
                                <td>${formattedCreatedAt}</td>
                                <td>${event.creatorName}</td>
                                <td>${status}</td>
                            </tr>
                        `;

                    tableBody.innerHTML += row;
                });

                generatePageButtons(data.paging.pageNumber, data.paging.totalPages);
                loadPageData(data.paging.totalPages, loadEvents);

            } catch (error){
                console.error('API[/events] 데이터를 불러오는 중 오류가 발생했습니다:', error);
            }

        });
}