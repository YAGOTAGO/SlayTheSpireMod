package YagoMod.actions;

import YagoMod.cards.SelfReflection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SelfReflectionAction extends AbstractGameAction {

    AbstractPlayer player;

    public SelfReflectionAction(AbstractPlayer p){
        this.player = p;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> drawnCards = DrawCardAction.drawnCards;

        //WARNING
        //
        // MIGHT NEED A CHECK OF HANDSIZE HERE
        if(SelfReflection.WillDraw){
            //Get card that was drawn
            AbstractCard c = drawnCards.get(drawnCards.size()-1);

            //Add copy of it to the hand
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        }

        this.isDone = true;
    }
}
