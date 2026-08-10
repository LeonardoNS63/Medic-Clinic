window.addEventListener("load", () => {
    document.body.classList.add("loaded");
});

async function buscarConsulta() {
    const resultadoEl = document.getElementById("p_result");
    const input = document.getElementById("input");
    const id = input.value.trim();

    resultadoEl.textContent = "";

    if (id === "") {
        resultadoEl.textContent = "Campo em branco: digite um id";
        return;
    }

    resultadoEl.textContent = "Buscando...";

    try {
        const resposta = await fetch(`http://localhost:8080/appointments/${id}`);

        if (!resposta.ok) {
            resultadoEl.textContent = `Erro: consulta não encontrada (status ${resposta.status})`;
            return;
        }

        const dados = await resposta.json();
        resultadoEl.textContent = JSON.stringify(dados, null, 2);

    } catch (erro) {
        resultadoEl.textContent = "Erro ao buscar: " + erro.message;
    }


}