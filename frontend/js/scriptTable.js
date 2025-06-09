
    // Dados simulados (poderiam vir do backend)
    const dadosEmpreendimentos = [
      {
        nome: "Empreendimento A",
        situacao: "CONCLUIDO",
        modalidade: "FAR",
        dataAssinatura: "2023-05-01",
        municipio: "Belo Horizonte",
        uf: "MG",
        construtora: "Construtora X",
        valor: 1500000
      },
      {
        nome: "Empreendimento B",
        situacao: "CANCELADO",
        modalidade: "ENTIDADES",
        dataAssinatura: "2022-11-15",
        municipio: "Recife",
        uf: "PE",
        construtora: "Construtora Y",
        valor: 900000
      },
      {
        nome: "Empreendimento C",
        situacao: "NAO_CONCLUIDO",
        modalidade: "PUBLICA",
        dataAssinatura: "2024-03-20",
        municipio: "São Paulo",
        uf: "SP",
        construtora: "Construtora Z",
        valor: 2300000
      }
    ];

    $(document).ready(function () {
      const tabela = $('#tabelaEmpreendimentos').DataTable({
        language: {
          url: "//cdn.datatables.net/plug-ins/1.13.6/i18n/pt-BR.json"
        }
      });

      // Preenche a tabela com os dados do array
      dadosEmpreendimentos.forEach(e => {
        tabela.row.add([
          e.nome,
          e.situacao,
          e.modalidade,
          e.dataAssinatura,
          e.municipio,
          e.uf,
          e.construtora,
          e.valor.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })
        ]).draw();
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