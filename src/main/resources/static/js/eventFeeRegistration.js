document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    if (path === '/budget-book-registration') {
        document.getElementById('eventFeeRegistrationModal').addEventListener('shown.bs.modal', function () {
            const status = 'ACTIVE'
            getEventsByStatus(status)
                .then(data => {
                    const eventSelectBox = document.getElementById('eventId')

                    data.forEach(event => {
                            const option = document.createElement('option')
                            option.value = event.eventId;
                            option.textContent = `${event.eventName} (${event.eventDescription})`;

                            eventSelectBox.appendChild(option);
                        }
                    );
                })
                .catch(error => console.error('/events/ACTIVE 호출 중 에러 발생:', error));
        });

    }

});