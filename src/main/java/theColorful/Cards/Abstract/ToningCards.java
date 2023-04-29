package theColorful.Cards.Abstract;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public abstract class ToningCards extends CustomCard {

    public static enum MainTone{
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        PURPLE,
        BLUE,
        BLACK,
        WHITE,
        GRAY,
        INDIGO,
        NONE;
        private MainTone(){

        }
    }

    public boolean isArma;
    public boolean sprout;
    public ToningCards(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }


}
