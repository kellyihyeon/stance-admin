document.addEventListener('DOMContentLoaded', function () {
    let path = window.location.pathname;

    document.querySelectorAll('.nav-item').forEach(item => {
        const link = item.querySelector('.nav-link');

        // 클릭 된 메뉴 활성화 상태로 바꾸기
        if (link && link.getAttribute('href') === path) {
            item.classList.add('active');

            // 서브 메뉴 화면에 노출하기
            let subMenu = item.closest('.sub-menu');
            if (subMenu) {
                subMenu.classList.remove('d-none');

                // 상위 메뉴 찾기
                let parentMenu = item.closest('.nav-item.menu-link');
                console.log('parentMenu found:', parentMenu)
                // 상위 메뉴의 토글 열기
                if (parentMenu) {
                    const grandParentToggle = parentMenu.querySelector('.menu-toggle');
                    console.log('grandParentToggle found:', grandParentToggle);

                    if (grandParentToggle) {
                        grandParentToggle.classList.add('show');
                        const grandParentSubMenu = grandParentToggle.nextElementSibling;
                        if (grandParentSubMenu) {
                            grandParentSubMenu.classList.remove('d-none');
                        }
                    }
                }
            }
        } else {
            item.classList.remove('active');
        }
    });

    // System 의 서브메뉴 토글 기능 열기
    document.querySelectorAll('.menu-toggle').forEach(toggle => {
        toggle.addEventListener('click', function(event) {
            event.preventDefault();
            const submenu = this.nextElementSibling;
            submenu.classList.toggle('d-none');
        });
    });
})