# Detalhes da base de dados de floricultura em Neo4j
## Entidades:
    - Clientes (Nome, Idade, Sexo)
    - Clubes (Nome)
    - Flores (Nome, Preço, Cor)

## Relacionamentos:
    - (Cliente)-[Compra]->(Flor)
    - (Clube)-[Dedica-se]->(Flor)
    - (Cliente)-[Associou-se]->(Clube)
