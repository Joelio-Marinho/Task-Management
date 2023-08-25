# Task-Management

## JSON para teste via Postman
### Departamento
Criar Departamento
POST : http://localhost:8080/departamentos
{
	"titulo":"teste"
}
 Listar departamento e quantidade de pessoas e tarefas
GET : http://localhost:8080/departamentos

### Pessoa
Criar pessoa
POST : http://localhost:8080/pessoas
{
	"nome": "joelio",
	"departamento":{
		"id": 1
	}	
}

Atualizar
PUT : http://localhost:8080/pessoas/3
{
	"nome": "teste",
	"departamento":{
		"id": 1,
		"titulo":"teste"
	}
}

Deletar
DELETE : http://localhost:8080/pessoas/5

Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas
GET : http://localhost:8080/pessoas

Buscar pessoas por nome e período, retorna média de horas gastas por tarefa
GET : http://localhost:8080/pessoas/gastos?nomePessoa=NOME_PESSOA&prazoInicial=YYYY-MM-DD&prazoFinal=YYYY-MM-DD

### Tarefa
Criar tarefa
POST : http://localhost:8080/tarefas
{
	"titulo":"teste02",
	"descricao": "teste de bancada",
	"prazo":"21/02/2021",
	"departamento":{
		"id":1
	},
	"duracao":12,
   "pessoa":{
     "id":1
   }
}

Alocar pessoa a Tarefa
PUT : http://localhost:8080/tarefas/alocar/5
{
	"id":1,
	"nome": "teste",
	"departamento":{
		"id": 1
	}
}

Finalizar tarefa
PUT : http://localhost:8080/tarefas/finalizar/5

Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos
GET : http://localhost:8080/tarefas/pendentes




