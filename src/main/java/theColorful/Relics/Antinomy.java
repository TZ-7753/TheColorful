package theColorful.Relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.NoAtk_pow;
import theColorful.Powers.NoPow_pow;
import theColorful.Powers.NoSkill_pow;

import static com.badlogic.gdx.math.MathUtils.random;

public class Antinomy extends CustomRelic{

    public static final String ID = NameAssist.MakePath("Antinomy");
    private static final Texture IMG = new Texture("TC_resources/img/relics/antinomy.png");
    private static final Texture OUTLINE = new Texture("TC_resources/img/relics/outline/antinomy.png");
    private static final RelicTier RELIC_TIER = RelicTier.BOSS;
    private static final LandingSound LANDING_SOUND = LandingSound.CLINK;

    public Antinomy() {
        super(ID, IMG, OUTLINE, RELIC_TIER, LANDING_SOUND);
        this.energyBased = true;
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Antinomy();
    }

    public void atBattleStart() {
        int n = random(1,3);
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player,this));
        switch (n){
            case 1:
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoAtk_pow(AbstractDungeon.player)));
                break;
            case 2:
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoSkill_pow(AbstractDungeon.player)));
                break;
            case 3:
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new NoPow_pow(AbstractDungeon.player)));
                break;
        }
    }

}
