import random
import shutil
import names
import os

print("Criandos arquivos de dados...")

clients = [names.get_full_name() for name in range(500)]

clubes = [
    "Clube das Rosas",
    "Clube dos Cravos",
    "Clube das Suculentas",
    "Clube das Orquideas",
    "Clube das Margaridas",
    "Clube das Tulipas",
    "Clube das Lotus",
    "Clube das Begonias",
    "Clube das Hortensias",
    "Clube das Azaleias",
]

flores = [
    ["Rosa", 20.50, "Vermelha"],
    ["Cravo", 15.50, "Rosa"],
    ["Suculenta", 3.50, "Verde"],
    ["Orquidea", 45.50, "Rosa"],
    ["Margarida", 12.50, "Amarela"],
    ["Tulipa", 2.50, "Vermelha"],
    ["Lotus", 3.50, "Branca"],
    ["Begonia", 4.50, "Rosa"],
    ["Hortensia", 17.50, "Azul"],
    ["Azaleia", 21.50, "Vermelha"],
]

clubsAssociations_csv = "name,flower"
clubsAssociations_csv += "\nClube das Rosas,Rosa"
clubsAssociations_csv += "\nClube das Cravo,Cravo"
clubsAssociations_csv += "\nClube das Suculenta,Suculenta"
clubsAssociations_csv += "\nClube das Orquidea,Orquidea"
clubsAssociations_csv += "\nClube das Margarida,Margarida"
clubsAssociations_csv += "\nClube das Tulipa,Tulipa"
clubsAssociations_csv += "\nClube das Lotus,Lotus"
clubsAssociations_csv += "\nClube das Begonia,Begonia"
clubsAssociations_csv += "\nClube das Hortensia,Hortensia"
clubsAssociations_csv += "\nClube das Azaleia,Azaleia"

clients_csv = "name,age,sex"
for client in clients:
    clients_csv += f'\n{client},{random.randint(18, 80)},{random.choice(["M", "F"])}'

clubes_csv = "name"
for clube in clubes:
    clubes_csv += f'\n{clube}'

flores_csv = "name,price,color"
for flor in flores:
    flores_csv += f'\n{flor[0]},{flor[1]},{flor[2]}'


associations_csv = "client,club"
for client in clients:
    if random.random() < 0.1:
        associations_csv += f'\n{client},{random.choice(clubes)}'

purchases_csv = "client,flower"
for fan in associations_csv.splitlines()[1:]:
    purchases_csv += f'\n{fan.split(",")[0]},{random.choice(flores)[0]}'
for client in clients:
    if random.random() < 0.3:
        purchases_csv += f'\n{client},{random.choice(flores)}'

# Verify is dir file exists
if not os.path.exists("./import"):
    os.mkdir("./import")
else:
    shutil.rmtree('./import')
    os.mkdir("./import")


with open("./import/clients.csv", "w") as f:
    f.write(clients_csv)

with open("./import/clubes.csv", "w") as f:
    f.write(clubes_csv)

with open("./import/flores.csv", "w") as f:
    f.write(flores_csv)

with open("./import/associations.csv", "w") as f:
    f.write(associations_csv)

with open("./import/purchases.csv", "w") as f:
    f.write(purchases_csv)

with open("./import/clubsAssociations.csv", "w") as f:
    f.write(clubsAssociations_csv)


print("finalizado!")