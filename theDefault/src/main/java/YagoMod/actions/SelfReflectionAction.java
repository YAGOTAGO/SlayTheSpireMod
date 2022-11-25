package YagoMod.actions;

import YagoMod.cards.SelfReflection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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

        int handSize = player.hand.size();

        // If can draw and have space in hand
        if(SelfReflection.WillDraw & handSize<10){

            //Get card that was drawn
            AbstractCard c = drawnCards.get(drawnCards.size()-1);

            //Add copy of it to the hand
            //ADD to top because same as Dual wield
            AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        }

        this.isDone = true;
    }
}
