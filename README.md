<div align="center">
  <b>ST-Utils</b><br><br>
</div>

A simple lib to help when developing for Minecraft plugins

## Commands

Create commands easily

```kotlin
plugin.command(test, "test.permission") { sender, args ->

    if (args.isEmpty()) {
        sender.sendMessage("Say something")
    } else {
        sender.sendMessage("Yeah ${args[0]}")
    }

}
```

## Events

Create events easily

```kotlin
plugin.event<PlayerJoinEvent> { e ->

    val p = e.player

    if (p.name == "Stanic") {
        p.sendMessage("Hi Stanic!")
    } else {
        p.sendMessage("Hi!")
    }

}
```

## ItemBuilder & SkullBuilder

Use ItemBuilder to create item easily

```kotlin
val item = ItemBuilder(Material.BARRIER).setName("§aI'm barrier").setLore(listOf("", "Invisible to players", "")).build()
```

Create skulls by an url with SkullUtils

```kotlin
val item = ItemBuilder(SkullUtils().getSkull("http://textures.minecraft.net/texture/9dbdaa755099edd7efa1f12882c7a51b5815db52e0b164aef6df9a1f53eca23")).setName("Sou uma skull!").build()
```

## ChatObject

Create a clickable chat easily

```kotlin
val list = ArrayList<ChatObject>()
list.add(
    ChatObject(
        "§aEnvie um aaaa no chat",
        HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder("Clique e digite /say aaaa").create()),
        ClickEvent(ClickEvent.Action.RUN_COMMAND, "/say aaaa")
    )
)
sendChatObject(sender, list)
```

## Utilities

* Send titles and actionbars easily

```kotlin
player.sendTitle("Title", "Subtitle")

player.sendActionBar("Message")
```

* Decrease a bit like things

```kotlin
CommandSender.send("Message")
```

```kotlin
CommandSender.command("op Stanic")
```

* Replace and replaceColor

Change the color in chat easily using replaceColor:<br>
```kotlin
string.replaceColor()
```
<br>

```kotlin
list.replaceColor()
```

Change values in lists easily using replace:<br>
```kotlin
list.replace(""value" to ""replacement")
```

