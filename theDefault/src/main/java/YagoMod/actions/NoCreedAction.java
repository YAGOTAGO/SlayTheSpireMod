package YagoMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class NoCreedAction extends AbstractGameAction {

    private AbstractPlayer player;

    private int amountCanDiscard;
    public static final String TEXT = "Discard.";
    public NoCreedAction(int i) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.player = AbstractDungeon.player;
        this.amountCanDiscard = i;
    }

    @Override
    public void update() {
        AbstractCard.CardType discardedCard;

        if (duration == Settings.ACTION_DUR_FAST) {
            if (player.hand.size() == 0) {
                isDone = true;
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT, amountCanDiscard, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.size() > 0) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                }

                discardedCard = AbstractDungeon.handCardSelectScreen.selectedCards.getTopCard().type;

                //Won't trigger on status or curse cards
                if (discardedCard != AbstractCard.CardType.STATUS && discardedCard != AbstractCard.CardType.CURSE) {
                    //Discover cards based on rarity
                    AbstractDungeon.actionManager.addToBottom(new DiscoveryAction(AbstractDungeon.handCardSelectScreen.selectedCards.getTopCard().type, 1));
                }

            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }
}
