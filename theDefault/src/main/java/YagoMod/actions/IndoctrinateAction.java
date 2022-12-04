package YagoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class IndoctrinateAction extends AbstractGameAction {
    private DamageInfo info;
    private AttackEffect attackEffect;

    public IndoctrinateAction(AbstractCreature target, DamageInfo info, AttackEffect attackEffect, AbstractCreature source){
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
        this.attackEffect = attackEffect;
        this.source = source;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, attackEffect, false));
                this.target.damage(this.info);
                if (this.target.lastDamageTaken > 0) {
                    //Gain block
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(source, source, new NextTurnBlockPower(source, this.target.lastDamageTaken), this.target.lastDamageTaken));
                    //if (this.target.hb != null) {
                        //this.addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
                    //}
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }
}
