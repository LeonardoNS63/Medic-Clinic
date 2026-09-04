function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}

function verificarLogin() {
    const token = localStorage.getItem("token");
    if (!token) {
        window.location.href = "login.html";
    }
}

verificarLogin();

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
        resultadoEl.textContent = "Campo em branco: selecione um perfil";
        return;
    }

    const tipo = radioPaciente.checked ? "patients" : "doctors";
    const label = radioPaciente.checked ? "paciente" : "médico";

    resultadoEl.textContent = "Buscando...";

    try {
        const resposta = await fetch(`http://localhost:8080/${tipo}/${id}`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (!resposta.ok) {
            resultadoEl.textContent = `Erro: ${label} não encontrado (status ${resposta.status})`;
            return;
        }

        if (resposta.status === 403 || resposta.status === 401) {
            localStorage.removeItem("token");
            window.location.href = "login.html";
            return;
        }

        // Criação de um objeto JS com a resposta do fetch 
        const dados = await resposta.json();

        //retira do objeto JS os valores desejados e os aplica em seus devidos lugares
        document.getElementById("saudacao").textContent = "Olá, " + dados.name;

        document.getElementById("nome").textContent = dados.name;
        document.getElementById("tel").textContent = dados.phone;
        document.getElementById("email").textContent = dados.email;

        document.getElementById("infos_interno").style.visibility = "visible";
        
        // Transforma em json
        resultadoEl.textContent = JSON.stringify(dados, null, 2);

    } catch (erro) {
        resultadoEl.textContent = "Erro ao buscar: " + erro.message;
    }
}



