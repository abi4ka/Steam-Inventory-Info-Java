# Steam Inventory Parser

A simple Java program to retrieve a Steam user's inventory items using their SteamID64.  
By default, the program fetches items from **CS2 (app_id = 730)**, but you can change `APP_ID` to target a different game.

> **Note:** This program works **without a Steam API Key**.

## Features

- Fetches inventory items for a given SteamID64.
- Displays item name, type, and marketable status.
- No API key required.

## Dependencies

- Java 8 or higher
- [JSON library](https://mvnrepository.com/artifact/org.json/json) (`org.json:json`)
- Internet connection

## Usage

1. Clone or download the repository.
2. Make sure the `json` library is added to your project dependencies.
3. Compile and run the program:
   ```bash
   javac -cp json.jar SteamInventoryInfo.java
   java -cp .:json.jar org.abik.steam_inventory_parcer.SteamInventoryInfo
    ````

4. Enter the SteamID64 when prompted.
5. View the list of inventory items in the console.

## Customization

* To fetch inventory from another game, change the `APP_ID` constant:

  ```java
  private static final int APP_ID = 730; // Replace 730 with your desired game ID
  ```

## Example Output

```
Inventory items:
---
Name: Music Kit | Daniel Sadowski, The 8-Bit Kit
Type: High Grade Music Kit
Marketable: Yes
---
Name: M4A4 | Dark Blossom
Type: Industrial Grade Rifle
Marketable: Yes
---
```

---

## Author

Created by [abik](https://github.com/abi4ka)
