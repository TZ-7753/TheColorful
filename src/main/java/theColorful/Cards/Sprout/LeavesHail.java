package theColorful.Cards.Sprout;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theColorful.Actions.ToningAction;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;

import static theColorful.characters.TC_character.Enums.SPROUT;
import static theColorful.characters.TC_character.Enums.TC_CARD;

public class LeavesHail extends ToningCards {
    public static final String ID = NameAssist.MakePath("LeavesHail");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "TC_resources/img/cards/LeavesHail.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = TC_CARD;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final MainTone TONE = MainTone.GREEN;


    public LeavesHail(int times) {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(SPROUT);
        this.timesUpgraded = times;
    }
    public LeavesHail() {
        this(0);
    }


    @Override
    public void upgrade() {
        this.upgradeMagicNumber(1);
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        if(p.hasPower(NameAssist.MakePath("ToneGreen"))){
            this.addToBot(new UpgradeSpecificCardAction(this));
            p.bottledCardUpgradeCheck(this);
        }
    }

    public boolean canUpgrade() {
        return true;
    }

    public AbstractCard makeCopy() {
        return new LeavesHail(this.timesUpgraded);
    }

}
