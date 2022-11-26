package YagoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class ThreeTenetsAction extends AbstractGameAction {


    public ThreeTenetsAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;

    }


    @Override
    public void update() {

        //Must have a card played beforehand to work, this card doesn't count
        if(AbstractDungeon.actionManager.cardsPlayedThisCombat.size()>1){
            AbstractCard.CardType cardType= AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).type;

            if(cardType == AbstractCard.CardType.ATTACK){
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.RED));
            } else if (cardType == AbstractCard.CardType.SKILL) {
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.GREEN));
            }else if (cardType == AbstractCard.CardType.POWER) {
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.BLUE));
            }
        }
        this.isDone = true;
    }
}
