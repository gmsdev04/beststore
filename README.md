<h1>Iniciativa BestStore</h1>
<p>
Este projeto consiste na criação de um sistema de gestão de vendas e produtos gratuito com o intuito da obtenção de dados de valores de produtos e localização dos mesmos. Obter uma rede social de busca de produtos e divulgações como um jornal virtual </p>

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
  <li>caixas</li>
</ul>

<h3>Rotas</h3>
<ul>
  <li>/apis/v1/lojas (POST)</li> 
  <li>/apis/v1/lojas/{id_loja} (GET, PATCH)</li>
  <li>/apis/v1/lojas/{id_loja}/produtos (GET, POST)</li>
  <li>/apis/v1/lojas/{id_loja}/produtos/{id_produto} (GET, PATCH)</li>
  <li>/apis/v1/tokens (POST)</li>
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
    <td>CassandraDB</td>
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
    <td>AWS</td>
  </tr
    </tr>
    <td>Linguagem principal das APIs</td>
    <td>JAVA 8</td>
  </tr>
</table>

<h3>Release Description</h3>
 - Ver em milestone da Release 1.0.0
 
<h3>Scripts Banco de dados</h3>

```sql
CREATE KEYSPACE beststore WITH replication = {'class': 'NetworkTopologyStrategy', 'datacenter1': '3'}  AND durable_writes = true;

CREATE TYPE beststore.atualizacao (
    id uuid,
    titulo text,
    descricao text,
    atualizador text,
    alteracoes map<text, text>,
    instante_atualizacao timestamp
);



CREATE TYPE beststore.email (
    id uuid,
    endereco text,
    principal boolean,
    instante_criacao timestamp,
    ultima_atualizacao timestamp
);



CREATE TYPE beststore.endereco (
    id uuid,
    logradouro text,
    pais text,
    estado text,
    cidade text,
    municipio text,
    uf text,
    complemento text,
    numero int,
    cep text,
    instante_criacao timestamp,
    ultima_atualizacao timestamp
);

CREATE TYPE beststore.telefone (
    id uuid,
    numero int,
    ddd int,
    ddi int,
    principal boolean,
    tipo int,
    instante_criacao timestamp,
    ultima_atualizacao timestamp
);



CREATE TABLE beststore.applications (
    client_id uuid PRIMARY KEY,
    ativo boolean,
    client_secret uuid,
    instante_criacao timestamp
) WITH bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';


CREATE TABLE beststore.produtos (
    idloja uuid,
    idproduto uuid,
    ativo boolean,
    atualizacoes set<frozen<atualizacao>>,
    categoria uuid,
    data_fabricacao date,
    data_validade date,
    descricao text,
    fabricante uuid,
    imagem blob,
    instante_criacao timestamp,
    nome text,
    quantidade int,
    valor double,
    PRIMARY KEY (idloja, idproduto)
) WITH CLUSTERING ORDER BY (idproduto ASC)
    AND bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';


CREATE TABLE beststore.lojas (
    id uuid PRIMARY KEY,
    emails set<frozen<email>>,
    endereco endereco,
    instante_criacao timestamp,
    nome text,
    telefones set<frozen<telefone>>,
    ultima_atualizacao timestamp
) WITH bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';



```

@Author gmsdev04
