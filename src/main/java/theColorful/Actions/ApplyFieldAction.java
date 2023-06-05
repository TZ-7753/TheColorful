package theColorful.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theColorful.Cards.Abstract.ToningCards;
import theColorful.Helpers.NameAssist;
import theColorful.Powers.*;

import java.util.Objects;

public class ApplyFieldAction extends AbstractGameAction {
    public AbstractPlayer owner;
    public AbstractPower field;
    public ApplyFieldAction(AbstractPlayer p,AbstractPower field) {
        this.owner = p;
        this.field = field;
    }

    @Override
    public void update() {

        //首先移除其他所有场地
        if(Objects.equals(field.ID, NameAssist.MakePath("LiveVolcano_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("Desert_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("HueCalibrator_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("MilkyWay_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("Tundra_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("RosyClouds_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Weald_pow")));
        }else if(Objects.equals(field.ID, NameAssist.MakePath("Weald_pow"))){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("LiveVolcano_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Desert_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("HueCalibrator_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("MilkyWay_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("Tundra_pow")));
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NameAssist.MakePath("RosyClouds_pow")));
        }

        //再添加传递的场地
        this.addToBot(new ApplyPowerAction(this.owner,this.owner,field));
        this.isDone = true;
    }


}
