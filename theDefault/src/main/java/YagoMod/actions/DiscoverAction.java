package YagoMod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;


import java.util.ArrayList;


import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;


public class DiscoverAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private ArrayList<AbstractCard> cardOptions;
    private boolean Costs0turn;
    private boolean Costs1LessTurn;
    public DiscoverAction(AbstractCard.CardColor cardColor, boolean Cost0thisturn, boolean costs1LessTurn) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = 1;

        //whether to make the card cost 0 this turn
        this.Costs0turn = Cost0thisturn;
        this.Costs1LessTurn = costs1LessTurn;
        //Add all cards from that color to card pool
        ArrayList<AbstractCard> cardPool = new ArrayList();
        CardLibrary.addCardsIntoPool(cardPool, cardColor);

        //Select 3 random ones from the pool
        cardOptions = GenerateRandomCards(cardPool);
    }

    @Override
    public void update() {

        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(cardOptions, CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (AbstractDungeon.player.hasPower("MasterRealityPower")) {
                        disCard.upgrade();
                    }

                    //if upgraded sets cost to 0 this turn
                    if(Costs0turn){
                        disCard.setCostForTurn(0);
                    } else if (Costs1LessTurn) {
                        if(disCard.cost>0){
                            disCard.setCostForTurn(disCard.cost-1);
                        }
                    }

                    disCard.current_x = -1000.0F * Settings.xScale;

                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }

    }


    //Returns a list with 3 cards picked randomly from input list
    private ArrayList<AbstractCard> GenerateRandomCards(ArrayList<AbstractCard> totalCards){
        ArrayList<AbstractCard> result = new ArrayList();
        boolean dupe = false;

        //We need 3 cards in list
        while(result.size() !=3){

            //Get a random card from the list
            AbstractCard temp = ReturnRandomCard(totalCards);

            //Make sure it is not already in result
            for (AbstractCard card : result) {
                if (card.cardID.equals(temp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            //If not in result add it to result
            if(!dupe){
                result.add(temp.makeCopy());
            }

        }

        return result;

    }

    //Returns random non-healing card
    private AbstractCard ReturnRandomCard(ArrayList<AbstractCard> totalCards){
        AbstractCard ca;

        do{
            ca = totalCards.get(cardRandomRng.random(totalCards.size()-1));
        }while(ca.hasTag(AbstractCard.CardTags.HEALING));

        return ca;
    }
}
