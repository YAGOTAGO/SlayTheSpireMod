package YagoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class DevotionAction extends AbstractGameAction {

    private AbstractPlayer player;
    private int amountCanDiscard;
    public static final String TEXT = "Discard.";
    public DevotionAction(int i) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.player = AbstractDungeon.player;
        this.amountCanDiscard = i;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (player.hand.size() == 0) {
                isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT, amountCanDiscard, true, true);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.size() > 0) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    player.hand.moveToDiscardPile(c);
                }
                //heal for amount of cards dicarded
                AbstractDungeon.actionManager.addToBottom(new HealAction(player, player, AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
