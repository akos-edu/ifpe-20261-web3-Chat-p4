Estou desenvolvendo um novo sistema web usando Spring Boot + Thymeleaf,
e o front-end deve usar Tailwind CSS + daisyUI (exigência do professor
da disciplina). Este sistema faz parte de uma série de projetos que
precisam manter a MESMA identidade visual entre si — ou seja, o design
definido aqui será reaproveitado nos próximos sistemas também.

O padrão visual já existe em outro sistema (feito em Jakarta EE + JSP +
Bootstrap) e precisa ser recriado fielmente com componentes daisyUI.
Segue a especificação do design a reproduzir:

## Identidade visual

- Tema: sistema institucional/solidário, visual limpo, verde como cor
  predominante.
- Paleta de cores:
  - primary (botões, destaques): #2e8b57
  - primary hover: #256f46
  - secondary/accent (header, títulos): #1f6f43
  - neutral (footer, fundo escuro): #173f29
  - base-100 (fundo geral da página): #f4f8f5
  - base-200 (blocos internos de destaque, ex: cards de info): #edf6f0
  - texto padrão: #26352b
  - texto secundário/muted: #66736a
  - error: #dc3545 / texto de erro #a33a3a / fundo de erro claro #f8dddd
- Tipografia: sans-serif (Arial/Helvetica ou a fonte padrão do Tailwind),
  títulos em negrito na cor secondary/accent (#1f6f43).
- Layout "sticky footer": header no topo, conteúdo ocupando o espaço
  disponível (flex-1), footer sempre fixo na base da página mesmo com
  pouco conteúdo.

## Estrutura de componentes (mapear para daisyUI)

1. **Header/Navbar** (`navbar` do daisyUI, cor de fundo primary/accent):
   - Logo/nome do sistema à esquerda, em negrito.
   - À direita: se usuário autenticado, saudação "Olá, {nome}" + botão
     "Sair" (btn btn-outline, estilo claro sobre fundo verde);
     se visitante, botão "Login".
   - Sombra leve abaixo do header (shadow-md).

2. **Footer** (`footer` do daisyUI, fundo neutral #173f29, texto claro):
   - Centralizado, fonte pequena.
   - Texto de crédito do sistema (nome do projeto + autor + ano).

3. **Cards** (`card` do daisyUI, `card bg-base-100 shadow-md`):
   - Cantos arredondados, sombra suave.
   - Borda de destaque verde (esquerda ou superior, ~4-5px, usar
     border-l-4 border-primary ou border-t-4).
   - Efeito hover: leve elevação (translate-y) e sombra maior
     (hover:shadow-lg hover:-translate-y-1, transition).
   - Ícones como emojis simples representando cada módulo do sistema
     (ex.: 🤝 organizações, 📦 insumos, 🚨 emergências, 📢 campanhas).

4. **Botões** (`btn` do daisyUI):
   - Primário: `btn btn-primary` (verde #2e8b57, hover mais escuro).
   - Outline (usado sobre fundo verde do header): `btn btn-outline
     btn-primary-content` ou similar, texto/borda branca.
   - Destrutivo (excluir): `btn btn-error` ou variante suave (fundo
     vermelho claro, texto vermelho escuro).

5. **Formulários** (`input`, `label`, `form-control` do daisyUI):
   - Inputs com bordas arredondadas, cor neutra suave.
   - Foco: borda e anel (ring) na cor primary.
   - Labels em negrito, cor de texto escura esverdeada.

## O que preciso que você faça

1. Crie um **tema customizado do daisyUI** (via `tailwind.config.js`,
   plugin daisyui, `themes: [...]`) usando exatamente as cores acima
   mapeadas para os tokens semânticos do daisyUI (primary, secondary,
   accent, neutral, base-100, base-200, error, etc.).

2. Crie um **layout base do Thymeleaf** (fragmento reutilizável, com
   `th:fragment`/`th:insert`/`th:replace`, ou usando thymeleaf-layout-dialect)
   contendo o header/navbar e o footer padronizados, para que TODAS as
   páginas do sistema herdem o mesmo cabeçalho e rodapé sem duplicar
   HTML — isso é essencial para manter consistência entre as telas e
   entre os diferentes sistemas da disciplina.

3. Recrie os componentes de card, botão e formulário descritos acima
   como classes/fragmentos reutilizáveis do daisyUI, evitando misturar
   abordagens (nada de CSS customizado solto junto com daisyUI — use
   apenas classes utilitárias Tailwind/daisyUI para manter um padrão
   único, diferente do sistema anterior que misturava Bootstrap puro
   com CSS customizado em páginas diferentes).

4. Ao final, mostre um exemplo de página (ex.: tela inicial com cards
   de módulos) já usando o layout, o tema e os componentes definidos.