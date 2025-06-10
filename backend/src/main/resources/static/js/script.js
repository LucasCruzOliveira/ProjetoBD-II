let atual = 1;
const total = 4;

function mostrarDashboard(n) {
  for (let i = 1; i <= total; i++) {
    const dash = document.getElementById('dashboard' + i);
    dash.classList.remove('ativo');
  }

  const atualDash = document.getElementById('dashboard' + n);
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

function abrirTabela(){
    window.location.href='tabela.html';
}

