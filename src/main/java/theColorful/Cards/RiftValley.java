package theColorful.Cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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

public class RiftValley extends CustomCard {
    public static final String ID = NameAssist.MakePath("RiftValley");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/RiftValley.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static CardTarget TARGET = CardTarget.ENEMY;


    public RiftValley() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 10;
        this.cardsToPreview = new ReTone();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            AbstractCard c = new ReTone();
            c.upgrade();
            this.cardsToPreview = c;
        }
    }

    @Override
    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(NameAssist.MakePath("ToneOrange")) && !p.hasPower(NameAssist.MakePath("ToneGreen")) && !p.hasPower(NameAssist.MakePath("ToneYellow"))){
            TARGET = CardTarget.ALL_ENEMY;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new ReTone();
        if(this.upgraded){
            c.upgrade();
        }
        if(!p.hasPower(NameAssist.MakePath("ToneOrange")) && !p.hasPower(NameAssist.MakePath("ToneGreen")) && !p.hasPower(NameAssist.MakePath("ToneYellow"))){
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            this.addToBot(new MakeTempCardInHandAction(c,1));
        }else{
            this.addToBot(new DamageAllEnemiesAction(p,this.damage,DamageInfo.DamageType.NORMAL,AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    public AbstractCard makeCopy(){
        return new RiftValley();
    }
}
