package YagoMod.actions;

import YagoMod.cards.SacrificialStab;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SacrificialStabAction extends AbstractGameAction {
    private int EnergyGain;
    private DamageInfo info;

    public SacrificialStabAction(AbstractCreature target, DamageInfo info, int EnergyGain){
        this.info = info;
        this.setValues(target, info);
        this.EnergyGain = EnergyGain;
        this.duration = 0.1F;
    }

    @Override
    public void update() {

        if(this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(EnergyGain));
            }
        }
        this.tickDuration();
    }
}
