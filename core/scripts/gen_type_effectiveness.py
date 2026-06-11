#!/bin/python

from bs4 import BeautifulSoup
import re

# Vlož sem HTML tabulku
with open("gen_type_effectiveness_table.html", "r", encoding="utf-8") as f:
    html = f.read()

soup = BeautifulSoup(html, "html.parser")

# Mapování CSS class -> enum
effectiveness_map = {
    "veff": "Effectiveness.WEAK",
    "nteff": "Effectiveness.RESISTANT",
    "neff": "Effectiveness.IMMUNE",
}

# Výchozí hodnota
DEFAULT = "Effectiveness.NONE"

# Najdi všechny řádky
rows = soup.find_all("tr")

# Hlavička typů
header_row = rows[0]
header_cells = header_row.find_all("th")[1:]

def extract_type(cell):
    div = cell.find("div")
    if not div:
        return None

    classes = div.get("class", [])

    for c in classes:
        if c.startswith("type-icon-"):
            return c.replace("type-icon-", "").upper()

    return None

defender_types = [extract_type(cell) for cell in header_cells]

max_len = max(
    max(len(f"Type.{t}") for t in defender_types if t),
    max(len(f"Type.{extract_type(r.find(['th', 'td']))}")
        for r in rows[1:]
        if extract_type(r.find(['th', 'td'])) is not None)
)

padding_len = 1

# Data řádky
output = []

for row in rows[1:]:
    first = row.find(["th", "td"])

    attacker = extract_type(first)

    # konec tabulky
    if attacker is None:
        break

    cells = row.find_all("td")

    for defender, cell in zip(defender_types, cells):
        classes = cell.get("class", [])

        effect = DEFAULT

        for cls in classes:
            if cls in effectiveness_map:
                effect = effectiveness_map[cls]
                break

        att_str = f"ElementType.{attacker},"
        def_str = f"ElementType.{defender}"
        line = (
            f"table[getIndex({att_str:<{max_len + 1 + padding_len}} "
            f"{def_str:<{max_len + padding_len}})] = {effect};"
        )

        output.append(line)

# Výpis
print("\n".join(output))
