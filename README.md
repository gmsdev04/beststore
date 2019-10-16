<h1>Iniciativa BestStore</h1>
<p>
Este projeto consiste na criação de um sistema de gestão de vendas e produtos gratuito com o intuito da obtenção de dados de valores de produtos e localização dos mesmos. </p>

<h2>Arquitetura</h2>
A arquitetura do sistema é baseada em serviços, mais especificamente RestFull. O sistema deve ser composto por modulos apartados sendo eles :

<h3>Modulos</h3>
<strong>Os modulos abaixo devem ser granulares e não devem possuir interdependencias e estado local para viabilizar a escalabilidade horizontal</strong>

</br>
<table>
  <tr>
    <td>Modulo</td>
    <td>Descrição</td>
  </tr>
  <tr>
    <td>Administrativo</td>
    <td>Central de administração de produtos, lojas e caixas cadastrados.</td>
  </tr>
  <tr>
    <td>Produção</td>
    <td>Central de processamento de dados transacionais (Vendas).</td>
  </tr>
  <tr>
    <td>Analytics</td>
    <td>Central de processamento de dados de análise em tempo real / Indicadores.</td>
  </tr>
</table>

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
