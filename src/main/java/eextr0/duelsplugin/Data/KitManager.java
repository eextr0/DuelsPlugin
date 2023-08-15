package eextr0.duelsplugin.Data;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KitManager {

    private final Map<String, ItemStack[]> kits = new HashMap<>();
    private final Map<Player, ItemStack[]> playerInventoryStates = new HashMap<>();

    public void registerKit(String kitName, ItemStack[] items) {
        kits.put(kitName, items);
    }

    public void equipKit(Player player, String kitName) {
        ItemStack[] kitItems = kits.get(kitName);
        if (kitItems != null) {
            saveInventoryState(player);
            clearInventory(player);

            for (int i = 0; i <kitItems.length; i++) {
                ItemStack item = kitItems[i];
                if (item != null) {
                    if (isArmor(item)) {
                        equipArmor(player, item);
                    } else {
                        player.getInventory().setItem(i, item.clone());
                    }
                }
            }
        }
    }

    public void restoreInventory(Player player) {
        ItemStack[] savedInventory = playerInventoryStates.get(player);
        if (savedInventory != null) {
            clearInventory(player);
            restoreArmor(player);
            player.getInventory().setContents(savedInventory);
            player.updateInventory();
            playerInventoryStates.remove(player);
        }
    }

    private void restoreArmor(Player player) {
        ItemStack[] savedInventory = playerInventoryStates.get(player);
        if (savedInventory != null) {

            for (int i = 0; i < 4; i++) {
                ItemStack armorItem = savedInventory[i];
                if (isArmor(armorItem)) {
                    equipArmor(player, armorItem);
                    savedInventory[i] = null;
                }
            }

            player.updateInventory();
        }
        if (Arrays.stream(savedInventory).allMatch(item -> item == null)) {
            playerInventoryStates.remove(player);
        }
    }

    private void saveInventoryState(Player player) {
        ItemStack[] inventoryContents = player.getInventory().getContents().clone();
        ItemStack[] armorContents = {
                player.getInventory().getHelmet(),
                player.getInventory().getChestplate(),
                player.getInventory().getLeggings(),
                player.getInventory().getBoots()
        };

        playerInventoryStates.put(player, combineArrays(inventoryContents, armorContents));
    }
    private void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    private boolean isArmor(ItemStack item) {
        Material itemType = item.getType();
        return itemType == Material.LEATHER_HELMET ||
                itemType == Material.LEATHER_CHESTPLATE ||
                itemType == Material.LEATHER_LEGGINGS ||
                itemType == Material.LEATHER_BOOTS ||
                itemType == Material.IRON_HELMET ||
                itemType == Material.IRON_CHESTPLATE ||
                itemType == Material.IRON_LEGGINGS ||
                itemType == Material.IRON_BOOTS ||
                itemType == Material.GOLDEN_HELMET ||
                itemType == Material.GOLDEN_CHESTPLATE ||
                itemType == Material.GOLDEN_LEGGINGS ||
                itemType == Material.GOLDEN_BOOTS ||
                itemType == Material.DIAMOND_HELMET ||
                itemType == Material.DIAMOND_CHESTPLATE ||
                itemType == Material.DIAMOND_LEGGINGS ||
                itemType == Material.DIAMOND_BOOTS ||
                itemType == Material.NETHERITE_HELMET ||
                itemType == Material.NETHERITE_CHESTPLATE ||
                itemType == Material.NETHERITE_LEGGINGS ||
                itemType == Material.NETHERITE_BOOTS;
    }

    private void equipArmor(Player player, ItemStack armorItem) {
        Material armorType = armorItem.getType();
        if (armorType == Material.LEATHER_HELMET ||
                armorType == Material.IRON_HELMET ||
                armorType == Material.GOLDEN_HELMET ||
                armorType == Material.DIAMOND_HELMET ||
                armorType == Material.NETHERITE_HELMET) {
            player.getInventory().setHelmet(armorItem);
        } else if (armorType == Material.LEATHER_CHESTPLATE ||
                armorType == Material.IRON_CHESTPLATE ||
                armorType == Material.GOLDEN_CHESTPLATE ||
                armorType == Material.DIAMOND_CHESTPLATE ||
                armorType == Material.NETHERITE_CHESTPLATE) {
            player.getInventory().setChestplate(armorItem);
        } else if (armorType == Material.LEATHER_LEGGINGS ||
                armorType == Material.IRON_LEGGINGS ||
                armorType == Material.GOLDEN_LEGGINGS ||
                armorType == Material.DIAMOND_LEGGINGS ||
                armorType == Material.NETHERITE_LEGGINGS) {
            player.getInventory().setLeggings(armorItem);
        } else if (armorType == Material.LEATHER_BOOTS ||
                armorType == Material.IRON_BOOTS ||
                armorType == Material.GOLDEN_BOOTS ||
                armorType == Material.DIAMOND_BOOTS ||
                armorType == Material.NETHERITE_BOOTS) {
            player.getInventory().setBoots(armorItem);
        }
    }

    private ItemStack[] combineArrays(ItemStack[] array1, ItemStack[] array2) {
        ItemStack[] combinedArray = new ItemStack[array1.length + array2.length];
        System.arraycopy(array1, 0, combinedArray, 0, array1.length);
        System.arraycopy(array2, 0, combinedArray, array1.length, array2.length);
        return combinedArray;
    }
}
