# 🚀 StarFleet System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![POO](https://img.shields.io/badge/POO-Nível_Avançado-blue?style=for-the-badge)

Bem-vindo ao **StarFleet System**, um sistema interativo de gestão de frota espacial via terminal! 

Desenvolvido inteiramente em **Java**, este projeto nasceu como um laboratório prático para aplicar e consolidar os pilares fundamentais da **Programação Orientada a Objetos (POO)**. O sistema coloca o usuário no papel de Capitão do Centro de Comando, permitindo criar, gerenciar, realizar manutenções e despachar diferentes tipos de naves para missões dinâmicas no espaço profundo.

## 📋 Funcionalidades do Centro de Comando

O sistema opera através de um menu interativo (`while` loop) que se mantém em execução até que o usuário decida desligar os terminais. As principais operações incluem:

* **Inicialização da Frota:** O sistema guia o usuário na criação personalizada de três veículos essenciais, capturando dados via `Scanner` (ID, Nome e atributos exclusivos de cada nave).
* **Relatório Dinâmico (Polimórfico):** O usuário pode solicitar o status atualizado de qualquer nave. O sistema busca o veículo na base de dados e exibe informações em tempo real (Energia restante, Status atual de operação, etc).
* **Sistema de Missões e Consumo de Recursos:** Ao despachar uma nave, o sistema altera seu status para `EM_MISSAO`, simula o tempo de viagem (utilizando manipulação de Threads) e consome os recursos da nave de forma matemática e segura, retornando um relatório do sucesso da operação.
* **Manutenção Especializada:** O sistema permite recarregar a bateria (restaurando para 100%) e modificar rotas ou arsenais específicos dependendo da nave selecionada.

---

## 💻 Arquitetura e Conceitos de POO Aplicados

Este projeto foi desenhado para ser uma vitrine técnica de boas práticas de Engenharia de Software. Abaixo, o detalhamento das ferramentas conceituais utilizadas:

### 1. Abstração e Herança (`extends`)
* A base do sistema é a classe abstrata `VeiculoEspacial`. Ela dita as regras globais: todo veículo possui um ID, Nome, Nível de Energia (0 a 100%) e um Status. 
* O fato de ser abstrata impede a criação de um "veículo genérico", forçando o uso de subclasses altamente especializadas. Além disso, o construtor da superclasse (`super()`) garante que toda nave inicializada comece com a bateria máxima.

### 2. Polimorfismo e Coleções (`LinkedList`)
* A frota inteira é armazenada em uma única `List<VeiculoEspacial>`. Isso significa que o Centro de Comando consegue iterar, buscar e gerenciar Sondas, Naves de Exploração e Caças de Combate dentro da mesma lista de forma simultânea.
* O método `executarMissao()` foi sobrescrito (`@Override`) em cada filha. O sistema invoca esse método cegamente e o Java, através do polimorfismo dinâmico, decide qual comportamento acionar dependendo de quem está instanciado na memória.

### 3. Interfaces (`implements`)
* Implementação da interface `Comunicavel`. Ela atua como um contrato, garantindo que toda nave construída na frota seja obrigada a ter métodos de `enviarMensagem` e `receberMensagem`, padronizando o sistema de rádio.

### 4. Encapsulamento e Segurança de Dados
* Variáveis de instância são rigorosamente `private`. 
* Atributos críticos não podem ser alterados livremente. Por exemplo, a energia só pode ser deduzida ou restaurada através de métodos controladores, impedindo que uma nave fique com energia negativa.

### 5. Tratamento de Exceções (`Try / Catch / Throws`)
* O sistema é **à prova de falhas** (Crash-proof).
* Dentro de cada nave, existem *Cláusulas de Guarda*. Se uma missão é ordenada sem que haja recursos suficientes, a nave não permite a operação e "grita" um erro (`throw new IllegalArgumentException`).
* O código principal (Centro de Comando) envolve as execuções em blocos `try/catch`. Em vez do programa explodir e desligar na cara do usuário, ele captura o erro, avisa o Capitão de forma amigável no console ("Comando Abortado") e retorna ao menu principal com segurança.

### 6. Downcasting Inteligente
* No menu de manutenção, o sistema resgata a nave com a roupagem genérica de `VeiculoEspacial`. Para acessar atributos exclusivos (como recarregar os mísseis da Nave de Combate), o código converte explicitamente o objeto (`Cast`) devolvendo sua identidade original, permitindo total controle do usuário.

---

## 🛸 Especificações Técnicas da Frota

Cada nave foi programada com regras de negócio únicas para suas missões:

1. **Sonda de Reconhecimento:** * *Objetivo:* Mapeamento visual.
   * *Mecânica:* Gasta um valor fixo e leve de energia (10) para operar suas lentes. Seu atributo exclusivo permite que o usuário redefina a `distanciaAlcancada` do alvo.
2. **Nave de Exploração:** * *Objetivo:* Coleta de amostras biológicas e geológicas.
   * *Mecânica:* Exige motores mais potentes, gastando 20 de energia por viagem. Possui o atributo `planetaDestino`, que pode ser redirecionado através da manutenção.
3. **Nave de Combate:** * *Objetivo:* Defesa ativa do setor estelar.
   * *Mecânica Avançada:* Possui validação dupla. Para executar uma missão, o sistema verifica a energia (gasta pesados 30) E verifica se há munição no atributo exclusivo `quantidadeMisseis`. Se um dos dois faltar, a missão é travada por segurança.

---

## 🔭 Próximos Passos (Roadmap de Evolução)

O projeto possui uma arquitetura escalável pronta para receber futuras atualizações:
- [ ] Implementar pontos de vida estrutural (HP) e um sistema de dano para embates.
- [ ] Conectar o sistema a um Banco de Dados Relacional (ex: PostgreSQL) utilizando JDBC para salvar as configurações das naves e persistir a frota mesmo após o desligamento do terminal.
- [ ] Criar eventos aleatórios em tempo de execução (utilizando a classe `Random` do Java), como falhas de comunicação ou encontros com tempestades solares.

---
*Projeto desenvolvido por Rodrigo Andrade como conclusão do módulo avançado de Programação Orientada a Objetos em Java.
