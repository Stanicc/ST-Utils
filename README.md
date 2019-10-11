<div align="center">
  <b>ST-Utils</b><br><br>
</div>

Uma simples lib para ajudar durante o desenvolvimento para plugins do Minecraft

## Comandos

Você pode usar plugin.command para criar novos comandos

Uma pequena demonstração:

```kotlin
plugin.command(teste, "teste.permissionUse") { sender, args ->

    if (args.isEmpty()) {
        sender.sendMessage("Você não disse nada!")
    } else {
        sender.sendMessage("Você disse algo!")
    }

}
```

## Eventos

Você pode usar plugin.event para criar novos eventos

Uma pequena demonstração:

```kotlin
plugin.event<PlayerJoinEvent> { e ->

    val p = e.player

    if (p.name == "Stanic") {
        p.sendMessage("Hi Stanic!")
    } else {
        p.sendMessage("Hi noName!")
    }

}
```

## ItemBuilder e SkullBuilder

Você pode usar ItemBuilder para criar itens

Uma pequena demonstração:

```kotlin
val item = ItemBuilder(Material.BARRIER).setName("§aSou uma barreira!").setLore(listOf("", "Me coloque no chão!", "")).build()
```

Você pode criar skulls customizadas a partir de uma url

Uma pequena demonstração:

```kotlin
val item = ItemBuilder(SkullUtils().getSkull("http://textures.minecraft.net/texture/9dbdaa755099edd7efa1f12882c7a51b5815db52e0b164aef6df9a1f53eca23")).setName("Sou uma skull!").build()
```

## ChatObject

Você pode usar o ChatObject para criar um chat clicável

Uma pequena demonstração:

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

## Outras utilidades

* Envie um title ou um actionbar com mais facilidade

```kotlin
sendTitle(sender, "Title", "Subtitle")

sendActionBar(sender, "Mensagem")
```

* Você pode diminuir um pouco as coisas usando abreviações

Uma pequena demonstração:

```kotlin
CommandSender.send("Mensagem")
```

É usado para abreviar o sendMessage

```kotlin
CommandSender.command("op Stanic")
```

É usado para abreviar o dispachcommand
