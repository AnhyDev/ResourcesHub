# AnhyLingo  



**AnhyLingo** is a plugin for Minecraft servers designed to create a multilingual interface. It allows players to receive system messages, messages from other plugins, as well as see names and descriptions of items in their chosen language. Tested on servers with `Spigot`, `Paper`, `Purpur` cores version `1.20.2`. The latest version of the `ProtocolLib` plugin is required for operation.

### Important Installation Note for AnhyLingo

To fully leverage the capabilities of AnhyLingo, it is essential to install the [AnhyLibAPI](https://github.com/AnhyDev/ResourcesHub/tree/main/AnhyLibAPI/releases/v1.3.3) library on your server. This integration is a critical component of AnhyLingo, providing the necessary infrastructure for its optimal performance. It's important to note that AnhyLibAPI is designed to be lightweight and efficient, ensuring that it does not impose any additional load on your server's performance. By installing AnhyLibAPI alongside AnhyLingo, you unlock the full potential of both, enhancing your server's functionality without compromising its efficiency.

A common limitation of many Minecraft plugins is their confinement to a single language, as defined in the plugin's configuration. Few plugins offer true multilingual support, often relying solely on the language of the Minecraft client.

**AnhyLingo** stands out in this landscape. It not only utilizes the client's language settings but also empowers players to choose multiple languages in a specified order for finding appropriate translations. Furthermore, **AnhyLingo** makes almost any in-game chat message multilingual by using language keys. These keys are replaced with corresponding translations during the server-to-client packet transmission, ensuring that messages are delivered in the player's chosen language.

Moreover, **AnhyLingo** brings multilingual capabilities to scenarios where it seemed improbable. For instance, when creating quests with a quest plugin, even if the plugin itself is multilingual, the quest messages are typically restricted to one language. **AnhyLingo** innovatively addresses this by allowing creators to use keys from custom language files, enabling the quests to be multilingual. This versatility greatly enhances the gaming experience, making it more inclusive and accessible to a diverse, global player base.

In conclusion, **AnhyLingo** transcends the typical boundaries of language in Minecraft, offering a versatile and inclusive solution that enriches the gaming experience for a diverse, global player base.


## Functionality of the plugin

1.  #### Multilingualism:

    ##### (all players):

    *   Ability to select desired languages using the command.
    *   Automatically use Minecraft client language settings if the player has not set their preferred languages.
    *   Display of text in the game chat and on the action bar in different languages.
    *   Automatic translation of custom items into the selected language during interaction.
2.  #### Working with game items:

    ##### (players with admin permissions):

    *   Ability to create multilingual items.

1.  #### Working with NBT tags:

    ##### (players with admin permissions):

    *   View available NBT item tags.
    *   Installing, changing and deleting NBT tags in items.

1.  #### Work with files:

    ##### (players with admin permissions):

    *   Uploading language files to the plugin folder.
    *   Uploading any files to allowed folderss.
    *   Deleting files from allowed folderss.


### Language Personalization with Commands for All Players:

> Players have the option to select languages for system messages, plugin notifications, and the display of game items that support multiple languages. If the language chosen by the player has been added by the administrators, all relevant informational messages will be displayed in that language. In cases where the player has not selected any specific language, the system will use the language settings of the Minecraft client. If the chosen language is not supported in the localization, the player will receive messages in the default language set in the plugin.

1.  #### Set the desired languages

*   (no permission is required)
*   `/lingo set <langs>`
*   <langs> - can be one or more languages, for example `en` or `en it es`. Plugin will search for the translation in order.

1.  #### Resetting the set language

*   (no permission is required)
*   `/lingo reset`
*   Clears the list of selected languages, the plugin will receive the language from the minecraft client.

1.  #### Get languages

*   (no permission is required)
*   `/lingo get`
*   View selected languages for translation.


## Administrator Commands

##### (with permission anhylingo.*)

> The AnhyLingo plugin for Minecraft servers is equipped with administrative commands to manage multilingualism on the server. These commands are only accessible to players with extended administrative rights and allow for customization and control over the plugin's language options. The administrators can use these tools to set the available languages on the server, modify translations of game elements and the interface, and adjust language settings as per the players' needs. This approach promotes inclusivity and accessibility of the game for players from different countries and ensures a more flexible approach to cultural and linguistic diversity on Minecraft


### Reloading and information commands:

1.  #### Reloading the plugin

*   (with permission anhylingo.reload)
*   Reloads the general configuration and language files configurations
*   `/lingo reload`

1.  #### Information about available item localizations

*   (with permission anhylingo.items.info)
*   Displays a list of "keys" for all items that have a translation in the specified language.
*   `/lingo items list <lang>`

1.  #### Show localization for key

*   (with permission anhylingo.items.info)
*   Shows the name and lore in the specified language for the given key.
*   `/lingo item <lang> <key>`


### Working with NBT tags:

##### (with permission anhylingo.nbt.*)

1.  #### Adding an NBT tag or changing its value

*   (with permission , "anhylingo.nbt.set")
*   `/lingo nbt set <nbt_key> <params...>`
*   Setting the NBT tag nbt_key with parameters params, specifying the data type (e.g., `string:value, int:10`).
*   Examples of value types: `int, double, intarray, string`.

1.  #### View NBT tags

*   (with permission , "anhylingo.nbt.list")
*   `/lingo nbt list`
*   Displays a list of NBT tags for the item currently in the player's hand
*   Examples of value types: `int, double, intarray, string`.

1.  #### Viewing the value of NBT tags

*   (with permission , "anhylingo.nbt.info")
*   `/lingo nbt info <nbt_key>`
*   Shows the value of a specific NBT tag for the item in the player's hand.


### Working with files

##### (with permission anhylingo.file.*)

1.  #### File Uploading

    ##### File YAML AnhyLingo uploading

    *   (with permission anhylingo.file.lingo)
    *   `/lingo fl (flingo) link_to_file folder true|false`:
    *   Uploading language files to subfolders of the **AnhyLingo** plugin directory.
    *   `link_to_file`: Direct link to the file.
    *   `folder`: Folder where the file is uploaded `items|system`.
    *   `true|false`: Parameter to determine the possibility of overwriting the file.
    *   File requirements: valid yml format, file name corresponds to the pattern `xxx_[language_code].yml`.
  
  
    ##### Uploading other files
    *   (with permission anhylingo.file.other)
    *   `/lingo fo (fother) link_to_file directory true|false`:
    *   Uploading any files into the `directory` within the server plugins folder.
    *   Important: Uploading is only possible in the directories defined as allowed in the plugin configuration file (`allowed_directories`).
    *   If a subdirectory within the allowed directory does not exist, it will be automatically created during the execution of the command.
    *   `link_to_file`: Direct link to the file for download.
    *   `directory`: Target directory for the file within the server plugins directories.
    *   `true|false`: Parameter that determines whether overwriting existing files is allowed.

1.  #### Deleting files

    *   #### Deleting a file
    *   (with permission anhylingo.file.other)`/lingo fd (fdel) directory file_name`
    *   Important: Deleting is only possible in the directories defined as allowed in the plugin configuration file (`allowed_del_directories`).
    *   `directory`: File path.
    *   `file_name`: File name

1.  #### File view

    *   #### View folder contents
    *   (with permission anhylingo.file.view)`/lingo dir [directory]`
    *   Displays the contents of the `directory` folder located in the server plugins directory.
    *   If `[directory]` is set to 0, it will show the contents of the `./plugins/` folder.


### Automatic Item Renaming:

   *   Renaming the name and lore of custom items to the player's language during the player's interaction with the item in the inventory.
   *   Automatic setting of the NBT tag with the language during renaming to prevent unnecessary renamings.
   *   The item is not modified in cases where the language of the item matches the language of the player, or when more than one player is looking at the inventory simultaneously.

> For the **AnhyLingo** plugin to track and rename certain items, it is necessary to add a special NBT tag `ItemLingo`. This tag should contain a string value that corresponds to the key in the language file.
> 
> #### Adding an NBT Tag to an Item
> 
>    *  Command for Adding Tag, example:
>         
>         *   `/lingo nbt set ItemLingo string:magic_wand`
>             *   This command adds the NBT tag ItemLingo with the string value `magic_wand` to the item in the player's hand.
>             *   The value `magic_wand` corresponds to the key that must be in the language file.
>
>    *  Using Other Plugins:
>      
>         *   Administrators can also use the functionality of other plugins to add this NBT tag to items.
>         *   It is important that the NBT tag has the name `ItemLingo` and a string value that corresponds to the key from the language file.
> 
> This feature provides the ability to rename certain items on the server according to the player's chosen language, making the gameplay more interactive and convenient for players who use different languages.


### Configuration (config.yml)

*   The comments in the configuration file explain each point:
```yaml
    #
    # Default language to be used in case a translation is not found in the desired language:
    language: en

    # Name of the plugin for display to users in chat:
    plugin_name: AnhyLingo

    # Enable translation of custom items:
    item_lingo: true

    # Enable translation of packets where language keys are found:
    packet_lingo: true

    # Allow viewing of files and folders:
    allow_browsing: true

    # Allow file uploads:
    allow_upload: false

    # Directories where file uploads are allowed:
    allowed_directories:
    - Denizen/scripts
    - AnhyLingo

    # Allow file removal:
    allow_removal: false

    # Directories where file removal is allowed:
    allowed_del_directories:
    - Denizen/scripts
    - AnhyLingo
```

### Directory Structure

*   `items`: Files with translations of names and lore of custom items.
```yaml
    magic_wand:
      name: "&bMagic Wand"
      lore:
        - "&7This is a magic wand."
        - "&7It can perform magical feats."
        - "&7Use it wisely."

    # A copy of the main item with the first line of lore changed
    magic_wand_1:
      copy: magic_wand
      lines:
        1: "&cEnhanced Magic Wand"

    # Another copy with changes in the first and third lines of the lore
    magic_wand_2:
      copy: magic_wand
      lines:
        1: "&dAdvanced Magic Wand"
        3: "&dIt holds immense power."

    eternal_sword:
      name: "&cEternal Sword"
      lore:
        - "&7A legendary sword that never dulls."
        - "&7Said to be forged from a fallen star."
        - "&7Its blade cuts through darkness."
```

*   `system`: Files with translations of system messages and messages from plugins.

```yaml
    # Internal system messages of the ItemLingo plugin
    #
    # 1 General
    lingo_err_not_have_permission: "You do not have permission to use this command."
    lingo_err_command_format: "Command format: "
    #
    # 2 Loading configurations
    lingo_err_folder_is_empty: "The folder is empty or an error occurred while reading it."
    lingo_err_folder_is_notexist: "The folder does not exist or it is not a folder."
    lingo_err_reloading_configuration: "Error reloading configuration."
    lingo_created_default_configuration: "Default configuration created."
    lingo_configuration_reloaded: "Configuration reloaded."
    lingo_language_reloaded: "Language files and configuration reloaded."
```

*   Files must have the name format: "`xxx_[language_code].yml`", for example, "`lingo_en.yml`", files that do not meet the standard are not read.


### API:

#### Developers of other plugins can use AnhyLingo as a library to make their plugins multilingual.

The AnhyLingo API offers a robust solution for developers seeking true multilingualism in their Minecraft plugins. By using this API, system messages within plugins can be made multilingual right at the point of their creation, before being sent to the player. This method of translating keys into the respective languages is far more efficient than renaming keys in already transmitted packets. For developers aiming to achieve authentic multilingual functionality in their plugins, incorporating the AnhyLingo API is a strategic and effective choice.

Use the AnhyLingo plugin in your project as a dependency. Here are the instructions for adding the plugin using Gradle and Maven.

#### Adding Using Gradle

To use AnhyLingo in your Gradle project, add the following lines to your project's `build.gradle` file:


```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencyResolutionManagementdependencies {
    implementation 'com.github.AnhyDev:AnhyLingo:v0.1.2'
}
```

#### Adding Using Maven

To include AnhyLingo in your Maven project, insert these lines into your `pom.xml` file:

```xml
    <repositories>
        repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
```

```xml
    <dependency>
        <groupId>com.github.AnhyDev</groupId>
        <artifactId>AnhyLingo</artifactId>
        <version>v0.1.2</version>
        <scope>provided</scope>
    </dependency>
```

### An example of using the repository

#### Class for Language Management:

```java
package myplugin.lang;

import myplugin.MyPlugin;
import ink.anh.lingo.api.lang.LanguageManager;

public class LangMessage extends LanguageManager {

    private static LangMessage instance = null;
    private static final Object LOCK = new Object();

    private LangMessage(MyPlugin plugin) {
        super(plugin, "lang"); // 'lang' is the name of the folder containing language files
    }

    public static LangMessage getInstance(MyPlugin plugin) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new LangMessage(plugin);
                }
            }
        }
        return instance;
    }
}
```


`lang` is the name of the folder containing language files.

Files must have the name format: `"xxx_[language_code].yml"`, for example, `"name_en.yml"`, files that do not meet the standard are not read.

The contents of the files must conform to the YAML standard, and have the form `key: "Value"`, where "value" is the translation for the given key in this language.

#### Initialization in the Plugin Main Class:


```java
import myplugin.lang.LangMessage;
import ink.anh.lingo.api.lang.LanguageManager;

public class MyPlugin extends JavaPlugin {

    private static MyPlugin instance;
    private LanguageManager languageManager;

    @Override
    public void onEnable() {
        instance = this;
        languageManager = LangMessage.getInstance(this);
    }

    public static MyPlugin getInstance() {
        return instance;
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }
}
```

#### Example Use in Other Classes:

```java
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import ink.anh.lingo.api.Translator;
import ink.anh.lingo.messages.MessageType;
import ink.anh.lingo.messages.Messenger;
import ink.anh.lingo.utils.LangUtils;

public class OtherClass {

    private MyPlugin plugin;
    private LanguageManager languageManager;

    public OtherClass(MyPlugin plugin) {
        this.plugin = plugin;
        this.languageManager = plugin.getLanguageManager();
    }

    public static String[] checkPlayerPermissions(CommandSender sender, String permission) {
        if (sender instanceof ConsoleCommandSender) {
            return null;
        }

        String[] langs = new String[] {null};
        if (sender instanceof Player) {
            Player player = (Player) sender;
            langs = LangUtils.getPlayerLanguage(player);
            if (!player.hasPermission(permission)) {
                sendMessage(sender, Translator.translateKeyWorld("shop_err_not_have_permission", langs, MyPlugin.getInstance().getLanguageManager()), MessageType.ERROR);
                return langs;
            }
        }
        return langs;
    }

    private static void sendMessage(CommandSender sender, String message, MessageType type) {
        Messenger.sendMessage(MyPlugin.getInstance(), sender, message, type);
    }
}
```
