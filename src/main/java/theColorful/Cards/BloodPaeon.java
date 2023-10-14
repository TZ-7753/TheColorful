package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
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

public class BloodPaeon extends CustomCard {
    public static final String ID = NameAssist.MakePath("BloodPaeon");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/BloodPaeon.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public BloodPaeon() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.misc = 4;
        this.magicNumber = this.baseMagicNumber = 1;
        this.damage = this.baseDamage = this.misc;
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.initializeDescription();
        }
    }

    public void applyPowers() {
        this.baseDamage = this.misc;
        super.applyPowers();
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(NameAssist.MakePath("ToneRed"))){
            this.addToBot(new VampireDamageAction(m,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.LIGHTNING));
            this.addToBot(new IncreaseMiscAction(this.uuid, this.misc, this.magicNumber));
        }else{
            this.addToBot(new DamageAction(m,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    public AbstractCard makeCopy(){
        return new BloodPaeon();
    }
}
