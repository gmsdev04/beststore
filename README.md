<h1>Iniciativa BestStore</h1>
<p>
Este projeto consiste na criação de um sistema de gestão de vendas e produtos gratuito com o intuito da obtenção de dados de valores de produtos e localização dos mesmos. </p>

<h2>Arquitetura</h2>
A arquitetura do sistema é baseada em serviços, mais especificamente RestFull. O sistema deve ser composto por modulos apartados sendo eles :

<h3>Modulos</h3>
<p>O sitema será dividido em dois tipos, projetos de dominios e STS (Security Token Service). Para cada dominio da aplicação
deverá ser desenvolvido um projeto apartado que manterá os micro serviços operantes de forma granular, o STS ficará responsável pelo gerenciamento dos tokens de consumo dos dominios publicaodos.</p>

<h3>Dominios</h3>

<ul>
  <li>pessoas</li>
  <li>lojas</li>
  <li>produtos</li>
  <li>acessos</li>
  <li>vendas</li>
  <li>tokens</li>
</ul>

<h3>Rotas</h3>
<ul>
  <li>pessoas/v1/acessos</li>
  <li>pessoas/v1/</li>
  <li>lojas/v1/produtos</li>
  <li>lojas/v1/vendas
  <li>lojas/v1/</li>
  <li>sts/v1/tokens</li>
</ul>


<h2>Tecnologias</h2>
<table>
  <tr>
    <td>Função</td>
    <td>Tecnologia</td>
  </tr>
  <tr>
    <td>Sistema operacional servidores</td>
    <td>Linux</td>
  </tr>
  <tr>
    <td>Cache</td>
    <td>Redis</td>
  </tr>
  <tr>
    <td>Banco de dados</td>
    <td>MongoDB</td>
  </tr>
    <td>Container</td>
    <td>Docker</td>
  </tr>
   </tr>
    <td>Container Orchestor</td>
    <td>Kubernetes</td>
  </tr>
  </tr>
    <td>Cloud</td>
    <td>Azure</td>
  </tr
    </tr>
    <td>Linguagem principal das APIs</td>
    <td>.NET Core 2.2+ </td>
  </tr>
</table>




@Author gmsdev04
