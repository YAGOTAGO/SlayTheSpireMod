package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.DesperatePrayerAction;
import YagoMod.actions.SelfReflectionAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class SelfReflection extends AbstractDynamicCard {

    /*
     * (1) Draw 1(2) card, add copy to your hand.
     */

    public static final String ID = DefaultMod.makeID(SelfReflection.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DRAW_AMOUNT = 2;
    private static final int DRAW_PLUS = 1;
    public static boolean WillDraw = false;

    public SelfReflection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = DRAW_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
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
        for(int i =0; i<magicNumber; i++){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1, new SelfReflectionAction(p), false));
        }

    }

    public AbstractCard makeCopy() {return new SelfReflection();}

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(DRAW_PLUS);
            this.rawDescription = "Draw 2 cards. NL Add copies of them to your hand.";
            initializeDescription();
        }
    }
}
