package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.DesperatePrayerAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static YagoMod.DefaultMod.makeCardPath;

public class DesperatePrayer extends AbstractDynamicCard {

    /*
     * (1) Draw a card, heal for its cost (+1).
     */

    public static final String ID = DefaultMod.makeID(DesperatePrayer.class.getSimpleName());
    public static final String IMG = makeCardPath("DesperatePrayer.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DRAW_AMOUNT = 1;
    public static boolean WillDraw = false;

    public DesperatePrayer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        //before we draw we do a check
        int deckSize = AbstractDungeon.player.drawPile.size();
        int discardSize = AbstractDungeon.player.discardPile.size();

        //true if deck and discard isn't 0
        WillDraw = deckSize + discardSize != 0;

        //Draw action
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(DRAW_AMOUNT, new DesperatePrayerAction(p, this.upgraded), false));
    }

    public AbstractCard makeCopy() {return new DesperatePrayer();}

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
