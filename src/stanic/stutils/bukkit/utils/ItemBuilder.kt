package stanic.stutils.bukkit.utils

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class ItemBuilder {

    private var itemStack: ItemStack? = null

    constructor(itemStack: ItemStack) {
        this.itemStack = itemStack
    }

    @JvmOverloads
    constructor(m: Material, quantia: Int = 1) {
        itemStack = ItemStack(m, quantia)
    }

    constructor(m: Material, quantia: Int, durabilidade: Byte) {
        itemStack = ItemStack(m, quantia, durabilidade.toShort())
    }


    constructor(m: Material, quantia: Int, durabilidade: Int) {
        itemStack = ItemStack(m, quantia, durabilidade.toShort())
    }

    fun setDurability(durability: Short): ItemBuilder {
        itemStack!!.durability = durability
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        itemStack!!.amount = amount
        val im = itemStack!!.itemMeta
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS)
        itemStack!!.itemMeta = im
        return this
    }

    fun setDurability(durability: Int): ItemBuilder {
        itemStack!!.durability = java.lang.Short.valueOf("" + durability)
        return this
    }

    fun setName(name: String): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.displayName = name
        itemStack!!.itemMeta = im
        return this
    }

    fun addUnsafeEnchantment(ench: Enchantment, level: Int): ItemBuilder {
        itemStack!!.addUnsafeEnchantment(ench, level)
        return this
    }

    fun removeEnchantment(ench: Enchantment): ItemBuilder {
        itemStack!!.removeEnchantment(ench)
        return this
    }

    fun setSkullOwner(dono: String): ItemBuilder {
        try {
            val im = itemStack!!.itemMeta as SkullMeta
            im.owner = dono
            itemStack!!.itemMeta = im
        } catch (expected: ClassCastException) {
        }

        return this
    }

    fun addEnchant(ench: Enchantment, level: Int): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.addEnchant(ench, level, true)
        itemStack!!.itemMeta = im
        return this
    }

    fun addEnchantments(enchantments: Map<Enchantment, Int>): ItemBuilder {
        itemStack!!.addEnchantments(enchantments)
        return this
    }

    fun addItemFlag(flag: ItemFlag): ItemBuilder {
        val im = itemStack!!.itemMeta
        im.addItemFlags(flag)
        itemStack!!.itemMeta = im
        return this
    }

    fun setLore(line: List<String>): ItemBuilder {
        val im = itemStack!!.itemMeta
        var lore: MutableList<String> = ArrayList()
        if (im.hasLore()) lore = ArrayList(im.lore)
        for (s in line) {
            lore.add(s)
        }
        im.lore = lore
        itemStack!!.itemMeta = im
        return this
    }

    fun build(): ItemStack? {
        return itemStack
    }


}