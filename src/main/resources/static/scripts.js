document.addEventListener('DOMContentLoaded', function () {

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
});
