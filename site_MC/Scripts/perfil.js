window.addEventListener("load", () => {
    document.body.classList.add("loaded");
});

async function buscar() {
    const radioPaciente = document.getElementById("paciente_checkbox");
    const radioMedico = document.getElementById("medico_checkbox");
    const resultadoEl = document.getElementById("p_result");
    const input = document.getElementById("input");
    const id = input.value.trim();

    resultadoEl.textContent = "";

    if (id === "") {
        resultadoEl.textContent = "Campo em branco: digite um id";
        return;
    }

    if (!radioPaciente.checked && !radioMedico.checked) {
        resultado_p.textContent = "Campo em branco: selecione um perfil";
        return;
    }

    const tipo = radioPaciente.checked ? "patients" : "doctors";
    const label = radioPaciente.checked ? "paciente" : "médico";

    resultadoEl.textContent = "Buscando...";

    try {
        const resposta = await fetch(`http://localhost:8080/${tipo}/${id}`);

        if (!resposta.ok) {
            resultadoEl.textContent = `Erro: ${label} não encontrado (status ${resposta.status})`;
            return;
        }

        const dados = await resposta.json();
        resultadoEl.textContent = JSON.stringify(dados, null, 2);

    } catch (erro) {
        resultadoEl.textContent = "Erro ao buscar: " + erro.message;
    }
}



