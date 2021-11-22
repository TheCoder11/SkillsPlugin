package com.somemone.skills.skill;

import com.somemone.skills.Skills;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.persistence.PersistentDataType;

public class VoucherManager {

    public static NamespacedKey getVoucherID() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "voucher-id");
    }

    public static NamespacedKey getVoucherType() {
        return new NamespacedKey(Skills.getPlugin(Skills.class), "voucher-type");
    }

    public ItemStack toItemVoucher (MainSkillExecutor skillExecutor) {

        ItemStack voucher = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) voucher.getItemMeta();

        bookMeta.setTitle(skillExecutor.getTier().getColor() + skillExecutor.getName());
        bookMeta.setAuthor("Skill Man :)");
        bookMeta.addPage(skillExecutor.getDescription() + "\n\n Type: " + skillExecutor.getTier().getTitle());

        bookMeta.getPersistentDataContainer().set(getVoucherID(), PersistentDataType.INTEGER, skillExecutor.getID());
        bookMeta.getPersistentDataContainer().set(getVoucherType(), PersistentDataType.STRING, "main");

        voucher.setItemMeta(bookMeta);
        return voucher;

    }

    public ItemStack toItemVoucher (SpecialSkillExecutor skillExecutor) {

        ItemStack voucher = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) voucher.getItemMeta();

        bookMeta.setTitle(skillExecutor.getTier().getColor() + skillExecutor.getName());
        bookMeta.setAuthor("Skill Man :)");
        bookMeta.addPage(skillExecutor.getDescription() + "\n\n Type: " + skillExecutor.getTier().getTitle());

        bookMeta.getPersistentDataContainer().set(getVoucherID(), PersistentDataType.INTEGER, skillExecutor.getID());
        bookMeta.getPersistentDataContainer().set(getVoucherType(), PersistentDataType.STRING, "special");

        voucher.setItemMeta(bookMeta);
        return voucher;

    }

    public MainSkillExecutor fromMainVoucher (ItemStack voucher) {

        if (!voucher.getItemMeta().getPersistentDataContainer().has(getVoucherID(), PersistentDataType.INTEGER)) return null;

        int id = voucher.getItemMeta().getPersistentDataContainer().get(getVoucherID(), PersistentDataType.INTEGER);
        String type = voucher.getItemMeta().getPersistentDataContainer().get(getVoucherType(), PersistentDataType.STRING);

        if (type.equals("main")) {
            return Skills.getSkillRegistry().getMainSkillFromID(id);
        }
        return null;

    }

    public SpecialSkillExecutor fromSpecialVoucher (ItemStack voucher) {

        if (!voucher.getItemMeta().getPersistentDataContainer().has(getVoucherID(), PersistentDataType.INTEGER)) return null;

        int id = voucher.getItemMeta().getPersistentDataContainer().get(getVoucherID(), PersistentDataType.INTEGER);
        String type = voucher.getItemMeta().getPersistentDataContainer().get(getVoucherType(), PersistentDataType.STRING);

        if (type.equals("special")) {
            return Skills.getSkillRegistry().getSpecialSkillFromID(id);
        }
        return null;

    }

}
