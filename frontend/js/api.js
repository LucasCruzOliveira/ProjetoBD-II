function getRandomColor() {
  const letters = '0123456789ABCDEF';
  let color = '#';
  for (let i = 0; i < 6; i++) {
    color += letters[Math.floor(Math.random() * 16)];
  }
  return color;
}



fetch('http://localhost:8080/dashboard/financiamentoRegiao')
  .then(res => res.json())
  .then(data => {

    const labels = data.map(item => item.regiao);
    const total = data.map(item => item.total);

    const ctxPizza = document.getElementById('graficoPizza').getContext('2d');
    const graficoPizza = new Chart(ctxPizza, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: 'Quantidade de Financiamentos',
                data: total, // porcentagens ou valores
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
})

fetch('http://localhost:8080/dashboard/financiamentoSituacao')
    .then(res => res.json())
    .then(data =>{

        const labels = data.map(item => item.situacao);
        const total = data.map(item => item.total);

        const dynamicColors = labels.map(() => getRandomColor());

        const ctx = document.getElementById('graficoStatus').getContext('2d');
        const graficoRosca = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Financiamentos',
                data: total,
                backgroundColor: dynamicColors,
                datasets: labels
            }],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            },
            
            options: {
                responsive: false,
            },

            responsive: false
        }
    });
})

fetch('http://localhost:8080/dashboard/financiamentoRegiao')
  .then(res => res.json())
  .then(data => {

    const labels = data.map(item => item.regiao);
    const total = data.map(item => item.total);

    const ctxPizza = document.getElementById('grafico').getContext('2d');
    const graficoPizza = new Chart(ctxPizza, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: 'Quantidade de Financiamentos',
                data: total, // porcentagens ou valores
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
})