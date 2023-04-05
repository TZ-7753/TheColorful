package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.LoseNRGNextTurn;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class UnusedColor extends CustomCard {
    public static final String ID = NameAssist.MakePath("UnusedColor");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/UnusedColor.png";
    private static final int COST = 0;
    //private static final String DESCRIPTION = "造成 !D! 点伤害。";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


    public UnusedColor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 7;
        this.isEthereal = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.NORMAL);
        this.addToBot(new DamageRandomEnemyAction(info, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        p.drawPile.moveToExhaustPile(p.drawPile.getRandomCard(AbstractDungeon.cardRandomRng));
        p.discardPile.moveToExhaustPile(p.discardPile.getRandomCard(AbstractDungeon.cardRandomRng));
        this.addToBot(new MakeTempCardInDiscardAction(this,1));
        this.addToBot(new MakeTempCardInDrawPileAction(this,1,true,true,false));
    }

    public AbstractCard makeCopy(){
        return new UnusedColor();
    }
}
