package YagoMod.cards;

import YagoMod.DefaultMod;
import YagoMod.actions.SelfReflectionAction;
import YagoMod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static YagoMod.DefaultMod.makeCardPath;

public class SelfReflection extends AbstractDynamicCard {

    /*
     * (1) Draw 1(2) card, add copy to your hand.
     */

    public static final String ID = DefaultMod.makeID(SelfReflection.class.getSimpleName());
    public static final String IMG = makeCardPath("SelfReflection.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DRAW_AMOUNT = 1;
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
        int deckSize = p.drawPile.size();
        int discardSize = p.discardPile.size();

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
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
