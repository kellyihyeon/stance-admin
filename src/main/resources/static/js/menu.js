document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    // 메뉴 클릭 시 활성화 상태로 바꾸기
    document.querySelectorAll('.nav-item').forEach(item => {
        const link = item.querySelector('.nav-link');

        if (link && link.getAttribute('href') === path) {
            item.classList.add('active');
        } else {
            item.classList.remove('active');
        }
    });

    // System 메뉴 클릭 시 모임통장 토글
    document.getElementById('systemMenu').addEventListener('click', function(event) {
        event.preventDefault();
        const systemSubmenu = document.getElementById('systemSubmenu');
        systemSubmenu.classList.toggle('d-none');
    });

    // 모임통장 클릭 시 서브메뉴 토글
    document.getElementById('meetingMenu').addEventListener('click', function(event) {
        event.preventDefault();
        const meetingSubmenu = document.getElementById('meetingSubmenu');
        meetingSubmenu.classList.toggle('d-none');
    });
})