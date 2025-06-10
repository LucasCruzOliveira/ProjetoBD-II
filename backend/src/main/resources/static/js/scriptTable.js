

$(document).ready(function () {
  const tabela = $('#tabelaEnderecos').DataTable({
    language: {
      url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json"
    }
  });

  fetch('http://localhost:8080/tabela/enderecos')
      .then(res => res.json())
      .then(data => {
        const dados = data.map(item => [
          item.logradouro ?? '',
          item.bairro ?? '',
          item.numero ?? '',
          item.cep ?? '',
          item.municipio?.nome ?? '',
          item.municipio?.uf ?? '',
          item.municipio?.regiao ?? '',
          item.texto ?? ''
        ]);
        tabela.clear().rows.add(dados).draw();
      });
});
    

    
let atual = 1;
const total = 2;

function mostrarTabela(n) {
  for (let i = 1; i <= total; i++) {
    const dash = document.getElementById('tabela' + i);
    dash.classList.remove('ativo');
  }

  const atualDash = document.getElementById('tabela' + n);
  if (atualDash) {
    atualDash.classList.add('ativo');
    atual = n;
  }
}

function avancar() {
  if (atual < total) {
    mostrarDashboard(atual + 1);
  }
}

function voltar() {
  if (atual > 1) {
    mostrarDashboard(atual - 1);
  }
}