package YagoMod.actions;

import YagoMod.DefaultMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ThreeTenetsAction extends AbstractGameAction {

    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());
    private boolean upgraded;

    public ThreeTenetsAction(boolean upgraded) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.upgraded =upgraded;

    }


    @Override
    public void update() {

        //Must have a card played beforehand to work, this card doesn't count
        if(AbstractDungeon.actionManager.cardsPlayedThisCombat.size()>1){
            AbstractCard.CardType cardType= AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2).type;
            logger.info("Last played card type is: "+cardType);
            logger.info("Last played card: "+AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 2).name);
            if(cardType == AbstractCard.CardType.ATTACK){
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.RED, upgraded));
            } else if (cardType == AbstractCard.CardType.SKILL) {
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.GREEN, upgraded));
            }else if (cardType == AbstractCard.CardType.POWER) {
                AbstractDungeon.actionManager.addToBottom(new DiscoverAction(AbstractCard.CardColor.BLUE, upgraded));
            }
        }
        this.isDone = true;
    }
}
