(function () {
    const chatLog = document.getElementById('chat-log');
    const form = document.getElementById('form-envio');
    const campoMensagem = document.getElementById('campo-mensagem');

    const stompClient = new StompJs.Client({
        webSocketFactory: () => new SockJS('/ws'),
        reconnectDelay: 5000
    });

    stompClient.onConnect = () => {
        stompClient.subscribe('/topic/sala.' + SALA_ID, (frame) => {
            const mensagem = JSON.parse(frame.body);
            exibirMensagem(mensagem);
        });

        stompClient.publish({
            destination: '/app/chat.' + SALA_ID + '.entrar',
            body: JSON.stringify({})
        });
    };

    stompClient.onStompError = (frame) => {
        console.error('Erro STOMP:', frame.headers['message'], frame.body);
    };

    stompClient.activate();

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        const texto = campoMensagem.value.trim();
        if (!texto || !stompClient.connected) {
            return;
        }

        stompClient.publish({
            destination: '/app/chat.' + SALA_ID + '.enviar',
            body: JSON.stringify({conteudo: texto})
        });

        campoMensagem.value = '';
    });

    function exibirMensagem(mensagem) {
        const linha = document.createElement('div');
        linha.className = 'mb-2 text-sm';

        if (mensagem.tipo === 'JOIN') {
            linha.style.color = '#66736a';
            linha.textContent = mensagem.remetente + ' entrou na sala.';
        } else if (mensagem.tipo === 'LEAVE') {
            linha.style.color = '#66736a';
            linha.textContent = mensagem.remetente + ' saiu da sala.';
        } else {
            const propria = mensagem.remetente === USUARIO;
            linha.innerHTML = '<span style="font-weight:bold;color:' + (propria ? '#2e8b57' : '#1f6f43') + ';">'
                + escapeHtml(mensagem.remetente) + ':</span> '
                + '<span style="color:#26352b;">' + escapeHtml(mensagem.conteudo) + '</span>';
        }

        chatLog.appendChild(linha);
        chatLog.scrollTop = chatLog.scrollHeight;
    }

    function escapeHtml(texto) {
        const div = document.createElement('div');
        div.textContent = texto;
        return div.innerHTML;
    }

    window.addEventListener('beforeunload', () => {
        stompClient.deactivate();
    });
})();
