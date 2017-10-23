package br.pucrs.ages.adocoes.Funcionalidades.TermosDeUso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.pucrs.ages.adocoes.MainActivity;
import br.pucrs.ages.adocoes.R;

/**
 * Created by kuquert on 26/05/17.
 */

public class TermosFragment extends Fragment {

    public TermosFragment(){}

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_termos, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView wv = (WebView) view.findViewById(R.id.wv_termos);
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        final Button btnTermos = (Button) (view.findViewById(R.id.btn_termos));
        btnTermos.setEnabled(false);
        btnTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnTermos.setEnabled(true);
                } else {
                    btnTermos.setEnabled(false);
                }
            }
        });


        String termosHTML = "<table style=\"border:0px\">"+
                "<tr>"+
                "<td width=\"15%\" style=\"vertical-align:middle\"><img src=\"http://www.tools.ages.pucrs.br/AGES-Adocoes/Adocoes/raw/384d25e6e798cff26b06b1f600acd67c13132743/img/adocoes-icone.png?raw=true\" alt=\"Adoções!\"/></td>"+
                "<td style=\"font-size:18px;vertical-align:middle\">O <b>Projeto Adoções</b> surgiu de convênio firmado entre o Poder Judiciário do Estado do Rio Grande do Sul, o Ministério Público do Estado do Rio Grande do Sul e a PUCRS, através da AGES - Agência Experimental de Engenharia de Software. O objetivo do convênio é o desenvolvimento de uma solução que possibilite o acesso dos usuários tanto a informações sobre adoção quanto a dados sobre crianças e adolescentes em condições de adotabilidade, sendo composta por um aplicativo para dispositivos móveis e uma ferramenta web de gestão.</td>"+
                "</tr>"+
                "</table>"+
                ""+
                "# **NOTÍCIAS**"+
                ""+
                "## Assinatura do convênio entre TJ-RS, MP-RS e PUC-RS"+
                "- [Zero Hora](https://gauchazh.clicrbs.com.br/geral/noticia/2017/09/aplicativo-sera-desenvolvido-para-facilitar-adocoes-no-rio-grande-do-sul-9906212.html)"+
                "- [TJ-RS](http://www.tjrs.jus.br/site/imprensa/noticias/?idNoticia=398835)"+
                "- [MP-RS](http://www.mprs.mp.br/noticias/45220/)"+
                "- [PUC-RS](http://www.pucrs.br/blog/convenio-permite-criacao-de-aplicativo-que-facilita-adocao-de-criancas-no-rs)"+
                "- [Post no Facebook](https://www.facebook.com/eduardo.h.p.arruda/posts/10207778250134475)"+
                "- [Fotos](./convenio-tjrs-mprs-pucrs-fotos)"+
                ""+
                "# **STAKEHOLDERS**"+
                ""+
                "- Poder Judiciário do Estado do Rio Grande do Sul"+
                "- Ministério Público do Estado do Rio Grande do Sul"+
                ""+
                "## Reuniões"+
                ""+
                "### Realizadas"+
                ""+
                "- [12/05/2017 - Apresentação do projeto](./resolucoes-reuniao-stakeholder-12-05-17)"+
                ""+
                "- [09/06/2017 - Ajuste de requisitos](./resolucoes-reuniao-stakeholder-09-06-17)"+
                ""+
                "- [07/07/2017 - Apresentação do resultado parcial de 2017/1 e ajuste de requisitos](./resolucoes-reuniao-stakeholder-07-07-17)"+
                ""+
                "- [25/08/2017 - Apresentação e motivação do time 2017/2](./resolucoes-reuniao-stakeholder-25-08-17)"+
                ""+
                "### Programadas"+
                ""+
                "- 22/09/2017 - Acompanhamento"+
                "- 27/10/2017 - Acompanhamento"+
                "- 17/11/2017 - Entrega"+
                ""+
                "# **TIME DE PROJETO**"+
                ""+
                "## [Time 2017-2](./time-projeto-2017-2)"+
                ""+
                "### Arquitetura de Software + DevOps"+
                ""+
                "- Eduardo Lima de Oliveira"+
                "- Matheus Mello Vaccaro"+
                ""+
                "### Rest API"+
                ""+
                "- Gabriel Franzoni"+
                "- Gabriel Weich"+
                "- Gregory Silveira Lagranha"+
                "- Guilherme Henrique Draghetti"+
                "- Matheus Guzinski Haas"+
                "- Ramiro Araujo Gonçalves de Lima"+
                "- Thiago Rocha"+
                ""+
                "### Aplicativo Android"+
                ""+
                "- Eduardo Lima de Oliveira"+
                "- Hercilio Martins Ortiz"+
                "- Homero Junior Leitte de Oliveira Santos"+
                "- Israel Deorce Vieira Junior"+
                "- Joao Octavio Freitas Bitelo"+
                "- Juan Lucas Gauto Collin"+
                "- Matheus Mello Vaccaro"+
                ""+
                "### Aplicativo HTML Responsivo"+
                ""+
                "A ser definido após a completação da Rest API"+
                ""+
                "## Reuniões"+
                ""+
                "- 11/08/2017 - Reunião inicial de avaliação do status do projeto em 2017-2"+
                "  - [Tecnologias, algoritmo e telas](/uploads/65d1cd8a4fe0b7f5c531eb952d36a1b9/Foto_1.jpg)"+
                "  - [Processo de desenvolvimento baseado no Scrum](/uploads/cb39569a9817d283806c77bd71181562/Foto_2.jpg)"+
                ""+
                "# **ACOMPANHAMENTO DO PROJETO**"+
                ""+
                "[Kanban](http://www.kanban.ages.pucrs.br/boards/projetos/Adocoes)"+
                ""+
                "[Repositório Git da API REST](http://www.tools.ages.pucrs.br/AGES-Adocoes/Adocoes)"+
                ""+
                "[Repositório Git do Aplicativo Android](http://www.tools.ages.pucrs.br/AGES-Adocoes/adocoes-android)"+
                ""+
                "# **ENGENHARIA DE REQUISITOS**"+
                ""+
                "Além do atendimento dos requisitos acadêmicos, o **Projeto Adoções** tem por objetivo o desenvolvimento de uma solução de incentivo à adoção de menores."+
                ""+
                "A solução será projetada de forma incremental, em entregas realizadas ao final de consecutivos semestres da disciplina de Prática na Agência Experimental de Engenharia de Software II."+
                ""+
                "O principal objetivo é o desenvolvimento de um aplicativo que através de telas e consulta a dados, forneça informações sobre crianças aptas para adoção. O sistema terá acesso para famílias já cadastradas no sistema de adoção."+
                ""+
                "Ao final deste desenvolvimento, todos os artefatos produzidos serão disponibilizados para a Coordenação da AGES, através da ferramenta GitLab, de forma a que a mesma possa repassá-los para o Poder Judiciário do Estado do Rio Grande do Sul."+
                ""+
                "O projeto será desenvolvido em dois semestres 2017/1 e 2017/2, sendo entregue no final do mês de dezembro de 2017."+
                ""+
                "## [Requisitos do Aplicativo Android](./requisitos aplicativo)"+
                ""+
                "[Aqui](./requisitos aplicativo) você conhece os requisitos do aplicativo para dispositivos móveis."+
                ""+
                "## [Requisitos da API REST](./requisitos api)"+
                ""+
                "[Aqui](./requisitos api) você conhece os requisitos da API REST."+
                ""+
                "## Requisitos do Aplicativo HTML Responsivo"+
                ""+
                "A ser especificado."+
                ""+
                "## ~~[Requisitos da Ferramenta de Gestão via Web](./requisitos web)~~ - DEPRECATED"+
                ""+
                "[Aqui](./requisitos web) você conhece os requisitos do aplicativo para dispositivos móveis da ferramenta web de gestão."+
                ""+
                "**No curso do projeto, a equipe do Departamento de Informática do Tribunal de Justiça assumiu o desenvolvimento da ferramenta de gestão.**"+
                ""+
                "# **PROJETO DE USABILIDADE (UX - User eXperience**)"+
                ""+
                "Veja os mockups (rascunhos) e layouts de telas que embasarão o desenvolvimento do aplicativo e da ferramenta web de gestão."+
                ""+
                "## [Aplicativo Android](./mockups-aplicativo)"+
                ""+
                "### Mockups"+
                ""+
                "<p align=\"center\">"+
                "<img src=\"/uploads/219335cfe31b5d288806ad880232e6bd/1-login.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/e4063a0db96f32a472d3a2880555a8e8/2-esqueci-senha.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/3057d76adf1001d90e48d4d2860cd09b/3-termos-uso.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/cc91f2a7bea3fb751d0d9b079b24a072/4-cadastro-simplificado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/7d496d57336b676617d60ad5332ac018/5-menu.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/230daf72e3d373194c2a4dbd73404992/6-criterio-ordenacao.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/44eaa500e2735214e4a9ead2080c8613/7-detalhes-nao-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/a44acf6dd478c3a1e94b6b32e2331179/8-detalhes-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/61ac2b4e9df5d072915fe2ffb8cefa43/9-confirmar-adocao-apadrinhamento.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/748cea2088c348d5a07eb5fe2ebe4433/10-lista-vertical-nao-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/c13f45eae926e8119259f2f852e1426b/11-lista-vertical-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/d2b7f8e0a087f30cb8e9cc766d360089/12-lista-horizontal-nao-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/24bd9a7103cb661d62c6444cfa31adf4/13-lista-horizontal-autenticado.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/0b9d71af055e3d5f0798834753cd1d11/14-cadastro-completo-dados-pessoais.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/f887a0c135a2b21c9d738768563d60a7/15-cadastro-completo-contatos.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/8c26bc7890567a4b3a31e94b8ea0e2c6/16-cadastro-completo-estado-civil.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/d4aa8b2453ad400b8c2be3945afd8a15/17-cadastro-completo-comprovantes.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/f12802e5ebf760a1830c340047ce0261/18-conteudo-institucional-indice.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/150645fc99f4a794d6363fc6b7831e77/19-conteudo-institucional-pagina.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/> "+
                "<img src=\"/uploads/9a9d0832ae7a076257b9465cfd791130/20-Sobre.png?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "</p>"+
                ""+
                "### [Layouts de Telas (BEPiB](./layouts)"+
                ""+
                "Veja os [layouts das telas](./layouts) do aplicativo para dispositivos móveis que já foram elaborados pelo BEPiD. Os layouts das telas serão elaborados com apoio de outras equipes. Aguardem novidades!"+
                ""+
                "### Layouts de Telas (TJ-RS)"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "## [Aplicativo HTML Responsivo](./mockups-web)"+
                ""+
                "### Mockups"+
                ""+
                "<p align=\"center\">"+
                "<img src=\"/uploads/9e3770bd6e43a00190285f5132f77675/0001.jpg\"?raw=true\" alt=\"0001\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/b1b4d82ec7458bd1c5c8844ddd18db9c/0002.jpg\"?raw=true\" alt=\"0002\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/7098c9e595b3c241a1c82aa26b4c9f35/0003.jpg\"?raw=true\" alt=\"0003\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/f78d651e27933c13c2cdabe51968d71d/0004.jpg\"?raw=true\" alt=\"0004\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/97bef322b0e1912a81af8321b3827de6/0005.jpg\"?raw=true\" alt=\"0005\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/041f1603c19f35d145b805e3a9e3c351/0006.jpg\"?raw=true\" alt=\"0006\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/0c31caa2a86ad9ff0b8f682ad2329c3d/0007.jpg\"?raw=true\" alt=\"0007\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/14c78d6227e1b54f2aeb6eaff83cba38/0008.jpg\"?raw=true\" alt=\"0008\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/7d64cdf52ec697d01a93881983663958/0009.jpg\"?raw=true\" alt=\"0009\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/6841627746b4d527d07b71d71c151096/0010.jpg\"?raw=true\" alt=\"0010\" height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "</p>"+
                ""+
                "### Layouts de Telas (TJ-RS)"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "## ~~[Ferramenta de Gestão Web](./mockups-web-adm)~~ - DEPRECATED"+
                ""+
                "<p align=\"center\">"+
                "<img src=\"/uploads/3a38abcb748725a16ff40587dc6ba566/001.jpg\"?raw=true\"  alt=\"001\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/f9dfd89c279646bfb57e0d1d501cfcd9/002.jpg\"?raw=true\"  alt=\"002\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/083a9df2fcb9beeba6cee47ad9fdd389/003.jpg\"?raw=true\"  alt=\"003\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/93685d565b345a2b111f6b8a91692306/004.jpg\"?raw=true\"  alt=\"004\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/93ad8938cd363e6e7d4286bceac1d846/005.jpg\"?raw=true\"  alt=\"005\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/fef4f790aee6145bcd118867b6790cdd/006.jpg\"?raw=true\"  alt=\"006\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/b10f927f9866354b5bef284b34f9003d/007.jpg\"?raw=true\"  alt=\"007\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/4398e61a9eb362757e693ee5b4e569cf/008.jpg\"?raw=true\"  alt=\"008\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "<img src=\"/uploads/fd6fa8a9638cc0bfbec21039399f3691/009.jpg\"?raw=true\"  alt=\"009\"  height=70px\" style=\"border-width: 1px; border-color: Black;\"/>"+
                "</p>"+
                ""+
                "# **ANÁLISE**"+
                ""+
                "## [Modelagem de Dados](./modelagem)"+
                ""+
                "O Projeto Adoções adotará o sistema de gerência de bancos de dados não-relacional MongoDB. Para tanto, foi realizada a [modelagem conceitual e lógica dos dados](./modelagem)."+
                ""+
                "# **IMPLEMENTAÇÃO E TESTES UNITÁRIOS**"+
                ""+
                "## API REST"+
                ""+
                "### Ambiente de Desenvolvimento"+
                ""+
                "- Para configurar o ambiente de desenvolvimento local é necessário instalar:"+
                "  - [Node.js](https://nodejs.org/en/)"+
                "  - [Mongo DB](./Mongo-DB)"+
                "  - RoboMongo ou outro cliente para o MongoDB"+
                "- Após instalar o MongoDB, abrir o [script para popular MongoDB](http://www.tools.ages.pucrs.br/AGES-Adocoes/Adocoes/blob/d8e88d26c9dc9ea9d83b82c3e23da3222844e632/api/test/inicializacaoMongo.mongo) e inserir no DB os seguintes objetos iniciais:"+
                "  - \"testCliente\": inserir na collection \"clientes\": cadastra uma app que vai utilizar a API de Adoções."+
                "  - \"testUsuario\": inserir ba collection \"usuarios\": cadastra o primeiro usuário administrador."+
                "  - Eles são necessários para fazer o \"login\" na API via OAuth2."+
                "- Sugere-se utilizar o [Postman](https://www.getpostman.com/) para testar a API REST."+
                "  - No repositório já existem arquivos criados com o [environment](http://www.tools.ages.pucrs.br/AGES-Adocoes/Adocoes/blob/d8e88d26c9dc9ea9d83b82c3e23da3222844e632/postman/adocoes.postman_environment.json) e a [collection de endpoints](http://www.tools.ages.pucrs.br/AGES-Adocoes/Adocoes/blob/d8e88d26c9dc9ea9d83b82c3e23da3222844e632/postman/adocoes.postman_collection.json) da API Adoções."+
                "- Sugere-se utilizar o [Visual Studio Code - VS Code](https://code.visualstudio.com/) como IDE."+
                "  - O repositório já tem todos os arquivos de configuração do VS Code para build, execução em develop, homo e prod, bem como depuração passo a passo."+
                ""+
                "#### [Getting Started](./getting-started)"+
                ""+
                "[Aqui](./getting-started) você encontra tudo que vai precisar para o setup inicial pra trabalhar no projeto."+
                ""+
                "### Testes Unitários"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "### Integração Contínua"+
                ""+
                "#### Funcionamento"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "#### Testes Automáticos de Aceitação"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "## Aplicativo Android"+
                ""+
                "### Ambiente de Desenvolvimento"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "### Testes Unitários"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "### Integração Contínua"+
                ""+
                "#### Funcionamento"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "#### Testes Automáticos de Aceitação"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "# **PRODUTOS FINAIS**"+
                ""+
                "## Aplicativo Android"+
                ""+
                "Aqui você terá acesso ao link para download do aplicativo para dispositivos móveis."+
                ""+
                "## API REST"+
                ""+
                "Aqui você terá acesso à documentação da API REST e ao link para download de arquivos para o Postman."+
                ""+
                "# **TECNOLOGIAS, PADRÕES E PROTOCOLOS UTILIZADOS**"+
                ""+
                "## [Node.js](https://nodejs.org/en/)"+
                ""+
                "O [Node.js](https://nodejs.org/en/) foi utilizado para o desenvolvimento da API REST."+
                ""+
                "## [Frameword IATE](./framework) (Interactor - Adapter - Translator - Entity)"+
                ""+
                "Conheça nosso [framework](./framework) de desenvolvimento em Node.js."+
                ""+
                "## [Mongo DB](./Mongo-DB)"+
                ""+
                "O [Mongo DB](https://www.mongodb.com/) é utilizado para armazenar os dados de crianças e adolescentes em condições de adoção e todos os demais dados necessários ao aplicativo."+
                ""+
                "### [Configuração do RoboMongo](./configuracao-do-robomongo)"+
                ""+
                "Aprenda [aqui](./configuracao-do-robomongo) a configurar o RoboMongo para gerenciar seus bancos de dados no MongoDB."+
                ""+
                "## REST"+
                ""+
                "### [Minha primeira API](./minha-primeira-api)"+
                ""+
                "Veja um exemplo de como construir um API REST simples utilizando Node.js: [Minha primeira API](./minha-primeira-api)."+
                ""+
                "### [Como criar uma API REST com Node.js e MongoDB](http://adrianmejia.com/blog/2014/10/01/creating-a-restful-api-tutorial-with-nodejs-and-mongodb/)"+
                ""+
                "Aprenda [nesse artigo](http://adrianmejia.com/blog/2014/10/01/creating-a-restful-api-tutorial-with-nodejs-and-mongodb/) como criar uma API REST com Node.js e MongoDB."+
                ""+
                "## [OAuth2.0](./oauth-2.0)"+
                ""+
                "O acesso à API REST do projeto obedece ao padrão de autorização de acesso [OAuth2.0](./oauth-2.0)."+
                ""+
                "## Android"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "# **FERRAMENTAS DE APOIO**"+
                ""+
                "## Ferramenta de Gerência de Artefatos de Projeto e Versionamento"+
                ""+
                "[GitLab](http://www.gitlab.com) é um sistema de controle de versões baseado no Git, que permite a gerência do código de projetos com equipes distribuídas, com ênfase em velocidade. O Git foi inicialmente projetado e desenvolvido por Linus Torvalds para o desenvolvimento do kernel Linux, mas foi adotado por muitos outros projetos."+
                ""+
                "### [Comandos básicos do GitLab](./comandos-basicos-de-git)"+
                ""+
                "Aprenda os [Comandos básicos](./comandos-basicos-de-git) para poder trabalhar no GitLab."+
                ""+
                "## Ferramenta de Comunicação Integrada"+
                ""+
                "[Slack](http://slack.com) é uma ferramenta de comunicação integrada para times de desenvolvimento."+
                ""+
                "### [Integração do GitLab com o Slack](./integracao-com-slack)"+
                ""+
                "Veja [aqui](./integracao-com-slack) como integrar o GitLab ao Slack."+
                ""+
                "## Kanban do Projeto"+
                ""+
                "O Projeto Adoções é acompanhado de perto através de um [kanban](http://www.kanban.ages.pucrs.br/boards/projetos/Adocoes) integrado ao GitLab."+
                ""+
                "## Ferramenta de Prototipação de Telas"+
                ""+
                "Para construção dos mockups das telas foi utilizada a ferramenta [Wireframe.cc](https://wireframe.cc/)."+
                ""+
                "## Ferramenta de Modelagem"+
                ""+
                "[Astah](http://astah.net/)"+
                ""+
                "## Ambientes de Desenvolvimento"+
                ""+
                "### Para a API REST"+
                ""+
                "[Visual Studio Code - VS Code](https://code.visualstudio.com/)"+
                ""+
                "### Para o aplicativo Android"+
                ""+
                "[Android Studio](https://developer.android.com/studio/index.html?hl=pt-br)"+
                ""+
                "## Ferramentas e Frameworks de Teste"+
                ""+
                "### Para a API REST"+
                "  "+
                "[Mocha e Expect](./teste:-mocha-e-expect.js)"+
                "[Postman](https://www.getpostman.com/)"+
                ""+
                "### Para o aplicativo Android"+
                ""+
                "Aguardem novidades!!!"+
                ""+
                "## Frameworks de Integração Contínua"+
                ""+
                "[Jenkins](https://jenkins.io/)";


        //Aqui deve ser chamado o conteudo html com o texto de termos de uso:
//        wv.loadData(termosHTML, "text/html", null);



    }
}
