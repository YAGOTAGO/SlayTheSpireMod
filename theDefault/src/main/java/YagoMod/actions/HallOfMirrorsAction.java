package YagoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HallOfMirrorsAction extends AbstractGameAction {

    private AbstractPlayer p;
    public HallOfMirrorsAction(AbstractPlayer p){
        this.p =p;
    }

    @Override
    public void update() {
        AbstractCard c;

        //total hand size
        int totalSlotsInHand = 10;

        //amount of cards in hand
        int handSize = p.hand.size();

        for (AbstractCard abstractCard : p.hand.group) {

            //Keep adding cards to hand while there is space
            if (handSize < totalSlotsInHand) {
                c = abstractCard;

                //Add to top because same as dual wield
                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
                handSize++;
            }

        }

        this.isDone = true;

    }
}
