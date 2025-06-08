const ctx = document.getElementById('graficoRosca').getContext('2d');
const graficoRosca = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['Norte', 'CSS', 'JavaScript', 'Java', 'yas'],
      datasets: [{
        label: 'Distribuição de Conhecimento',
        data: [80, 25, 42, 3, 8], // porcentagens ou valores
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56','#ce51ce', '#ec1b5a'],
        borderColor: '#fff',
        borderWidth: 2
      }]
    },
    options: {
      responsive: false,
      plugins: {
        legend: {
          position: 'right',
          labels: {
            padding: 50
          }
        }
      }
    }
});

