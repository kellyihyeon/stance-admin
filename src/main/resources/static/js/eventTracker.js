document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/event-tracker') {
        const eventRegisterInEventModal = new bootstrap.Modal(document.getElementById('eventRegisterInEventModal'));
        loadEvents(1);

        document.getElementById('eventRegisterInEventModal').addEventListener('shown.bs.modal', function () {
            addEventListenerWithActiveStatus();
        });

        document.getElementById('eventForm').addEventListener('submit', function (event) {
            event.preventDefault();

            const permissionKeyCheckerModal = new bootstrap.Modal(document.getElementById('permissionKeyCheckerModal'));
            permissionKeyCheckerModal.show();
        });

        const redirectUrl = '/event-tracker'
        processValidatePermissionKey(eventRegisterInEventModal, redirectUrl);
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

                const imgUrls = {
                    "회식": "images/team-party.png",
                    "유니폼": "images/basketball-jersey.png",
                    "엠티": "images/membership-training.png"
                };

                events.forEach(event => {
                    const formattedCreatedAt = removeSeconds(`${event.createdAt}`);
                    const eventStatusText = event.status === 'ACTIVE' ? "on" : "off";
                    const creatorName = event.creatorName ? event.creatorName : '';
                    const eventName = event.eventName
                    const imgUrl = imgUrls[eventName];

                    const row = `
                            <tr>
                                <td>
                                    <img src="${imgUrl}" alt="${eventName} 이미지" style="width: 30px; height: 30px; margin-right: 8px;">
                                    <strong>${event.eventName}</strong>
                                </td>
                                <td>${event.description}</td>
                                <td>${event.amount.toLocaleString()}원</td>
                                <td>${event.dueDate}</td>
                                <td>${formattedCreatedAt}</td>
                                <td>${creatorName}</td>
                                <td><span class="event-status ${event.status}">${eventStatusText}</span></td>
                            </tr>
                        `;

                    tableBody.innerHTML += row;
                });

                generatePageButtons(data.paging.pageNumber, data.paging.totalPages);
                loadPageData(data.paging.totalPages, loadEvents);

            } catch (error){
                console.error('API [/events] 데이터를 불러오는 중 오류가 발생했습니다:', error);
            }

        });
}