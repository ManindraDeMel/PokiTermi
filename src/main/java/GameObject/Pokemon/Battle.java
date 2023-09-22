package GameObject.Pokemon;

import GameObject.Item.BattleItem.BattleItem;
import GameObject.Item.BattleItem.BattleItemType;
import GameObject.Item.PokeBall.PokeBall;
import GameObject.Item.PokeBall.PokeBallType;
import GameObject.Item.Potion.Potion;
import com.googlecode.lanterna.terminal.swing.TerminalScrollController;

import java.util.Random;

public class Battle {
    public Pokemon pokemon;//The pokemon selected to battle.
    public Pokemon enemy;
    public BattleItem battleItem;
    public PokeBall pokeBall;
    public Potion potion;

    public Battle(Pokemon pokemon, Pokemon enemy, BattleItem battleItem, PokeBall pokeBall, Potion potion) {
        this.pokemon = pokemon;
        this.enemy = enemy;
        this.battleItem = battleItem;
        this.pokeBall = pokeBall;
        this.potion = potion;
    }

    public ActionResult battleResult(Battle battle){
        if(pokeBall != null && pokeBall.type == PokeBallType.GREATBALL){
            return ActionResult.CAPTURE;//if greatball, 100% catched.
        }
        if(battleItem != null){
            if(battleItem.type == BattleItemType.SPECIALATTACK){
                pokemon.attackValue += battleItem.boostAmount;
            }else{
                pokemon.defenseValue += battleItem.boostAmount;
            }
        }
        if(battle.potion != null){
            pokemon.health += potion.healAmount;
        }
        int originalHP = pokemon.health;//used to renew the HP after battle.
        while(pokemon.health > 0 && enemy.health > 0){
            Random random = new Random();
            int pokeChose = random.nextInt(2);//0:defence, 1:attack
            int enemyChose = random.nextInt(2);
            if(pokeChose == 1 && enemyChose == 1){
                pokemon.health -= enemy.attackValue;
                enemy.health -= pokemon.attackValue;
            } else if (pokeChose == 0 && enemyChose == 1) {
                if(pokemon.defenseValue < enemy.attackValue){
                    pokemon.health -= (enemy.attackValue - pokemon.defenseValue);
                }
            } else if (pokeChose == 1 && enemyChose == 0) {
                if(enemy.defenseValue < pokemon.attackValue){
                    enemy.health -= (pokemon.attackValue - enemy.defenseValue);
                }
            }
        }
        if(pokemon.health < enemy.health){
            pokemon.health = originalHP;
            return ActionResult.DEFEAT;
        }else{
            if(pokeBall != null){
                pokemon.health = originalHP;
                return ActionResult.CAPTURE;
            }else{
                pokemon.health = originalHP;
                return ActionResult.VICTORY;
            }
        }
    }
}
