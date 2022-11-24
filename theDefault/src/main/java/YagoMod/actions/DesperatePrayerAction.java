package YagoMod.actions;

import YagoMod.cards.DesperatePrayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DesperatePrayerAction extends AbstractGameAction {

    private AbstractCreature player;
    private boolean isUpgraded;

    public DesperatePrayerAction(AbstractCreature p, boolean isUpgraded){
        this.player = p;
        this.isUpgraded = isUpgraded;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> drawnCards = DrawCardAction.drawnCards;

        if(DesperatePrayer.WillDraw){
            int cost = drawnCards.get(drawnCards.size()-1).cost;

            //in the case of unplayable cards
            if(cost < 0){
                cost = 0;
            }

            if(isUpgraded){
                AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, cost+1));
            }else{
                AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, cost));
            }

        }

        this.isDone = true;

    }
}
