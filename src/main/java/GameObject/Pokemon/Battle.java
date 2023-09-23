package GameObject.Pokemon;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

import java.util.Random;

public class Battle {
    private Pokemon pokemon;//The pokemon selected to battle.
    private Pokemon enemy;
    private BattleItem battleItem;
    private PokeBall pokeBall;
    private Potion potion;
    private static final int DEFENSE = 0;
    private static final int ATTACK = 1;

    /**
     * Constructs a battle object.
     *
     * @param pokemon     The player's chosen Pokemon.
     * @param enemy       The enemy's Pokemon.
     * @param battleItem  The battle item to be used.
     * @param pokeBall    The PokeBall to be used.
     * @param potion      The healing potion to be used.
     */
    public Battle(Pokemon pokemon, Pokemon enemy, BattleItem battleItem, PokeBall pokeBall, Potion potion) {
        this.pokemon = pokemon;
        this.enemy = enemy;
        this.battleItem = battleItem;
        this.pokeBall = pokeBall;
        this.potion = potion;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getEnemy() {
        return enemy;
    }

    public void setEnemy(Pokemon enemy) {
        this.enemy = enemy;
    }

    public BattleItem getBattleItem() {
        return battleItem;
    }

    public void setBattleItem(BattleItem battleItem) {
        this.battleItem = battleItem;
    }

    public PokeBall getPokeBall() {
        return pokeBall;
    }

    public void setPokeBall(PokeBall pokeBall) {
        this.pokeBall = pokeBall;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }

    /**
     * Applies the effect of a battle item to a Pokemon.
     *
     * @param pokemon     The Pokemon to apply the item's effect to.
     * @param battleItem  The battle item to be used.
     */
    private void applyBattleItem(Pokemon pokemon, BattleItem battleItem){
        if(battleItem.getType() == BattleItemType.SPECIALATTACK){
            pokemon.setAttackValue(pokemon.getAttackValue() + battleItem.getBoostAmount());
        }else{
            pokemon.setDefenseValue(pokemon.getDefenseValue() + battleItem.getBoostAmount());
        }
    }

    /**
     * Applies the effect of a healing potion to a Pokemon.
     *
     * @param pokemon  The Pokemon to apply the potion's effect to.
     * @param potion   The healing potion to be used.
     */
    private void applyPotion(Pokemon pokemon, Potion potion){
        pokemon.setHealth(pokemon.getHealth() + potion.getHealAmount());
    }

    /**
     * Applies the actions between two Pokemon in battle.
     *
     * @param pokemon     The attacking Pokemon.
     * @param enemy       The defending Pokemon.
     * @param pokeChose   The choice of the attacking Pokemon (0: Defense, 1: Attack).
     * @param enemyChose  The choice of the defending Pokemon (0: Defense, 1: Attack).
     */
    private void applyAction(Pokemon pokemon, Pokemon enemy, int pokeChose, int enemyChose){
        if(pokeChose == ATTACK && enemyChose == ATTACK){
            pokemon.setHealth(pokemon.getHealth() - enemy.getAttackValue());
            enemy.setHealth(enemy.getHealth() - pokemon.getAttackValue());
        } else if (pokeChose == DEFENSE && enemyChose == ATTACK) {
            if(pokemon.getDefenseValue() < enemy.getAttackValue()){
                pokemon.setHealth(pokemon.getHealth() - (enemy.getAttackValue() - pokemon.getDefenseValue()));
            }
        } else if (pokeChose == ATTACK && enemyChose == DEFENSE) {
            if(enemy.getDefenseValue() < pokemon.getAttackValue()){
                enemy.setHealth(enemy.getHealth() - (pokemon.getAttackValue() - enemy.getDefenseValue()));
            }
        }
    }

    /**
     * Resets a Pokemon's attributes to their original values before the battle.
     *
     * @param pokemon             The Pokemon to reset attributes for.
     * @param originalHP          The original health points.
     * @param originalAttackValue The original attack value.
     * @param originalDefenceValue The original defense value.
     */
    private void applyRenew(Pokemon pokemon, int originalHP, int originalAttackValue, int originalDefenceValue){
        pokemon.setHealth(originalHP);
        pokemon.setAttackValue(originalAttackValue);
        pokemon.setDefenseValue(originalDefenceValue);
    }

    /**
     * Executes a battle and returns the result.
     *
     * @param battle  The battle object.
     * @return        The battle result, which can be Victory, Defeat, or Capture.
     */
    public ActionResult battleResult(Battle battle){
        if(pokeBall != null && pokeBall.getType() == PokeBallType.GREATBALL){
            return ActionResult.CAPTURE;//if greatball, 100% catched.
        }

        //used to renew the attributes after battle.
        int originalHP = pokemon.getHealth();
        int originalAttackValue = pokemon.getAttackValue();
        int originalDefenceValue = pokemon.getDefenseValue();

        if(battleItem != null){
            applyBattleItem(pokemon, battleItem);
        }
        if(battle.potion != null){
            applyPotion(pokemon, potion);
        }

        while(pokemon.getHealth() > 0 && enemy.getHealth() > 0){
            Random random = new Random();
            int pokeChose = random.nextInt(2);//0:defence, 1:attack
            int enemyChose = random.nextInt(2);
            applyAction(pokemon, enemy, pokeChose, enemyChose);
        }

        if(pokemon.getHealth() < enemy.getHealth()){
            applyRenew(pokemon, originalHP, originalAttackValue, originalDefenceValue);
            return ActionResult.DEFEAT;
        }else{
            if(pokeBall != null){
                applyRenew(pokemon, originalHP, originalAttackValue, originalDefenceValue);
                return ActionResult.CAPTURE;
            }else{
                applyRenew(pokemon, originalHP, originalAttackValue, originalDefenceValue);
                return ActionResult.VICTORY;
            }
        }
    }
}
