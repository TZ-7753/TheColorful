package theColorful.Cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.Painted;

import static theColorful.characters.TC_character.Enums.TC_CARD;

public class StarLight extends ToningCards {
    public static final String ID = NameAssist.MakePath("StarLight");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/StarLight.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final MainTone TONE = MainTone.PURPLE;

    public StarLight() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new ReTone();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(3);
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            AbstractCard c = new ReTone();
            c.upgrade();
            this.cardsToPreview = c;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m,p,new Painted(m)));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        AbstractCard c = new ReTone();
        if(this.upgraded){
            c.upgrade();
        }
        if(p.hasPower(NameAssist.MakePath("TonePurple")) || p.hasPower(NameAssist.MakePath("ToneBlue")) || p.hasPower(NameAssist.MakePath("ToneRed"))){
            this.addToBot(new MakeTempCardInHandAction(c,1));
        }else{
            this.addToBot(new ApplyPowerAction(m,p,new WeakPower(m,this.magicNumber,false),this.magicNumber));
            this.addToBot(new ToningAction(p,TONE));
        }
    }


    public AbstractCard makeCopy(){
        return new StarLight();
    }
}
