package theColorful.Cards.Arma.Blue;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

public class TigerShark extends ToningCards {

    public static final String ID = NameAssist.MakePath("TigerShark");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/TigerShark.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = CardColor.COLORLESS;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final MainTone TONE = MainTone.BLUE;

    public TigerShark() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 6;
        this.selfRetain = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(4);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

//    public void applyPowers() {
//        this.damage = this.baseDamage + AbstractDungeon.player.hand.size() * this.magicNumber;
//        super.applyPowers();
//        if(this.damage > 100){
//            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0];
//        }
//        this.initializeDescription();
//    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage = this.baseDamage + (AbstractDungeon.player.hand.size() * this.magicNumber);
        if(this.damage < 100){
            int add = AbstractDungeon.cardRandomRng.random((100 - this.damage));
            this.damage += add;
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if(this.damage > 50){
            this.addToBot(new TalkAction(true, CARD_STRINGS.EXTENDED_DESCRIPTION[1], 1.0F, 2.0F));
        }
        this.addToBot(new ToningAction(p,TONE));
    }


    public AbstractCard makeCopy(){
        return new TigerShark();
    }

}
