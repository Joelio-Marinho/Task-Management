# Task-Management

## JSON para teste via Postman
### Departamento
Criar Departamento
POST : http://localhost:8080/departamento
{
	"titulo":"teste"
}

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



