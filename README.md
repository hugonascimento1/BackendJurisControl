# Documentação Sistema de Gerenciamento de Câmara de Arbitragem 

# JurisControl

**Professor:** Rennan Cavalcante Raffaele

**Alunos:** 
- Hugo Vinícius do Nascimento 
- Jõao Miguel Carlos da Silva 
- Mariana dos Anjos Ribeiro
- Maria Cecília Sitcovsky Rodrigues 
- MAtheus Vinícius Guimarães 
- Pedro Miguel Matsushita de Araújo

## Sumário 
 
1. [Requisitos](#requisitos)
2. [Requisitos Funcionais](#funcionais)
3. [Requisitos Não Funcionais](#Não-Funcionais) 
4. [Diagrama de Classes](#Diagrama)
5. [Protótipo de Baixa Fidelidade](#Protótipo) 
6. [Definição das Tecnologias](#Tecnologias) 
7. [Design e Prototipação](#Design-Prototipação) 
8. [Desenvolvimento Frontend](#FrontEnd) 
9. [Desenvolvimento Backend](#BackEnd) 
10. [Banco de Dados](#Banco-de-Dados)
11. [Implantação e Hospedagem](#Implantação-Hospedagem)
12. [Ferramentas de Desenvolvimento e Produtividade](#Ferramentas)


## 1. Requisitos

### 2. Requisitos Funcionais
**Controle de Acesso e Privacidade:** 
- Autenticação segura para advogados e administradores. 
- Restrição de acesso a informações processuais.
- exibindo apenas dados básicos a clientes e terceiros.

**Gerenciamento e Cadastro de Franquias:** 
- Criação e gerenciamento de franquias da câmara de arbitragem. 
- Expansão para novas localidades, permitindo que escritórios 
regionais acessem os serviços. 

**Dashboard Centralizado:** 
- Painel central para a matriz monitorar todas as franquias. 
- Visualização do andamento dos processos em cada unidade.

**Cadastro e Acompanhamento de Processos:** 
- Formulário para cadastro de novos processos, associando 
documentos pertinentes. 
- Advogados podem acompanhar o progresso dos casos e manter a 
comunicação com as partes. 
- Clientes podem acompanhar o andamento dos seus processos por 
meio de um identificador.

**Gestão de Prazos e Alertas:** 
- Sistema de alertas automáticos para advogados e clientes sobre 
prazos, datas de audiências e vencimento de parcelas. 

**Biblioteca de Modelos Pré-Configurados:** 
- Disponibilização de modelos padrões de documentos, que 
advogados podem personalizar. 
 
**Acesso Mobile:** 
- Acesso via dispositivos móveis para clientes, com acompanhamento 
do processo.

### 3. Requisitos Não Funcionais
**Segurança:** 
- Implementação de criptografia de dados e autenticação através de 
tokens para proteção de informações. 
 
**Usabilidade:** 
- Interface intuitiva para usuários de diferentes níveis de experiência. 

**Escalabilidade:** 
- O sistema deve ser escalável para suportar o crescimento do número 
de usuários e processos conforme a expansão das franquias. 

**Desempenho:** 
- Tempos de resposta rápidos para carregar processos, documentos e 
gerar relatórios, mesmo com muitos usuários simultâneos.

**Confiabilidade:** 
- O sistema deve garantir alta disponibilidade (99,9%), com backup 
automático e recuperação rápida em caso de falhas. 

**Portabilidade:** 
- Acesso multiplataforma para facilitar o uso em diferentes 
dispositivos.

## 4. Diagrama de Classes
- **Classes presentes no sistema:** Foram definidas 6 classes inicialmente para o 
projeto. Sendo elas: Administrador do sistema, Administrador de Franquia, Franquia, Advogado, Cliente e Processo. Elas estão representadas na “imagem 1” logo abaixo com os seus atributos, métodos e relacionamentos.

<img width="3783" alt="Diagrama de Classes (UML) (4)" src="https://github.com/user-attachments/assets/1dc15e2a-5f52-4597-89f0-94ee6d3dc1b8">

## 5. Definição das Tecnologias 

**Design e Prototipação:**

- **Figma:** O Figma será utilizado para a criação de diagramas, fluxos de 
navegação e design visual do projeto. O mais importante é que ele será usado para prototipação, permitindo que a equipe visualize e valide a interface do usuário antes do desenvolvimento. 

- **Shadcn/UI:** Um framework de componentes React voltado para a criação 
de interfaces modernas, com foco em acessibilidade e responsividade. Será 
utilizado para prototipação rápida e construção de componentes reutilizáveis de UI.

**Desenvolvimento Frontend:** 

- **Next.js:** Utilizado para o desenvolvimento do frontend do projeto, é um 
framework React que facilita a criação de aplicações web modernas com suporte 
para renderização no lado do servidor e geração de sites estáticos. Ele oferece 
um ambiente altamente escalável e otimizado. 

- **React:** Biblioteca JavaScript para a construção de interfaces de usuário 
baseadas em componentes. React é a base do frontend, facilitando a criação de 
interfaces dinâmicas e interativas. 

- **JavaScript:** Linguagem de programação utilizada em conjunto com React 
e Next.js para implementar as funcionalidades e lógicas de interação no frontend. 

- **Recharts:** Biblioteca de gráficos baseada em React, usada para 
visualização de dados no projeto, como dashboards e relatórios interativos. 

**Desenvolvimento Backend:** 

- **Java:** Linguagem de programação utilizada no backend para desenvolver 
a lógica de negócios e garantir a robustez do sistema. Java será a base para a 
implementação dos serviços do lado do servidor. 

- **Spring Boot:** framework que facilita o desenvolvimento de APIs e 
microserviços em Java. Ele será utilizado para criar a camada de backend do 
sistema, permitindo a comunicação com o frontend e o banco de dados.  

**Banco de Dados:** 

- **Baserow:** Banco de dados NoSQL usado para armazenar dados do 
projeto de maneira flexível. Ele permite integração com APIs e será o repositório principal dos dados de usuários e de processos do sistema.  

- **Supabase:** Alternativa open-source ao Firebase, será utilizada para 
autenticação de usuários e gerenciamento do banco de dados relacional. Ele 
permite fácil integração com o backend e frontend para garantir a segurança e 
integridade dos dados. 

**Implantação e Hospedagem:**

- **Vercel:** Plataforma de deployment que será usada parra hospedar o 
frontend do projeto desenvolvido em Next.js. O Vercel oferece recursos com 
deploy contínuo e integração com o Github. 

- **Github:** Serviço de versionamento de código, utilizado para gerenciar o 
repositório do projeto, colaborando com as mudanças e garantindo o controle 
de versões. O projeto será integrado ao Github para rastreamento de alterações 
e colaboração entre os membros da equipe. 

**Ferramentas de Desenvolvimento e Produtividade:** 

- **Visual Studio Code (Vscode):** Editor de código utilizado pelos 
desenvolvedores para escrever, depurar e testar o código do projeto. Suas 
extensões, como integração com o Github, suporte para múltiplas linguagens e 
terminal embutido, facilitam o desenvolvimento. 

- **Flutter:** Framework para o desenvolvimento de aplicações móveis e 
web, que vai ser utilizado para as versões mobile/Android do projeto, permitindo 
criar uma versão mobile com alta performance e UI nativa. 

- **Dart:** Linguagem de programação utilizada junto com Flutter, para criar 
a lógica de aplicativos móveis e web que compartilham código.

