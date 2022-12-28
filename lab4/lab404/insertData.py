inserts = [
'''LOAD CSV WITH HEADERS
FROM "file:///clients.csv" AS Row
MERGE (client:Client {name: Row.name, age: Row.age, sex: Row.sex})'''
,
'''LOAD CSV WITH HEADERS
FROM "file:///clubes.csv" AS Row
MERGE (club:Club {name: Row.name})'''
,
'''LOAD CSV WITH HEADERS
FROM "file:///flores.csv" AS Row
MERGE (flower:Flower {name: Row.name, price: Row.price, color: Row.color})'''
,
'''LOAD CSV WITH HEADERS
FROM "file:///associations.csv" AS Row
MATCH (client:Client {name: Row.name}),(club:Club {name: Row.club})
CREATE (client)-[:JOIN]->(club)'''
,
'''LOAD CSV WITH HEADERS
FROM "file:///clubsAssociations.csv" AS Row
MATCH (club:Club {name: Row.name}),(flower:Flower {name: Row.name})
CREATE (club)-[:JOIN]->(flower)'''
,
'''LOAD CSV WITH HEADERS
FROM "file:///purchases.csv" AS Row
MATCH (client:Client {name: Row.name}),(flower:Flower {name: Row.name})
CREATE (client)-[:PURCHASE]->(flower)'''
]

from neo4j import GraphDatabase

driver = GraphDatabase.driver("bolt://localhost:7687")

print('Inserindo dados...')
for query in inserts:
    with driver.session(database="neo4j") as session:
        output = session.execute_write(lambda tx: tx.run(query).data())

driver.close()
print('Finalizado!')
