package theColorful.Cards;

import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import java.util.Iterator;

import static theColorful.characters.TC_character.Enums.TC_CARD;
public class DarknessPaeon extends CustomCard {
    public static final String ID = NameAssist.MakePath("DarknessPaeon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    //private static final String NAME = "打击";
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/DarknessPaeon.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public DarknessPaeon() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractPower> var5 = p.powers.iterator();
        while(var5.hasNext()){
            AbstractPower pow = var5.next();
            if(pow.type == AbstractPower.PowerType.BUFF){
                this.addToBot(new RemoveSpecificPowerAction(p, p, pow.ID));
            }else if(pow.type == AbstractPower.PowerType.DEBUFF && this.upgraded){
                this.addToBot(new RemoveSpecificPowerAction(p, p, pow.ID));
            }
        }
        //人工制品的码好像抄不了 - 阻止上Buff
        Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = var3.next();
            this.addToBot(new ApplyPowerAction(mo, p, new SlowPower(mo,1), 1, false, AbstractGameAction.AttackEffect.NONE));
        }

    }


    public AbstractCard makeCopy(){
        return new DarknessPaeon();
    }
}
