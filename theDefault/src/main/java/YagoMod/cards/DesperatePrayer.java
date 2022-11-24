package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static YagoMod.DefaultMod.makeCardPath;

public class DesperatePrayer extends AbstractDynamicCard {

    /*
     * (1) Draw a card, heal for its cost (+1).
     */

    public static final String ID = DefaultMod.makeID(DesperatePrayer.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final Logger logger = LogManager.getLogger(DrawCardAction.class.getName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DRAW_AMOUNT = 1;

    public DesperatePrayer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        int originalDrawnSize = DrawCardAction.drawnCards.size();

        for(int i =0; i<originalDrawnSize; i++){
            logger.info(DrawCardAction.drawnCards.get(i).name);
        }

        //Draw action
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(DRAW_AMOUNT));

        for(int i =0; i<originalDrawnSize; i++){
            logger.info(DrawCardAction.drawnCards.get(i).name);
        }

        //if not same size then draw happened
        if(originalDrawnSize != DrawCardAction.drawnCards.size()){
            //Heal for the cost of top card in drawn list
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, DrawCardAction.drawnCards.get(DrawCardAction.drawnCards.size()-1).cost));
            logger.info("Card Drawn is: " + DrawCardAction.drawnCards.get(DrawCardAction.drawnCards.size()-1).name);
        }


    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = "Draw a card. NL Heal for its cost + 1.";
            initializeDescription();
        }
    }
}
