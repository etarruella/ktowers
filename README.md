
# KTowers

KTowers is a Minecraft plugin based on the Bukkit API that introduces a strategic team-based minigame. In KTowers, two teams compete to capture each other's tower while defending their own. Players also battle to control resource-generating towers located in the center of the battlefield. The game supports multiple gameplay modes, offering both casual and competitive experiences. 

> [!NOTE]
> KTowers is inspired by an existing minigame called TheTowers, and this plugin automates its functionality, streamlining gameplay and enhancing the overall experience for players. With KTowers, the complexities of managing game mechanics are simplified, allowing players to focus on strategy and teamwork.

> [!IMPORTANT]
> This plugin is currently under development and does not yet have a stable version available for public use.

## Features

- 📡 **Event-Driven API**: The plugin is built around an event-driven system, making it highly customizable and extensible for developers.
- 💾 **Connection to MongoDB and MySQL**: Store persistent data such as player stats, game results, and leaderboards in MongoDB or MySQL.
- 🌍 **Support for Multiple Maps**: Easily configure and switch between different arenas and maps for varied gameplay experiences.
- ✏️ **Editor Mode**: The built-in editor mode allows admins to create, edit, and test arenas directly within the game.
- 🛠️ **Simple Configurations**: Easy-to-use configuration files allow for tweaking game settings, enabling quick and simple setup for any server.
- ⚔️ **Multiple Gameplay Systems**: Different game modes, including tie-breakers, arena matches, deathmatch, and last team standing. Customize victory conditions for unique gaming experiences.

## Installation

1. **Build the plugin using Gradle**:

   - Ensure you have [Gradle](https://gradle.org/install/) installed on your machine.
   - Clone this repository and build the project:
     ```bash
     git clone https://github.com/etarruella/KTowers.git
     cd KTowers
     
     ./gradlew build
     ```
   - After building, the plugin JAR file can be found in the `build/libs` directory.

2. **Install the Plugin**:
   - Locate your Minecraft server directory and navigate to the `plugins` folder.
   - Copy the built JAR file from `build/libs` and paste it into the `plugins` folder of your server.

3. **Configure the Plugin**:
   - Start your server to generate the necessary configuration files.
   - After the initial startup, stop the server and locate the newly created `KTowers` folder in the `plugins` directory.
   - Open the configuration files (e.g., `config.yml`, `ktowers.yml`) and customize the settings to suit your server’s needs.

4. **Set Up Your Database**:
   - If using **MongoDB** or **MySQL**, ensure the database server is running and accessible.
   - Update the database connection settings in the `config.yml` file.

5. **Load Your Maps**:
   - Use the built-in editor mode to create or modify maps.
   - Save your zip map in the `KTowers/maps` folder.
   - Save your map settings in the `KTowers/maps/cfg` folder. The name of the config must be the same as the zip map file name.

6. **Start the Server**:
   - Run your Minecraft server again to load the KTowers plugin.
   - Use the `/ktowers` command to access the plugin's main menu and configure gameplay settings.

## Commands

- `/ktowers start`: Start a new game in the selected arena.
- `/ktowers stop`: Stop the current game.
- `/ktowers join <team>`: Join a specified team.
- `/ktowers leave`: Leave the current game.
- `/ktowers stats <player>`: View stats for a specified player.
- `/ktowers config`: Open the configuration menu to adjust game settings.

## Permissions

- `ktowers.admin`: Access to all administrative commands and settings.
- `ktowers.player`: Basic permissions to join games and view stats.
- `ktowers.edit`: Permissions to use the editor mode for arena creation and modification.
  
## Contribution

We welcome contributions! If you would like to contribute to **KTowers**, please follow these steps:

1. **Fork the repository.**
2. **Create a new branch for your feature or bug fix.** Please follow the GitFlow branching model:
   - Use the `feature/` prefix for new features (e.g., `feature/add-new-arena`).
   - Use the `bugfix/` prefix for bug fixes (e.g., `bugfix/fix-typo`).
   - Use the `hotfix/` prefix for urgent fixes (e.g., `hotfix/urgent-fix`).
   - For more information on GitFlow, check out the [GitFlow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).
3. **Make your changes and commit them.** Ensure your commit messages are descriptive and follow the format: `type(scope): description`. For guidelines on writing commit messages, see [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/).
4. **Push your branch to your forked repository.**
5. **Create a pull request** describing your changes and referencing any relevant issues.

## License

**KTowers** is unlicensed. See the [LICENSE](LICENSE) file for more details.

## Support

For support or questions, please open an issue on the GitHub repository.
