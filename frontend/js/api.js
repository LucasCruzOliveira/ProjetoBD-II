
fetch('http://localhost:8080/dashboard/financiamentoRegiao')
  .then(res => res.json())
  .then(data => {
    console.log("dados", data)

    const labels = data.map(item => item.regiao);
    const dados = data.map(item => item.total);
    console.log(labels,dados)

    const ctxPizza = document.getElementById('graficoPizza').getContext('2d');
    const graficoPizza = new Chart(ctxPizza, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: 'Quantidade de Financiamentos',
                data: dados, // porcentagens ou valores
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


