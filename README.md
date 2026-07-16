# Logic Circuits

Logic Circuits adds compact, ready-to-use digital logic components to Minecraft's redstone system. Build combinational circuits, add binary values, and store a single bit without recreating every gate from dust, torches, and repeaters.

## Compatibility

| Requirement | Version |
| --- | --- |
| Minecraft | 1.21.1 |
| Mod loader | NeoForge 21.1.234 or newer |
| Java (for development) | 21 |

The mod is server-compatible and uses only NeoForge and Minecraft as dependencies.

## Included components

All components are available in the **Redstone Blocks** creative tab. Gates behave like redstone diodes: place them on a solid surface, use their facing direction to identify the output, and feed their inputs from the sides unless stated otherwise. Their update delay is two redstone ticks.

### Logic gates

| Component | Inputs | Output is powered when... |
| --- | --- | --- |
| NOT Gate | One | the input is off |
| OR Gate | Two | either input is on |
| AND Gate | Two | both inputs are on |
| NOR Gate | Two | neither input is on |
| NAND Gate | Two | at least one input is off |
| XOR Gate | Two | exactly one input is on |
| XNOR Gate | Two | both inputs have the same state |

### Arithmetic and memory

| Component | Purpose | Outputs |
| --- | --- | --- |
| Half Adder | Adds two one-bit inputs | Sum and carry |
| Full Adder | Adds two one-bit inputs plus carry-in | Sum and carry-out |
| SR Latch | Stores one bit using set/reset inputs | Complementary state across its two halves |

The Half Adder, Full Adder, and SR Latch occupy two adjacent blocks when placed. Make sure there is clear space beside the component before placing it.

## Installation

1. Install a compatible NeoForge 1.21.1 profile.
2. Download the `logicc-1.0.0.jar` release file.
3. Put the JAR in your Minecraft instance's `mods` folder.
4. Launch Minecraft with NeoForge.

For multiplayer, install the same mod version on both the client and server.

## Building from source

Clone the repository, then run the Gradle wrapper from its root:

```powershell
.\gradlew.bat build
```

The built mod JAR is written to `build/libs/`. Other useful development tasks:

```powershell
# Launch a development client
.\gradlew.bat runClient

# Regenerate block models, block states, and loot tables
.\gradlew.bat runData
```

Use JDK 21. The project includes the Gradle wrapper, so a separate Gradle installation is not required.

## Project layout

```text
src/main/java/dev/chyken/
|-- block/       Component implementations and registrations
|-- data/        Data-generation providers
|-- item/        Block-item registrations
`-- logicc/      Mod entry point and configuration
src/main/resources/      Textures, models, and translations
src/generated/resources/ Generated block states, item models, and loot tables
```

## License

Logic Circuits is released under the [MIT License](LICENSE.txt).
